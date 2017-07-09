package Objects;

import javax.swing.*;

/**
 * Created by 160289 on 26/06/2017.
 */
public class Order {
    private int fitness;
    private int len;
    private int[] order;

    public void setOrderSize(int size){
        order = new int[size];
    }

    public void create(){
        try {
            for (int i = 0; i < order.length; i++) {
                order[i] = i;
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Set Objects.Order Size!");
        }
    }
    public int[] getOrder(){
        return order;
    }

}