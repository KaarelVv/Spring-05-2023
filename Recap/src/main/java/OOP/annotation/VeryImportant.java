package OOP.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // TYPE is only used for class(you can change it to  method etc)
// and with {} you can add multiple element types
@Retention(RetentionPolicy.RUNTIME) // You prolly use it 99% of time
public @interface VeryImportant {
}
