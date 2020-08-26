package htwberlin.mau_mau.game_management.service;

import htwberlin.mau_mau.card_management.service.CardService;
import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.data.Deck;
import htwberlin.mau_mau.game_management.data.GameData;
import htwberlin.mau_mau.player_management.data.Player;
import htwberlin.mau_mau.real_player_management.service.RealPlayerService;
import htwberlin.mau_mau.rules_management.data.GameRulesId;
import htwberlin.mau_mau.virtual_player_management.service.VirtualPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * The type GameServiceImpl implements operations to manage game flow.
 */
@Component
public class GameServiceImpl implements GameService {
	@Autowired
	private RealPlayerService realPlayerService;

	@Autowired
	private VirtualPlayerService virtualPlayerService;

	@Autowired
	private CardService cardService;

	@Override
	public GameData setupNewGame(String name, int numberOfVirtualPlayers, GameRulesId gameRulesId) {

		GameData gameData = new GameData(new Deck(), new Deck(), new ArrayList<Player>(), gameRulesId);
		gameData.getPlayers().add(realPlayerService.createRealPlayer(name));
		for (int i = 0; i < numberOfVirtualPlayers; i++){
			gameData.getPlayers().add(virtualPlayerService.createVirtualPlayer());
				}
		gameData.setDrawingStack(cardService.createDeckOfCards());
		cardService.shuffleDrawingDeck(gameData.getDrawingStack()); 	// call shuffle
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
