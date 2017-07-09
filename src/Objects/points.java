package Objects;

import java.util.ArrayList;

/**
 * Created by Matthew on 08/07/2017.
 */
public class points {
    private int total = 0;
    private ArrayList points = new ArrayList();

    public ArrayList getPoints() {
        return points;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    public void setPoints(){
        for (int i = 0; i<total;i++){
            int[] currentPoints = new int[total];
            points.add(currentPoints);
        }

    }

}

