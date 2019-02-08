import java.awt.*;

class ComputerPaddle {
    private int x, y;
    private final int WIDTH, HEIGHT;

    ComputerPaddle(int widthOfFrame, int heightOfFrame) {
        this.y = heightOfFrame/2;
        HEIGHT = heightOfFrame/6;
        WIDTH = widthOfFrame/25;
        this.x = widthOfFrame - (widthOfFrame/12);
    }

    private void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x,y, WIDTH, HEIGHT);
    }

    void move(int yOfBall, Graphics g, Color c) {
        g.setColor(c);
        g.fillRect(x,y,WIDTH,HEIGHT);
        g.setColor(Color.black);
        y = yOfBall-(HEIGHT/2);
        draw(g);
    }

    int getLeftEdge() {
        return x;
    }
}