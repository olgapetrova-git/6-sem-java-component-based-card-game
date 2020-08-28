package htwberlin.mau_mau.view_management.view;

public class PlayerMoveException extends Exception {

    public PlayerMoveException(int num, int min, int max) {
        super("Expected value between "+min+" and "+max+", 100, 200, 300, or 400, but received "+num+".");
    }
}