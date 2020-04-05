package Interval_Treap;

import org.jetbrains.annotations.NotNull;

/*This is the big boi*/
public class IntervalTreap {
    Node root;
    int globalRotate;

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

        Node temp = root;
        return size(temp);
    }

    /*Recursively count nodes in the tree */
    public int size(Node temp){
        if(temp == null){
           return 0;
        }else{
            return(size(temp.getLeft()) + 1 + size(temp.getRight()));
        }
    }


    /*Returns the height*/
    public int getHeight(){
        Node temp = root;
        return height(temp);
    }

    /*Recursivley finds the height of the tree*/
    public int height(Node temp){
        if(temp == null){
            return -1;
         }
        int leftH = height(temp.getLeft());
        int rightH = height(temp.getRight());

        if(leftH > rightH){
            return leftH +1;
        }else{
            return rightH + 1;
        }

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
        while(z.getParent()!= null ){
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
        //update Imax for nodes above inserted node
        Node curr = z;
        while(curr.getParent()!= null ){
            curr = curr.getParent();
            updateImax(curr);
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
        }else{  //no children
            z.setIMax(z.getInterv().getHigh());
        }
    }

    /*Removes node z from the tree O(log(n))*/
    public void intervalDelete(Node z){

        int rightORleft = 2; // 1 - Right, 0 - left

        //Phase 1
        //Z has no left child
        if(z.getLeft() == null && z.getRight() != null){ //No left but right child

            if(z.getParent() != null){ // not root
                z.getRight().setParent(z.getParent());
                if(z.getParent().getRight() == z ){  //Check what side of parent
                    z.getParent().setRight(z.getRight());
                }else{
                    z.getParent().setLeft(z.getRight());
                }
            }else{// Root
                z.getRight().setParent(null);
                root = z.getRight();
            }

        }else if(z.getRight() == null && z.getLeft() != null){ //Has left child but no right

            if(z.getParent() != null){//Not root
                z.getLeft().setParent(z.getParent());
                if(z.getParent().getRight() == z ){
                    z.getParent().setRight(z.getLeft());
                }else{
                    z.getParent().setLeft(z.getLeft());
                }

            }else{//Root
                z.getLeft().setParent(null);
                root = z.getLeft();
            }

        }else if(z.getRight() != null && z.getLeft() != null){ //Z has two children
            //Finds the successor
            Node successor = minimum(z.getRight());


            if(z.getParent() != null){//Not root
                if(z.getParent().getRight() == z){ // Check side of parent
                    z.getParent().setRight(successor);
                }else{
                    z.getParent().setLeft(successor);
                }
            }

            z.getLeft().setParent(successor); //Parent child relationship on left side
            successor.setLeft(z.getLeft());

            if(z.getRight() != successor){ //Make sure suc. does not point to itself
                z.getRight().setParent(successor);
                successor.setRight(z.getRight());
                successor.getParent().setLeft(null);
            }

            if(z.getParent() != null){
                successor.setParent(z.getParent());
            }else{
                successor.setParent(null);
                root = successor;
            }

        }else{ //No children
            if(z == root){
                root = null;
            } else if(z.getParent().getLeft() == z){
                z.getParent().setLeft(null);
            }else if(z.getParent().getRight() == z){
                z.getParent().setRight(null);
            }
            z = null;
        }

    }

    /*Updates the trees IMax after a delete*/
    public int DeleteUpdateImax(int imax, Node z){
        if(z.getIMax()!=imax || (imax == z.getInterv().getHigh() && globalRotate==1)){
            z.setIMax(z.getIMax());
            return z.getIMax();
        }else {
            if (z.getRight() != null && z.getLeft() != null) {
                z.setIMax(Math.max(DeleteUpdateImax(imax, z.getRight()), DeleteUpdateImax(imax, z.getLeft())));
            }else if(z.getLeft() == null){
                z.setIMax(DeleteUpdateImax(imax, z.getRight()));
            }else if (z.getRight() == null){

            }else{  //has no children now
                z.setIMax(z.getInterv().getHigh());
            }
            return 0;
        }
    }

    /*gets the minimum*/
    public Node minimum(Node z){
        while(z.getLeft() != null){
            z = z.getLeft();
        }
        return z;
    }

    /*Returns a reference to a node in a certain interval O(log(n))*/
    public Node intervalSearch(Interval i){
        Node temp = root;
        while(temp != null){
            if(temp.getInterv().equals(i)){
                return temp;
            }else if(temp.getInterv().getLow() < i.getLow()){
                temp = temp.getRight();
            }else{
                temp = temp.getLeft();
            }
        }
        return temp;
    }

    /*Helper method to Right rotate*/
    public void rightRotate(Node x){

        Node y = x.getParent();
        y.setLeft(x.getRight());
        if(x.getRight() != null){
            //Node tempRight = x.getRight();
            x.getRight().setParent(y);
        }
        x.setParent(y.getParent());
        //Node tempParent = y.getParent();
        if(y.getParent() == null){
            this.root = x;
        }else if (y == y.getParent().getRight()){
            y.getParent().setRight(x);
        }else{
            y.getParent().setLeft(x);
        }
        x.setRight(y);
        y.setParent(x);
    }

    /*Helper method to Left rotate*/
    public void leftRotate(Node x){
        Node y = x.getParent();
        y.setRight(x.getLeft());
        if(x.getLeft() != null){
            //Node tempLeft = x.getLeft();
            x.getLeft().setParent(y);
        }
        x.setParent(y.getParent());
        //Node tempParent = y.getParent();
        if(y.getParent() == null){
            this.root = x;
        }else if(y == y.getParent().getLeft()){
            y.getParent().setLeft(x);
        }else{
            y.getParent().setRight(x);
        }
        x.setLeft(y);
        y.setParent(x);
    }
}
