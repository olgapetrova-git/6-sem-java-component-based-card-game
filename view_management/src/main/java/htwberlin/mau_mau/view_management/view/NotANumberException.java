package htwberlin.mau_mau.view_management.view;

public class NotANumberException extends Exception {

    public NotANumberException(String input, int min, int max) {
        super("Expected numeric value between "+min+" and "+max+", but received "+input+".");
    }
}
