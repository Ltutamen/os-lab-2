import controller.BuyerController;
import controller.MailBoxController;
import controller.VendingMachineController;
import controller.VendingTerminalController;
import model.ApplicationModel;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ApplicationController {
    public static void main(String[] args) {
        ApplicationModel applicationModel = new ApplicationModel();

        MailBoxController mailBox = new MailBoxController();

        VendingTerminalController terminalA = new VendingTerminalController(mailBox);
        VendingTerminalController terminalB = new VendingTerminalController(mailBox);

        BuyerController buyerA = new BuyerController(Arrays.asList(terminalA, terminalB), applicationModel);
        BuyerController buyerB = new BuyerController(Arrays.asList(terminalA, terminalB), applicationModel);

        List<BuyerController> buyers = Arrays.asList(buyerA, buyerB);

        VendingMachineController vendingMachine = new VendingMachineController(mailBox, applicationModel);

        Set<Thread> runningThreads = new HashSet<>();
        runningThreads.add(new Thread(vendingMachine));
        buyers.forEach(b -> runningThreads.add(new Thread(b)));

        runningThreads.forEach(Thread::start);

        try {
            Thread.sleep(500);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        applicationModel.stop();

        runningThreads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
