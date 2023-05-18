package basicStuff.constructors;
/*In Java, a constructor is a special method that is used to initialize objects of a class.
It has the same name as the class and does not have a return type (not even void).

When an object of a class is created, the constructor is automatically called.
The constructor is responsible for initializing the instance variables of the object.
If a constructor is not explicitly defined in a class, Java automatically provides a default constructor that takes no arguments and does nothing.

There are two types of constructors in Java:

-Default constructor: As mentioned above, this constructor is automatically provided by Java if no constructor is defined in the class.
It takes no arguments and does nothing.

-Parameterized constructor: This constructor takes one or more arguments and initializes the instance variables of the object with the values passed as arguments.
*/

import basicStuff.constructors.Dog;

public class ConstructorMain {
    public static void main(String[] args) {
        Dog myDog= new Dog("Jerry",8);

        System.out.println("New dog name is " + myDog.name+" and age is " + myDog.age);

    }
}
