package Interval_Treap;
/*This is the big boi*/
public class IntervalTreap {
    Node root;

    /*Constructor*/
    public IntervalTreap(){
        this.root = null;
    }

    /*Returns the root node*/
    public Node getRoot(){
        return this.root;
    }

    /*Returns the number of nodes in the treap*/
    public int getSize(){
        //TODO
        return 0;
    }

    /*Returns the height*/
    public int getHeight(){
        //TODO
        return 0;
    }

    /*Adds node z and refrences interv to the interval treap. O(log(n))*/

    public void intervalInsert(Node z){
        Node tempNode = null;
        Node tempRoot = this.root;

        /* phase 1: insert based on z.key which is equal to z.getInterv().getLow(), like binary tree */
        while(tempRoot != null){
            tempNode = tempRoot;
            if(z.getInterv().getLow() < tempRoot.getInterv().getLow()){
                tempRoot = tempRoot.getLeft();
            }else{
                tempRoot = tempRoot.getRight();
            }
        }
        z.setParent(tempNode);
        if(tempRoot == null ){
            this.root = z;
        }else if(z.getInterv().getLow() < tempNode.getInterv().getLow()){
            tempNode.setLeft(z);
        }else{
            tempNode.setRight(z);
        }

        /* phase 2: rotate based on priority */


    }

    /*Removes node z from the tree O(log(n))*/
    public void intervalDelete(Node z){
        //TODO
    }

    /*Returns a refrence to a node in a certain interval O(log(n))*/
    public Node intervalSearch(Interval i){
        return root;    //TODO
    }

    /*Helper method to Right rotate*/
    //TODO: Check for overwritting on the last if statement
    public void rightRotate(Node x){

        Node y = x.getParent();
        y.setLeft(x.getRight());
        if(x.getRight() != null){
            Node tempRight = x.getRight();
            tempRight.setParent(y);
        }
        x.setParent(y.getParent());
        Node tempParent = y.getParent();
        if(y.getParent() == null){
            this.root = x;
        }else if (y == y.getParent().getRight()){
            tempParent.setRight(x);
        }else{
            tempParent.setLeft(x);
        }
        x.setRight(y);
        y.setParent(x);
    }

    /*Helper method to Left rotate*/
    //TODO: Check for overwritting on the last if statement
    public void leftRotate(Node x){
        Node y = x.getParent();
        y.setRight(x.getLeft());
        if(x.getLeft() != null){
            Node tempLeft = x.getLeft();
            tempLeft.setParent(y);
        }
        x.setParent(y.getParent());
        Node tempParent = y.getParent();
        if(y.getParent() == null){
            this.root = x;
        }else if(y == y.getParent().getLeft()){
            tempParent.setLeft(x);
        }else{
            tempParent.setRight(x);
        }
        x.setLeft(y);
        y.setParent(x);
    }
}
