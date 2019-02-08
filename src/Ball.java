import java.awt.*;
import java.util.Random;

class Ball {
    private int diameter, xMovement, yMovement, radius;
    private final int PANEL_CENTER, PANEL_HEIGHT, HUMAN_PADDLE_RIGHT_EDGE,HUMAN_PADDLE_HEIGHT,COMPUTER_PADDLE_LEFT_EDGE;
    private Point ballCenter;
    private Random random = new Random();

    Ball(int panelHeight, int humanPaddleHeight, int humanPaddleRightEdge, int computerPaddleLeftEdge){
        PANEL_CENTER = panelHeight/2;
        PANEL_HEIGHT = panelHeight;
        xMovement =-15;
        yMovement = 0;
        diameter = panelHeight / 25;
        radius = diameter /2;
        ballCenter = new Point(PANEL_CENTER, PANEL_CENTER);
        HUMAN_PADDLE_HEIGHT = humanPaddleHeight;
        HUMAN_PADDLE_RIGHT_EDGE = humanPaddleRightEdge;
        COMPUTER_PADDLE_LEFT_EDGE = computerPaddleLeftEdge;
        setCenter();
    }

    private void draw(Graphics g, Color c){
        g.setColor(c);
        g.fillOval((int) ballCenter.getX()-radius, (int) ballCenter.getY()-radius, diameter, diameter);
    }

    boolean move(Graphics g, Color c, int humanPaddleY){
        draw(g,c);
        ballCenter.setLocation(ballCenter.getX()+xMovement,ballCenter.getY()+yMovement);
        if((int) ballCenter.getY() <= 0)
            yMovement = -yMovement;
        if((int)ballCenter.getY() >= PANEL_HEIGHT)
            yMovement = -yMovement;
        boolean hitHumanPaddle = hitsHumanPaddle(humanPaddleY);
        if (hitHumanPaddle || hitComputerPaddle())
            changeDirection();
        draw(g,Color.BLACK);
        return hitHumanPaddle;
    }
    private void changeDirection(){
        xMovement = -xMovement;
        int randomX = random.nextInt(4);
        if (xMovement > 0)
            xMovement += randomX;
        else
            xMovement -= randomX;
        int randomY = random.nextInt(4);
        if (yMovement > 0)
            yMovement += randomY;
        else
            yMovement -= randomY;
        if (random.nextInt(3) == 1)
            yMovement = -yMovement;
    }

    int getCenter_Y(){
        return (int)ballCenter.getY();
    }
    void setCenter(){ ballCenter.setLocation(PANEL_CENTER,PANEL_CENTER); }

    private boolean hitComputerPaddle() {
        return (ballCenter.getX()+radius >= COMPUTER_PADDLE_LEFT_EDGE && ((ballCenter.getX()+radius)-xMovement) <= COMPUTER_PADDLE_LEFT_EDGE);
    }

    boolean pastHumanPaddle(int humanPaddleY) {
        return (xCoordinateOfHumanPaddle() &&
                (ballCenter.getY()-radius < humanPaddleY || ballCenter.getY()+radius > humanPaddleY+HUMAN_PADDLE_HEIGHT));
    }

    private boolean hitsHumanPaddle(int humanPaddleY) {
        return (xCoordinateOfHumanPaddle() &&
                (ballCenter.getY()-radius >= humanPaddleY && ballCenter.getY()+radius <= humanPaddleY+HUMAN_PADDLE_HEIGHT));
    }
    private boolean xCoordinateOfHumanPaddle(){
        return ballCenter.getX()-radius <= HUMAN_PADDLE_RIGHT_EDGE && ((ballCenter.getX()-radius)-xMovement) >= HUMAN_PADDLE_RIGHT_EDGE;
    }
}