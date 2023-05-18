package codingWithMoshExampleOOP.taxCalculator;

public class TaxCalculator2018 implements TaxCalculator {
    private double taxableIncome;

    public TaxCalculator2018(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }
    @Override //implementing method from interface
    public double calculateTax(){
        return taxableIncome * 0.3;
    }
}
