package Interval_Treap;
import java.util.Random;
import java.lang.*;
/*Represents the actual nodes in the tree*/
public class Node {
    Interval interval;
    int priority, imax;
    private Node left,right,parent;

    /*Constructor takes object i as param and generates priority*/
    Node (Interval i){
        Random rand = new Random();
        int priorityTemp = rand.nextInt(Integer.MAX_VALUE);

        this.left = null;
        this.right = null;
        this.parent = null;

        /*init*/
        this.priority = priorityTemp;
        interval = i;
        this.imax = interval.getHigh();


    }

    /*Returns the parent of the node*/
    public Node getParent(){
        return parent;
    }

    /*Return the left child of the node*/
    public Node getLeft(){
        return left;
    }

    /*Return the right child of the node*/
    public Node getRight(){
        return right;
    }

    /*Sets the parent of a node*/
    public void setParent(Node z){
        parent = z;
    }

    /*Sets the left child of a node*/
    public void setLeft(Node z){
        left = z;
    }

    /*Sets the right child of a node*/
    public void setRight(Node z){
        right = z;
    }

    /*Returns the interval object stored in this node*/
    public Interval getInterv(){
        return interval;
    }

    /*Returns the value of imax field of this node*/
    //TODO Change the high value to be the highest interval including childs
    public int getIMax(){
        return this.imax;
    }

    /*Sets the value of imax field of this node*/
    public void setIMax(int imax){
        this.imax = imax;
    }

    /*Returns the priority of this node*/
    public int getPriority(){
        return priority;
    }

}
