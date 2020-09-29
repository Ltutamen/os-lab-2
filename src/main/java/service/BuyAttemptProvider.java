package service;

import model.TicketToBuy;
import model.dto.BuyAttemptDTO;

import java.util.Random;

public class BuyAttemptProvider {
    private final Random random;

    public BuyAttemptProvider() {
        this.random = new Random();
    }

    public BuyAttemptDTO getBuyAttemptDTO() {
        TicketToBuy ticket = getRandomTicketToBuy(random);

        return new BuyAttemptDTO(ticket, 100);
    }

    private static TicketToBuy getRandomTicketToBuy(Random random) {
        int ordinal = random.nextInt(TicketToBuy.values().length);
        return TicketToBuy.values()[ordinal];
    }
}
