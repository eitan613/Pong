import java.awt.*;

public class ComputerPaddle implements Paddle {
    private int x, y;
    public ComputerPaddle(int width, int height) {
        y = height/2;
        x = width-40;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x,y, 20, 80);
    }

    @Override
    public void move(int n, Graphics g,Color c) {
        g.setColor(c);
        g.fillRect(x,y,20,80);
        g.setColor(Color.black);
        y = n-10;
        draw(g);
    }

    @Override
    public int getY() {
        return y;
    }
}