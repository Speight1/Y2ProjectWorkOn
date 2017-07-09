package Objects;

import javax.swing.*;

/**
 * Created by Matthew on 22/06/2017.
 */
public class line {
    private int x,y,x2,y2;
    private int len;
    private int name;
    points Distances;

    /*
        [ [1] = A - B, [2] = A - B, [3] = A - B, [4] = A - B, [5] = A - B]
     */

    //Getter
    public int getLen() {
        return len;
    }
    public int getName() {
        return name;
    }
    public int getX() {
        return x;
    }
    public int getX2() {
        return x2;
    }
    public int getY() {
        return y;
    }
    public int getY2() {
        return y2;
    }
    public points getDistances() {
        return Distances;
    }

    //Setter
    public void setX(int x) {
        this.x = x;
    }
    public void setX2(int x2) {
        this.x2 = x2;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setY2(int y2) {
        this.y2 = y2;
    }
    public void setName(int name) {
        this.name = name;
    }

    /////////////////   Functions
    public void setNumberPoints(int n){
           Distances.setTotal(n);
    }


    public JTextField length = new JTextField();

    public void setLength(int len){
        this.len = len;
        this.length.setText(""+len);
    }
    public void updateLength(int len){
        this.length.setText(""+len);
    }




}
