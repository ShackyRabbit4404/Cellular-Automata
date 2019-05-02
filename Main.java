import javax.swing.*;
import java.awt.*;
import java.util.*;
public class Main{
    public static void main(String[] args){
        JFrame frame = new JFrame("Screen");
        ArrayList<ArrayList<Creature>> board = new ArrayList<ArrayList<Creature>>();
        ArrayList<Creature> creatureList = new ArrayList<Creature>();
        int numSpeciesType = 10;
        int[] creatureCount = new int[numSpeciesType];
        double maxStartStrength = 5;
        double maxStartResistance = .2;
        double hightestOldAgeDeathChance = 0.03;
        double maxSpawnRate = 0.05;
        double chanceOfMutation = 0.01;
        for(int a = 0; a < numSpeciesType; a++){
            creatureList.add(new Creature("Species "+(a+1),Math.random()*maxStartStrength,Math.random()*maxStartResistance,new int[]{(int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1},Math.random()*maxSpawnRate,Math.random()*hightestOldAgeDeathChance));
        }
        
        int WIDTH = 50;
        int HEIGHT = 50;
        int SCALE = 10;
        double creatureChance = 0.3;
        
        System.out.println("started creating board");
        for(int x = 0; x < WIDTH; x++){
            board.add(new ArrayList<Creature>());
            for(int y = 0; y < HEIGHT; y++){
                if(Math.random() < creatureChance){
                    int creatureNum = (int)(creatureList.size()*Math.random());
                    board.get(x).add(creatureList.get(creatureNum).clone());
                    creatureCount[creatureNum] ++;
                }
                else{
                    board.get(x).add(null);
                }
            }
        }
        System.out.println("finished creating board: "+board.size()+","+board.get(0).size());
        
        CreatureManager creatureManager = new CreatureManager(board,creatureList,creatureCount,chanceOfMutation);
        Display screen = new Display(board,WIDTH,HEIGHT,SCALE,creatureList,creatureCount);
        frame.add(screen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH*SCALE+150,HEIGHT*SCALE+50);
        frame.setVisible(true);
        while(true){
            screen.draw();
            try{
                Thread.sleep(100);
            }
            catch(Exception e){
                System.out.println(e);
            }
            creatureManager.update();
        }        
    }   
}