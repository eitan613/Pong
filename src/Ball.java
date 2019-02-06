import java.awt.*;
import java.util.Random;

public class Ball {
    private int x, y, height, velx, vely;
    private final int CENTER = 250;
    private Random random = new Random();

    public void ball(int height){
        x = CENTER;
        y = CENTER;
        velx = -10;
        vely = 10;
        this.height = height;
    }

    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.fillOval(x-10, y-10, 20, 20);
    }

    public void move(Graphics g, Color c){
        g.setColor(c);
        g.fillOval(x-10, y-10, 20, 20);
        g.setColor(Color.black);

        x += velx;
        y += vely;

        if(y < 85){
            vely = -vely;
        }
        if(y > height){
            vely = -vely;
        }
        g.fillOval(x-10, y-10, 20, 20);
    }
    public void changeDirection(){
        velx = -velx;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
    public void setXCenter(){
        x = CENTER;
    }
    public void setYCenter(){
        y = CENTER;
    }
}