package htwberlin.mau_mau.game_management.controller;

import htwberlin.mau_mau.card_management.controller.CardController;
import htwberlin.mau_mau.card_management.model.Card;
import htwberlin.mau_mau.card_management.model.Deck;
import htwberlin.mau_mau.game_management.model.GameData;
import htwberlin.mau_mau.player_management.model.Player;
import htwberlin.mau_mau.real_player_management.controller.RealPlayerController;
import htwberlin.mau_mau.rules_management.model.GameRulesId;
import htwberlin.mau_mau.virtual_player_management.controller.VirtualPlayerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * The type GameControllerImpl implements operations to manage game flow.
 */
@Component
public class GameControllerImpl implements GameController{
	@Autowired
	private RealPlayerController realPlayerController;

	@Autowired
	private VirtualPlayerController virtualPlayerController;

	@Autowired
	private CardController cardController;

	@Override
	public GameData setupNewGame(String name, int numberOfVirtualPlayers, GameRulesId gameRulesId) {

		GameData gameData = new GameData(new Deck(), new Deck(), new ArrayList<Player>(), gameRulesId);
		gameData.getPlayers().add(realPlayerController.createRealPlayer(name));
		for (int i = 0; i < numberOfVirtualPlayers; i++){
			gameData.getPlayers().add(virtualPlayerController.createVirtualPlayer());
				}
		gameData.setDrawingStack(cardController.createDeckOfCards());
		cardController.shuffleDrawingDeck(gameData.getDrawingStack()); 	// call shuffle
		return gameData;
	}

@Override
	public GameData dealCardsToPlayers(GameData gameData) {
		// TODO: call shuffleDrawingDeck;
		//  then initially deal cards to the each player, i.e. save cards in Hand for each Player in ArrayList in GameData
		return null;
	}

	@Override
	public boolean makeGameMoveForRealPlayer(int cardPosition, GameData gameData) {
		return false;
	}

	@Override
	public boolean makeGameMoveForVirtualPlayer(Card topmostCard, Deck hand) {
		return false;
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
