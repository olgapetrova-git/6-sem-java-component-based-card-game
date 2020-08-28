package htwberlin.mau_mau.game_management.service;

import htwberlin.mau_mau.card_management.service.CardService;
import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.data.Deck;
import htwberlin.mau_mau.card_management.service.EmptyDrawingStackException;
import htwberlin.mau_mau.card_management.service.EmptyPlayingStackException;
import htwberlin.mau_mau.game_management.data.GameData;
import htwberlin.mau_mau.player_management.data.Player;
import htwberlin.mau_mau.real_player_management.data.RealPlayer;
import htwberlin.mau_mau.real_player_management.service.RealPlayerService;
import htwberlin.mau_mau.rules_management.data.GameRulesId;
import htwberlin.mau_mau.virtual_player_management.service.VirtualPlayerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * The type GameServiceImpl implements operations to manage game flow.
 */
@Component
public class GameServiceImpl implements GameService {

    private static final Logger LOGGER = LogManager.getLogger(GameServiceImpl.class);

    @Autowired
    private RealPlayerService realPlayerService;

    @Autowired
    private VirtualPlayerService virtualPlayerService;

    @Autowired
    private CardService cardService;


    @Override
    public GameData setupNewGame(String name, int numberOfVirtualPlayers, GameRulesId gameRulesId) {

        GameData gameData = new GameData(new Deck(), new Deck(), new ArrayList<Player>(), gameRulesId);
        RealPlayer realPlayer = realPlayerService.createRealPlayer(name);
        gameData.getPlayers().add(realPlayer);
        for (int i = 0; i < numberOfVirtualPlayers; i++) {
            gameData.getPlayers().add(virtualPlayerService.createVirtualPlayer());
        }
        gameData.setCurrentPlayer(realPlayer);
        gameData.setDrawingStack(cardService.createDrawingStack());
        cardService.shuffleDrawingDeck(gameData.getDrawingStack());
        dealCardsToPlayers(gameData);
        try {
            gameData.setPlayingStack(cardService.createPlayingStack(gameData.getDrawingStack()));
        } catch (EmptyDrawingStackException e) {
            LOGGER.error(e.getMessage());

        } catch (EmptyPlayingStackException e) {
            LOGGER.error(e.getMessage());
        }
        gameData.setOpenCard(cardService.getOpenCard(gameData.getPlayingStack()));
        return gameData;
    }

    @Override
    public GameData dealCardsToPlayers(GameData gameData) {
        for (int i = 0; i < 5; i++) {
            for (Player player : gameData.getPlayers()) {
                try {
                    cardService.drawCard(gameData.getDrawingStack(), gameData.getPlayingStack(), player.getHand());
                } catch (EmptyDrawingStackException | EmptyPlayingStackException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }
        return gameData;
    }

    @Override
    public boolean makeGameMoveForRealPlayer(int cardPosition, GameData gameData) {
        return false;
    }

    @Override
    public boolean makeGameMoveForVirtualPlayer(Card openCard, Deck hand) {
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
