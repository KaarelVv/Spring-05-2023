package OOP.Interface;
/*
In Java, an interface is a collection of abstract methods that are declared but not implemented.
An interface is essentially a contract that specifies what a class implementing the interface should do,
but not how it should do it.

Java interfaces can also have constants and static methods, but cannot have instance variables or instance methods with an implementation.
They are useful for creating a set of behaviors that multiple classes can implement, allowing for polymorphism and flexible programming.
 */

public class Main {
    public static void main(String[] args) {
        Rabbit rabbit = new Rabbit();
        Hawk hawk = new Hawk();
        Fish fish = new Fish();

        rabbit.flee();

        hawk.hunt();

        fish.flee();

        fish.hunt();
    }



}
