package htwberlin.mau_mau.card_management.service;

import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.data.Deck;
import htwberlin.mau_mau.card_management.data.Rank;
import htwberlin.mau_mau.card_management.data.Suit;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.EmptyStackException;

/**
 * The type CardServiceImpl implements operations over the cards and decks.
 */
@Component
public class CardServiceImpl implements CardService {

    /**
     * Fixed number of cards in the deck (french).
     */
    private static final int NUMBER_OF_CARDS_IN_DECK = 32;


//rename createDrawingDeck
    //make a method moveCard() Moves one Card from Deck to Deck, not specified.

    @Override
    public Deck createDrawingStack() {
        Deck drawingDeck = new Deck();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                drawingDeck.getCards().add(new Card(suit, rank));
            }
        }
        return drawingDeck;
    }

    @Override
    public Deck createPlayingStack(Deck drawingStack)
            throws EmptyDrawingStackException, EmptyPlayingStackException {
        Deck playingStack = new Deck();
        try {
            playingStack.getCards().add(drawingStack.getCards().pop());
        } catch (EmptyStackException ex) {
            throw new EmptyDrawingStackException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyPlayingStackException();
        }

        return playingStack;
    }

    @Override
    public void removeCardFromHandAddToPlayingStack(Deck hand, Card card, Deck playingStack) {

    }

    @Override
    public void drawCard(Deck drawingStack, Deck playingStack, Deck hand)
            throws EmptyDrawingStackException, EmptyPlayingStackException {
        try {
            hand.getCards().add(drawingStack.getCards().pop());
            if (drawingStack.getCards().isEmpty()) {
                do {
                    drawingStack.getCards().add(playingStack.getCards().remove(0));
                }
                while (playingStack.getCards().size() > 1);
                shuffleDrawingDeck(drawingStack);
            }
        } catch (EmptyStackException ex) {
            throw new EmptyDrawingStackException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyPlayingStackException();
        }
    }

    @Override
    public Card getCardByPositionFromHand(int position, Deck hand) {
        return null;
    }

    @Override
    public Card getOpenCard(Deck playingStack) {
        return playingStack.getCards().peek();
    }

    @Override
    public Deck shuffleDrawingDeck(Deck drawingStack) {
        Collections.shuffle(drawingStack.getCards());
        return drawingStack;
    }
}
