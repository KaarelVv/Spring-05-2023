package basicStuff.ArraysListsMapsSets.LinkedListVsArrayList;

import java.util.ArrayList;
import java.util.LinkedList;
/*
LinkedList and ArrayList are two commonly used data structures in Java. Both are used to store collections of elements,
but they have different characteristics that make them more suitable for different use cases.

LinkedList is a data structure that consists of a sequence of nodes,
where each node contains a reference to the next node in the sequence.
This means that inserting or removing an element in a LinkedList can be done efficiently,
since only the references of the affected nodes need to be updated.
However, accessing an element at a specific index in a LinkedList is less efficient,
since the list needs to be traversed sequentially from the beginning or end to reach the desired element.

ArrayList, on the other hand, is a data structure that stores elements in a contiguous block of memory,
which means that accessing an element at a specific index can be done very efficiently.
However, inserting or removing an element in the middle of an ArrayList requires shifting all the elements that come after it,
which can be expensive if the list is large.

In general, if you need to frequently insert or remove elements from a collection,
LinkedList may be a better choice. On the other hand, if you need to frequently access elements at specific positions in the collection,
or if you know the size of the collection in advance and want to allocate memory accordingly, ArrayList may be a better choice.
 */

public class Main {
    public static void main(String[] args) {


        LinkedList<String>namesLinkedList=new LinkedList<>();
        namesLinkedList.add("Jhon");
        namesLinkedList.add("Joe");
        namesLinkedList.add("Peter");
        namesLinkedList.add("Mark");
        namesLinkedList.add("David");


        ArrayList<String>namesArrayList=new ArrayList<>();
        namesArrayList.add("Jhon");
        namesArrayList.add("Joe");
        namesArrayList.add("Peter");
        namesArrayList.add("Mark");
        namesArrayList.add("David");
    }
}
