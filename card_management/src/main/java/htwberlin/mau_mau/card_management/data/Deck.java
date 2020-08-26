package htwberlin.mau_mau.card_management.data;

import java.util.Stack;

/**
 * The type Deck.
 * Describes deck of cards used for drawing deck, playing deck and players' hands.
 */
public class Deck {
    /**
     * Collection of type Stack representing a deck of cards.
     */
    private Stack<Card> cards;

    /**
     * Instantiates a new Deck.
     */
    public Deck() {
        cards = new Stack<Card>();
    }

    /**
     * Gets cards.
     *
     * @return the cards
     */
    public Stack<Card> getCards() {
        return cards;
    }
}
