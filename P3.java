import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;        
/**
 * Main program of the P3 suite, allowing users to see my findings thus far. 
 * 
 * @author (Sarah Abowitz/Lepidopterane) 
 * @version (5.16.17)
 * 
 * Note: Code base borrowed from Nick Howe's PointGUI.
 */

public class P3
{
    /** The graph to be displayed */
    private PointCanvas canvas;

    /** Text at the top (instr) and bottom (key) of the screen */
    private JLabel instr, key;

    /** Modal enum */
    InputMode mode = InputMode.REL_MAP;

    /** Remembers point where last mousedown event occurred */
    Point pointUnderMouse;

    /**
     *  Schedules a job for the event-dispatching Linum
     *  creating and showing this application's GUI.
     */
    public static void main(String[] args) {
        final P3 GUI = new P3();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    GUI.createAndShowGUI();
                }
            });
    }

    /** Sets up the GUI window */
    public void createAndShowGUI() {
        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the window.
        JFrame frame = new JFrame("Lepidopterane Presents");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add components
        createComponents(frame);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    /** Puts content in the GUI window */
    public void createComponents(JFrame frame) {
        // graph display
        Container pane = frame.getContentPane();
        pane.setLayout(new FlowLayout());
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        canvas = new PointCanvas();
        PointMouseListener pml = new PointMouseListener();
        canvas.addMouseListener(pml);
        canvas.addMouseMotionListener(pml);
        panel1.add(canvas);
        instr = new JLabel("Pompeian Socio-Political Subgraphs");
        panel1.add(instr,BorderLayout.NORTH);
        
        key = new JLabel("Nodes: Y = Menenian, Blue = Oscan, C = Etruscan, G = Celtic, R = Roman, W = Unknown, Black = Divine. Edges: G = Support, B = Cooperation, R = Aggression");
        // ^^this line is long because the string in it is
        panel1.add(key,BorderLayout.SOUTH);
        pane.add(panel1);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2,1));
        JButton polMapButton = new JButton("Political Ties");
        panel2.add(polMapButton);
        polMapButton.addActionListener(new PolMapListener());
        
        JButton relMapButton = new JButton("Religious Ties");
        panel2.add(relMapButton);
        relMapButton.addActionListener(new RelMapListener());
        
        JButton infoModeButton = new JButton("Info Mode");
        panel2.add(infoModeButton);
        infoModeButton.addActionListener(new InfoModeListener());
        pane.add(panel2);
    }

    /** 
     * Returns a point found within the drawing radius of the given location, 
     * or null if none
     *
     *  @param x  the x coordinate of the location
     *  @param y  the y coordinate of the location
     *  @return  a point from the canvas if there is one covering this location, 
     *           or a null reference if not
     */
    public Point findNearbyPoint(int x, int y) {
        double dox = (double) x; 
        double doy = (double) y;
        LinkedList<Profile> profiles= canvas.getProfileList();
        for (Profile pro : profiles) {
           Point p = pro.getPoint(); 
           if(p.distance(dox, doy) <= 15) {return p;}
          }
        return null; 
    }

    /** Constants for recording the input mode */
    enum InputMode {
        POL_MAP, REL_MAP, INFO_MODE
    }

    /** Listener for Political Ties button */
    private class PolMapListener implements ActionListener {
        /** Event handler for AddPoint button */
        public void actionPerformed(ActionEvent e) {
            mode = InputMode.POL_MAP;
            instr.setText("Pompeian Socio-Political Subgraphs");
            canvas.clear();
            //adding points based on the order in spreadsheet 
            canvas.addProfile(new Profile(new Point(386, 66),Color.WHITE,1,"C. Gavius Rufus: Aedile candidate favored by sagarii."));
            /*
             * Dissection of this and following lines:
             * Each line adds a Profile, and the Profile parameter itself is constructed w/Profile's
             * argument constructor.
             */
            
            canvas.addProfile(new Profile(new Point(311, 254),Color.YELLOW,7,"A. Trebius Valens: Aedile, duumvir, rogator, quinquennial candidate."));
            canvas.addProfile(new Profile(new Point(119, 136),Color.CYAN,0,"C. Cuspius Pansa: Yes, *that* Pansa who ran for aedile. Favored by Isiaci."));
            canvas.addProfile(new Profile(new Point(162, 136),Color.WHITE,3,"L. Popidius Secundus: Aedile, duumvirate candidate. Favored by those in Isis and Augustan cults."));
            canvas.addProfile(new Profile(new Point(205, 179),Color.WHITE,0,"A. Suettius Verus: The son of A. S. Certus, ran for aedile."));
            canvas.addProfile(new Profile(new Point(248, 173),Color.BLUE,3,"N. Herennius Celsus: An Oscan who held aedile once and duumvir twice."));
            canvas.addProfile(new Profile(new Point(148, 89),Color.YELLOW,2,"M. Epidius Sabinus: Aedile, duumvirate candidate, defensor coloniae."));
            canvas.addProfile(new Profile(new Point(111, 92),Color.BLUE,0,"Q. Marius Rufus: Another aedile candidate, of Oscan descent."));
            canvas.addProfile(new Profile(new Point(108, 37),Color.WHITE,1,"A. Vettius Caprasius Felix: Aedile, duumvir candidate. Of the trading Vettii."));
            canvas.addProfile(new Profile(new Point(144, 37),Color.BLUE,1,"P. Paquius Proculus: Aedile, duumvir candidate, kept his alliances."));
            canvas.addProfile(new Profile(new Point(124, 180),Color.RED,0,"Cn. Helvius Sabinus: Ran for aedile with support from Isiaci."));
            canvas.addProfile(new Profile(new Point(174, 182),Color.GREEN,0,"M. Samellius Modestus: Aedile candidate attested to be Celtic."));
            canvas.addProfile(new Profile(new Point(199, 231),Color.RED,0,"Q. Postumius Proculus: Aedile candidate of Roman descent."));
            canvas.addProfile(new Profile(new Point(252, 253),Color.BLUE,0,"M. Cerrinius Vatia: Aedile candidate of Oscan descent."));
            canvas.addProfile(new Profile(new Point(344, 38),Color.YELLOW,1,"M. Holconius Priscus: Aedile, ran for duumvir. His family makes wine."));
            canvas.addProfile(new Profile(new Point(197, 87),Color.WHITE,1,"A. Suettius Certus: The father of A. S. Verus, he ran for duumvir."));
            canvas.addProfile(new Profile(new Point(406, 101),Color.YELLOW,2,"Cn. Audius Bassus: Ran for quinquennial after terms as duumvir and aedile."));
            canvas.addProfile(new Profile(new Point(78, 188),Color.RED,0,"C. Iulius Polybius: Aedile, turned down a few female rogatores."));
            canvas.addProfile(new Profile(new Point(41, 175),Color.CYAN,0,"M. Lucretius Fronto: Ran for duumvir after aedileship, good wine business."));
            canvas.addProfile(new Profile(new Point(57, 230),Color.WHITE,0,"Zmyrina: A female rogator, one of many."));
            canvas.addProfile(new Profile(new Point(110, 243),Color.WHITE,0,"Cuculla: A female rogator, one of many."));
            canvas.addProfile(new Profile(new Point(83, 103),Color.YELLOW,3,"M. Epidius Hymenaeus: A notable rogator."));
            
            int[] ends0 = {1,2,3,14};
            for (int i: ends0){ canvas.addLinum(new Linum(canvas.getProfile(0).getPoint(),canvas.getProfile(i).getPoint(),Color.BLUE));}
            /*
             * Dissection of this and similar lines:
             * The typical line starting canvas.addLinum takes a Linum object here made by the Linum constructor.
             * To get the Points for said constructor, we access certain Profiles with getProfile and then extract 
             * the Points with getPoint.
             * 
             * If there's enough connections involving a Profile, it is more efficient to make one end the Profile
             * and then loop through the other ends with a for-each.
             */
            
            
            int[] ends1 = {2,4,10,14,17};
            canvas.addLinum(new Linum(canvas.getProfile(1).getPoint(),canvas.getProfile(3).getPoint(),Color.RED));
            canvas.addLinum(new Linum(canvas.getProfile(1).getPoint(),canvas.getProfile(16).getPoint(),Color.BLUE));
            canvas.addLinum(new Linum(canvas.getProfile(1).getPoint(),canvas.getProfile(5).getPoint(),Color.BLUE));
            for (int i: ends1){ canvas.addLinum(new Linum(canvas.getProfile(1).getPoint(),canvas.getProfile(i).getPoint(),Color.GREEN));}
            
            canvas.addLinum(new Linum(canvas.getProfile(2).getPoint(),canvas.getProfile(3).getPoint(),Color.BLUE));
            canvas.addLinum(new Linum(canvas.getProfile(2).getPoint(),canvas.getProfile(10).getPoint(),Color.RED));
            canvas.addLinum(new Linum(canvas.getProfile(2).getPoint(),canvas.getProfile(11).getPoint(),Color.RED));
            canvas.addLinum(new Linum(canvas.getProfile(2).getPoint(),canvas.getProfile(14).getPoint(),Color.BLUE));
            
            canvas.addLinum(new Linum(canvas.getProfile(3).getPoint(),canvas.getProfile(14).getPoint(),Color.BLUE));
            canvas.addLinum(new Linum(canvas.getProfile(3).getPoint(),canvas.getProfile(10).getPoint(),Color.RED));
            canvas.addLinum(new Linum(canvas.getProfile(3).getPoint(),canvas.getProfile(11).getPoint(),Color.RED));
            
            int[] ends4 = {5,16,6,15};
            canvas.addLinum(new Linum(canvas.getProfile(4).getPoint(),canvas.getProfile(12).getPoint(),Color.RED));
            canvas.addLinum(new Linum(canvas.getProfile(4).getPoint(),canvas.getProfile(13).getPoint(),Color.RED));
            for (int i: ends4){ canvas.addLinum(new Linum(canvas.getProfile(4).getPoint(),canvas.getProfile(i).getPoint(),Color.BLUE));}
            
            canvas.addLinum(new Linum(canvas.getProfile(5).getPoint(),canvas.getProfile(6).getPoint(),Color.BLUE));
            canvas.addLinum(new Linum(canvas.getProfile(5).getPoint(),canvas.getProfile(15).getPoint(),Color.BLUE));
            canvas.addLinum(new Linum(canvas.getProfile(5).getPoint(),canvas.getProfile(16).getPoint(),Color.BLUE));
            canvas.addLinum(new Linum(canvas.getProfile(5).getPoint(),canvas.getProfile(12).getPoint(),Color.RED));
            canvas.addLinum(new Linum(canvas.getProfile(5).getPoint(),canvas.getProfile(13).getPoint(),Color.RED));
            
            int[] ends6 = {15,7,8,9};
            for (int i: ends6){canvas.addLinum(new Linum(canvas.getProfile(6).getPoint(),canvas.getProfile(i).getPoint(),Color.BLUE));}
            
            canvas.addLinum(new Linum(canvas.getProfile(7).getPoint(),canvas.getProfile(8).getPoint(),Color.BLUE));
            canvas.addLinum(new Linum(canvas.getProfile(7).getPoint(),canvas.getProfile(9).getPoint(),Color.BLUE));
            
            canvas.addLinum(new Linum(canvas.getProfile(8).getPoint(),canvas.getProfile(9).getPoint(),Color.BLUE));
            
            canvas.addLinum(new Linum(canvas.getProfile(10).getPoint(),canvas.getProfile(11).getPoint(),Color.BLUE));
            
            canvas.addLinum(new Linum(canvas.getProfile(12).getPoint(),canvas.getProfile(13).getPoint(),Color.BLUE));
            
            canvas.addLinum(new Linum(canvas.getProfile(17).getPoint(),canvas.getProfile(18).getPoint(),Color.BLUE));
            canvas.addLinum(new Linum(canvas.getProfile(17).getPoint(),canvas.getProfile(19).getPoint(),Color.RED));
            canvas.addLinum(new Linum(canvas.getProfile(17).getPoint(),canvas.getProfile(20).getPoint(),Color.RED));
            
            int[] ends21 = {3,10,4};
            for (int i: ends21){canvas.addLinum(new Linum(canvas.getProfile(21).getPoint(),canvas.getProfile(i).getPoint(),Color.GREEN));}
            canvas.repaint();
        }
    }
    
    /** Listener for Religious Ties button*/
    private class RelMapListener implements ActionListener {
        /** Event handler for RelMap button */
        public void actionPerformed(ActionEvent e) {
            mode = InputMode.REL_MAP;
            //System.out.println("X:"+x+" Y:"+y);
            instr.setText("Pompeian Socio-Political Subgraphs");
            canvas.clear();
            
            //adding points based on the order in spreadsheet (not included in suite)
            //GODS
            canvas.addProfile(new Profile(new Point(386, 66),Color.BLACK,1,"Venus Pompeiana: Pompeii's patron goddess of love and motherhood."));
            /*
             * Dissection of this and following lines:
             * Each line adds a Profile, and the Profile parameter itself is constructed w/Profile's
             * argument constructor.
             */
            canvas.addProfile(new Profile(new Point(291, 84),Color.BLACK,0,"Ceres: Roman goddess of nature."));
            canvas.addProfile(new Profile(new Point(119, 136),Color.BLACK,0,"Isis: Egyptian goddess of magic."));
            canvas.addProfile(new Profile(new Point(291, 136),Color.BLACK,0,"Neptune: Roman god of the sea."));
            canvas.addProfile(new Profile(new Point(205, 149),Color.BLACK,0,"Augustus: Roman emperor (technically a mortal) but worshipped as god anyway."));
            canvas.addProfile(new Profile(new Point(248, 163),Color.BLACK,1,"All connections made with this node are with people of the rotating imperial flamen cult."));
                
            //MORTALS (hehe)
            canvas.addProfile(new Profile(new Point(128, 89),Color.WHITE,1,"C. Calventius Quietus: A quite notable Augustalis."));
            canvas.addProfile(new Profile(new Point(111, 92),Color.BLUE,1,"M. Cerrinius Restitutus: Another Augustalis, of Oscan descent."));
            canvas.addProfile(new Profile(new Point(108, 37),Color.WHITE,1,"A. Vettius Conviva: A quite notable Augustalis. Of the trading Vettii."));
            canvas.addProfile(new Profile(new Point(144, 37),Color.WHITE,5,"Cn. Alleius Nigidius Maius: Flamen Vespasiani and man of the munera."));
            canvas.addProfile(new Profile(new Point(124, 180),Color.CYAN,0,"C. Cuspius Pansa: Ran for aedile with support from Isiaci."));
            canvas.addProfile(new Profile(new Point(74, 102),Color.WHITE,2,"L. Popidius Secundus: Aedile, ran for duumvir, liked Isis and Augustus."));
            canvas.addProfile(new Profile(new Point(199, 231),Color.RED,0,"Cn. Helvius Sabinus: Aedile candidate of Roman descent, supported by Isiaci."));
            canvas.addProfile(new Profile(new Point(252, 233),Color.WHITE,1,"L. Popidius Ampliatus: Aedile candidate supported by Isiaci."));
            canvas.addProfile(new Profile(new Point(344, 38),Color.WHITE,1,"M. Heia Rufula: Public priestess of Ceres."));
            canvas.addProfile(new Profile(new Point(197, 87),Color.WHITE,1,"L. Heia Rufula: Public priestess of Ceres."));
            canvas.addProfile(new Profile(new Point(336, 71),Color.GREEN,1,"Sex. Pompeius Ruma: Celtic priest of Neptune."));
            canvas.addProfile(new Profile(new Point(78, 188),Color.WHITE,2,"Eumachia: Public priestess of Ceres, founded the Clothworkers' Guild."));
            canvas.addProfile(new Profile(new Point(41, 175),Color.WHITE,1,"L. Caecilius Felix: ministri Augusti."));
            canvas.addProfile(new Profile(new Point(57, 230),Color.WHITE,1,"C. Vergilius Salinator: Another one of Augustus."));
            canvas.addProfile(new Profile(new Point(110, 243),Color.WHITE,1,"N. Popidius Ampliatus: A ministri Augusti."));
            canvas.addProfile(new Profile(new Point(430, 143),Color.WHITE,1,"Numerius Popidius Ampliatus: Rebuilt the Temple of Isis."));
            canvas.addProfile(new Profile(new Point(430, 163),Color.WHITE,1,"Alleia Decimilla: Public priestess of Ceres."));
            canvas.addProfile(new Profile(new Point(430, 243),Color.WHITE,3,"Alleia: Public priestess of Ceres and Venus."));
            canvas.addProfile(new Profile(new Point(460, 63),Color.YELLOW,10,"M. Holconius Rufus: Flamen Augusti and so much more."));
            canvas.addProfile(new Profile(new Point(480, 183),Color.YELLOW,5,"M. Holconius Celer: A flamen Augusti following the steps of those before him."));
            canvas.addProfile(new Profile(new Point(500, 153),Color.WHITE,1,"Lassia: Public priestess of Ceres."));
            canvas.addProfile(new Profile(new Point(460, 223),Color.RED,1,"Clodia: Public priestess of Ceres."));
            
            int[] ends0 = {23};
            for (int i: ends0){ canvas.addLinum(new Linum(canvas.getProfile(0).getPoint(),canvas.getProfile(i).getPoint(),Color.YELLOW));}
             /*
             * Dissection of this and similar lines:
             * The typical line starting canvas.addLinum takes a Linum object here made by the Linum constructor.
             * To get the Points for said constructor, we access certain Profiles with getProfile and then extract 
             * the Points with getPoint.
             * 
             * If there's enough connections involving a Profile, it is more efficient to make one end the Profile
             * and then loop through the other ends with a for-each.
             */
            
            int[] ends1 = {14,15,17,22,23,26,27};
            for (int i: ends1){ canvas.addLinum(new Linum(canvas.getProfile(1).getPoint(),canvas.getProfile(i).getPoint(),Color.YELLOW));}
            
            int[] ends2 = {10,11,12,13};
            for (int i: ends2){ canvas.addLinum(new Linum(canvas.getProfile(2).getPoint(),canvas.getProfile(i).getPoint(),Color.YELLOW));}
            
            int[] ends4 = {6,7,8,11,19,21};
            for (int i: ends4){ canvas.addLinum(new Linum(canvas.getProfile(4).getPoint(),canvas.getProfile(i).getPoint(),Color.YELLOW));}
            
            int[] ends5 = {9,18,20,24,25};
            for (int i: ends5){canvas.addLinum(new Linum(canvas.getProfile(5).getPoint(),canvas.getProfile(i).getPoint(),Color.YELLOW));}
            canvas.addLinum(new Linum(canvas.getProfile(3).getPoint(),canvas.getProfile(16).getPoint(),Color.YELLOW));
            canvas.repaint();
        }
    }
    /** Listener for InfoMode button */
    private class InfoModeListener implements ActionListener {
        /** Event handler for RmvPoint button */
        public void actionPerformed(ActionEvent e) {
            mode = InputMode.INFO_MODE;
            
        }
    }

    /** Mouse listener for PointCanvas element */
    private class PointMouseListener extends MouseAdapter
        implements MouseMotionListener {
        Point where;
        /** Responds to click event depending on mode */
        public void mouseClicked(MouseEvent e) {
            int x = e.getX(), y = e.getY();
            
            if ( mode == InputMode.INFO_MODE){ 
                    if (findNearbyPoint(x,y)!=null) {
                        instr.setText(canvas.getProfile(canvas.findPointIndex(findNearbyPoint(x,y))).getBio());
                    }else{
                        
                        instr.setText("Pompeian Socio-Political Subgraph. Click Nodes for bios!");
                    }
                    
            }
            canvas.repaint();
        }

        /** Records point under mousedown event in anticipation of possible drag */
        public void mousePressed(MouseEvent e) {
            where = e.getPoint();
        }

        /** Responds to mouseup event */
        public void mouseReleased(MouseEvent e) {
            where = null;
        }

        /** Responds to mouse drag event */
        public void mouseDragged(MouseEvent e) {}

        // Empty but necessary to comply with MouseMotionListener interface.
        public void mouseMoved(MouseEvent e) {}
    }
}
