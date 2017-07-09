package processes;

import Objects.line;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Matthew on 22/06/2017.
 */
public class mainFrame extends JFrame {
    int[] SIZE = {700,500};

    public mainFrame(int[] SIZE, ArrayList<line> cities){ //int[] SIZE, int TotalNCities, ArrayList<line> cities
        setDefaultCloseOperation(2);
        setSize(SIZE[0]+100,SIZE[1]+100);
        panel p = new panel(SIZE, cities);

        add(p);
    }
}
