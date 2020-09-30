package htwberlin.mau_mau.card_management.service;

/**
 * The type Incorrect card position exception is thrown by the Error: Attempted to remove a card at specific position
 * in stack, but there are not enough cards in that stack.
 */
public class IncorrectCardPositionException extends Exception {
    public IncorrectCardPositionException() {
        super("Error: Attempted to remove a card at specific position in stack, but there are not enough cards in that stack.");
    }
}
