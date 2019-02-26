import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class Pong{
    private Ball ball;
    private ComputerPaddle computerPaddle;
    private HumanPaddle humanPaddle;
    private Timer ballTimer;
    private JButton playAgain = new JButton("Click here to play again.");
    private JFrame frame = new JFrame("Pong");
    private JPanel panel = new JPanel();
    private JLabel status;
    private HighScore highScore;

    public static void main(String[] args) {
        new Pong();
    }

    private Pong(){
        setPanel();
        setFrame();
        computerPaddle = new ComputerPaddle(panel.getWidth(), panel.getHeight());
        humanPaddle = new HumanPaddle(panel.getWidth(),panel.getHeight());
        ball = new Ball(panel.getHeight(),humanPaddle.getHeight(),humanPaddle.getRightEdge(),computerPaddle.getLeftEdge());
        frame.setVisible(true);
        playAgain.addActionListener(e -> {
            frame.dispose();
            new Pong();
        });
        ballTimer = new Timer(100, e -> {
            playAgain.setEnabled(false);
            boolean hitHumanPaddle = ball.move(panel.getGraphics(), panel.getBackground(),humanPaddle.getY());
            computerPaddle.move(ball.getCenter_Y(), panel.getGraphics(), panel.getBackground());
            if (hitHumanPaddle) {
                highScore.incrementPoints();
                status.setText("You have " + highScore.getPoints() + " points");
            }
            else if (ball.pastHumanPaddle(humanPaddle.getY())) {
                ballTimer.stop();
                playAgain.setEnabled(true);
                highScore.endGame();
            }
        });
        ballTimer.start();
    }

    private void setPanel() {
        panel.setSize(500,500);
        panel.setFocusable(true);
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                    humanPaddle.move(humanPaddle.getY()+30,panel.getGraphics(),panel.getBackground());
                else if (e.getKeyCode() == KeyEvent.VK_UP)
                    humanPaddle.move(humanPaddle.getY()-30,panel.getGraphics(),panel.getBackground());
            }
        });
        panel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {}
            @Override
            public void mouseMoved(MouseEvent e) {
                humanPaddle.move(e.getY(), panel.getGraphics(), panel.getBackground());
            }
        });
    }

    private void setHighScore() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("hs.obj"));
            Object obj = ois.readObject();
            ois.close();
            highScore = (HighScore) obj;
        } catch (IOException io) {
            highScore = new HighScore();
        } catch (ClassNotFoundException ignored) {}
    }

    private void setFrame() {
        frame.add(BorderLayout.CENTER,panel);
        frame.add(BorderLayout.SOUTH,playAgain);
        setHighScore();
        status = new JLabel("You have " + highScore.getPoints() + " points");
        frame.add(BorderLayout.NORTH, status);
        frame.setSize(panel.getWidth()+16,panel.getHeight()+80);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}