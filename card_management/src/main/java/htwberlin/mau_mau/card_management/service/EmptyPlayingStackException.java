package htwberlin.mau_mau.card_management.service;

/**
 * The type Empty playing stack exception is thrown by the Error: Attempted to remove a card from
 * the empty playing stack.
 */
public class EmptyPlayingStackException extends Exception {
    public EmptyPlayingStackException() {
        super("Error: Attempted to remove a card from the empty playing stack.");
    }
}
