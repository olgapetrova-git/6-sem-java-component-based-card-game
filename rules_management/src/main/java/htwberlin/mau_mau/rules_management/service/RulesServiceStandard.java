package htwberlin.mau_mau.rules_management.service;

import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.player_management.data.Player;
import htwberlin.mau_mau.rules_management.data.PostAction;
import htwberlin.mau_mau.rules_management.data.RulesResult;
import htwberlin.mau_mau.rules_management.data.RulesResultStandard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The type RulesServiceStandard implements operations to check game conditions according to the set of rules.
 */
@Component
@Qualifier("RulesServiceStandard")
public class RulesServiceStandard implements RulesService {

    private static final Logger LOGGER = LogManager.getLogger(RulesServiceStandard.class);
    private static final String[] messages = {"Success! ", "Cool! ", "Great! ", "WOW! ", "Prima! ", "Nice move. ", "OK. ",
            "Phew! ", "Awesome! ", "Fine. ", "Alright! ", "Not bad. ", "Good. ", "Okay. ", "Super! ", "Well done. ",
            "Quite good. ", "Acceptable. "};

    @Override
    public RulesResult validatePlayerMove(Card card, Card openCard, RulesResult rulesResult, int numberOfPlayers) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("*** VALIDATING %s of %s AGAINST %s of %s USING STANDARD RULES",
                    card.getRank().toString(), card.getSuit().toString(), openCard.getRank().toString(),
                    openCard.getSuit().toString()));
        }
        if ((card.getSuit() == openCard.getSuit())
                || (card.getRank() == openCard.getRank())) {
            LOGGER.debug("*** CARDS MATCH");
            rulesResult.setSuccess(true);

            int randomNum = ThreadLocalRandom.current().nextInt(0, messages.length);
            String successMessage = messages[randomNum];

            rulesResult.setMessage(successMessage);
        } else {
            rulesResult.setSuccess(false);
            rulesResult.setMessage("You can't play this card now. Choose another card from " +
                    "your hand or \n" +
                    "draw a new card from the drawing stack, please.");
        }

        return rulesResult;
    }

    @Override
    public PostAction definePostAction(RulesResult rulesResult) {
        return PostAction.DRAWONE;
    }

    @Override
    public RulesResult setUpRules(Card openCard) {
          return new RulesResultStandard(false,"");
    }

    @Override
    public Player defineNextPlayer(RulesResult rulesResult, List<Player> players) {
        int oldCurrentPlayerIndex = rulesResult.getCurrentPlayerIndex();

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
