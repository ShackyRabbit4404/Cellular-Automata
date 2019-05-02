import java.util.*;
public class CreatureManager{
    private ArrayList<ArrayList<Creature>> creatures = new ArrayList<ArrayList<Creature>>();
    private ArrayList<Creature> creatureTypes;
    private int[] numOfEachType;
    private double mutationChance;
    private double maxVariation = 0.2;
    public CreatureManager(ArrayList<ArrayList<Creature>> c,ArrayList<Creature> ct, int[] noet,double mc){
        creatures = c;
        creatureTypes = ct;
        numOfEachType = noet;
        mutationChance = mc;
    }
    public void update(){
        for(int x = 0; x < creatures.size(); x++){
            for(int y = 0; y < creatures.get(x).size(); y++){
                if(creatures.get(x).get(y) != null){
                    if(Math.random() < creatures.get(x).get(y).getSpawnChance()){
                        /*
                        if(mutationChance < Math.random())
                            spawn(mutate(creatures.get(x).get(y)),x,y);
                        else
                        */
                            spawn(creatures.get(x).get(y),x,y);
                    }
                    if(Math.random() < creatures.get(x).get(y).getAgeChance()){
                        numOfEachType[getIndexOfSpeciesType(creatures.get(x).get(y).getSpecies())] --;
                        creatures.get(x).set(y,null);
                    }
                    else{
                        int xc = (int)(Math.random()*3)-1;
                        int yc = (int)(Math.random()*3)-1;
                        if(!(x+xc < 0 || y+yc < 0 || x+xc >= creatures.size() || y+yc >= creatures.get(0).size())){
                            //checks for open spot
                            if(creatures.get(x+xc).get(y+yc) == null){
                                creatures.get(x+xc).set(y+yc,creatures.get(x).get(y));
                                creatures.get(x).set(y,null);
                            }
                            // checks for different species
                            else if(!creatures.get(x+xc).get(y+yc).getSpecies().equals(creatures.get(x).get(y).getSpecies())){
                                if(creatures.get(x+xc).get(y+yc).takeDamage(creatures.get(x).get(y).getStrength())){
                                    creatures.get(x).get(y).setMaxHealth();
                                    numOfEachType[getIndexOfSpeciesType(creatures.get(x+xc).get(y+yc).getSpecies())] --;
                                    creatures.get(x+xc).set(y+yc,null);
                                }
                            }
                            //the only other option is to hit the same species
                            
                        }
                    }
                }
            }
        }
    }
    public void spawn(Creature c, int x, int y){
        for(int xc = -1; xc < 2; xc++){
            for(int yc = -1; yc < 2; yc++){
                if(!(x+xc < 0 || y+yc < 0 || x+xc >= creatures.size() || y+yc >= creatures.get(0).size()) && !(xc == 0 && yc == 0) && creatures.get(x+xc).get(y+yc) == null){
                    creatures.get(x+xc).set(y+yc,c.clone());
                    numOfEachType[getIndexOfSpeciesType(creatures.get(x).get(y).getSpecies())] ++;
                    break;
                }
            }
        }
    }
    public Creature mutate(Creature c){
        Creature ret = new Creature("Species: "+(creatureTypes.size()+1),((Math.random()-maxVariation)+1)*c.getStrength(),((Math.random()-maxVariation)+1)*c.getResistance(),new int[]{(int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1},((Math.random()-maxVariation)+1)*c.getSpawnChance(),((Math.random()-maxVariation)+1)*c.getAgeChance());
        /*
        creatureTypes.add(ret);
        int[] numOfEachTypeTemp = new int[creatureTypes.size()+1];
        for(int a = 0; a < numOfEachType.length; a++){
            numOfEachTypeTemp[a] = numOfEachType[a];
        }
        numOfEachTypeTemp[numOfEachTypeTemp.length-1] = 1;
        numOfEachType = numOfEachTypeTemp;
        */
        return ret;
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