package htwberlin.mau_mau.game_management.service;

import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.data.Deck;
import htwberlin.mau_mau.card_management.service.CardService;
import htwberlin.mau_mau.card_management.service.EmptyDrawingStackException;
import htwberlin.mau_mau.card_management.service.EmptyPlayingStackException;
import htwberlin.mau_mau.card_management.service.IncorrectCardPositionException;
import htwberlin.mau_mau.game_management.data.GameData;
import htwberlin.mau_mau.player_management.data.Player;
import htwberlin.mau_mau.real_player_management.data.RealPlayer;
import htwberlin.mau_mau.real_player_management.service.RealPlayerService;
import htwberlin.mau_mau.rules_management.data.GameRulesId;
import htwberlin.mau_mau.rules_management.data.PostAction;
import htwberlin.mau_mau.rules_management.data.RulesResult;
import htwberlin.mau_mau.rules_management.service.RulesProvider;
import htwberlin.mau_mau.rules_management.service.RulesService;
import htwberlin.mau_mau.virtual_player_management.service.VirtualPlayerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;

/**
 * The type GameServiceImpl implements operations to manage game flow.
 */
@Component
public class GameServiceImpl implements GameService {

    private static final Logger LOGGER = LogManager.getLogger(GameServiceImpl.class);
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private RealPlayerService realPlayerService;

    @Autowired
    private VirtualPlayerService virtualPlayerService;

    @Autowired
    private CardService cardService;

    @Autowired
    private RulesProvider rulesProvider;

    @Override
    public GameData setupNewGame(String name, int numberOfVirtualPlayers, GameRulesId gameRulesId) {

        GameData gameData = new GameData(new Deck(), new Deck(), new ArrayList<Player>(), gameRulesId);
        RealPlayer realPlayer = realPlayerService.createRealPlayer(name);
        gameData.getPlayers().add(realPlayer);
        for (int i = 0; i < numberOfVirtualPlayers; i++) {
            boolean duplicate = false;
            Player newPlayer;
            do {
                newPlayer = virtualPlayerService.createVirtualPlayer();
                for (Player player : gameData.getPlayers()) {
                    if (player.getName().equals(newPlayer.getName())) {
                        duplicate = true;
                        break;
                    }
                }
            } while (duplicate);
            gameData.getPlayers().add(newPlayer);
        }
        gameData.setCurrentPlayer(realPlayer);
        gameData.setDrawingStack(cardService.createDrawingStack());
        cardService.shuffleDrawingDeck(gameData.getDrawingStack());
        dealCardsToPlayers(gameData);
        try {
            gameData.setPlayingStack(cardService.createPlayingStack(gameData.getDrawingStack()));
        } catch (EmptyDrawingStackException | EmptyPlayingStackException e) {
            LOGGER.error(e.getMessage());
        }
        gameData.setOpenCard(cardService.getOpenCard(gameData.getPlayingStack()));
        return gameData;
    }

    @Override
    public void dealCardsToPlayers(GameData gameData) {
        for (int i = 0; i < 5; i++) {
            for (Player player : gameData.getPlayers()) {
                try {
                    cardService.drawCard(gameData.getDrawingStack(), gameData.getPlayingStack(), player.getHand());
                } catch (EmptyDrawingStackException | EmptyPlayingStackException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }
    }

    @Override
    public RulesResult makeGameMoveForRealPlayer(int cardPosition, GameData gameData) {
        RulesService rulesService = rulesProvider.getRulesService();
        gameData.setRulesResult(rulesService.validatePlayerMove(
                gameData.getPlayers().get(0).getHand().getCards().get(cardPosition),
                gameData.getOpenCard(), gameData.getRulesResult(),
                gameData.getPlayers().size()));

        try {
            if (gameData.getRulesResult().getSuccess()) {
                cardService.playCard(gameData.getPlayers().get(0).getHand(), cardPosition, gameData.getPlayingStack());
                gameData.setOpenCard(cardService.getOpenCard(gameData.getPlayingStack()));
            }
        } catch (IncorrectCardPositionException e) {
            LOGGER.error(e.getMessage());
        }

        return gameData.getRulesResult();
    }

    @Override
    public RulesResult makeGameMoveForVirtualPlayer(Card openCard, Deck hand, GameData gameData) {
        RulesService rulesService = rulesProvider.getRulesService();

        for (int i = 0; i < hand.getCards().size(); i++) {

            gameData.setRulesResult(rulesService.validatePlayerMove(
                    hand.getCards().get(i), openCard, gameData.getRulesResult(),
                    gameData.getPlayers().size()
            ));

            if (gameData.getRulesResult().getSuccess()) {
                try {
                    cardService.playCard(hand, i, gameData.getPlayingStack());
                    gameData.setOpenCard(cardService.getOpenCard(gameData.getPlayingStack()));
                } catch (IncorrectCardPositionException e) {
                    LOGGER.error(e.getMessage());
                }
                break;
            }
        }

        return gameData.getRulesResult();
    }

    @Override
    public PostAction getPostAction(RulesResult rulesResult) {
        RulesService rulesService = rulesProvider.getRulesService();

        return rulesService.definePostAction(rulesResult);
    }
    @Override
    public int countPenaltyCards(PostAction postAction){
        int numberOfPenaltyCards;
        switch(postAction){
            case DRAWONE: numberOfPenaltyCards = 1;
                break;
            case DRAWTWO: numberOfPenaltyCards = 2;
                break;
            case DRAWFOUR: numberOfPenaltyCards = 4;
                break;
            default: numberOfPenaltyCards = 0;
        }
        return numberOfPenaltyCards;
    }

    @Override
    public void saveToDB(GameData gameData) {
        if (LOGGER.isDebugEnabled()) { LOGGER.debug("SAVETODB START"); }
        entityManager.getTransaction().begin();
        if (LOGGER.isDebugEnabled()) { LOGGER.debug("PERSIST START"); }
        entityManager.persist(gameData);
        if (LOGGER.isDebugEnabled()) { LOGGER.debug("COMMIT START"); }
        entityManager.getTransaction().commit();
        if (LOGGER.isDebugEnabled()) { LOGGER.debug("COMMIT FINISH"); }
    }
}
