package htwberlin.mau_mau.view_management.view;

public class PlayerMoveDrawException extends Exception {

    public PlayerMoveDrawException(String move, int min, int max) {
        super("Expected value between "+min+" and "+max+", 'D', 'M', 'MM', or 'Q', but received "+move+".");
    }
}