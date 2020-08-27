package htwberlin.mau_mau.card_management.service;

public class EmptyDrawingStackException extends Throwable {

    public EmptyDrawingStackException() {
        super("Error: Attempted to remove a card from the empty drawing stack.");
    }
}
