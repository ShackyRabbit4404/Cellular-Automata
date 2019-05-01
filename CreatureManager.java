import java.util.*;
public class CreatureManager{
    ArrayList<ArrayList<Creature>> creatures = new ArrayList<ArrayList<Creature>>();
    public CreatureManager(ArrayList<ArrayList<Creature>> c){
        creatures = c;
    }
    public void update(){
        for(int x = 0; x < creatures.size(); x++){
            for(int y = 0; y < creatures.get(x).size(); y++){
                if(creatures.get(x).get(y) != null){
                    int xc = (int)(Math.random()*3)-1;
                    int yc = (int)(Math.random()*3)-1;
                    if(!spotTaken(x+xc,y+yc)){
                        creatures.get(x+xc).set(y+yc,creatures.get(x).get(y));
                        creatures.get(x).set(y,null);
                    }
                }
            }
        }
    }
    public boolean spotTaken(int cx, int cy){
        if(cx < 0 || cy < 0 || cx >= creatures.size() || cy >= creatures.get(0).size()){
            return true;
        }
        for(int x = 0; x < creatures.size(); x++){
            for(int y = 0; y < creatures.get(x).size(); y++){
                if(creatures.get(x).get(y) != null && x == cx && y == cy){
                    return true;
                }
            }
        }
        return false;
    }
}