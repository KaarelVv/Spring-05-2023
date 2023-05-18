package designPatterns.builder;


/*What is a Builder Pattern?
-Pattern used to create objects made from a bunch of other objects
    -When you want to build an object made up from other objects
    -when you want the creation of these parts to be independent of the main object
    -Hide the creation of the part from the client so both aren't dependent
    -The builder knows the specifics and nobody else does
*/

import designPatterns.builder.robot.OldRobotBuilder;
import designPatterns.builder.robot.Robot;
import designPatterns.builder.robot.RobotBuilder;
import designPatterns.builder.robot.RobotEngineer;
import designPatterns.builder.user.User;
import designPatterns.builder.user.UserBuilder;

public class Main {
    public static void main(String[] args) {

        RobotBuilder oldStyleRobot = new OldRobotBuilder();

        RobotEngineer robotEngineer = new RobotEngineer(oldStyleRobot);

        robotEngineer.makeRobot();

        Robot firstRobot = robotEngineer.getRobot();

        System.out.println("Robot Built");
        System.out.println("Robot Head Type " + firstRobot.getRobotHead());
        System.out.println("Robot Head Type " + firstRobot.getRobotTorso());
        System.out.println("Robot Head Type " + firstRobot.getRobotArms());
        System.out.println("Robot Head Type " + firstRobot.getRobotLegs());



        User human1 = new UserBuilder()
                    .addAge(20)
                    .addEmail("asd@com")
                    .build();

        System.out.println(human1.getAge());
        System.out.println(human1.getEmail());


    }
}
