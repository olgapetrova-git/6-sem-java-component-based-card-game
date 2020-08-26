package htwberlin.mau_mau.rules_management.data;

/**
 * The type StandardRulesGameData describes a current state of the attributes related to the game rules.
 */
public class StandardRulesGameData {
    /**
    * Number of the current player whose turn is to make a move.
     */
    private int currentPlayerNum;
    //Je nach Regel darf er anschließend diese Karte, wenn sie den angegebenen Bedingungen
    // genügt, ablegen, oder muss warten, bis er erneut an der Reihe ist

    /**
     * Instantiates a new Game rules data.
     *
     * @param currentPlayerNum the current player num
     */
    public StandardRulesGameData(int currentPlayerNum) {
        this.currentPlayerNum = currentPlayerNum;
    }

    /**
     * Gets current player number.
     *
     * @return the current player num
     */
    public int getCurrentPlayerNum() {
        return currentPlayerNum;
    }

    /**
     * Sets current player number.
     *
     * @param currentPlayerNum the current player num
     */
    public void setCurrentPlayerNum(int currentPlayerNum) {
        this.currentPlayerNum = currentPlayerNum;
    }
}
