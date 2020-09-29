package controller.interfaces;

import model.dto.BuyAttemptDTO;
import model.dto.BuyResponseDTO;

import java.util.Optional;

public interface MailBoxMachineInterface {
    boolean hasNextBuyAttempt();

    Optional<BuyAttemptDTO> getBuyAttempt();

    void addBuyResponse(BuyResponseDTO response);
}
