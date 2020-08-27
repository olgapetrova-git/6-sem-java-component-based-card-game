package htwberlin.mau_mau.card_management.service;

public class EmptyPlayingStackException extends Throwable {
    public EmptyPlayingStackException() {
        super("Error: Attempted to remove a card from the empty playing stack.");
    }
}
