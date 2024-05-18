import javax.swing.*;
import java.awt.event.KeyListener;

public class Main
    {
        public static void main(String[] args)
        {
            JFrame breakerWindow = new JFrame();
            GamePlay gameplay = new GamePlay();

            breakerWindow.setBounds(10, 10, 700, 600);
            breakerWindow.setTitle("Brick Breaker");

            breakerWindow.setResizable(false);
            breakerWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            breakerWindow.add(gameplay);
            breakerWindow.setVisible(true);
        }
}
