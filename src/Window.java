import javax.swing.JFrame;
import java.awt.Dimension;

    public class Window {

        final JFrame frame;

        public Window(final Dimension screenSize, final String title, final Game game) {
            frame = new JFrame(title);

            frame.setPreferredSize(screenSize);
            frame.setMaximumSize(screenSize);
            frame.setMinimumSize(screenSize);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.add(game);
            frame.setVisible(true);
    }
}
