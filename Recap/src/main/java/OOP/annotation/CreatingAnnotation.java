package OOP.annotation;
/* Annotation = a form of metadata that provides additional information about classes, methods, variables, and other program elements.
    -Annotations are a way to associate information with program elements in a declarative way.
    -Annotations are defined using the "@" symbol, followed by the annotation name, and can include one or more parameters.
    -For example, the @Override annotation is used to indicate that a method is intended to override a superclass method.
 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CreatingAnnotation {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

               
            Cat myCat = new Cat("Stella");
            Dog myDog = new Dog("Jerry");

            if(myDog.getClass().isAnnotationPresent(VeryImportant.class)){
                System.out.println("This thing is very important");
            }else {
                System.out.println("This thing is not very important");
            }

            for (Method method : myCat.getClass().getDeclaredMethods()){
                if (method.isAnnotationPresent(RunImmediately.class)){
                    RunImmediately annotation = method.getAnnotation(RunImmediately.class);

                    for (int i = 0; i < annotation.times();i++){
                        method.invoke(myCat);
                    }

                    method.invoke(myCat);
                }
            }
        }
    }


