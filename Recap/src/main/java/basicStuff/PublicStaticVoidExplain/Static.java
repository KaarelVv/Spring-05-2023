package basicStuff.PublicStaticVoidExplain;

public class Static {
    /*
    In Java, the static keyword is used to declare a member (i.e., a field, method, or inner class) of a class as a class-level member rather
    than an instance-level member.
    This means that the member is associated with the class itself, rather than with any specific instance of the class.

Here are some key characteristics of static members:

    Static fields are shared among all instances of a class.
    This means that if you change the value of a static field in one instance of a class, the change will be reflected in all other instances of the class.

    Static methods can be called without creating an instance of the class.
    This means that you can call a static method directly on the class itself, rather than on an instance of the class.

    Static classes are used to group related methods together within a class, without requiring an instance of the class to be created.
     */

    private int x;
    private static int y;

    public void setX(int value) {
        x = value;
    }

    public int getX() {
        return x;
    }

    public static void setY(int value) {
        y = value;
    }

    public static int getY() {
        return y;
    }
    /*
    In this code, the x field is an instance-level field, which means that each instance of the MyClass class will have its own separate x field.
    On the other hand, the y field is declared as static, which means that there will only be one y field shared among all instances of the MyClass class.
    The setY and getY methods are also declared as static, which means that they can be called directly on the MyClass class itself,
    rather than on an instance of the class.

    By using the static keyword, you can create class-level members that are shared among all instances of a class,
    and you can call methods without needing to create an instance of the class.
    This can be particularly useful for utility classes or for maintaining a common state across instances of a class.
    However, it's important to use static carefully,
    as it can also introduce potential issues with thread safety and can make code harder to test.
     */
}
