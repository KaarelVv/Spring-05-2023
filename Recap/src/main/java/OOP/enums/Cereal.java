package OOP.enums;

public enum Cereal {

    FROOT_LOOPS(10,4.55),
    CAPTAIN_CRUNCH(50,10.65),
    KELLOGS(90,8.99),
    REESES_CUPS(75, 5.66);

    final int levelOfDeliciousness; //you want usually make those fields final so no on cant change them
    final double price;
    Cereal(int levelOfDeliciousness,double price){
        this.levelOfDeliciousness = levelOfDeliciousness;
        this.price = price;
    }
}
