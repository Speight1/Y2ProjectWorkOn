package logic;

import Objects.Order;
import Objects.line;

import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Created by Matthew on 22/06/2017.
 */
public class logic {
    int tcount = 0;
    ArrayList bestRoute = new ArrayList();

   public void swap(ArrayList<line> lines, int count, double CurTime, double StartTime) {


        if (count > lines.size()-1){
            count = 0;
        }
       int ran = (int)Math.floor(Math.random()*(lines.size()-1));


        line tc = lines.get(count);
        lines.set(count, lines.get(count+ran));
        lines.set(count+ran, tc);
       count = count+1;
    }

    public void Dist(ArrayList<line> lines,int TotalNumCities, int best){
        int length = 0;
        for (int i = 0;i<TotalNumCities;i++){
            line cur = lines.get(i);
            length = (int) Point2D.distance(cur.getX(), cur.getY(), cur.getX2(), cur.getY2());

            cur.setLength(length);

        }


    }

    public int CalcSum(ArrayList<line> lines, int TotalNumCities, int best, ArrayList<line> bestRoute,int currentOrderSum){
        currentOrderSum = 0;
        int sum =0;
        for (int i = 0; i<TotalNumCities;i++){
            sum+=lines.get(i).getLen();
        }

        if(sum<best){
            System.out.print(sum+"\n\t");
            this.bestRoute = (ArrayList) lines;
            best = sum;
            return best;
        }
        return best;
    }


    public int factorial(int n) {
        int fact = 1; // this  will be the result
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    public ArrayList getBestRoute(){
        return this.bestRoute;
    }


    public void swapOrder(ArrayList<Order> curPopulation, int populationSize, int TotalNumCities){

        for (int i = 0; i<populationSize;i++){
            int r1 = (int)Math.floor(Math.random()*TotalNumCities);
            int r2 = (int)Math.floor(Math.random()*TotalNumCities);

            int[] newOrder = curPopulation.get(i).getOrder();
            int tc = newOrder[r1];

            newOrder[r1] = newOrder[r2];
            newOrder[r2] = tc;


        }


    }



    public void calcFitness(int populationSize, int CurOrderSum){
        for (int i =0; i<populationSize;i++){

        }
    }

    public Order NTGA(Order curOrder){ // Normal To Genetic Algorithm
        int curOrderSize = curOrder.getOrder().length;

        //The GAOrder
        Order newOrder = new Order();
        newOrder.setOrderSize(curOrderSize);

        //The counting list
        Order standard = new Order();
        standard.setOrderSize(curOrderSize);
        standard.create();

        //Conversion
        for (int i = 0; i<curOrderSize;i++){ // Current order loop
            for (int n = 0; n< standard.getOrder().length;n++){

                if (curOrder.getOrder()[i] == standard.getOrder()[n]){
                    newOrder.getOrder()[i] = n;

                    //Remove the last selected one
                    int[] temp = standard.getOrder();
                    standard.setOrderSize(standard.getOrder().length-1);
                    int count = 0;
                    for (int l = 0; l<temp.length;l++){
                        if (temp[l] != curOrder.getOrder()[i]){
                            standard.getOrder()[count] = temp[l];
                            count++;
                        }
                    }
                    break;
                }
            }
        }
        return newOrder;
    }
    public Order GATN(Order GA){ // Genetic Objects.Order To Normal (Objects.Order of indexes)
        int GAOrderSize = GA.getOrder().length;

        // new order
        Order newOrder = new Order();
        newOrder.setOrderSize(GAOrderSize);

        //The counting list
        Order standard = new Order();
        standard.setOrderSize(GAOrderSize);
        standard.create();


        for (int i = 0; i<GAOrderSize;i++){ // loop for the GAOrder

            newOrder.getOrder()[i] = standard.getOrder()[GA.getOrder()[i]];

            int[] temp = standard.getOrder();
            standard.setOrderSize(standard.getOrder().length-1);

            int count = 0;

            for (int n = 0; n<temp.length;n++){

                if ( n != GA.getOrder()[i] ){
                    standard.getOrder()[count] = temp[n];
                    count++;
                }

            }

        }

        return newOrder;
    }

    public void swapCrossover(ArrayList<Order> curPopulation, int populationSize, int TotalNumCities){



    }
    public Order Crossover(Order GA1, Order GA2){
        Order n = new Order();

        int crossPoint;
        int size = (GA1.getOrder().length+GA2.getOrder().length)/2;

        int[] temp1 = new int[size/2];
        for (int i = 0; i<size/2;i++){
            temp1[i] = GA1.getOrder()[i];

        }


        return n;
    }

    public void PrintArray(int[] array){
        for (int i = 0;i<array.length;i++){
            if (i == 0){
                System.out.print("[ ");
            }
            System.out.print("["+i+"]: "+array[i]+" ");
            if (i == array.length-1){
                System.out.print("]\n");
            }
        }
    }



    //////////////////////  Get Results  //////////////////////

    public void brute(int TotalNumCities, int count, ArrayList<line> cities, double CurTime, double StartTime){
        tcount++;
        int r1 = (int) Math.floor(Math.random() * TotalNumCities);
        int r2 = (int) Math.floor(Math.random() * TotalNumCities);
        if (tcount != TotalNumCities*factorial(TotalNumCities)) {
            swap(cities,count,CurTime,StartTime);
        }
    }



}


