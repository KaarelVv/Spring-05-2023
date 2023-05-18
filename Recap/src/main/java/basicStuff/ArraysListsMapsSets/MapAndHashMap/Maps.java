package basicStuff.ArraysListsMapsSets.MapAndHashMap;

import java.util.HashMap;
/*
In Java, both Map and HashMap are used to represent a collection of key-value pairs,
but they have some differences.

Map is an interface in Java that defines the basic operations for working with key-value pairs.
It has several implementations such as HashMap, TreeMap, and LinkedHashMap.
The choice of implementation depends on the specific use case and requirements.

HashMap is one of the most commonly used implementations of Map interface.
It is a hash table-based implementation that provides constant-time performance for the basic operations (insertion,
deletion, and retrieval) on average, assuming a good hash function and uniform distribution of keys.

Some of the differences between Map and HashMap are:

    Map is an interface, while HashMap is a class that implements the Map interface.
    Map defines the basic operations for working with key-value pairs,
        while HashMap provides a hash table-based implementation of those operations.
    HashMap allows null values and null keys,
        while some other implementations of Map do not allow null keys (such as TreeMap).
    HashMap is not thread-safe by default, which means it may cause issues in multi-threaded environments.
        If thread-safety is required, you can use ConcurrentHashMap instead.
    HashMap provides constant-time performance (O(1)) for basic operations on average,

 */
public class Maps {

    public static void main(String[] args) {

        HashMap<String,Integer> empIds = new HashMap<>();   //With this hashmap we link Keys to values ie Name to ID. You cannot use primitive datatype

        empIds.put("Kaarel",12345);
        empIds.put("Jhon",54321);
        empIds.put("Luke",13689);

        System.out.println(empIds);

        System.out.println(empIds.get("Kaarel"));

        System.out.println(empIds.containsKey("Kaarel")); //Gives true bc its exist in the map
        System.out.println(empIds.containsValue(123));   // Gives false bc there is no such value.
        System.out.println(empIds.containsKey("David")); //gives false bc there is no such key

        empIds.put("Jhon",998877);  //Changes Jhon(key) value to 998877
        System.out.println(empIds);



    }
}
