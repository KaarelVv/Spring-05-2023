package basicStuff.constructors;

public class Dog {

    String name;
    int age;

    public Dog(){} //This is empty constructor(default no-args). It will be created by default when there is no other constructor is present.
                        //And it's not visible.
    public Dog(String name) { //This constructor is only meant for name parameter
        this.name = name; //You can replace name with real name, but then you cant customize it

    }

    public Dog(int age)  { //This constructor is only meant for age
        this.age = age;
    }

    public Dog(String name, int age) { //This have two parameters
        this.name = name;
        this.age = age;
    }

    //Remember that if you dont create constructor java will do it by default and it doesnt have parameters
}

