package codingWithMoshExampleOOP;

public abstract class UIControl { //Base/Super/Parent class

    private boolean isEnabled = true;

//    public UIControl(boolean isEnabled) {
//        this.isEnabled = isEnabled;
//        System.out.println("UI Control");
//    }
    public abstract void render();



    public void enabled(){
        isEnabled=true;
    }
    public void disabled(){
        isEnabled=false;
    }

    public boolean isEnabled(){
        return isEnabled;
    }

}
