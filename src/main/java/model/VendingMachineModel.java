package model;

import java.text.CollationElementIterator;
import java.util.Collection;
import java.util.HashMap;

public class VendingMachineModel {

    private HashMap<Coin, Integer> coinLeftAmount = new HashMap<>();
    private int hryvnaCoins = 0;


    public VendingMachineModel() {
        this.coinLeftAmount.put(Coin.coin1, 50);
        this.coinLeftAmount.put(Coin.coin2, 25);
        this.coinLeftAmount.put(Coin.coin5, 20);
        this.coinLeftAmount.put(Coin.coin10, 15);
        this.coinLeftAmount.put(Coin.coin25, 10);
        this.coinLeftAmount.put(Coin.coin50, 5);
    }

    public void addHryvna() {
        hryvnaCoins++;
    }

    public int getHryvnaCoinsAmount(Coin coin) {
        return coinLeftAmount.get(coin);
    }

    public boolean hasAnyMoreCoins(Coin coin) {
        return coinLeftAmount.get(coin) > 0;
    }

    public void getCoin(Coin coin) {
        int coinsLeft = coinLeftAmount.get(coin);
        if(coinsLeft > 0) {
            coinLeftAmount.put(coin, --coinsLeft);
            return;
        }
        throw new IllegalStateException("cant retain more coins: <" + coin + ">");
    }

    public void addAllCoins(Collection<Coin> coins) {
        for (Coin coin : coins) {
            int previous = coinLeftAmount.get(coin);
            coinLeftAmount.put(coin, previous + 1);
        }
    }




}
