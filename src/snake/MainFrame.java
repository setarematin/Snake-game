package snake;

import javax.swing.JFrame;

// This is our main Frame of game
public class MainFrame extends JFrame {

    public MainFrame() {
        //initialize main frame
        initUI();
    }

    private void initUI() {
        //add snake.Board panel to main frame
        add(new Board());
        //make the main frame not resizable
        setResizable(false);
        pack();

        setTitle("snake.Snake");
        //open main frame in center
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

//        EventQueue.invokeLater(() -> {
            JFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
//        });
    }
}