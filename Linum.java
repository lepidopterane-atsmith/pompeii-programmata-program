import java.util.*;
import java.awt.*;
import javax.swing.*;
/**
 * The edge class for my Pompeii final, indicating relationships between person nodes. 
 * 
 * Dev Note: I originally called it Thread but when I saw that was taken, I settled for its Latin
 * counterpart.
 * 
 * @author (Sarah Abowitz) 
 * @version (5.1.17)
 */
public class Linum
{
    // instance variables - replace the example below with your own
    private Point head, tail;
    private Color hue;

    /**
     * Constructor for objects of class Linum.
     * @param h The head node of this edge.
     * @param t The tail node of this edge.
     * @param c The color of said edge.
     */
    public Linum(Point h, Point t, Color c)
    {
        head = h;
        tail = t; 
        hue = c;
        
    }


    public Color getColor(){return hue;}
    public Point getHead(){return head;}
    public Point getTail(){return tail;}
}