package controller.interfaces;

import controller.VendingTerminalController;
import model.dto.BuyAttemptDTO;
import model.dto.BuyResponseDTO;

import java.util.Optional;

public interface MailBoxTerminalInterface {
    void addBuyAttempt(BuyAttemptDTO buyAttemptDTO);

    boolean hasBuyResponse(VendingTerminalController forTerminal);

    Optional<BuyResponseDTO> getBuyResponse(VendingTerminalController forTerminal);
}
