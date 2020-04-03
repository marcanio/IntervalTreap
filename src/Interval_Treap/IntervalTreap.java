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
        while(z.getParent()!= null){    //TODO: fix condition
            boolean rotate = (z.getPriority() < z.getParent().getPriority());
            int leftOrRight = 2;   //if unchanged, don't need to update imax, right = 0 and left = 1
            if(rotate && (z.getParent().getLeft()== z)){
                rightRotate(z);
                leftOrRight = 0;   //right child of z needs imax update
            }else if(rotate && (z.getParent().getRight() == z)){
                leftRotate(z);
                leftOrRight = 1;   //left child of z needs imax updat
            }

            if((leftOrRight != 2)) {        //update Imax
                if(leftOrRight == 0){  //right
                    Node right = z.getRight();
                    //update right child's imax
                    if(right.getRight() != null && right.getLeft() != null){    //both children exist
                        int childImax = Math.max(right.getRight().getIMax(), right.getLeft().getIMax());
                        right.setIMax(Math.max(right.getIMax(),childImax));
                    }
                    else if(right.getRight() != null && right.getLeft() == null) { //only right child
                        right.setIMax(Math.max(right.getRight().getIMax(), right.getIMax()));
                    }
                    else if( right.getRight() == null && right.getLeft() != null){//only left child
                        right.setIMax(Math.max(right.getLeft().getIMax(), right.getIMax()));
                    }else{}

                    // update z's imax
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
                else if(leftOrRight == 1) {  //left
                    Node left = z.getLeft();
                    //update right child's imax
                    if(left.getRight() != null && left.getLeft() != null){    //both children exist
                        int childImax = Math.max(left.getRight().getIMax(), left.getLeft().getIMax());
                        left.setIMax(Math.max(left.getIMax(),childImax));
                    }
                    else if(left.getRight() != null && left.getLeft() == null) { //only right child
                        left.setIMax(Math.max(left.getRight().getIMax(), left.getIMax()));
                    }
                    else if( left.getRight() == null && left.getLeft() != null){//only left child
                        left.setIMax(Math.max(left.getLeft().getIMax(), left.getIMax()));
                    }else{}

                    // update z's imax
                    if(z.getRight() != null && z.getLeft() != null){    //both children exist
                        int zChildImax = Math.max(z.getRight().getIMax(), z.getLeft().getIMax());
                        z.setIMax(Math.max(z.getIMax(),zChildImax));
                    }
                    else if(z.getRight() != null && z.getLeft() == null) { //only right child
                        z.setIMax(Math.max(z.getRight().getIMax(), z.getIMax()));
                    }
                    else if(z.getRight() == null && z.getLeft() != null){//only left child
                        z.setIMax(Math.max(z.getLeft().getIMax(), z.getIMax()));
                    }
                }
            }
        }

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
