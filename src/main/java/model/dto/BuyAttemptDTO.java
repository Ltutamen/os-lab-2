package model.dto;

import controller.VendingTerminalController;
import model.TicketToBuy;

public class BuyAttemptDTO {
    public VendingTerminalController terminal;
    public final TicketToBuy ticket;
    public final int inputCoinAmount;

    public BuyAttemptDTO(TicketToBuy ticket, int inputCoinAmount) {
        this.ticket = ticket;
        this.inputCoinAmount = inputCoinAmount;
    }

    public void setTerminal(VendingTerminalController controller) {
        this.terminal = controller;
    }
}
