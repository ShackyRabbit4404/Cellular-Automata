import java.util.*;
public class CreatureManager{
    private ArrayList<ArrayList<Creature>> creatures = new ArrayList<ArrayList<Creature>>();
    private ArrayList<Creature> creatureTypes;
    private int[] numOfEachType;
    public CreatureManager(ArrayList<ArrayList<Creature>> c,ArrayList<Creature> ct, int[] noet){
        creatures = c;
        creatureTypes = ct;
        numOfEachType = noet;
    }
    public void update(){
        for(int x = 0; x < creatures.size(); x++){
            for(int y = 0; y < creatures.get(x).size(); y++){
                if(creatures.get(x).get(y) != null){
                    int xc = (int)(Math.random()*2)-1;
                    int yc = (int)(Math.random()*2)-1;
                    if(!(x+xc < 0 || y+yc < 0 || x+xc >= creatures.size() || y+yc >= creatures.get(0).size()) && creatures.get(x+xc).get(y+yc) == null){
                        creatures.get(x+xc).set(y+yc,creatures.get(x).get(y));
                        creatures.get(x).set(y,null);
                    }
                    // check for same species
                    else if(!(x+xc < 0 || y+yc < 0 || x+xc >= creatures.size() || y+yc >= creatures.get(0).size())){
                        if(creatures.get(x+xc).get(y+yc).takeDamage(creatures.get(x).get(y).getStrength())){
                            creatures.get(x).get(y).setMaxHealth();
                            numOfEachType[getIndexOfSpeciesType(creatures.get(x+xc).get(y+yc).getSpecies())] --;
                            creatures.get(x+xc).set(y+yc,null);
                        }
                    }
                }
            }
        }
    }
    public int getIndexOfSpeciesType(String species){
        for(int i = 0; i < creatureTypes.size(); i++){
            if(creatureTypes.get(i).getSpecies().equals(species)){
                return i;
            }
        }
        return 0;
    }
}