import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener
    {
        private boolean play = false;
        private int score = 0;

        private int totalBricks = 21;

        private int delay = 6;
        private Timer timer;

        private int playerX = 310;
        private int ballPositionX = 120;
        private int ballPositionY = 350;

        private int ballDirectionX = -1;
        private int ballDirectionY = -2;

        private MapGen map;

        public GamePlay()
        {
            map = new MapGen(3, 7);
            addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            timer = new Timer(delay, this);
            timer.start();
        }

        public void paint(Graphics g)
        {
            //Background
            g.setColor(Color.BLACK);
            g.fillRect(1,1,692, 592);

            //Bricks
            map.drawBricks((Graphics2D) g);

            //Borders
            g.setColor(Color.YELLOW);
            g.fillRect(0, 0, 3, 592);
            g.fillRect(0, 0, 692, 3);
            g.fillRect(691, 0, 3, 592);

            //Scores
            g.setColor(Color.WHITE);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("" + score, 590, 30);

            //Paddle
            g.setColor(Color.GREEN);
            g.fillRect(playerX, 550, 100, 8);

            //Ball
            g.setColor(Color.ORANGE);
            g.fillOval(ballPositionX, ballPositionY, 20, 20);

            //Game Over

            if (totalBricks <= 0)
            {
                play = false;
                ballDirectionX = 0;
                ballDirectionY = 0;

                g.setColor(Color.RED);
                g.setFont(new Font("serif", Font.BOLD, 30));
                g.drawString("YOU COMPLETED THE LEVEL! Score: " + score, 70, 300);

                g.setFont(new Font("serif", Font.BOLD, 20));
                g.drawString("Press ENTER to continue", 230, 350);
            }

            if (ballPositionY > 570)
            {
                play = false;
                ballDirectionX = 0;
                ballDirectionY = 0;

                g.setColor(Color.RED);
                g.setFont(new Font("serif", Font.BOLD, 30));
                g.drawString("GAME OVER, Score: " + score, 190, 300);

                g.setFont(new Font("serif", Font.BOLD, 20));
                g.drawString("Press ENTER to try again", 230, 350);
            }

            g.dispose();
        }


        @Override
        public void actionPerformed(ActionEvent e)
        {
            timer.start();
            if (play)
            {
                //Collision detection
                if (new Rectangle(ballPositionX, ballPositionY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8)))
                {
                    ballDirectionY = -ballDirectionY;
                }

                A: for (int i = 0;i < map.map.length;i++)
                {
                    for (int j = 0;j < map.map[0].length;j++)
                    {
                        if (map.map[i][j] > 0)
                        {
                            int brickX = j * map.brickWidth + 80;
                            int brickY = i * map.brickHeight + 50;
                            int brickWidth = map.brickWidth;
                            int brickHeight = map.brickHeight;

                            Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                            Rectangle ballRect = new Rectangle(ballPositionX, ballPositionY, 20, 20);
                            Rectangle brickRect = rect;

                            if (ballRect.intersects(brickRect))
                            {
                                map.setBrickValue(0, i, j);
                                totalBricks--;
                                score += 5;

                                if (ballPositionX + 19 <= brickRect.x || ballPositionX + 1 >= brickRect.x + brickRect.width)
                                {
                                    ballDirectionX = -ballDirectionX;
                                } else
                                {
                                    ballDirectionY = -ballDirectionY;
                                }
                                break A;
                            }
                        }
                    }
                }

                ballPositionX += ballDirectionX;
                ballPositionY += ballDirectionY;
                if (ballPositionX < 0)
                {
                    ballDirectionX = -ballDirectionX;
                }
                if (ballPositionY < 0)
                {
                    ballDirectionY = -ballDirectionY;
                }
                if (ballPositionX > 670)
                {
                    ballDirectionX = -ballDirectionX;
                }
            }
            repaint();
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            {
                if (playerX >= 600)
                {
                    playerX = 600;
                } else moveRight();
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT)
            {
                if (playerX < 10)
                {
                    playerX = 10;
                } else moveLeft();
            }

            //Restart
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                if (!play)
                {
                    play = true;
                    ballPositionX = 120;
                    ballPositionY = 350;
                    ballDirectionX = -1;
                    ballDirectionY = -2;
                    playerX = 310;
                    score = 0;
                    totalBricks = 21;
                    map = new MapGen(3, 7);
                    repaint();
                }
            }
        }

        public void moveLeft()
        {
            play = true;
            playerX -= 20;
        }

        public void moveRight()
        {
            play = true;
            playerX += 20;
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
