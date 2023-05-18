package basicStuff.ArraysListsMapsSets.ArrayVsArrayList;

import java.util.ArrayList;
import java.util.Arrays;
/*
    An array and an ArrayList are both used to store collections of elements in Java, but there are some differences between them.

    An array is a fixed-size data structure that stores a collection of elements of the same type.
    The size of the array must be specified at the time of declaration and cannot be changed during runtime.
    Arrays are accessed using an index, which starts at 0 and ends at length-1.

    An ArrayList, on the other hand, is a dynamic data structure that can grow or shrink as needed.
    It is part of the Java Collections Framework and provides more flexibility than arrays.
    An ArrayList can store a collection of elements of any type, and the size can be changed at runtime.
    Elements are added to the ArrayList using the add() method and accessed using an index just like an array.

    Here are some key differences between arrays and ArrayLists:

        Size: Arrays have a fixed size, while ArrayLists can grow or shrink as needed.

        Type: Arrays can only store elements of the same type, while ArrayLists can store elements of any type.

        Performance: Arrays offer better performance when it comes to accessing elements since they are indexed.
        ArrayLists have additional overhead associated with dynamically managing their size.

        Memory usage:
        Arrays can be more memory efficient than ArrayLists, as ArrayLists require additional memory to manage their dynamic resizing.

    In general, if you know the size of the collection you need to store and the type of elements you need to store,
    an array is a good choice. If you need to store a collection of elements of varying types or need to dynamically add or
    remove elements from the collection, an ArrayList is a better choice.
 */

public class MainTest {

    public static void main(String[] args) {


        String[] friendsArray = new String[4];  //Regular arrays need to have size but can be used for primitive datatypes and also objects// This array have 5 elements and values are null bc they are not provided

                                //  0         1        2      3      4
        String[] friendsArray2 = {"Jhon","Charlie","David","Joe","Martin"};  //This array have 5 elements with values which are names

        ArrayList<String> friendsArrayList = new ArrayList<>();  //arrayList do not have a fixed size(grows and shrinks automatically)
        ArrayList<String> friendsArrayList2 = new ArrayList<>(Arrays.asList("Jhon","Charlie","David","Joe","Martin")); //Be aware that arraylist can only hold objects

        //Printing individual elements
        System.out.println(friendsArray2[1]);//prints charlie
        System.out.println(friendsArrayList2.get(3));//prints David.

        //How to get size
        System.out.println(friendsArray2.length);
        System.out.println(friendsArrayList2.size());

        //How to add elements
        System.out.println(friendsArrayList2.add("Mitch")); // Adding value to the ArrayList. You cant do that to Array bc there is a fixed size
        System.out.println(friendsArrayList2);

        //How to set element in both
        friendsArray2[1]="Jimmy";
        System.out.println(friendsArray2[1]); //Prints out Jimmy
        friendsArrayList2.set(1,"Michael");
        System.out.println(friendsArrayList2.get(1)); //Prints out Micheal

        //Remove an element
        //Cant to this with arrays...
        friendsArrayList2.remove(0);//Removes first element

        //How to print out whole list
        System.out.println(friendsArray2); //Doesnt give actual values. Only memory address. Only way to get Array list is using for loop or something
        for (String names:friendsArray2){
            System.out.println(names);
        }

        System.out.println(friendsArrayList2); //Actual strings (list)






    }
}
