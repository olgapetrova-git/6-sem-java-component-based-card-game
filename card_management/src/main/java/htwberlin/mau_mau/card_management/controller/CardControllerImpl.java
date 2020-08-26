package htwberlin.mau_mau.card_management.controller;
import htwberlin.mau_mau.card_management.model.Card;
import htwberlin.mau_mau.card_management.model.Deck;
import org.springframework.stereotype.Component;

/**
 * The type CardControllerImpl implements operations over the cards and decks.
 */
@Component
public class CardControllerImpl implements CardController {

    /**
     * Fixed number of cards in the deck (french).
     */
    private static final int NUMBER_OF_CARDS_IN_DECK = 32;

    /**
     * Turn over and shuffle the playing stack to serve as new drawing stack, when the drawing stack is empty,
     * i.e. add the cards form the playing stack (except for the topmost card) to drawing stack.
     *
     * @param drawingDeck  the drawing deck
     * @param playingStack the playing stack
     */
    void turnOver(Deck drawingDeck, Deck playingStack) {
        //TODO: implement add the cards form the playing stack (except for the topmost card) to drawing stack
        //call shuffleDrawingDeck
    }

    @Override
    public Deck createDeckOfCards() {
        //TODO
        return null;
    }

    @Override
    public void removeCardFromHandAddToPlayingStack(Deck hand, Card card, Deck playingStack) {

    }

    @Override
    public Deck addCardFromDrawingStackToHand(Deck drawingStack, Deck hand) {
        return null;
        //TODO: if the last card is drawn...call turnOver
    }

    @Override
    public Card getCardByPositionFromHand(int position, Deck hand) {
        return null;
    }

    @Override
    public Card getTopmostCardFromPlayingStack(Deck playingStack) {
        return null;
    }

    @Override
    public Deck shuffleDrawingDeck(Deck drawingStack) {
        return null;
    }
}
