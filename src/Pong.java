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
        Pong pong = new Pong();
        pong.pong();
    }

    private void pong(){
        setPanel();
        setFrame();
        humanPaddle = new HumanPaddle(panel.getWidth(),panel.getHeight());
        ball = new Ball();
        computerPaddle = new ComputerPaddle(panel.getWidth(), panel.getHeight());
        ball.ball(frame.getHeight());
        frame.setVisible(true);
        playAgain.addActionListener(e -> {
            ball.setXCenter();
            ball.setYCenter();
            status.setText("You have " + highScore.getPoints() + " points");
            highScore.setHighScoreText(null);
            ballTimer.start();
        });
        ballTimer = new Timer(100, e -> {
                playAgain.setEnabled(false);
                ball.move(panel.getGraphics(), panel.getBackground());
                computerPaddle.move(ball.getY(), panel.getGraphics(), panel.getBackground());
                if (ball.getX() == 50 && (ball.getY() - 10 >= humanPaddle.getY() && ball.getY() + 10 <= humanPaddle.getY() + 80)) {
                    ball.changeDirection();
                    highScore.incrementPoints();
                    status.setText("You have " + highScore.getPoints() + " points");
                }
                if (ball.getX() == frame.getWidth() - 50)
                    ball.changeDirection();
                if (ball.getX() < -10) {
                    try {
                        ballTimer.stop();
                        playAgain.setEnabled(true);
                        highScore.endGame();
                        /**
                         * TODO have hs textArea added to this frame instead of new, with no button-joption pane instead
                         *  improve ball to speed up & change more than just xVelocity
                         *  fix up code-better practices
                         */
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            ballTimer.start();
    }

    private void setPanel() {
        panel.setSize(500,500);
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
      //  frame.add(BorderLayout.SOUTH,playAgain);
        setHighScore();
        status = new JLabel("You have " + highScore.getPoints() + " points");
        //frame.add(BorderLayout.NORTH, status);
        frame.setSize(panel.getWidth()+16,panel.getHeight()+37);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}