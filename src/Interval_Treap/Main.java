package Interval_Treap;
/**
 *Main
 * @author Morgan Ambourn
 * @author Eric Marcanio
 */
public class Main {
    //TODO test if IMax updates
    //TODO Test Size & Height
    public static void main(String[] args) {
        Interval interval1 = new Interval(16,21);
        Interval interval2 = new Interval(8,9);
        Interval interval3 = new Interval(5,8);
        Interval interval4 = new Interval(0,3);
        Interval interval5 = new Interval(6,10);
        Interval interval6 = new Interval(15,23);
        Interval interval7 = new Interval(25,30);
        Interval interval8 = new Interval(17,19);
        Interval interval9 = new Interval(26,26);
        Interval interval10 = new Interval(19,20);

        int sorry =0;
        int sorry2 = 0;
        int sorry3 = 0;
        int sorry4 =0;
        Node node1 = new Node(interval1);
        Node node2 = new Node(interval2);
        Node node3 = new Node(interval3);
        Node node4 = new Node(interval4);
        Node node5 = new Node(interval5);
        Node node6 = new Node(interval6);
        Node node7 = new Node(interval7);
        Node node8 = new Node(interval8);
        Node node9 = new Node(interval9);
        Node node10 = new Node(interval10);

        node1.setPriority(8);
        node2.setPriority(10);
        node3.setPriority(17);
        node4.setPriority(21);
        node5.setPriority(20);
        node6.setPriority(100);
        node7.setPriority(11);
        node8.setPriority(13);
        node9.setPriority(12);
        node10.setPriority(17);


        IntervalTreap treap = new IntervalTreap();

        treap.intervalInsert(node1);
        treap.intervalInsert(node2);
        treap.intervalInsert(node3);
        treap.intervalInsert(node4);
        treap.intervalInsert(node5);
        treap.intervalInsert(node6);
        treap.intervalInsert(node7);
        treap.intervalInsert(node8);
        treap.intervalInsert(node9);
        treap.intervalInsert(node10);





        if(node1.getParent() != null){
            sorry = node1.getParent().interval.getLow();
        }
        System.out.println("1. " + node1.priority + " Imax:" + node1.getIMax() +" "+ sorry);
        if(node2.getParent() != null){
             sorry2 = node2.getParent().interval.getLow();
        }
        System.out.println("2. " + node2.priority + " Imax:" + node2.getIMax() +" "+ sorry2);
        if(node3.getParent() != null){
            sorry3 = node3.getParent().interval.getLow();
        }
        System.out.println("3. " + node3.priority + " Imax:" + node3.getIMax() +" "+ sorry3);
        if(node4.getParent() != null){
            sorry4 = node4.getParent().interval.getLow();
        }
        System.out.println("4. " + node4.priority + " Imax:" + node4.getIMax() +" "+ sorry4);


        System.out.println("Root: " + treap.root.interval.getLow());
        treap.intervalDelete(node6);
        treap.intervalDelete(node2);
        treap.intervalDelete(node3);
        treap.intervalDelete(node1);
        treap.intervalDelete(node9);
        treap.intervalDelete(node7);
        System.out.println(node2.getIMax());

        System.out.println();
        System.out.println(treap.getSize());
        System.out.println(treap.getHeight());
        //System.out.println(treap.intervalSearch(interval3).priority);
    }




}
