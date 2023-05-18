package basicStuff.ArraysListsMapsSets.SetsVsHashSets;
/*
In Java, the Set interface is defined in the java.util package,
and there are several implementations available, including HashSet, TreeSet, and LinkedHashSet.

The HashSet class implements the Set interface using a hash table to store its elements.
It provides constant time complexity for the basic operations like adding, removing, and searching for elements.
However, because HashSet is not ordered, there is no guarantee on the order in which the elements will be stored.
 */

import java.util.HashSet;
import java.util.Set;

public class SetsAndHashsets {

    public static void main(String[] args) {

        Set<String>names = new HashSet<>();

        names.add("John");
        names.add("Jessy");
        names.add("Salt");
        names.add("Mike");
        names.add("Carl");
        names.add("John");// Hashset doest allow duplicates. It wont be printed out.


        System.out.println(names);
    }
}
