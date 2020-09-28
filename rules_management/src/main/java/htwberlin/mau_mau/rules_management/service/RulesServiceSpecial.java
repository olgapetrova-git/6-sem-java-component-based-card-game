package htwberlin.mau_mau.rules_management.service;

import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.data.Rank;
import htwberlin.mau_mau.player_management.data.Player;
import htwberlin.mau_mau.rules_management.data.PostAction;
import htwberlin.mau_mau.rules_management.data.RulesResult;
import htwberlin.mau_mau.rules_management.data.RulesResultSpecial;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Qualifier("RulesServiceSpecial")
public class RulesServiceSpecial implements RulesService {
    private static final Logger LOGGER = LogManager.getLogger(RulesServiceSpecial.class);
    private static final String[] messages = {"Success! ", "Cool! ", "Great! ", "WOW! ", "Prima! ", "Nice move. ", "OK. ",
            "Phew! ", "Awesome! ", "Fine. ", "Alright! ", "Not bad. ", "Good. ", "Okay. ", "Super! ", "Well done. ",
            "Quite good. ", "Acceptable. "};

    @Override
    public RulesResult validatePlayerMove(Card card, Card openCard, RulesResult rulesResult, int numberOfPlayers) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("*** VALIDATING %s of %s AGAINST %s of %s USING SPECIAL RULES",
                    card.getRank().toString(), card.getSuit().toString(),
                    openCard.getRank().toString(), openCard.getSuit().toString()));
        }

        rulesResult.setMessage("");
        if (((RulesResultSpecial) rulesResult).isSevenPlayed()) {
            validateSeven(card, ((RulesResultSpecial) rulesResult));
        } else if (((RulesResultSpecial) rulesResult).isEightPlayed()) {
            validateEight(card, ((RulesResultSpecial) rulesResult));
        } else if (((RulesResultSpecial) rulesResult).isJackPlayed()
                || card.getRank()==Rank.JACK) {
            validateJack(card, openCard, ((RulesResultSpecial) rulesResult), numberOfPlayers);
        } else if ((card.getSuit() == openCard.getSuit())
                || (card.getRank() == openCard.getRank())) {
            validateDefaultSpecial(card, rulesResult, numberOfPlayers);
        } else {
            rulesResult.setSuccess(false);
            rulesResult.addMessage("You can't play this card now. Choose another card from your hand or \n"
                    + "draw a new card from the drawing stack, please.");
        }

        return rulesResult;
    }

    private void validateDefaultSpecial(Card card, RulesResult rulesResult, int numberOfPlayers){
        if (card.getRank() == Rank.SEVEN) {
            validateSeven(card, ((RulesResultSpecial) rulesResult));
        } else if (card.getRank() == Rank.EIGHT) {
            validateEight(card, ((RulesResultSpecial) rulesResult));
        } else if (card.getRank() == Rank.NINE) {
            validateNine(((RulesResultSpecial) rulesResult), numberOfPlayers);
        } else {
            int randomNum = ThreadLocalRandom.current().nextInt(0, messages.length);
            String successMessage = messages[randomNum];
            rulesResult.setSuccess(true);
            rulesResult.addMessage(successMessage);
        }
        LOGGER.debug("*** CARDS MATCH");
    }

    /**
     * Validates Player's move according to SEVEN rules.
     *
     * @param card        played card
     * @param rulesResult object containing rules validation result and text message
     */
    private void validateSeven(Card card, RulesResultSpecial rulesResult) {
        if (card.getRank() == Rank.SEVEN) {
            LOGGER.debug("*** SEVEN RULES ");
            rulesResult.setSuccess(true);
            if (rulesResult.getSevenCounter() == 0) {
                rulesResult.addMessage("SEVEN is played! Next player must play SEVEN or draw two cards!");
                rulesResult.setSevenPlayed(true);
                rulesResult.setSevenCounter(1);
            } else if (rulesResult.getSevenCounter() == 1) {
                rulesResult.addMessage("SEVEN is played again! Next Player must play SEVEN or draw four cards!");
                rulesResult.setSevenCounter(2);
            } else if (rulesResult.getSevenCounter() == 2) {
                rulesResult.addMessage("SEVEN is played third time! Game continues as usual.");
                rulesResult.setSevenPlayed(false);
                rulesResult.setSevenCounter(0);
            }
        } else {
            rulesResult.setSuccess(false);
            if (rulesResult.getSevenCounter() == 1) {
                rulesResult.addMessage("Play SEVEN or draw 2! Choose SEVEN if you have it or " +
                        "enter 'D' to draw (you will be given two cards).");
            } else if (rulesResult.getSevenCounter() == 2) {
                rulesResult.addMessage("Play SEVEN or draw 4! Choose another card or " +
                        "enter 'D' to draw (you will be given four cards).");
            }
        }
    }

    private void validateEight(Card card, RulesResultSpecial rulesResult) {
        if (card.getRank() == Rank.EIGHT) {
            LOGGER.debug("*** EIGHT RULES ");
            rulesResult.setSuccess(true);
            if (rulesResult.getEightCounter() == 0) {
                rulesResult.addMessage("EIGHT is played! Next player must play EIGHT or skip a round!");
                rulesResult.setEightPlayed(true);
                rulesResult.setEightCounter(1);
            } else if (rulesResult.getEightCounter() == 1) {
                rulesResult.addMessage("EIGHT is played again! Next Player must play EIGHT or skip a round!");
                rulesResult.setEightCounter(2);
            } else if (rulesResult.getEightCounter() == 2) {
                rulesResult.addMessage("EIGHT is played third time! Game continues as usual.");
                rulesResult.setEightPlayed(false);
                rulesResult.setEightCounter(0);
            }
        } else {
            rulesResult.setSuccess(false);

            rulesResult.addMessage("Play EIGHT or skip a round! Choose EIGHT if you have it or " +
                    "enter 'S' to skip.");
        }
    }

    private void validateNine(RulesResultSpecial rulesResult, int numberOfPlayers) {
        LOGGER.debug("*** NINE RULES ");
        rulesResult.setNinePlayed(true);
        rulesResult.setSuccess(true);
        if(numberOfPlayers==2) {
            rulesResult.addMessage("NINE is played! The direction of play changes.\n" +
                    "Because there are only two players, the card means skipping a round for next player.");
        } else {
            rulesResult.addMessage("NINE is played! The direction of play changes.");
        }
    }

    private void validateJack(Card card, Card openCard, RulesResultSpecial rulesResult, int numberOfPlayers) {
        LOGGER.debug("*** JACK RULES ");
        if(!rulesResult.isJackPlayed()) {
            if(openCard.getRank()!=Rank.JACK){
                rulesResult.setJackPlayed(true);
                rulesResult.addMessage("JACK is played! Player will now make a wish for a suit. \n" +
            "Next player must play a card with that suit or draw two cards.");
                rulesResult.setSuccess(true);
            } else {
                rulesResult.addMessage("JACK can't be played against another JACK.");
                rulesResult.setSuccess(false);
            }
        } else {
            if(card.getSuit() == rulesResult.getWish()){
                rulesResult.setJackPlayed(false);
                rulesResult.setSuccess(true);
                rulesResult.setMessage(card.getSuit().toString() + " played! Wish fulfilled.\n");
                validateDefaultSpecial(card, rulesResult, numberOfPlayers);
            } else {
                rulesResult.addMessage("Play " + rulesResult.getWish().toString() + " or draw 2! Choose " +
                        rulesResult.getWish().toString() + " if you have it or " +
                        "enter 'D' to draw (you will be given two cards).");
                rulesResult.setSuccess(false);
            }
        }
    }

    @Override
    public PostAction definePostAction(RulesResult rulesResult) {
        if (((RulesResultSpecial) rulesResult).isSevenPlayed()) {
            if (((RulesResultSpecial) rulesResult).getSevenCounter() == 1) {
                ((RulesResultSpecial) rulesResult).setSevenPlayed(false);
                ((RulesResultSpecial) rulesResult).setSevenCounter(0);
                return PostAction.DRAWTWO;
            } else if (((RulesResultSpecial) rulesResult).getSevenCounter() == 2) {
                ((RulesResultSpecial) rulesResult).setSevenPlayed(false);
                ((RulesResultSpecial) rulesResult).setSevenCounter(0);
                return PostAction.DRAWFOUR;
            }
        } else if (((RulesResultSpecial) rulesResult).isEightPlayed()) {
            ((RulesResultSpecial) rulesResult).setEightPlayed(false);
            ((RulesResultSpecial) rulesResult).setEightCounter(0);
            return PostAction.SKIP;
        } else if (((RulesResultSpecial) rulesResult).isJackPlayed()) {
            ((RulesResultSpecial) rulesResult).setJackPlayed(false);
            return PostAction.DRAWTWO;
        }
        return PostAction.DRAWONE;
    }

    @Override
    public RulesResult setUpRules(Card openCard) {
        RulesResultSpecial rulesResultSpecial = new RulesResultSpecial(false, "");
        if (openCard.getRank() == Rank.SEVEN) {
            rulesResultSpecial.setSevenCounter(1);
            rulesResultSpecial.setSevenPlayed(true);
        }
        if (openCard.getRank() == Rank.EIGHT) {
            rulesResultSpecial.setEightCounter(1);
            rulesResultSpecial.setEightPlayed(true);
        }
        return rulesResultSpecial;
    }

    @Override
    public Player defineNextPlayer(RulesResult rulesResult, List<Player> players) {
        int oldCurrentPlayerIndex = rulesResult.getCurrentPlayerIndex();

        if(((RulesResultSpecial)rulesResult).isNinePlayed()){
            ((RulesResultSpecial)rulesResult).setNinePlayed(false);

            if(players.size()==2){
                return players.get(oldCurrentPlayerIndex);
            } else {
                rulesResult.setDirection(-rulesResult.getDirection());
            }
        }

        int newCurrentPlayerIndex = oldCurrentPlayerIndex + rulesResult.getDirection();
        if (newCurrentPlayerIndex >= players.size()){
            newCurrentPlayerIndex = 0;
        }
        if (newCurrentPlayerIndex < 0){
            newCurrentPlayerIndex = players.size() - 1;
        }

        rulesResult.setCurrentPlayerIndex(newCurrentPlayerIndex);
        return players.get(newCurrentPlayerIndex);
    }
}
