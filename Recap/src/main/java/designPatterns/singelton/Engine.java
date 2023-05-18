package designPatterns.singelton;

public class Engine {
    private static Engine instance = null;
    private boolean isEngineWorking;

    private Engine() {
        // private constructor to prevent instantiation from outside the class
        isEngineWorking = false;
    }

    public static Engine getInstance() {
        if (instance == null) {
            instance = new Engine();
        }
        return instance;
    }

    public boolean isEngineWorking() {
        return isEngineWorking;
    }

    public void setEngineWorking(boolean isEngineWorking) {
        this.isEngineWorking = isEngineWorking;
    }
}