package htwberlin.mau_mau.rules_management.data;

/**
 * Class containing rules validation result and related text message.
 * Used by RulesService to pass rules related data to game service and view service.
 */
public abstract class RulesResult {
    protected boolean success;
    protected String message;



    protected int currentPlayerIndex;
    protected int direction;

    public RulesResult(boolean success, String message) {
        this.success = success;
        this.message = message;
        currentPlayerIndex = 0;
        //clockwise
        direction = 1;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
