package codingWithMoshExampleOOP;

public class TextBox extends UIControl {
    private String text= ""; //Field. The reason why we declared this as a empty string bc in default it is NULL.
                            // And in main method when one wants to use uppercase method it will give an exception.
//    public TextBox() {
//        super(true);
//        System.out.println("TextBox");
//    }


    @Override
    public void render() {
        System.out.println("Render TextBox");
    }

    @Override
    public String toString(){
        return text;
    }

    public void setText(String text){    //this is method
        this.text=text;     //this keyword uses declared field
    }
    public void clear(){    //method
        text="";            //method behaviour is to clear text ie changes text to empty string what is declared in the field
    }
}
