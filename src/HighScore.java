import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class HighScore implements Serializable{

    private int points;
    private ArrayList<Integer> highScores = new ArrayList<>(11);
    private ArrayList<String> highScoresInit = new ArrayList<>(11);
    private JFrame frame = new JFrame();
    private JTextArea displayForHighScores = new JTextArea();

    public HighScore(){
        for (int x = 0; x < 10; x++){
            highScores.add(0);
            highScoresInit.add("---");
        }
    }

    public void endGame() throws IOException{
        checkHighScore();
        serializeHighScores();
        displayHighScores();
    }

    private void displayHighScores() {
        displayForHighScores.append("HIGH SCORES\n");
        for (int i = 0; i < 10; i++)
           displayForHighScores.append(highScoresInit.get(i) + ":" + highScores.get(i) + "\n");
        frame.setSize(250,300);
        frame.add(displayForHighScores);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    public void setHighScoreText(String s){
        displayForHighScores.setText(s);
    }

    private void serializeHighScores() throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("hs.obj"));
        oos.writeObject(this);
        oos.close();
    }

    public int getPoints(){return points;}
    public void incrementPoints(){points++;}

    private void checkHighScore() {
        for (int x = 0; x <= 9; x++){
            if (points > highScores.get(x))
            {
                highScores.add(x,points);
                points=0;
                highScores.remove(10);
                do {
                    highScoresInit.add(x, JOptionPane.showInputDialog("What is your 3 letter initials?"));
                } while (highScoresInit.get(x).length() > 3);

                highScoresInit.remove(10);
            }
        }
    }
}