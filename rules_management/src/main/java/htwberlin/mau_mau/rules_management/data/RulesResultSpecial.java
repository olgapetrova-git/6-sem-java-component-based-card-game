package htwberlin.mau_mau.rules_management.data;

import htwberlin.mau_mau.card_management.data.Suit;

public class RulesResultSpecial extends RulesResult {

    private boolean sevenPlayed;
    private int sevenCounter;
    private boolean eightPlayed;
    private int eightCounter;
    private boolean ninePlayed;

    private boolean jackPlayed;
    private Suit wish;

    public RulesResultSpecial(boolean success, String message) {
        super(success, message);

        sevenPlayed = false;
        sevenCounter = 0;
        eightPlayed = false;
        eightCounter = 0;
        ninePlayed = false;
        jackPlayed = false;
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

    public boolean isEightPlayed() {
        return eightPlayed;
    }

    public void setEightPlayed(boolean eightPlayed) {
        this.eightPlayed = eightPlayed;
    }

    public int getEightCounter() {
        return eightCounter;
    }

    public void setEightCounter(int eightCounter) {
        this.eightCounter = eightCounter;
    }

    public boolean isNinePlayed() { return ninePlayed; }

    public void setNinePlayed(boolean ninePlayed) { this.ninePlayed = ninePlayed; }

    public boolean isJackPlayed() { return jackPlayed; }

    public void setJackPlayed(boolean jackPlayed) { this.jackPlayed = jackPlayed; }

    public Suit getWish() { return wish; }

    public void setWish(Suit wish) { this.wish = wish; }
}
