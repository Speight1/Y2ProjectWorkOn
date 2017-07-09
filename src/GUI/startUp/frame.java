package GUI.startUp;

import javax.swing.*;

/**
 * Created by Matthew on 17/06/2017.
 */
public class frame {

    public static void main(String[] args) {

        int[] SIZE = {700,700};

        JFrame j = new JFrame("MAP-IT");
        j.setSize(SIZE[0],SIZE[1]);
        j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //j.setUndecorated(true);

        panel p = new panel(SIZE);




        j.add(p);
        j.setVisible(true);
    }

}
