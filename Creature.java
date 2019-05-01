import java.awt.*;
public class Creature{
    private int age;
    private String species;
    private double strength;
    private double health;
    private double resistance;
    private int[] color;
    private double spawnChance;
    public Creature(String s, double st,double r,int[] c,double sc){
        species = s;
        strength = st;
        resistance = r;
        age = 0;
        health = 10;
        color = c;
        spawnChance = sc;
    }
    public String getSpecies(){
        return species;
    }
    public double getHealth(){
        return health;
    }
    public void setMaxHealth(){
        health = 10;
    }
    public double getSpawnChance(){
        return spawnChance;
    }
    public boolean takeDamage(double d){
        health -= d*(1-resistance);
        if(health <= 0)
            return true;
        return false;
    }
    public double getStrength(){
        return strength;
    }
    public Color getColor(){
        return new Color(color[0],color[1],color[2]);
    }
    public Creature clone(){
        return new Creature(species,strength,resistance,color,spawnChance);
    }
}