package Interval_Treap;

public class Main {

    public static void main(String[] args) {
        Interval interval1 = new Interval(5,7);
        Interval interval2 = new Interval(3,4);
        Interval interval3 = new Interval(1,4);
        Interval interval4 = new Interval(8,10);

        Node node1 = new Node(interval1);
        Node node2 = new Node(interval2);
        Node node3 = new Node(interval3);
        Node node4 = new Node(interval4);


        IntervalTreap treap = new IntervalTreap();

        treap.intervalInsert(node1);
        treap.intervalInsert(node2);
        //treap.intervalInsert(node3);
        //treap.intervalInsert(node4);
        System.out.println(treap.getRoot().imax);
    }




}
