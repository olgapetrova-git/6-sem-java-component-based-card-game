package htwberlin.mau_mau.view_management.view;

public class PlayerMoveSkipException extends Exception{

    public PlayerMoveSkipException(String move, int min, int max) {
        super("Expected value between "+min+" and "+max+", 'S', 'M', 'MM', or 'Q', but received "+move+".");
    }
}