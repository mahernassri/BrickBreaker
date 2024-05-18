import java.awt.*;

public class MapGen
{
    public int map[][];
    public int brickWidth;
    public int brickHeight;

    public MapGen(int row, int column)
    {
        map = new int[row][column];
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[0].length; j++)
            {
                map[i][j] = 1;
            }
        }

        brickWidth = 540/column;
        brickHeight = 150/row;
    }

    public void drawBricks(Graphics2D g)
    {
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[0].length; j++)
            {
                if (map[i][j] > 0)
                {
                    g.setColor(Color.WHITE);
                    g.fillRect(j * brickWidth + 80,i * brickHeight + 50,brickWidth, brickHeight);

                    //Borders for Bricks
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j * brickWidth + 80,i * brickHeight + 50,brickWidth, brickHeight);
                }
            }
        }
    }
    //Collision Detection
    public void setBrickValue(int value, int row, int column)
    {
        map[row][column] = value;

    }

}
