package htwberlin.mau_mau.view_management.controller;

import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.service.CardService;
import htwberlin.mau_mau.card_management.service.EmptyDrawingStackException;
import htwberlin.mau_mau.card_management.service.EmptyPlayingStackException;
import htwberlin.mau_mau.game_management.data.GameData;
import htwberlin.mau_mau.game_management.data.GameStatus;
import htwberlin.mau_mau.game_management.service.GameService;
import htwberlin.mau_mau.player_management.data.Player;
import htwberlin.mau_mau.real_player_management.data.RealPlayer;
import htwberlin.mau_mau.rules_management.data.GameRulesId;
import htwberlin.mau_mau.rules_management.data.PostAction;
import htwberlin.mau_mau.rules_management.data.RulesResult;
import htwberlin.mau_mau.rules_management.data.RulesResultSpecial;
import htwberlin.mau_mau.rules_management.service.RulesProvider;
import htwberlin.mau_mau.view_management.view.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * The type View controller.
 */
@Controller
public class ViewControllerImpl implements ViewController {
    private static final Logger LOGGER = LogManager.getLogger(ViewControllerImpl.class);

    @Autowired
    private View view;

    @Autowired
    private GameService gameService;

    @Autowired
    private RulesProvider rulesProvider;

    @Autowired
    private CardService cardService;

    @Override
    public void run() {
        view.showStartGreeting();

        String name = view.requestPlayerName();
        int numberOfVirtualPlayers = view.requestNumberOfVirtualPlayers();
        GameRulesId gameRulesId = view.requestGameRules();
        GameData gameData = gameService.setupNewGame(name, numberOfVirtualPlayers, gameRulesId);
        gameService.saveToDB(gameData);
        rulesProvider.chooseRules(gameData.getGameRulesId());
        gameData.setRulesResult(rulesProvider.getRulesService().setUpRules(gameData.getOpenCard()));
        view.showNewGameGreeting(name, numberOfVirtualPlayers, gameRulesId);
        processMainFlow(gameData);
        view.requestEnter();
        view.close();
    }

