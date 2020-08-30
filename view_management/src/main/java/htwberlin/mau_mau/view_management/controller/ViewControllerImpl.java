package htwberlin.mau_mau.view_management.controller;

import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.data.Rank;
import htwberlin.mau_mau.card_management.service.CardService;
import htwberlin.mau_mau.card_management.service.EmptyDrawingStackException;
import htwberlin.mau_mau.card_management.service.EmptyPlayingStackException;
import htwberlin.mau_mau.game_management.data.GameData;
import htwberlin.mau_mau.game_management.data.GameStatus;
import htwberlin.mau_mau.game_management.service.GameService;
import htwberlin.mau_mau.player_management.data.Player;
import htwberlin.mau_mau.real_player_management.data.RealPlayer;
import htwberlin.mau_mau.rules_management.data.GameRulesId;
import htwberlin.mau_mau.rules_management.data.RulesResult;
import htwberlin.mau_mau.rules_management.data.RulesResultSpecial;
import htwberlin.mau_mau.rules_management.service.RulesProvider;
import htwberlin.mau_mau.rules_management.service.RulesServiceSpecial;
import htwberlin.mau_mau.view_management.view.UI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The type View controller.
 */
@Component
public class ViewControllerImpl implements ViewController {
    private static final Logger LOGGER = LogManager.getLogger(ViewController.class);

    @Autowired
    private UI ui;

    @Autowired
    private GameService gameService;

    @Autowired
    private RulesProvider rulesProvider;

    @Autowired
    private CardService cardService;

    @Override
    public void run() {
        ui.showStartGreeting();

        String name = ui.requestPlayerName();
        int numberOfVirtualPlayers = ui.requestNumberOfVirtualPlayers();
        GameRulesId gameRulesId = ui.requestGameRules();
        GameData gameData = gameService.setupNewGame(name, numberOfVirtualPlayers, gameRulesId);

        rulesProvider.chooseRules(gameData.getGameRulesId());
        if(rulesProvider.getRulesService() instanceof RulesServiceSpecial){
            gameData.setRulesResult(new RulesResultSpecial(false,""));
            if(gameData.getOpenCard().getRank()== Rank.SEVEN){
                ((RulesResultSpecial)(gameData.getRulesResult())).setSevenCounter(1);
                ((RulesResultSpecial)(gameData.getRulesResult())).setSevenPlayed(true);
            }
        }

        ui.showNewGameGreeting(name, numberOfVirtualPlayers, gameRulesId);

        processMainFlow(gameData);
        ui.requestEnter();
        ui.close();
    }

