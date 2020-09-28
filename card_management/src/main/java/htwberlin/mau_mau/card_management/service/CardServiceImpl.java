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
            playingStack.getCards().add(drawingStack.pop());
        } catch (EmptyStackException ex) {
            throw new EmptyDrawingStackException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyPlayingStackException();
        }

        return playingStack;
    }

    @Override
    public void playCard(Deck hand, int cardPosition, Deck playingStack) throws IncorrectCardPositionException {
        try{
            Card card = hand.getCards().get(cardPosition);
            playingStack.push(card);
            hand.getCards().remove(cardPosition);}
        catch (ArrayIndexOutOfBoundsException e){
            throw new IncorrectCardPositionException();
        }
    }

    @Override
    public Card drawCard(Deck drawingStack, Deck playingStack, Deck hand)
            throws EmptyDrawingStackException, EmptyPlayingStackException {
        try {
            Card card = drawingStack.pop();
            hand.getCards().add(card);
            if (drawingStack.getCards().isEmpty()) {
                do {
                    drawingStack.getCards().add(playingStack.getCards().remove(0));
                }
                while (playingStack.getCards().size() > 1);
                shuffleDrawingDeck(drawingStack);
            }
            return  card;
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
        return playingStack.peek();
    }

    @Override
    public Deck shuffleDrawingDeck(Deck drawingStack) {
        Collections.shuffle(drawingStack.getCards());
        return drawingStack;
    }
}