    /**
     * Process main game flow in a console cycle.
     *
     * @param gameData current game object
     */
    private void processMainFlow(GameData gameData) {


        do {
            doOneRound(gameData);
        } while (gameData.getGameStatus() == GameStatus.NORMAL);

        if (gameData.getGameStatus() == GameStatus.WIN) {
            if (gameData.getCurrentPlayer() instanceof RealPlayer) {
                view.showVictoryRealPlayer(gameData.getCurrentPlayer().getName());
            } else {
                view.showVictoryVirtualPlayer(gameData.getCurrentPlayer().getName(), gameData.getPlayers().get(0).getName());
            }
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(String.format("*** %s  HAS WON! ***", gameData.getCurrentPlayer().getName()));
            }
        } else if (gameData.getGameStatus() == GameStatus.QUIT) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("*** QUIT ***");
            }
            view.showQuit(gameData.getPlayers().get(0).getName());
            //Press enter to continue
            // quit
        }
    }

    /**
     * Process one game move for one real or virtual player.
     *
     * @param gameData the game data
     * @param player   the player
     * @return the boolean: true, if the player has won, else false
     */
    boolean processMove(GameData gameData, Player player) {

        boolean moveSuccess;
        if (player instanceof RealPlayer) {
            moveSuccess = processRealPlayerMove(gameData, player);
        } else {
            // VIRTUAL PLAYER MOVE
            moveSuccess = processVirtualPlayerMove(gameData, player);
        }
        return moveSuccess;
    }

    private void doOneRound(GameData gameData) {
        view.showTable(gameData.getPlayers().get(0).getHand(), gameData.getOpenCard(), gameData.getPlayers());

        for (Player player : gameData.getPlayers()) {
            boolean success;

            if (player instanceof RealPlayer) {
                ((RealPlayer) player).setSaidMau(false);
                ((RealPlayer) player).setSaidMauMau(false);
            }

            gameData.setCurrentPlayer(player);
            do {
                success = processMove(gameData, player);
                // success = true if card is played, or player drew a card, or quit
                // success = false if incorrect card is played or mau is said - card has to be played yet
            } while (!success);
            if (gameData.getGameStatus() != GameStatus.NORMAL) { // NORMAL, WIN, QUIT
                break;
            }
        }
    }

    boolean processRealPlayerMove(GameData gameData, Player player) {
        // Real Player Move
        boolean moveSuccess = false;
        boolean isEightPlayed = false;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("*** PLAYING STACK SIZE: %s ", gameData.getPlayingStack().getCards().size()));
            LOGGER.debug(String.format("*** DRAWING STACK SIZE: %s ", gameData.getDrawingStack().getCards().size()));
        }
        if (gameData.getRulesResult() instanceof RulesResultSpecial) {
            isEightPlayed = ((RulesResultSpecial) gameData.getRulesResult()).isEightPlayed();
            if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("*** EIGHT STATUS BEFORE PLAYER MOVE: %s ",
                    isEightPlayed));
        }
                }
        int move = view.requestPlayerMove(1, player.getHand().getCards().size(), isEightPlayed);

        // try to play card
        if (move >= 1 && move <= player.getHand().getCards().size()) {

            moveSuccess = playRealPlayerCard(gameData, player, move - 1, gameData.getOpenCard());

        } else if (move == 100) {
            int numberOfPenaltyCards = gameService.countPenaltyCards(gameService.getPostAction(gameData.getRulesResult()));
            givePenaltyCards(gameData, player, numberOfPenaltyCards, "You");
            moveSuccess = true;

        } else if (move == 200) {
            if (player.getHand().getCards().size() > 2) {
                view.showIncorrectMau();
            } else {
                view.showMau("You");
            }
            ((RealPlayer) player).setSaidMau(true);
        } else if (move == 300) {
            if (player.getHand().getCards().size() > 1) {
                view.showIncorrectMauMau();
            } else {
                view.showMauMau("You");
            }
            ((RealPlayer) player).setSaidMauMau(true);
        } else if (move == 400) {
            gameData.setGameStatus(GameStatus.QUIT);
            moveSuccess = true;
        } else if (move == 500){
            gameService.getPostAction(gameData.getRulesResult());
            view.showSkip("You");
            moveSuccess = true;
        }
            if (LOGGER.isDebugEnabled()) {
            LOGGER.debug((String.format("*** MOVE: %s", move)));
        }
        return moveSuccess;
    }

    private boolean playRealPlayerCard(GameData gameData, Player player, int cardNumber, Card oldOpenCard) {
        RulesResult rulesResult = gameService.makeGameMoveForRealPlayer(cardNumber, gameData);
        boolean success = rulesResult.getSuccess();
        if (success) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(String.format("*** YOU PLAYED %s of %s ", gameData.getOpenCard().getRank().toString(),
                        gameData.getOpenCard().getSuit().toString()));
            }
            view.showPlayedCard(true, "You", rulesResult.getMessage(), gameData.getOpenCard(),
                    oldOpenCard);
            if (player.getHand().getCards().size() == 1 && !((RealPlayer) player).isSaidMau()) {
                LOGGER.debug("*** 1 CARD LEFT AND MAU NOT SAID");
                view.showNotSaid("'Mau!'");
                givePenaltyCards(gameData, player, 2, "You");
            }
            if (player.getHand().getCards().isEmpty() && !((RealPlayer) player).isSaidMauMau()) {
                LOGGER.debug("*** 0 CARDS LEFT AND MAU-MAU NOT SAID");
                view.showNotSaid("'Mau-Mau!'");
                givePenaltyCards(gameData, player, 2, "You");
            } else if (player.getHand().getCards().isEmpty()) {
                gameData.setGameStatus(GameStatus.WIN);
            }

        } else {
            LOGGER.debug("*** YOU CAN'T PLAY THIS CARD NOW");
            view.showPlayedCard(false, "You", rulesResult.getMessage(), gameData.getOpenCard(),
                    oldOpenCard);
        }
        return success;
    }

    private void givePenaltyCards(GameData gameData, Player player, int numberOfPenaltyCards, String name) {
        try {
            for (int i = 0; i < numberOfPenaltyCards; i++) {
                Card drawnCard = cardService.drawCard(gameData.getDrawingStack(),
                        gameData.getPlayingStack(), player.getHand());
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(String.format("*** %s DREW A CARD", player.getName()));
                }
                view.showDrawCard(name, drawnCard);
            }
        } catch (EmptyPlayingStackException | EmptyDrawingStackException e) {
            LOGGER.error(e.getMessage());
        }
    }

    boolean processVirtualPlayerMove(GameData gameData, Player player) {
        Card oldOpenCard = gameData.getOpenCard();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug((String.format("*** %s MAKES HIS MOVE", player.getName())));
        }
        view.showVirtualPlayerTurn(player.getName());
        RulesResult rulesResult = gameService.makeGameMoveForVirtualPlayer(gameData.getOpenCard(), player.getHand(),
                gameData);
        boolean success = rulesResult.getSuccess();
        // here mau/mau-mau if required
        // here view.show what card was played (open card)
        if (success) {
            if (player.getHand().getCards().size() == 1) {
                view.showMau(player.getName());
            }
            if (player.getHand().getCards().isEmpty()) {
                view.showMauMau(player.getName());
            }
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(String.format("*** %s  PLAYED %s of %s ", player.getName(),
                        gameData.getOpenCard().getRank().toString(), gameData.getOpenCard().getSuit().toString()));
            }
            view.showPlayedCard(true, player.getName(), rulesResult.getMessage(), gameData.getOpenCard(), oldOpenCard);
        } else {
            PostAction postAction = gameService.getPostAction(gameData.getRulesResult());
            if (postAction == PostAction.SKIP) {
                view.showSkip(player.getName());
            } else {
                int numberOfPenaltyCards = gameService.countPenaltyCards(postAction);
                givePenaltyCards(gameData, player, numberOfPenaltyCards, player.getName());
            }
        }
        if (player.getHand().getCards().isEmpty()) {
            gameData.setGameStatus(GameStatus.WIN);
        }

        return true;
    }
}
