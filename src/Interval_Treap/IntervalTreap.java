package Interval_Treap;
/**
 *Interval Treap class
 * @author Morgan Ambourn
 * @author Eric Marcanio
 */
import org.jetbrains.annotations.NotNull;

/*This is the big boi*/
public class IntervalTreap {
    Node root;
    int size =0;
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

        return findSize(this.root);
    }

    /*Helper method to find the size recursiley*/
    public int findSize(Node n){
        if(n==null) return 0;

        int ret = 1;

        if(n.getLeft() != null) ret += findSize(n.getLeft());
        if(n.getRight() != null) ret += findSize(n.getRight());

        return ret;
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
                //updateImax(z);
            }
            else if(leftOrRight == 1) {  //left
                Node left = z.getLeft();
                //update right child's imax
                updateImax(left);
                // update z's imax
                //updateImax(z);
            }
        }
        //update Imax for nodes above inserted node
        Node curr = z;
        while(curr.getParent()!= null ){
            curr = curr.getParent();
            updateImax(curr);
        }
        size++;
        }
    public void updateImax(Node z){
        if(z.getRight() != null && z.getLeft() != null){    //both children exist
            int zChildImax = Math.max(z.getRight().getIMax(), z.getLeft().getIMax());
            z.setIMax(Math.max(z.getInterv().getHigh(),zChildImax));
        }
        else if(z.getRight() != null && z.getLeft() == null) { //only right child
            z.setIMax(Math.max(z.getRight().getIMax(), z.getInterv().getHigh()));
        }
        else if(z.getRight() == null && z.getLeft() != null){//only left child
            z.setIMax(Math.max(z.getLeft().getIMax(), z.getInterv().getHigh()));
        }else{  //no children
            z.setIMax(z.getInterv().getHigh());
        }
    }

    /*Removes node z from the tree O(log(n))*/
    public void intervalDelete(Node z){

        int rightORleft = 2; // 1 - Right, 0 - left
        Node rotations = z;
        //Phase 1
        //Z has no left child
        if(z.getLeft() == null && z.getRight() != null){ //No left but right child

            if(z.getParent() != null){ // not root
                if(z.getParent().getRight() == z ){  //Check what side of parent
                    z.getParent().setRight(z.getRight());
                }else{
                    z.getParent().setLeft(z.getRight());
                }
                z.getRight().setParent(z.getParent());
            }else{// Root
                z.getRight().setParent(null);
                root = z.getRight();
            }
            rotations = z.getRight();
        }else if(z.getRight() == null && z.getLeft() != null){ //Has left child but no right

            if(z.getParent() != null){//Not root

                if(z.getParent().getRight() == z ){
                    z.getParent().setRight(z.getLeft());
                }else{
                    z.getParent().setLeft(z.getLeft());
                }
                z.getLeft().setParent(z.getParent());
            }else{//Root
                z.getLeft().setParent(null);
                root = z.getLeft();
            }

            rotations = z.getLeft();// Node replacement location

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

            if(z.getRight() == successor){
                //successor.setRight(null);
            }else{
                //Sets successors right childs parent
                if(successor.getRight() != null){
                    successor.getRight().setParent(z.getRight());
                    z.getRight().setLeft(successor.getRight());
                }else{
                    //If there is nothing right to the sucessor set to null
                    z.getRight().setLeft(null);
                }
                //Sets up successors right child
                successor.setRight(z.getRight());
                z.getRight().setParent(successor);

            }

            if(z.getParent() != null){
                successor.setParent(z.getParent());
            }else{
                successor.setParent(null);
                root = successor;
            }

            rotations = successor;

        }else{ //No children
            if(z == root){
                root = null;
            } else if(z.getParent().getLeft() == z){
                z.getParent().setLeft(null);
            }else if(z.getParent().getRight() == z){
                z.getParent().setRight(null);
            }
            z = null;
            //Does not rotate
            rotations.setRight(null);
            rotations.setLeft(null);
        }

        //Loop till we get to a leaf
        int tempMin;
        Node tempRotations = rotations;
        while(rotations.getRight()!= null || rotations.getLeft() != null){ //Go until at a leaf node
            if(rotations.getLeft() != null && rotations.getRight() != null){ //Has two children
                tempMin = Math.min(rotations.getRight().priority,rotations.getLeft().priority);
                if(tempMin < rotations.getPriority()){
                    if(tempMin == rotations.getRight().priority){
                        leftRotate(rotations.getRight());
                    }else if(tempMin == rotations.getLeft().priority){
                        rightRotate(rotations.getLeft());
                    }
                }else{
                    break;
                }
            }else if(rotations.getRight() == null && rotations.getLeft() != null){ // Has left child
                if(rotations.priority > rotations.getLeft().priority){
                    rightRotate(rotations.getLeft());
                }else{
                    break;
                }
            }else if(rotations.getRight() != null && rotations.getLeft() == null){// has right child
                if(rotations.priority > rotations.getRight().priority){
                    leftRotate(rotations.getRight());
                }else{
                    break;
                }
            }
        }

        Node curr = rotations;
        while(curr.getParent()!= null ){
            curr = curr.getParent();
            updateImax(curr);
        }
        size --;
    }

    /*Find the last highest priority  */

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
        while(temp != null && !Overlap(temp, i)){
            if(temp.getInterv().getLow() < i.getLow()){
                temp = temp.getRight();
            }else{
                temp = temp.getLeft();
            }
        }
        return temp;
    }

    /*Helper to check overlap*/
    private boolean Overlap(Node x, Interval i)
    {
        return (i.getLow() <= x.getInterv().getHigh() && x.getInterv().getLow() <= i.getHigh());
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
        updateImax(x);
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
        updateImax(x);
    }
}
