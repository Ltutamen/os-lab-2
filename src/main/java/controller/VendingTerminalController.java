package controller;

import controller.interfaces.MailBoxTerminalInterface;
import model.dto.BuyAttemptDTO;
import model.dto.BuyResponseDTO;
import view.TerminalView;

import java.util.Optional;

public class VendingTerminalController {
    private final MailBoxTerminalInterface mailBox;
    private final TerminalView view = new TerminalView();

    public VendingTerminalController(MailBoxTerminalInterface mailBox) {
        this.mailBox = mailBox;
    }

    public synchronized void attemptToBuy(BuyAttemptDTO attemptDTO) {
        attemptDTO.setTerminal(this);
        mailBox.addBuyAttempt(attemptDTO);
    }

    public synchronized void processAttemptAnswer() {
        if(mailBox.hasBuyResponse(this)) {
            Optional<BuyResponseDTO> result = mailBox.getBuyResponse(this);

            result.ifPresent(view::output);
        }
    }
}
