package OOP.enums;

/*In Java, an enum (short for "enumeration") is a special data type that allows you to define a fixed set of constants.
Enums are often used to represent a set of related values that are not likely to change,
such as the days of the week, the months of the year, or the different types of cards in a deck of cards.

To define an enum in Java, you use the "enum" keyword followed by the name of the enum and a list of its possible values

 */

public class Main {
    public static void main(String[] args) {

        DaysOfTheWeek day = DaysOfTheWeek.MONDAY;

        if (day == DaysOfTheWeek.MONDAY){
            System.out.println("Almost tuesday");
        }

        for (DaysOfTheWeek myDay:DaysOfTheWeek.values()){
            System.out.println(myDay);
        }

        System.out.println("The good cereal is " + Cereal.FROOT_LOOPS + " with the tastiness of "+ Cereal.FROOT_LOOPS.levelOfDeliciousness + " with the price of " + Cereal.FROOT_LOOPS.price);

    }
}
