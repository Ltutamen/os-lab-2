package model.dto;

import model.Coin;

import java.util.HashMap;
import java.util.Map;

public class BuyResponseDTO {
    public final BuyAttemptDTO buyAttempt;

    private final boolean isSuccessful;

    private Map<Coin, Integer> change;

    public BuyResponseDTO(BuyAttemptDTO buyAttempt, boolean isSuccessful) {
        this.buyAttempt = buyAttempt;
        this.isSuccessful = isSuccessful;
    }

    public void setChange(Coin coin, int amount) {
        if(change == null) {
            change = new HashMap<>();
        }

        change.put(coin, amount);
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public Map<Coin, Integer> getChange() {
        return new HashMap<>(change);
    }
}
