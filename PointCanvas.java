import java.util.*;
import java.awt.*;
import javax.swing.*;        

/**
 *  Implements a graphical canvas that displays a graph made from Linum and Profile objects.
 *
 *  @author  Nicholas R. Howe (modded by Sarah Abowitz/Lepidopterane)
 *  @version CSC 112, 18 April 2006 (modded 4.20.17)
 */
class PointCanvas extends JComponent {
    private LinkedList<Linum> threads; 
    private LinkedList<Profile> profiles; 
    
    /** No-args constructor */
    public PointCanvas() { 
        threads = new LinkedList<Linum>();
        profiles = new LinkedList<Profile>();
        
    }

    /**
     *  Paints the graph.
     *
     *  @param g The graphics object to draw with
     */
    public void paintComponent(Graphics g) {
        for (Linum t: threads){
            g.setColor(t.getColor());
            int x1 = (int) t.getHead().getX(), y1 = (int) t.getHead().getY();
            int x2 = (int) t.getTail().getX(), y2 = (int) t.getTail().getY();
            g.drawLine(x1, y1, x2, y2);
        }
        
        int cnt = 0;
        for (Profile profile: profiles){
            Point p = profile.getPoint();
            g.setColor(profile.getHue());
            int x = (int) p.getX();
            int y = (int) p.getY();
            int xtra = profile.getWidth()*2;
            g.fillOval(x-5,y-5,10+xtra,10+xtra);
            //String postring = "X:"+x+" Y:"+y;
            cnt++;
            //g.setColor(Color.BLACK);
            //g.drawString(postring,x,y);
        }
    }
    
    /** 
     *  Securely adds a profile to profiles. @param p The Profile added.
     */
    public void addProfile(Profile p) {profiles.add(p);}
    
    /** 
     *  Securely adds a linum to threads. @param t The Linum added.
     */
    public void addLinum(Linum t) {threads.add(t);}
    
    /** 
     *  Securely clears all data in this element.
     */
    public void clear() {
        threads.clear();
        profiles.clear();
    }
    
    /** 
     *  Securely adds a profile to profiles. 
     *  @param i The index of the target profile.
     *  @return The target profile.
     */
    public Profile getProfile(int i){return profiles.get(i);}
    
    /**
     *  Accesses profiles.
     *  @return profiles The LinkedList of profiles.
     */
    public LinkedList<Profile> getProfileList() {return profiles;}
    
    /**
     * Checks if a given point is the point of any Profile in profiles.
     * @param p The given point.
     * @return The index of the given point in profiles. 
     *         An index of -1 indicates that the given point is not in profiles.
     */
    public int findPointIndex(Point p){
        LinkedList<Point> pts = new LinkedList<Point>();
        for (Profile pr: profiles){
            pts.add(pr.getPoint());
        }
        return pts.indexOf(p);
    }
    
    /**
     *  The component will look bad if it is sized smaller than this
     *
     *  @returns The minimum dimension
     */
    public Dimension getMinimumSize() {
        return new Dimension(500,3000);
    }

    /**
     *  The component will look best at this size
     *
     *  @returns The preferred dimension
     */
    public Dimension getPreferredSize() {
        return new Dimension(400,300);
    }
}
