import java.awt.*;

public interface Paddle {
    void draw(Graphics g);
    void move(int n, Graphics g, Color c);
    int getY();
}