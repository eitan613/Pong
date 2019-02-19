import java.awt.*;

class HumanPaddle {
    private int y, x;
    private final int WIDTH, HEIGHT, HEIGHT_OF_PANEL;

    HumanPaddle(int widthOfFrame, int heightOfFrame) {
        y = heightOfFrame/2;
        x = widthOfFrame/25;
        HEIGHT = heightOfFrame/6;
        WIDTH = widthOfFrame/25;
        this.HEIGHT_OF_PANEL = heightOfFrame;
    }

    private void draw(Graphics g, Color color) {
        g.setColor(color);
        g.fillRect(x,y, WIDTH,HEIGHT);
    }

    void move(int yOfMouse, Graphics g, Color c) {
        draw(g,c);
        y = yOfMouse;
        if(y <  0)
            y = 0;
        else if((y+HEIGHT) > HEIGHT_OF_PANEL)
            y = HEIGHT_OF_PANEL -HEIGHT;
        draw(g, Color.black);
    }


    int getY() {
        return y;
    }

    int getRightEdge() {
        return x+WIDTH;
    }

    int getHeight() {
        return HEIGHT;
    }
}