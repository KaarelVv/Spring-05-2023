package basicStuff.PublicStaticVoidExplain;

public class Protected {
    /*
        -protected  is an access modifier that is used to provide access to class members
        (i.e., fields, methods, and inner classes) within the same package or
        in a subclass (i.e., a class that extends the class that declares the protected member).
     */
        protected int x;

        public void setX(int value) {
            x = value;
        }

        public int getX() {
            return x;
        }
        /*
        In this code, the x field is declared as protected,
        which means that it can be accessed by any class within the mypackage package,
        as well as by any subclass of MyClass, regardless of its package.

        By using the protected keyword, you can provide access to certain class members to specific subclasses or classes within the same package,
        while still maintaining some level of encapsulation.
        It can be particularly useful in large codebases where different parts of the code may need to interact with each other,
        but you want to limit access to certain parts to maintain a clean and organized codebase.
         */
    }

