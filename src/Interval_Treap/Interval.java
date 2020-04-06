package Interval_Treap;
/**
 *Interval Class
 * @author Morgan Ambourn
 * @author Eric Marcanio
 */

/*All methods should run in constant time and also using ints*/
public class Interval {

    private int lowValue;
    private int highValue;

    /*Constructor*/
    Interval(int low, int high){
        this.lowValue = low;
        this.highValue = high;
    }

    /*Returns the low endpoint of the interval*/
    public int getLow(){
        return this.lowValue;
    }

    /*Returns the high endpoint of the interval*/
    public int getHigh(){
        return this.highValue;
    }
}
