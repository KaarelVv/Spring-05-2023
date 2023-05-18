package basicStuff.thiS;

public class Dog {
    private String name;
    private int age;


    public void setName(String name) {
        this.name = name; //It refers to the name what is in the parameter.Otherwise, it would refer to the field
    }

    public String getName() {
        return this.name;
    }

    public void bark(){
        System.out.println("Bark!");
    }
}
