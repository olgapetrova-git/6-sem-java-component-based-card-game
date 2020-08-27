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
	 * @param drawingStack the drawing stack
	 * @return the deck
	 * @throws EmptyDrawingStackException the empty drawing stack exception
	 * @throws EmptyPlayingStackException the empty playing stack exception
	 */
	Deck createPlayingStack(Deck drawingStack) throws EmptyDrawingStackException, EmptyPlayingStackException;

	/**
	 * Removes one card from the player's hand and add the played card to the top of the playing stack.
	 *
	 * @param hand         the hand
	 * @param card         the card
	 * @param playingStack the playing stack
	 */
	void removeCardFromHandAddToPlayingStack(Deck hand, Card card, Deck playingStack);

	/**
	 * Picks the new card from the top of drawing stack and add it to the player's hand.
	 * When the drawing stack is empty,turns over and shuffles the playing stack to serve as new drawing stack,
	 *
	 * @param drawingStack the drawing stack
	 * @param playingStack the playing stack
	 * @param hand         the hand
	 * @return Deck the modified hand
	 * @throws EmptyDrawingStackException the empty drawing stack exception
	 * @throws EmptyPlayingStackException the empty playing stack exception
	 */
	void drawCard(Deck drawingStack, Deck playingStack, Deck hand) throws EmptyDrawingStackException, EmptyPlayingStackException;

	/**
	 * Gets card by position from player's hand.
	 *
	 * @param position the position of card in player's hand, returned by View
	 * @param hand     the hand
	 * @return Card the card
	 */
	Card getCardByPositionFromHand(int position, Deck hand);

	/**
	 * Gets topmost card from the playing stack.
	 *
	 * @param playingStack the playing stack
	 * @return Card the card
	 */
	Card getTopmostCardFromPlayingStack(Deck playingStack);

	/**
	 * Shuffles deck, i.e. rearrange a drawing deck of cards by placing them in random order.
	 *
	 * @param drawingStack the drawing stack
	 * @return Deck the deck
	 */
	Deck shuffleDrawingDeck(Deck drawingStack);






}
