import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class Pong extends JFrame implements MouseWheelListener {
    Ball b;
    ComputerPaddle Cp;
    HumanPaddle hp;
    private JButton playAgain = new JButton("Click here to play again.");
    private int notches;

    public static void main(String[] args) throws ClassNotFoundException {
        Pong pong = new Pong();
        pong.pong();
    }
    public void pong() throws ClassNotFoundException {
        this.setSize(500, 500);
        add(BorderLayout.SOUTH,playAgain);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        hp = new HumanPaddle();
        addMouseWheelListener(this);
        b = new Ball();
        Cp = new ComputerPaddle(getWidth(), getHeight());
        b.ball(getHeight());
        JPanel statusBar = new JPanel();
        highScore hs;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("hs.obj"));
            Object obj = ois.readObject();
            ois.close();
            hs = (highScore) obj;
        } catch (IOException io) {
            hs = new highScore();
        }
        JLabel status = new JLabel("You have " + hs.getPoints() + " points");
        statusBar.add(status);
        add(BorderLayout.NORTH, statusBar);
        this.setVisible(true);
        highScore Hs = hs;
        playAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.setXCenter();
                b.setYCenter();
                status.setText("You have " + Hs.getPoints() + " points");
                Hs.setHighScoreText(null);
                ballTimer.start();
            }
        });
            ballTimer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playAgain.setEnabled(false);
                    b.move(getGraphics(), getBackground());
                    Cp.move(b.getY(), getGraphics(), getBackground());
                    if (b.getX() == 50 && (b.getY() - 10 >= hp.getY() && b.getY() + 10 <= hp.getY() + 80)) {
                        b.changeDirection();
                        Hs.incrementPoints();
                        status.setText("You have " + Hs.getPoints() + " points");
                    }
                    if (b.getX() == getWidth() - 50)
                        b.changeDirection();
                    if (b.getX() < -10) {
                        try {
                            ballTimer.stop();
                            playAgain.setEnabled(true);
                            Hs.endGame();
                            //have hs textArea added to this frame instead of new, with no button-joption pane instead
                            //stop hp from flickering
                            //have hp move based on mouse not mouse wheel
                            //improve ball to speed up & change more than just xVelocity
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });
            ballTimer.start();
    }


    Timer ballTimer;
    public void paint(Graphics g) {
        super.paint(g);
        hp.draw(g);
        b.draw(g);
        Cp.draw(g);
    }


    

    Timer scrollTimer;
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        notches = e.getWheelRotation();
        scrollTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hp.move(notches, getGraphics(), getBackground());
            }
        });
        scrollTimer.start();
    }
}