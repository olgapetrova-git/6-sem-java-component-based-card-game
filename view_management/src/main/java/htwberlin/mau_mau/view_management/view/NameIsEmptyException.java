package htwberlin.mau_mau.view_management.view;

public class NameIsEmptyException extends Exception {

    public NameIsEmptyException() {
        super("Empty name entered.");
    }
}