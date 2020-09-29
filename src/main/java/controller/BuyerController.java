package controller;

import model.ApplicationModel;
import model.dto.BuyAttemptDTO;
import service.BuyAttemptProvider;
import view.BuyerView;

public class BuyerController implements Runnable {
    private static final int BUYING_INTERVAL_MSECS = 5;

    private Iterable<VendingTerminalController> terminals;

    private BuyAttemptProvider buyAttemptProvider;
    private final ApplicationModel applicationModel;
    private final BuyerView view = new BuyerView();

    public BuyerController(Iterable<VendingTerminalController> terminals, ApplicationModel model) {
        this.terminals = terminals;
        this.applicationModel = model;
        this.buyAttemptProvider = new BuyAttemptProvider();
    }

    @Override
    public void run() {
        while (applicationModel.isRunning()) {

            terminals.forEach(t -> {
                BuyAttemptDTO attemptDTO = buyAttemptProvider.getBuyAttemptDTO();

                t.attemptToBuy(attemptDTO);

                try {
                    Thread.sleep(BUYING_INTERVAL_MSECS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            terminals.forEach(VendingTerminalController::processAttemptAnswer);

        }
    }
}
