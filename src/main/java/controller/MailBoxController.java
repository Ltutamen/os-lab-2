package controller;

import controller.interfaces.MailBoxMachineInterface;
import controller.interfaces.MailBoxTerminalInterface;
import model.dto.BuyAttemptDTO;
import model.dto.BuyResponseDTO;

import java.util.*;

public class MailBoxController implements MailBoxTerminalInterface, MailBoxMachineInterface {
    private Queue<BuyAttemptDTO> buyAttempts = new LinkedList<>();
    private Map<VendingTerminalController, Set<BuyResponseDTO>> responses = new HashMap<>();

    @Override
    public synchronized boolean hasNextBuyAttempt() {
        return !buyAttempts.isEmpty();
    }

    @Override
    public synchronized Optional<BuyAttemptDTO> getBuyAttempt() {
        BuyAttemptDTO buyAttempt = buyAttempts.isEmpty() ? null : buyAttempts.iterator().next();
        buyAttempts.remove(buyAttempt);

        return Optional.ofNullable(buyAttempt);
    }

    @Override
    public synchronized void addBuyAttempt(BuyAttemptDTO buyAttempt) {
        buyAttempts.add(buyAttempt);
    }

    @Override
    public synchronized Optional<BuyResponseDTO> getBuyResponse(VendingTerminalController terminal) {
        Set<BuyResponseDTO> responseDTOS = responses.get(terminal);
        BuyResponseDTO responseDTO = responseDTOS.size() > 0 ? responseDTOS.iterator().next() : null;
        responseDTOS.remove(responseDTO);
        return Optional.ofNullable(responseDTO);
    }

    @Override
    public synchronized boolean hasBuyResponse(VendingTerminalController forTerminal) {

        Set<BuyResponseDTO> responseSet = responses.get(forTerminal);

        return responseSet != null && !responseSet.isEmpty();
    }

    @Override
    public synchronized  void addBuyResponse(BuyResponseDTO response) {
        Set<BuyResponseDTO> set = responses.get(response.buyAttempt.terminal);
        if(set == null) {
            set = new HashSet<>();
            responses.put(response.buyAttempt.terminal, new HashSet<>());
        }
        set.add(response);
    }
}
