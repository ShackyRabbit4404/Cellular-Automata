import java.awt.*;
public class Creature{
    private int age;
    private String species;
    private double strength;
    private double health;
    private double resistance;
    private int[] color;
    public Creature(String s, double st,double r,int[] c){
        species = s;
        strength = st;
        resistance = r;
        age = 0;
        health = 100;
        color = c;
    }
    public String getSpecies(){
        return species;
    }
    public double getHealth(){
        return health;
    }
    public double getStrength(){
        return strength;
    }
    public Color getColor(){
        return new Color(color[0],color[1],color[2]);
    }
    public Creature clone(){
        return new Creature(species,strength,resistance,color);
    }
}