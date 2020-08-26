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
	Deck createDeckOfCards();


	/**
	 * Removes one card from the player's hand and add the played card to the top of the playing stack.
	 *
	 * @param hand         the hand
	 * @param card         the card
	 * @param playingStack the playing stack
	 */
	void removeCardFromHandAddToPlayingStack(Deck hand, Card card, Deck playingStack);

	/**
	 * Pick the new card from the top of drawing stack and add it to the player's hand.
	 * Done if a player is not able to play any of his cards or at the beginning of the game.
	 *
	 * @param drawingStack the drawing stack
	 * @param hand         the hand
	 * @return Deck the deck
	 */
	Deck addCardFromDrawingStackToHand(Deck drawingStack, Deck hand);

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
