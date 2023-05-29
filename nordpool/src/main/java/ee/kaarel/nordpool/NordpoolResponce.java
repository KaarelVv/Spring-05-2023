package ee.kaarel.nordpool;

import lombok.Data;

import java.util.ArrayList;
//When the @Data annotation is applied to a class, Lombok automatically generates the following methods:
//
//    Getters: Get methods for all non-static fields of the class.
//    Setters: Set methods for all non-final and non-static fields of the class.
//    toString(): A string representation of the object, including all its fields.
//    equals(): An equality comparison method based on the object's fields.
//    hashCode(): A method that generates a hash code based on the object's fields.

@Data
public class NordpoolResponce {
    public boolean success;
    public Andmed data;
}
@Data
 class Andmed{
    private ArrayList<TimestampPrice> ee;
    private ArrayList<TimestampPrice> fi;
    private ArrayList<TimestampPrice> lv;
    private ArrayList<TimestampPrice> lt;
}
@Data
class TimestampPrice{
    public int timestamp;
    public double price;
}


