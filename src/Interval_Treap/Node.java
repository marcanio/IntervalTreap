package Interval_Treap;
/*Represents the actual nodes in the tree*/
public class Node {
    Node root;

    /*Constructor takes object i as param and generates priority*/
    Node (Interval i){

    }

    /*Returns the parent of the node*/
    public Node getParent(){
        Node test = root;
        return test;
    }

    /*Return the left child of the node*/
    public Node getLeft(){
        Node test = root;
        return test;
    }

    /*Return the right child of the node*/
    public Node getRight(){
        Node test = root;
        return test;
    }

    /*Returns the interval object stored in this node*/
    //public Interval getInterv(){
        //Interval test= 0;
        //return test;
    //}

    /*Returns the value of imax field of this node*/
    public int getIMax(){
        return 0;
    }

    /*Returns the priority of this node*/
    public int getPriority(){
        return 0;
    }

}
