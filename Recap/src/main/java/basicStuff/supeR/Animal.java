package basicStuff.supeR;

public class Animal {

    int age;
    String name;

    public Animal(){}

    public Animal(int age, String name){
        this.age = age;
        this.name = name;
    }

    public void makeNoise(){
        System.out.println("Hello, Im am an animal");
    }

    public void eat(){
        System.out.println("Nom nom");
    }
}
