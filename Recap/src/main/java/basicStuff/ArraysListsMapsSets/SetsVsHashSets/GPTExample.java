package basicStuff.ArraysListsMapsSets.SetsVsHashSets;

import java.util.HashSet;

public class GPTExample {
    public static void main(String[] args) {

        HashSet<String> set = new HashSet<>();

        // Adding elements to the set
        set.add("apple");
        set.add("banana");
        set.add("orange");
        set.add("banana");  // duplicate element, not added

        // Printing the set
        System.out.println(set);
    }
}
