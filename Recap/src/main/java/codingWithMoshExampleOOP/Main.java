package codingWithMoshExampleOOP;

import codingWithMoshExampleOOP.taxCalculator.TaxCalculator2018;
import codingWithMoshExampleOOP.taxCalculator.TaxCalculator2019;
import codingWithMoshExampleOOP.taxCalculator.TaxReport;

public class Main {

    public static void main(String[] args) {
        /* declaring new variable
        var textBox = new TextBox(); // new instance of the class
//      type  name        instance

        textBox.setText("Box1");
        System.out.println(textBox.text.toUpperCase());

        var textBox1 = new TextBox(); //new instance
        textBox1.setText("Box2");
        System.out.println(textBox1.text);
        //we have two different object  using the same class!!!!!!!!!!!!!!!!!!!!!!!!!!!

        var employee = new Employee(50_000,20);
        int wage = employee.calculateWage(2);
        int wage1 =employee.calculateWage();
        System.out.println(wage1);
        // employee. we can only call one method .calculateWage. This is basics of abstractions. Only need to show what's necessary*/

//        var textbox = new TextBox();
//        textbox.setText("Hello World");
//        System.out.println(textbox);
//
//        var point1 = new Point(1, 2);
//       var point2 = new Point(1, 2);
//        System.out.println(point1.equals(point2));

        UIControl [] controls = {new TextBox(), new CheckBox()};
        for (var control : controls)
            control.render();




    }
//    public static void shot(UIControl control){
//        System.out.println(control);
//    }


}
