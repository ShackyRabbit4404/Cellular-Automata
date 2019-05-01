import javax.swing.*;
import java.awt.*;
import java.util.*;
public class Display extends JComponent{
    private ArrayList<ArrayList<Creature>> board;
    private int width;
    private int height;
    private int scale;
    private ArrayList<Creature> creatureTypes;
    private int[] numOfEachType;
    public Display(ArrayList<ArrayList<Creature>> b,int w,int h,int s,ArrayList<Creature> ct, int[] cc){
        board = b;
        width = w;
        height = h;
        scale = s;
        creatureTypes = ct;
        numOfEachType = cc;
    }
    public void draw(){
        super.repaint();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if(board.get(x).get(y) != null){
                    //System.out.println("creature found");
                    g.setColor(board.get(x).get(y).getColor());
                    g.fillRect(x*scale,y*scale,scale,scale);
                }
                else{
                    //System.out.println("no creature found");
                    g.setColor(Color.GRAY);
                    g.fillRect(x*scale,y*scale,scale,scale);
                }
            }
        }
        for(int a = 0; a < creatureTypes.size(); a++){
            g.setColor(creatureTypes.get(a).getColor());
            g.drawString(creatureTypes.get(a).getSpecies()+": "+numOfEachType[a],width*scale+10,30+a*15);
        }
    }
}