package model;

public class ApplicationModel {
    private boolean isRunning = true;

    public boolean isRunning() {
        return isRunning;
    }

    public void stop() {
        this.isRunning = false;
    }
}
