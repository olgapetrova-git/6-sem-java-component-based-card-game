package htwberlin.mau_mau.rules_management.data;

public class RulesResultSpecial extends RulesResult {

    private boolean sevenPlayed;
    private int sevenCounter;

    public RulesResultSpecial(boolean success, String message) {
        super(success, message);

        sevenPlayed = false;
        sevenCounter = 0;
    }

    public boolean isSevenPlayed() {
        return sevenPlayed;
    }

    public void setSevenPlayed(boolean sevenPlayed) {
        this.sevenPlayed = sevenPlayed;
    }

    public int getSevenCounter() {
        return sevenCounter;
    }

    public void setSevenCounter(int sevenCounter) {
        this.sevenCounter = sevenCounter;
    }
}
