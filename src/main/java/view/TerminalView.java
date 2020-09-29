package view;

import model.dto.BuyResponseDTO;

public class TerminalView {

    public void output(BuyResponseDTO response) {
        if(response.isSuccessful()) {
            giveChangeOutput(response);
        }
        else {
            noChangeOutput(response);
        }
    }

    private void noChangeOutput(BuyResponseDTO response) {
        System.out.println("not enough change to give change:" + response.buyAttempt.ticket);
    }

    private void giveChangeOutput(BuyResponseDTO response) {
        System.out.println("your ticket: " + response.buyAttempt.ticket);
        System.out.println("and your change: " + response.getChange());

    }

}
