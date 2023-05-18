package codingWithMoshExampleOOP;

public class Employee {
    private int baseSalary; //this is a instance member it belongs to the object.
    private int hourlyRate;


    public Employee(int baseSalary, int hourlyRate){  // Constructor. Its a special method to create a new object
        setBaseSalary(baseSalary);  //We use setSalary method bc we have certain values we need to set and also exception if there is inputted wrong values+ int the parenthesis is value.
        setHourlyRate(hourlyRate);
        //this.baseSalary = baseSalary; The reason why we don't do it here at the moment bc we dont want to set values to 0 or negative number!
        //this.setHourlyRate = setHourlyRate; This way we can set values whatever we want.
    }
    public Employee(int baseSalary) { //Go over constructor overloading once again
        this(baseSalary,0);
    }


    public int calculateWage(int extraHours){
        return baseSalary + (hourlyRate * extraHours);
    }

    public int calculateWage(){ //this is method overloading. It doesnt have parameters and we can call it without parameters and still does the job
        return calculateWage(0) ; // but this parameter is already defined  and cannot be changed. But It's not ideally recommended
    }

    private void setBaseSalary(int baseSalary){

        if(baseSalary<= 0)
            throw new IllegalArgumentException("Salary cannot be 0 or less");
        this.baseSalary = baseSalary;
    }
    private int getBaseSalary(){ //with this method we can get base salary if needed.
        return baseSalary;
    }
    private void setHourlyRate(int hourlyRate) {
        if(hourlyRate < 0)
            throw new IllegalArgumentException("Hourly rate cannot be negative");
        this.hourlyRate = hourlyRate;
    }
    private int getHourlyRate() {
        return hourlyRate;
    }
}
