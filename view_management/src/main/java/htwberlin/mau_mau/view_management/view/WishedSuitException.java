package htwberlin.mau_mau.view_management.view;

public class WishedSuitException extends Exception{

    public WishedSuitException(String input) {
        super("Expected value 'S', 'C', 'H', or 'D', but received "+input+".");
    }
}