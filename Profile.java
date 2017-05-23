import java.util.*;
import java.awt.*;
import javax.swing.*;        
/**
 * The node class of P3, called Profile because each node contains information about a certain
 * Pompeian aristocrat.
 * 
 * @author (Lepidopterane) 
 * @version (5/14/17)
 */
public class Profile
{
    Point pt;
    Color hue;
    int width; // width represents social score, see readme
    String bio; 

    /**
     * No-args constructor for objects of class Profile
     */
    public Profile()
    {
        pt = new Point(10,10);
        hue = Color.WHITE;
        width = 0;
        bio = "";
        
    }
    
    /**
     * Constructor for objects of class Profile
     * @param p The Point of this specific node.
     * @param s The Color of this specific node.
     * @param b The String displayed for this node in Info Mode.
     */
    public Profile(Point p, Color s, int w, String b)
    {
        pt = p;
        hue = s;
        width = w;
        bio = b;
    }
    
    public Point getPoint(){return pt;}
    public void setName(Point p){pt=p;}
    
    public Color getHue(){return hue;}
    public void setHue(Color h){hue = h;}
    
    public int getWidth(){return width;}
    public void setWidth(int w){width = w;}    
    
    public String getBio(){return bio;}
    public void setBio(String b){bio=b;}
    
}
