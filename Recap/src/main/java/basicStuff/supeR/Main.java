package basicStuff.supeR;

public class Main {

    public static void main(String[] args) {


        Cat myCat = new Cat(5,"Bella","meat");

        myCat.makeNoise();

        System.out.println("Cat age is " + myCat.age + " and the name is " + myCat.name + " and likes to eat only " + myCat.catFoodPreferences + "!");
    }


}
