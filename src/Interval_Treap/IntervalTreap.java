package Interval_Treap;
/*This is the big boi*/
public class IntervalTreap {
    Node root;

    /*Constructor*/
    public IntervalTreap(){
        root = null;
    }

    /*Returns the root node*/
    public Node getRoot(){
        return root;
    }

    /*Returns the number of nodes in the treap*/
    public int getSize(){
        return 0;
    }

    /*Returns the height*/
    public int getHeight(){
        return 0;
    }

    /*Adds node z and refrences interv to the interval treap. O(log(n))*/
    public void intervalInsert(Node z){
        if(this.root == null ){
            this.root = z;
        }
        /* phase 1: insert based on z.key which is equal to z.getInterv().getLow(), like binary tree */

        /* phase 2: rotate based on priority */

    }

    /*Removes node z from the tree O(log(n))*/
    public void intervalDelete(Node z){

    }

    /*Returns a refrence to a node in a certain interval O(log(n))*/
    public Node intervalSearch(Interval i){
        return root;    //TODO
    }
}
