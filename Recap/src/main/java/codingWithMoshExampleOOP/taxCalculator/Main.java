package codingWithMoshExampleOOP.taxCalculator;

public class Main {
    public static void main(String[] args) {


        var calculator = new TaxCalculator2018(500000); // you can change var to TaxCalculator2018.Same thing
//        TaxReport report = new TaxReport(calculator);
//
//        report.show();
//
//        report.setCalculator(new TaxCalculator2019(1_000_000));
//        report.show();

        //method injection
        var report = new TaxReport();
        report.show(calculator);
        report.show(new TaxCalculator2019(200_000));


    }
}
