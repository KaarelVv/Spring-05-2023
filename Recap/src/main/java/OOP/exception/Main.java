package OOP.exception;
// Exception = 	an event that occurs during the execution of a program that,
//				disrupts the normal flow of instructions


import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter a whole number to divide: ");
            int x = scanner.nextInt();
            System.out.println("Enter a whole number to divide by: ");
            int y = scanner.nextInt();

            int z = x / y;

            System.out.println("result: " + z);
        }
        catch(ArithmeticException e) {
            System.out.println("You cant divide by zero! lol");
        }
        catch(InputMismatchException e){
            System.out.println("Please input a number");
        }
        catch (Exception e){
            System.out.println("Something went wrong");
        }
        finally {
            System.out.println("This will be always printed");
            scanner.close();//its always good to close open files
        }

    }
}
