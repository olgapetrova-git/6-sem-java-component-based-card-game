package htwberlin.mau_mau.rules_management.model;

import htwberlin.mau_mau.card_management.model.Suit;

/**
 * The type Special rules game data.
 */
public class SpecialRulesGameData extends StandardRulesGameData {
    /**
     * Instantiates a new Special rules Game data.
     *
     * @param currentPlayerNum the current player num
     */
    public SpecialRulesGameData(int currentPlayerNum) {
        super(currentPlayerNum);
        sevenPlayed = false;
        sevenPlayedCounter = 0;
        eightPlayed = false;
        eightPlayedCounter = 0;
        jackPlayed = false;
        jackPlayedCounter = 0;
        clockwise = true;
    }

    private boolean sevenPlayed;

    private int sevenPlayedCounter;

    private boolean eightPlayed;

    private int eightPlayedCounter;

    private boolean jackPlayed;

    private int jackPlayedCounter;

    private Suit wishedSuit;

    private boolean clockwise;

    /**
     * Is seven played boolean.
     *
     * @return the boolean
     */
    public boolean isSevenPlayed() {
        return sevenPlayed;
    }

    /**
     * Sets seven played.
     *
     * @param sevenPlayed the seven played
     */
    public void setSevenPlayed(boolean sevenPlayed) {
        this.sevenPlayed = sevenPlayed;
    }

    /**
     * Gets seven played counter.
     *
     * @return the seven played counter
     */
    public int getSevenPlayedCounter() {
        return sevenPlayedCounter;
    }

    /**
     * Sets seven played counter.
     *
     * @param sevenPlayedCounter the seven played counter
     */
    public void setSevenPlayedCounter(int sevenPlayedCounter) {
        this.sevenPlayedCounter = sevenPlayedCounter;
    }

    /**
     * Is eight played boolean.
     *
     * @return the boolean
     */
    public boolean isEightPlayed() {
        return eightPlayed;
    }

    /**
     * Sets eight played.
     *
     * @param eightPlayed the eight played
     */
    public void setEightPlayed(boolean eightPlayed) {
        this.eightPlayed = eightPlayed;
    }

    /**
     * Gets eight played counter.
     *
     * @return the eight played counter
     */
    public int getEightPlayedCounter() {
        return eightPlayedCounter;
    }

    /**
     * Sets eight played counter.
     *
     * @param eightPlayedCounter the eight played counter
     */
    public void setEightPlayedCounter(int eightPlayedCounter) {
        this.eightPlayedCounter = eightPlayedCounter;
    }

    /**
     * Is jack played boolean.
     *
     * @return the boolean
     */
    public boolean isJackPlayed() {
        return jackPlayed;
    }

    /**
     * Sets jack played.
     *
     * @param jackPlayed the jack played
     */
    public void setJackPlayed(boolean jackPlayed) {
        this.jackPlayed = jackPlayed;
    }

    /**
     * Gets jack played counter.
     *
     * @return the jack played counter
     */
    public int getJackPlayedCounter() {
        return jackPlayedCounter;
    }

    /**
     * Sets jack played counter.
     *
     * @param jackPlayedCounter the jack played counter
     */
    public void setJackPlayedCounter(int jackPlayedCounter) {
        this.jackPlayedCounter = jackPlayedCounter;
    }

    /**
     * Gets wished suit.
     *
     * @return the wished suit
     */
    public Suit getWishedSuit() {
        return wishedSuit;
    }

    /**
     * Sets wished suit.
     *
     * @param wishedSuit the wished suit
     */
    public void setWishedSuit(Suit wishedSuit) {
        this.wishedSuit = wishedSuit;
    }

    /**
     * Is clockwise boolean.
     *
     * @return the boolean
     */
    public boolean isClockwise() {
        return clockwise;
    }

    /**
     * Sets clockwise.
     *
     * @param clockwise true, if clockwise
     */
    public void setClockwise(boolean clockwise) {
        this.clockwise = clockwise;
    }
}
