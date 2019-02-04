import java.awt.*;

public class HumanPaddle implements Paddle {//stop hp from flickering
    private int y, x;

    public HumanPaddle() {
        y = 220;
        x = 20;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x,y, 20, 80);
    }

    @Override
    public void move(int n, Graphics g, Color c) {
        g.setColor(c);
        g.fillRect(x,y,20,80);
        g.setColor(Color.black);
        if(n < 0){
            y -= n;
        }
        else{
            y -= n;
        }

        if(y <  75)
            y = 75;
        else if(y > 420)
            y = 420;
        g.fillRect(x,y,20,80);
    }

    @Override
    public int getY() {
        return y;
    }
}