package OOP.Interface;

public class Fish implements Prey,Predator{


    @Override
    public void hunt() {
        System.out.println("There is always a bigger fish");
    }

    @Override
    public void flee() {
        System.out.println("Today im not a bigger fish");

    }
}
