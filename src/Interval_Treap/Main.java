package Interval_Treap;

public class Main {
    //TODO test if IMax updates
    public static void main(String[] args) {
        Interval interval1 = new Interval(5,7);
        Interval interval2 = new Interval(3,4);
        Interval interval3 = new Interval(1,4);
        Interval interval4 = new Interval(8,10);
        Interval interval5 = new Interval(8,10);
        Interval interval6 = new Interval(8,10);
        Interval interval7 = new Interval(8,10);
        Interval interval8 = new Interval(8,10);
        Interval interval9 = new Interval(8,10);
        Interval interval10 = new Interval(8,10);
        Interval interval11 = new Interval(8,10);
        int sorry =0;
        int sorry2 = 0;
        int sorry3 = 0;
        int sorry4 =0;
        Node node1 = new Node(interval1);
        Node node2 = new Node(interval2);
        Node node3 = new Node(interval3);
        Node node4 = new Node(interval4);
        Node node5 = new Node(interval4);
        Node node6 = new Node(interval4);
        Node node7 = new Node(interval4);
        Node node8 = new Node(interval4);
        Node node9 = new Node(interval4);
        Node node10 = new Node(interval4);
        Node node11 = new Node(interval4);


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
        treap.intervalInsert(node11);


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
        System.out.println(treap.getSize());
    }




}
