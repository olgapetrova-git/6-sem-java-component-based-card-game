package htwberlin.mau_mau.rules_management.service;

import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.rules_management.data.RulesResult;

/**
 * The interface RulesService provides operations to check game conditions according to the set of rules.
 */
public interface RulesService {
	/**
	 * Validates player move to check if the card can be played.
	 *
	 * @param card        the card chosen by player to play with
	 * @param openCard    the open card on the top of the playing deck
	 * @param rulesResult object containing rules validation result and text message
	 * @return rulesResult : object containing validation result and text message
	 */
	RulesResult validatePlayerMove(Card card, Card openCard, RulesResult rulesResult);

	/**
	 * Counts number of cards player has to draw as a penalty.
	 *
	 * @param rulesResult object containing rules validation result and text message
	 * @return int number of penalty cards
	 */
	int countPenaltyCards(RulesResult rulesResult);
}
