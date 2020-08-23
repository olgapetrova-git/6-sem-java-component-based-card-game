package htwberlin.mau_mau.game_management.controller;

import htwberlin.mau_mau.card_management.model.Deck;
import htwberlin.mau_mau.game_management.model.GameData;
import htwberlin.mau_mau.player_management.model.Player;
import htwberlin.mau_mau.real_player_management.model.RealPlayer;
import htwberlin.mau_mau.rules_management.model.GameRulesId;

import java.util.ArrayList;



/**
 * The type GameControllerImpl implements operations to manage game flow.
 */
public class GameControllerImpl implements GameController{

	@Override
	public GameData setupNewGame(RealPlayer realPlayer, int virtualPlayers, GameRulesId gameRulesId) {
		// create game data object:
		GameData gameData = new GameData(new Deck(), new Deck(), new ArrayList<Player>(), gameRulesId);
		// TODO:
		// add realPlayer to GameData (call addRealPlayerToGame)
		// implement GameRules Factory to get an obj for gameRulesId (Standard Rules or specific Rules)
		// call createDeckOfCards(gameRules.NUMBER_OF_CARDS_IN_DECK)
		// call shuffleDeck (from DeckController)
		// create specified number of virtual players: createVirtualPlayer(), and add them to ArrayList
		return gameData;
	}

@Override
	public GameData addRealPlayerToGame(GameData gameData, String name) {
		//TODO:
		// RealPlayer player = new RealPlayer(name);
		// add real player to ArrayList in GameData
		//
		return gameData;
	}

	@Override
	public GameData addVirtualPlayerToGame(GameData gameData) {
		return null;
	}

@Override
	public GameData dealCardsToPlayers(GameData gameData) {
		// TODO: call shuffleDrawingDeck;
		//  then initially deal cards to the each player, i.e. save cards in Hand for each Player in ArrayList in GameData
		return null;
	}

	@Override
	public GameData makeRealPlayerMove(int cardPosition, GameData gameData) {
		return null;
	}

	@Override
	public GameData makeVirtualPlayerMove(GameData gameData) {
		return null;
	}

	/**
	 * End game.
	 *
	 * @param gameData the game data
	 */
	void endGame(GameData gameData) {
		// TODO: reset the game and show menu
	}


}
