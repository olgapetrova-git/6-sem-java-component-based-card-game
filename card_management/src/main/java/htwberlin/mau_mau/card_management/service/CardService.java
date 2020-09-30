package htwberlin.mau_mau.card_management.service;

import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.data.Deck;

/**
 * The interface CardService provides operations over the cards and decks.
 */
public interface CardService {

	/**
	 * Creates initial drawing deck of cards.
	 *
	 * @return Deck the deck
	 */
	Deck createDrawingStack();

	/**
	 * Create initial playing stack deck.
	 *
	 * @param drawingStack Deck object representing the drawing stack
	 * @return Deck object representing the playing stack
	 * @throws EmptyDrawingStackException the empty drawing stack exception
	 * @throws EmptyPlayingStackException the empty playing stack exception
	 */
	Deck createPlayingStack(Deck drawingStack) throws EmptyDrawingStackException, EmptyPlayingStackException;

	/**
	 * Removes one card from the player's hand and add the played card to the top of the playing stack.
	 *
	 * @param hand         Deck object representing cards in the player's hand
	 * @param cardPosition card position in hand
	 * @param playingStack Deck object representing the playing stack
	 * @throws IncorrectCardPositionException the incorrect card position exception
	 */
	void playCard(Deck hand, int cardPosition, Deck playingStack) throws IncorrectCardPositionException;

	/**
	 * Picks the new card from the top of drawing stack and add it to the player's hand.
	 * When the drawing stack is empty, turns over and shuffles the playing stack to serve as new drawing stack.
	 *
	 * @param drawingStack Deck object representing the drawing stack
	 * @param playingStack Deck object representing the playing stack
	 * @param hand         Deck object representing cards in the player's hand
	 * @return Card the card
	 * @throws EmptyDrawingStackException the empty drawing stack exception
	 * @throws EmptyPlayingStackException the empty playing stack exception
	 */
	Card drawCard(Deck drawingStack, Deck playingStack, Deck hand)
			throws EmptyDrawingStackException, EmptyPlayingStackException;

	/**
	 * Gets card by position from player's hand.
	 *
	 * @param position the position of card in player's hand, returned by View
	 * @param hand     Deck object representing cards in the player's hand
	 * @return Card the card
	 */
	Card getCardByPositionFromHand(int position, Deck hand);

	/**
	 * Gets the open card from top of the the playing stack.
	 *
	 * @param playingStack Deck object representing the playing stack
	 * @return Card the open card on the top of the playing stack.
	 */
	Card getOpenCard(Deck playingStack);

	/**
	 * Shuffles a drawing deck of cards by placing them in random order.
	 *
	 * @param drawingStack Deck object representing the drawing stack
	 * @return Deck object representing the shuffled drawing stack
	 */
	Deck shuffleDrawingDeck(Deck drawingStack);
}
