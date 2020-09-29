package controller;

import controller.interfaces.MailBoxMachineInterface;
import model.ApplicationModel;
import model.Coin;
import model.VendingMachineModel;
import model.dto.BuyAttemptDTO;
import model.dto.BuyResponseDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class VendingMachineController implements Runnable {

    private final MailBoxMachineInterface mailBox;
    private final ApplicationModel applicationModel;
    private final VendingMachineModel vendingMachineModel = new VendingMachineModel();

    public VendingMachineController(MailBoxMachineInterface mailBox, ApplicationModel applicationModel) {
        this.mailBox = mailBox;
        this.applicationModel = applicationModel;
    }

    public void run() {
        while (applicationModel.isRunning()) {
            if(mailBox.hasNextBuyAttempt()) {
                BuyAttemptDTO attempt = mailBox.getBuyAttempt().orElseThrow(IllegalStateException::new);
                List<Coin> changeCoins = new ArrayList<>();

                int priceToBePaid = attempt.ticket.getPriceInKops();
                Coin[] orderedCoins = Coin.values();
                Arrays.sort(orderedCoins, (c1, c2) -> c2.value - c1.value);
                for (Coin coin : orderedCoins) {
                    while (priceToBePaid != 0 && coin.value <= priceToBePaid && vendingMachineModel.hasAnyMoreCoins(coin)) {
                        vendingMachineModel.getCoin(coin);
                        changeCoins.add(coin);
                        priceToBePaid -= coin.value;
                    }
                }

                BuyResponseDTO responseDTO = new BuyResponseDTO(attempt, priceToBePaid == 0);

                if (priceToBePaid != 0) {
                    //  rollback
                    vendingMachineModel.addAllCoins(changeCoins);
                }
                else {
                    for (Coin coin : changeCoins) {
                        responseDTO.setChange(coin, 1);
                    }
                }

                mailBox.addBuyResponse(responseDTO);
            }
        }
    }
}
