package OOP.InputOutput;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        String[] names = {"John","Mark","David","Rose"};

        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Java IO location\\Output.txt"));
        writer.write("Writing first line.");
        writer.write("\nWriting second line.");
        for (String loopingnames:names){  //This enchased for loop writes all the names provided by array called names with provided string values
            writer.write("\n" + loopingnames);
        }
        writer.close();

        BufferedReader reader = new BufferedReader(new FileReader("C:\\Java IO location\\Output.txt"));
        String line;
        while((line = reader.readLine())!= null){    //This while loop writes all the file what haves string on that line until there is no strings (no line equals null)
            System.out.println(line);
        }
        //System.out.println(reader.readLine()); //reads single line
        reader.close();


    }
}
