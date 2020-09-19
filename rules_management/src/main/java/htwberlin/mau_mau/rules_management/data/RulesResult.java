package htwberlin.mau_mau.rules_management.data;

/**
 * Class containing rules validation result and related text message.
 * Used by RulesService to pass rules related data to game service and view service.
 */
public abstract class RulesResult {
    protected boolean success;
    protected String message;

    public RulesResult(boolean success, String message) {
        this.success = success;
        this.message = message;
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
}
