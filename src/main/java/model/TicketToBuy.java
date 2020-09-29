package model;

public enum TicketToBuy {
    TO_KYIV(28),
    TO_MOSCOW(37),
    TO_LONDON(50),
    TO_BERLIN(77),
    TO_PARIS(91)
    ;

    private final int priceInKops;

    TicketToBuy(int priceInKops) {
        this.priceInKops = priceInKops;
    }

    public int getPriceInKops() {
        return priceInKops;
    }
}
