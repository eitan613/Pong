import java.awt.*;

public class HumanPaddle implements Paddle {
    private int y, x;
    private final int WIDTH, HEIGHT, HEIGHT_OF_PANEL;

    HumanPaddle(int widthOfFrame, int heightOfFrame) {
        y = heightOfFrame/2;
        x = widthOfFrame/25;
        HEIGHT = heightOfFrame/6;
        WIDTH = widthOfFrame/25;
        this.HEIGHT_OF_PANEL = heightOfFrame;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x,y, WIDTH,HEIGHT);
    }

    @Override
    public void move(int yOfMouse, Graphics g, Color c) {
        g.setColor(c);
        g.fillRect(x,y,WIDTH,HEIGHT);
        y = yOfMouse;
        if(y <  0)
            y = 0;
        else if((y+HEIGHT) > HEIGHT_OF_PANEL)
            y = HEIGHT_OF_PANEL -HEIGHT;
        draw(g);
    }

    @Override
    public int getY() {
        return y;
    }

    int getRightEdge() {
        return x+WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
}