package model;

public enum Coin{
    coin1(1),
    coin2(2),
    coin5(5),
    coin10(10),
    coin25(25),
    coin50(50)
    ;

    public final int value;

    Coin(int value) {
        this.value = value;
    }
}