    /**
     * Process main game flow in a console cycle.
     *
     * @param gameData current game object
     */
    private void processMainFlow(GameData gameData) {


        do {
            ui.showTable(gameData.getPlayers().get(0).getHand(), gameData.getOpenCard(), gameData.getPlayers());

            for (Player player : gameData.getPlayers()) {
                boolean success = false;

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
        } while (gameData.getGameStatus() == GameStatus.NORMAL);

        if (gameData.getGameStatus() == GameStatus.WIN) {
            if (gameData.getCurrentPlayer() instanceof RealPlayer) {
                ui.showVictoryRealPlayer(gameData.getCurrentPlayer().getName());
            } else {
                ui.showVictoryVirtualPlayer(gameData.getCurrentPlayer().getName(), gameData.getPlayers().get(0).getName());
            }
            LOGGER.debug("*** " + gameData.getCurrentPlayer().getName() + " HAS WON! ***");
        } else if (gameData.getGameStatus() == GameStatus.QUIT) {
            LOGGER.debug("*** QUIT ***");
            ui.showQuit(gameData.getPlayers().get(0).getName());
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

        boolean moveSuccess = false;
        if (player instanceof RealPlayer) {
            moveSuccess = processRealPlayerMove(gameData, player);
        } else {
            // VIRTUAL PLAYER MOVE
            moveSuccess = processVirtualPlayerMove(gameData, player);
        }
        return moveSuccess;
    }

    boolean processRealPlayerMove(GameData gameData, Player player) {
        // Real Player Move
        boolean moveSuccess = false;
        Card oldOpenCard = gameData.getOpenCard();

        LOGGER.debug("*** PLAYING STACK SIZE: " + gameData.getPlayingStack().getCards().size());
        LOGGER.debug("*** DRAWING STACK SIZE: " + gameData.getDrawingStack().getCards().size());

        int move = ui.requestPlayerMove(1, player.getHand().getCards().size());

        // try to play card
        if (move >= 1 && move <= player.getHand().getCards().size()) {
            RulesResult rulesResult = gameService.makeGameMoveForRealPlayer(move - 1, gameData);
            boolean success = rulesResult.getSuccess();
            if (success) {
                LOGGER.debug("*** YOU PLAYED " + gameData.getOpenCard().getRank().toString() + " of "
                        + gameData.getOpenCard().getSuit().toString());
                ui.showPlayedCard(success, "You", rulesResult.getMessage(), gameData.getOpenCard(),
                        oldOpenCard);
                if (player.getHand().getCards().size() == 1 && !((RealPlayer) player).isSaidMau()) {
                    LOGGER.debug("*** 1 CARD LEFT AND MAU NOT SAID");
                    ui.showNotSaid("'Mau!'");
                    try {
                        for (int i = 0; i < 2; i++) {
                            Card drawnCard = cardService.drawCard(gameData.getDrawingStack(),
                                    gameData.getPlayingStack(), player.getHand());
                            ui.showDrawCard("You", drawnCard);
                        }
                    } catch (EmptyPlayingStackException | EmptyDrawingStackException e) {
                        LOGGER.error(e.getMessage());
                    }
                }
                if (player.getHand().getCards().size() == 0 && !((RealPlayer) player).isSaidMauMau()) {
                    LOGGER.debug("*** 0 CARDS LEFT AND MAU-MAU NOT SAID");
                    ui.showNotSaid("'Mau-Mau!'");
                    try {
                        for (int i = 0; i < 2; i++) {
                            Card drawnCard = cardService.drawCard(gameData.getDrawingStack(),
                                    gameData.getPlayingStack(), player.getHand());
                            ui.showDrawCard("You", drawnCard);
                        }
                    } catch (EmptyPlayingStackException | EmptyDrawingStackException e) {
                        LOGGER.error(e.getMessage());
                    }
                } else if (player.getHand().getCards().size() == 0) {
                    gameData.setGameStatus(GameStatus.WIN);
                }

            } else {
                LOGGER.debug("*** YOU CAN'T PLAY THIS CARD NOW");
                ui.showPlayedCard(false, "You", rulesResult.getMessage(), gameData.getOpenCard(),
                        oldOpenCard);
            }

            moveSuccess = success;
            // Draw card
        } else if (move == 100) {
            try {
                int numberOfPenaltyCards = gameService.countPenaltyCards(gameData.getRulesResult());

                for (int i = 0; i < numberOfPenaltyCards; i++) {
                    Card drawnCard = cardService.drawCard(gameData.getDrawingStack(), gameData.getPlayingStack(),
                            player.getHand());
                    ui.showDrawCard("You", drawnCard);
                }
                moveSuccess = true;
            } catch (EmptyPlayingStackException | EmptyDrawingStackException e) {
                LOGGER.error(e.getMessage());
            }
        } else if (move == 200) {
            if(player.getHand().getCards().size()>2) {
                ui.showIncorrectMau();
            } else {
                ui.showMau("You");
            }
            ((RealPlayer) player).setSaidMau(true);
        } else if (move == 300) {
            if(player.getHand().getCards().size()>1) {
                ui.showIncorrectMauMau();
            } else {
                ui.showMauMau("You");
            }
            ((RealPlayer) player).setSaidMauMau(true);
        } else if (move == 400) {
            gameData.setGameStatus(GameStatus.QUIT);
            moveSuccess = true;
        }

        LOGGER.debug("*** MOVE: " + move);

        return moveSuccess;
    }

    boolean processVirtualPlayerMove(GameData gameData, Player player) {
        Card oldOpenCard = gameData.getOpenCard();

        LOGGER.debug("*** " + player.getName() + " MAKES HIS MOVE");
        ui.showVirtualPlayerTurn(player.getName());
        RulesResult rulesResult = gameService.makeGameMoveForVirtualPlayer(gameData.getOpenCard(), player.getHand(),
                gameData);
        boolean success = rulesResult.getSuccess();
        // here mau/maumau if required
        // here ui.show what card was played (open card)
        if (success) {
            if (player.getHand().getCards().size() == 1) {
                LOGGER.debug("*** " + player.getName() + " SAID MAU!");
                ui.showMau(player.getName());
            }
            if (player.getHand().getCards().size() == 0) {
                LOGGER.debug("*** " + player.getName() + " SAID MAU-MAU!");
                ui.showMauMau(player.getName());
            }
            LOGGER.debug("*** " + player.getName() + " PLAYED " + gameData.getOpenCard().getRank().toString()
                    + " of " + gameData.getOpenCard().getSuit().toString());
            ui.showPlayedCard(success, player.getName(), rulesResult.getMessage(), gameData.getOpenCard(), oldOpenCard);
        } else {
            try {
                int numberOfPenaltyCards = gameService.countPenaltyCards(gameData.getRulesResult());

                for (int i = 0; i < numberOfPenaltyCards; i++) {
                    Card drawnCard = cardService.drawCard(gameData.getDrawingStack(), gameData.getPlayingStack(),
                            player.getHand());
                    LOGGER.debug("*** " + player.getName() + " DREW A CARD");
                    ui.showDrawCard(player.getName(), drawnCard);
                }
            } catch (EmptyPlayingStackException | EmptyDrawingStackException e) {
                LOGGER.error(e.getMessage());
            }
        }
        if (player.getHand().getCards().size() == 0) {
            gameData.setGameStatus(GameStatus.WIN);
        }

        return true;
    }

}
