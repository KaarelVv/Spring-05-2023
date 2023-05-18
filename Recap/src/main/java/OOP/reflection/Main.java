package OOP.reflection;
/*
In Java, reflections are a powerful feature that allows developers to examine, introspect,
and modify objects and classes at runtime.
Reflections are useful in cases where it is necessary to access or modify the internal structure of objects,
which cannot be done using standard Java language features.

One of the most common uses of reflection in Java is to instantiate objects dynamically.
Reflection allows developers to create objects at runtime without knowing their exact type at compile-time.
For example, using reflection, developers can create instances of classes based on user input or configuration files.

Reflections are also useful for creating generic code that can work with objects of any type.
By using reflection, developers can examine and manipulate the properties of objects, regardless of their type.
This is particularly useful for libraries and frameworks that need to work with a variety of different types.

Another use of reflection in Java is to access private fields, methods, and constructors of classes.
By using reflection, developers can bypass the access restrictions imposed by the Java language and modify the internal state of objects.
While this is a powerful feature, it should be used with caution, as it can lead to security vulnerabilities and other issues.


In summary, reflections are a powerful feature in Java that allow developers to examine, introspect, and modify objects and classes at runtime.
While they can be very useful in certain situations, they should be used with care,
as they can introduce security vulnerabilities and other problems if not used correctly.
 */

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) {


        Cat myCat = new Cat("Stella",6);

        Field[] myCatFields = myCat.getClass().getDeclaredFields();

        for (Field field:myCatFields){
            System.out.println(field.getName());
        }

    }
}
