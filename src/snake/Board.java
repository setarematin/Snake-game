package snake;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.*;

import exceptions.SnakeException;
import  sun.audio.*;

public class Board extends JPanel implements ActionListener {
    private static int timeCount = 1;
    private Timer timer = new Timer(GameState.getInstance().speed, this);;
    private JButton restart;
    private boolean gameOverPanel = false;

    public Board() {

        initBoard();
    }

    private void initBoard() {
        //handel key pressed moves
        addKeyListener(new TAdapter());
        setBackground(Color.white);
        setFocusable(true);
        backgroundMusic();
        setPreferredSize(new Dimension(GameOptions.B_WIDTH, GameOptions.B_HEIGHT));
        initGame();
    }

    private void backgroundMusic()
    {
        try {
            System.out.println("hiiiiiiiiiiiiiiii");
            InputStream in = new FileInputStream("src/resources/afterhill.wav");
            AudioStream as = new AudioStream(in);
            AudioPlayer.player.start(as);

        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private void initGame() {
        timer.setDelay(GameState.getInstance().speed);
        GameState.getInstance().initGameState();
        System.out.println("timers is starting");
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("time: " + Integer.toString(timeCount++));

        if (GameState.getInstance().isInGame()) {

            if(GameState.getInstance().snake.isCollied())
                GameState.getInstance().setInGame(false);
            else {
                if(GameState.getInstance().checkFoodEaten())
                    timer.setDelay(timer.getDelay()-10);
                try {
                    GameState.getInstance().snake.move(GameState.getInstance().getCurrentDirection());
                }
                catch (SnakeException e1)
                {
                    System.out.println(e1.getMessage());
                }
            }
        }

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        System.out.println("Do Drawing");

        if (GameState.getInstance().isInGame()) {
            GameState.getInstance().draw(g, this);
            //Toolkit.getDefaultToolkit().sync();

        } else if(!gameOverPanel){
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        System.out.println("Ovvvvvvvver");
        gameOverPanel = true;
        String msg = "Game Over";
//        Font small = new Font("Helvetica", Font.BOLD, 14);
//        FontMetrics metr = getFontMetrics(small);
        if(restart == null) {
            System.out.println("create restart button object");
            restart = new JButton();
            restart.setBounds(GameOptions.B_WIDTH/2, (GameOptions.B_HEIGHT/2), 100, 50);
            restart.setText("Restart");
            add(restart);
            restart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("restart clicked");
                    gameOverPanel = false;
                    GameState.getInstance().setInGame(true);
                    initGame();
                    restart.setVisible(false);
                }
            });
        }
        else{
            System.out.println("here");
            restart.setVisible(true);
        }
        g.setColor(Color.black);
//        g.setFont(small);
        g.drawString(msg, (GameOptions.B_WIDTH ) / 2, GameOptions.B_HEIGHT / 2);
//        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);

    }


    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (GameState.getInstance().getCurrentDirection() != GameOptions.Direction.RIGHT))
                GameState.getInstance().setCurrentDirection(GameOptions.Direction.LEFT);
            else if ((key == KeyEvent.VK_RIGHT) && (GameState.getInstance().getCurrentDirection() != GameOptions.Direction.LEFT))
                GameState.getInstance().setCurrentDirection(GameOptions.Direction.RIGHT);
            else if ((key == KeyEvent.VK_UP) && (GameState.getInstance().getCurrentDirection() != GameOptions.Direction.DOWN))
                GameState.getInstance().setCurrentDirection(GameOptions.Direction.UP);
            else if ((key == KeyEvent.VK_DOWN) && (GameState.getInstance().getCurrentDirection() != GameOptions.Direction.UP))
                GameState.getInstance().setCurrentDirection(GameOptions.Direction.DOWN);
//                         timer.setDelay(timer.getDelay()-100);
        }


    }
}
