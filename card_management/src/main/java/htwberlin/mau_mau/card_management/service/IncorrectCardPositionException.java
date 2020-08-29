package htwberlin.mau_mau.card_management.service;

public class IncorrectCardPositionException extends Throwable {
    public IncorrectCardPositionException() {
        super("Error: Attempted to remove a card at specific position in stack, but there are not enough cards in that stack");
    }
}
