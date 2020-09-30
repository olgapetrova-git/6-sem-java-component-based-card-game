package htwberlin.mau_mau.rules_management.service;

import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.player_management.data.Player;
import htwberlin.mau_mau.rules_management.data.PostAction;
import htwberlin.mau_mau.rules_management.data.RulesResult;

import java.util.List;

/**
 * The interface RulesService provides operations to check game conditions according to the set of rules.
 */
public interface RulesService {
	/**
	 * Validates player move to check if the card can be played.
	 *
	 * @param card the card chosen by player to play with
	 * @param openCard the open card on the top of the playing deck
	 * @param rulesResult object containing rules validation result and text message
	 * @param numberOfPlayers number of players in game
	 * @return rulesResult object containing validation result and text message
	 */
	RulesResult validatePlayerMove(Card card, Card openCard, RulesResult rulesResult, int numberOfPlayers);


	/**
	 * Defines the post action if the player can not make a move.
	 *
	 * @param rulesResult object containing rules validation result and text message
	 * @return the post action object indicating an action to be done in case the move can't be made
	 */
	PostAction definePostAction(RulesResult rulesResult);

	/**
	 * Sets up rulesResult object at the start of the game. Checks, if the open card is special,
	 * then sets up related rules.
	 *
	 * @param openCard the open card on the top of the playing deck
	 * @return rulesResult object containing validation result and text message
	 */
	RulesResult setUpRules(Card openCard);

	Player defineNextPlayer(RulesResult rulesResult, List<Player> players);
}
