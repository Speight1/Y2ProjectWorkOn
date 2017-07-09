package processes;

import Objects.*;
import logic.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Matthew on 22/06/2017.
 */
public class panel extends JPanel {
    int fontSize = 25;

    ArrayList<line> cities = new ArrayList<>();
    int TotalNumCities;

    Order order = new Order();
    ArrayList<Order> population = new ArrayList<>();
    int populationSize;
    int currentOrderSum = 0;

    logic logic = new logic();

    int[] SIZE;
    int Diameter = 10;
    int radius = Diameter / 2;

    int best;
    ArrayList<line> bestRoute = new ArrayList<>();

    int count = 0;
    double CurTime;
    double StartTime;


    public panel(int[] SIZE, ArrayList<line> cities) {
        this.cities = cities;


        this.SIZE = SIZE;

        populationSize = (int) Math.floor(Math.random() * SIZE[0]);//factorial(TotalNumCities); //- Get all possible combinations//(int)Math.floor(Math.random()*SIZE[0]);

        this.setSize(SIZE[0], SIZE[1]);
        // Set up Cities

        //WITH GUI
        this.cities = cities;
        TotalNumCities = cities.size();
        best = TotalNumCities * SIZE[0] * SIZE[1];

        //MANUAL
        //setupCities();
        //TotalNumCities = 6;

        //Start Time
        StartTime = System.currentTimeMillis();
        //Set up the order
        order.setOrderSize(TotalNumCities);
        for (int i = 0; i < TotalNumCities; i++) {
            order.getOrder()[i] = i;
        }

        // Set up population;
        for (int i = 0; i < populationSize; i++) {
            population.add(order);
        }
        //make the population random
        logic.swapOrder(population,populationSize,TotalNumCities);

    }

    public void setupCities() {
        for (int i = 0; i < TotalNumCities; i++) {
            int r1 = (int) Math.floor(Math.random() * SIZE[0]);
            int r2 = (int) Math.floor(Math.random() * SIZE[1]);

            line cur = new line();
            cur.setX(r1);
            cur.setY(r2);
            cities.add(cur);
        }
        updateLines();
    }

    public void updateLines() {
        for (int i = 0; i < TotalNumCities - 1; i++) {
            line cur = cities.get(i);
            cur.setX2( cities.get(i + 1).getX() );
            cur.setY2( cities.get(i + 1).getY() );

        }
        /*line cur = cities.get(TotalNumCities-1);
        cur.x2 = cities.get(0).x;
        cur.y2 = cities.get(0).y;*/

    }

    ////////////////////////////////////////////Graphics////////////////////////////////////////

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));

        Graphics2D gn = (Graphics2D) g;


        gn.setStroke(new BasicStroke(1));
        for (int n = 0; n < population.size(); n++) {
            int[] curOrder = population.get(n).getOrder();


            for (int i = 0; i < curOrder.length; i++) {

                if (i == 0){
                    g.setColor(Color.BLUE);
                }else if (i == curOrder.length-1) {
                    g.setColor(Color.RED);
                }else{
                    g.setColor(Color.BLACK);
                }
                line cur = cities.get(curOrder[i]);
                g.drawOval(cur.getX() - radius, cur.getY() - radius, Diameter, Diameter);
                //lines
                g.setColor(Color.BLACK);
                g.drawLine(cur.getX(), cur.getY(), cur.getX2(), cur.getY2());

                //System.out.println(cur.getX() + "   " + cur.getY()+ "  " +  cur.getX2()+ "   " +  cur.getY2());
            }

        }
        /*//Best One
        gn.setColor(Color.green);
        gn.setStroke(new BasicStroke(3));

        for (int i = 0; i < bestRoute.size(); i++) {
            line cur = bestRoute.get(i);
            //g.drawOval(cur.x - radius, cur.y - radius, Diameter, Diameter);
            g.drawLine(cur.getX(), cur.getY(), cur.getX2(), cur.getY2());
        }*/
        ///////////////////
        g.setColor(Color.BLACK);
        g.drawString("Quickest Route: " + (int) (best) + " Km", 10, 30);
        gn.drawString("Time: " + (int) CurTime + " ms", SIZE[0] - 120, SIZE[1] - (SIZE[1] - 30));

        //Check next
        logic.brute(TotalNumCities,count,cities,CurTime,StartTime);
        CurTime = (int)(System.currentTimeMillis() - StartTime );
        logic.Dist(cities,TotalNumCities,best);
        best = logic.CalcSum(cities,TotalNumCities,best,bestRoute,currentOrderSum);
        bestRoute = logic.getBestRoute();
        updateLines();

        repaint();
    }
}

    ////////////////////////////////////////////Logic////////////////////////////////////////
