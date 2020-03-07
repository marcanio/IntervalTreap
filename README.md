# Interval Treaps
//TODO

## Interval
* *int getLow()* - Returns the low endpoint of the interval.
* *int getHigh()* - Returns the high endpoint of the interval.

## Node
* *Node getParent* - Returns the parent of this node.
* *Node getLeft()* - Returns the left child of this node.
* *Node getRight()* - Returns the right child of this node.
* *Interval getInterv()* - Returns the interval object stored in this node.
* *int getIMax()* - Returns the value of the imax field of this node.
* *int getPriority()* - Returns the priority of this node.

## Interval Treap
* *Treap()*- Constructor with no parameters.
* *Node getRoot()* - Returns a reference to the root node.
* *int getSize()*- Returns the number of nodes in the treap.
* *int getHeight()* - Returns the height of the treap.
* *void intervalInsert(Node z)* adds node z, whose interv attribute references an
Interval object, to the interval treap. This operation must maintain the required interval
treap properties. The expected running time of this method should be O(log n) on an
n-node interval treap.
* *void intervalDelete(Node z)* - removes node z from the interval treap. This operation
must maintain the required interval treap properties. The expected running time of this
method should be O(log n) on an n-node interval treap.
* *Node intervalSearch(Interval i)* returns a reference to a node x in the interval
treap such that x.interv overlaps interval i, or null if no such element is in the treap.
This method must not modify the interval treap. The expected running time of this method
should be O(log n) on an n-node interval treap.




