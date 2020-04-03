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
        Node y = null;
        Node x = this.root;

        /* phase 1: insert based on z.key which is equal to z.getInterv().getLow(), like binary tree */
        while(x != null){
            y = x;
            if(z.getInterv().getLow() < x.getInterv().getLow()){
                x = x.getLeft();
            }else{
                x = x.getRight();
            }
        }
        z.setParent(y);
        if(y == null ){
            this.root = z;
        }else if(z.getInterv().getLow() < y.getInterv().getLow()){
            y.setLeft(z);
        }else{
            y.setRight(z);
        }


        /* phase 2: rotate based on priority */
        while(z.getParent()!= null ){    //TODO: fix condition
            boolean rotate = (z.getPriority() < z.getParent().getPriority());
            int leftOrRight;   //right = 0 and left = 1
            if(rotate && (z.getParent().getLeft()== z)){
                rightRotate(z);
                leftOrRight = 0;   //right child of z needs imax update
            }else if(rotate && (z.getParent().getRight() == z)){
                leftRotate(z);
                leftOrRight = 1;   //left child of z needs imax updat
            }else{
                break;
            }

            if(leftOrRight == 0){  //right
                Node right = z.getRight();
                //update right child's imax
                updateImax(right);
                // update z's imax
                updateImax(z);
            }
            else if(leftOrRight == 1) {  //left
                Node left = z.getLeft();
                //update right child's imax
                updateImax(left);
                // update z's imax
                updateImax(z);
            }

        }

    }
    public void updateImax(Node z){
        if(z.getRight() != null && z.getLeft() != null){    //both children exist
            int zChildImax = Math.max(z.getRight().getIMax(), z.getLeft().getIMax());
            z.setIMax(Math.max(z.getIMax(),zChildImax));
        }
        else if(z.getRight() != null && z.getLeft() == null) { //only right child
            z.setIMax(Math.max(z.getRight().getIMax(), z.getIMax()));
        }
        else if(z.getRight() == null && z.getLeft() != null){//only left child
            z.setIMax(Math.max(z.getLeft().getIMax(), z.getIMax()));
        }else{}
    }

    /*Removes node z from the tree O(log(n))*/
    public void intervalDelete(Node z){
        //TODO
    }

    /*Returns a reference to a node in a certain interval O(log(n))*/
    public Node intervalSearch(Interval i){
        return root;    //TODO
    }

    /*Helper method to Right rotate*/
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
