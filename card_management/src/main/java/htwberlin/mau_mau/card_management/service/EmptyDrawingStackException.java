package htwberlin.mau_mau.card_management.service;

/**
 * The type Empty drawing stack exception is thrown when the following error occurs: Attempted to remove a card from
 * the empty drawing stack.
 */
public class EmptyDrawingStackException extends Exception {

    public EmptyDrawingStackException() {
        super("Error: Attempted to remove a card from the empty drawing stack.");
    }
}
