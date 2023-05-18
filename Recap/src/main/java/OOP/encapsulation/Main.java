package OOP.encapsulation;

// Encapsulation = 	attributes of a class will be hidden or private,
//					Can be accessed only through methods (getters & setters)
//					You should make attributes private if you don't have a reason to make them public/protected


public class Main {
    public static void main(String[] args) {
        Car car = new Car("BMW","3series",2005);

        System.out.println(car.getMake());

        car.setMake("4series");
        System.out.println(car.getMake());
    }

}
