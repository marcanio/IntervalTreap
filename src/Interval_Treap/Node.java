package Interval_Treap;
import java.util.Random;
import java.lang.*;
/*Represents the actual nodes in the tree*/
public class Node {
    Node root;
    Interval interval;
    int priority;
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

    /*Returns the interval object stored in this node*/
    public Interval getInterv(){
        return interval;
    }

    /*Returns the value of imax field of this node*/
    public int getIMax(){
        return interval.getHigh();
    }

    /*Returns the priority of this node*/
    public int getPriority(){
        return priority;
    }

}
