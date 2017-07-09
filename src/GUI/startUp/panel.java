package GUI.startUp;

import Objects.*;
import processes.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by Matthew on 17/06/2017.
 */
public class panel extends JPanel implements MouseMotionListener, MouseListener, KeyListener{
    ArrayList<line> locations;

    int x1 = 0,y1 = 0,x2 = 0,y2 = 0, tempx1 = 0, tempy1 = 0;
    int click = 0;
    boolean dragging = false;
    int D = 30; // Diameter
    int R = D/2; //Radius
    int tempLen = 0;
    line curLoc;
    int name = 65;
    int FONTSIZE = 20;
    boolean start = true;
    int [] SIZE;
    JButton done;
    public panel(int[] SIZE){
        this.SIZE = SIZE;
        locations = new ArrayList<line>();

        this.addMouseMotionListener(this);
        this.addMouseListener(this);

        done = new JButton("Calc");
        done.setBounds(SIZE[0]-80,SIZE[1]-(SIZE[1]-30),20,20);
        done.addMouseListener(this);
        add(done);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("TimesRoman", Font.BOLD, FONTSIZE));
        if(dragging){ // Real time movement (Current Location)

            int midpointx = (curLoc.getX()+curLoc.getX2())/2;
            int midpointy = (curLoc.getY()+curLoc.getY2())/2;

            g.drawLine(curLoc.getX(),curLoc.getY(),curLoc.getX2(),curLoc.getY2()); // x1,y1,x2,y2

            g.drawOval(curLoc.getX()-R,curLoc.getY()-R,D,D);

            g.drawString(""+curLoc.getLen(),midpointx,midpointy);
            g.drawString(""+(char)curLoc.getName(),curLoc.getX(),curLoc.getY()-(D+1));
        }

        // Draw all other paths
        for (line loc : locations) {
            //for edit
            loc.length.addKeyListener(this);
            //------------------------------------
            int midpointx = (loc.getX()+loc.getX2())/2;
            int midpointy = (loc.getY()+loc.getY2())/2;

            g.drawLine(loc.getX(), loc.getY(), loc.getX2(), loc.getY2()); // x1,y1,x2,y2
            g.drawOval(loc.getX()-R,loc.getY()-R,D,D);
            // Add editable length
            loc.length.setBounds(midpointx,midpointy,30,30);
            this.add(loc.length);
            g.drawString(""+(char)loc.getName(),loc.getX(),loc.getY()-(D+1));
        }
    }




    int getLength(){
        int length;
        int tempx = (x2-x1);
        if (tempx <0){
            tempx = (x1-x2);
        }
        int tempy = (y2-y1);
        if (tempy <0){
            tempy = (y1-y2);
        }

        length = (int)Math.sqrt(tempx^2 + tempy^2);
        return length;
    }
    void checkClick(){
        if(click == 0){
            click = 1;
        }else {
            click = 0;
        }
    }
    void lineEnd(MouseEvent e){
        x2 = e.getX();
        y2 = e.getY();

    }
    void lineStart(MouseEvent e){
        x1 = e.getX();
        y1 = e.getY();
    }
    void setSize(int[] S){
        this.setSize(S[0],S[1]); // [0] = Width , [1] = Height
    }

    void curLocation(boolean len){

        curLoc = new line();
        curLoc.setX(x1);
        curLoc.setY(y1);
        curLoc.setX2(x2);
        curLoc.setY2(y2);
        curLoc.setName(name);
        curLoc.setLength(getLength());
    }
    void updateCurLocation(){
        curLoc.setX(x1);
        curLoc.setY(y1);
        curLoc.setX2(x2);
        curLoc.setY2(y2);
    }
    void addLine(ArrayList<line> lines, int r1, int r2){
        line tc = lines.get(r1);
        int tcx = tc.getX();
        int tcy = tc.getY();

        lines.get(r1).setX2( lines.get(r2).getX() );
        lines.get(r1).setY2( lines.get(r2).getY() );

        lines.get(r2).setX2( tcx );
        lines.get(r2).setY2( tcy );
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        curLocation(true);

        dragging = true;
        lineEnd(e);
        repaint();

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == done){
             new mainFrame(SIZE,locations).setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lineStart(e);


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() != done) {
            click += 1;
            // create new line and set all cords name and length
            curLocation(true);

            if (!dragging) {
                if (start) {
                    tempx1 = x1;
                    tempy1 = y1;
                    start = false;
                }
            }

            //set cur location
            if (!start) {
                x2 = tempx1;
                y2 = tempy1;
            }

            //update with connections
            updateCurLocation();

            // add to list
            locations.add(curLoc);

            //change name
            name += 1;

            // add new line to screen
            repaint();
            dragging = false;
            //set start
            tempx1 = x1;
            tempy1 = y1;
            if (start) {
                start = false;
            }
            for (int i = 0; i < locations.size(); i++) {
                addLine(locations, (int) Math.random() * locations.size(), (int) Math.random() * locations.size());
            }

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


    }

    @Override
    public void keyReleased(KeyEvent e) {
        for(line loc : locations){
            if (loc.length == e.getSource()){
                loc.length.setText(loc.length.getText());
                loc.setLength( Integer.parseInt(loc.length.getText()) );
            }
        }
    }
}

