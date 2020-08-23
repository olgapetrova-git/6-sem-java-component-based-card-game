package htwberlin.mau_mau.rules_management.controller;

import htwberlin.mau_mau.card_management.model.Card;
import htwberlin.mau_mau.card_management.model.Deck;

/**
 * The interface GameRules provides operations to check game conditions according to the set of rules.
 */
public interface GameRules {
	/**
	 * Validate player move to check if the card can be played.
	 *
	 * @param card the card chosen by player to play with
	 * @param topmostCard the topmost card in the playing deck
	 * @return the boolean: true if the card can be played, else false
	 */
	boolean validatePlayerMove(Card card, Card topmostCard);

	/**
	 * Validate game state if the win conditions have been achieved by any of the players.
	 *
	 * @param gameData the game data, object of the current game
	 * @return the boolean: true if the win conditions are met, else false
	 */
	boolean validateWin(Deck hand);
}
// wer nächste dran (oder übernächste)
// factory brauchen wir nicht, wir wollen dependency injection
// warum createDeck nicht in deck controller?
// private methoden nicht in interface sollen nicht in interface
// nur public methoden die von anderen komponenten zb von oberfäche aufgerufen sind
// komponente: view, game controller, deck controller, game rules