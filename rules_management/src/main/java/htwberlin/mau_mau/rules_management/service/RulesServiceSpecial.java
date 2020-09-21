package htwberlin.mau_mau.rules_management.service;


import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.data.Rank;
import htwberlin.mau_mau.rules_management.data.RulesResult;
import htwberlin.mau_mau.rules_management.data.RulesResultSpecial;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@Qualifier("RulesServiceSpecial")
public class RulesServiceSpecial implements RulesService {
    private static final Logger LOGGER = LogManager.getLogger(RulesServiceSpecial.class);
    private static final String[] messages = {"Success! ", "Cool! ", "Great! ", "WOW! ", "Prima! ", "Nice move. ", "OK. ",
            "Phew! ", "Awesome! ", "Fine. ", "Alright! ", "Not bad. ", "Good. ", "Okay. ", "Super! ", "Well done. ",
            "Quite good. ", "Acceptable. "};

    @Override
    public RulesResult validatePlayerMove(Card card, Card openCard, RulesResult rulesResult) {
        /*
                    "* A SEVEN forces the next player to take two cards from the drawing stack \n" +
                    "- unless he can counter the attack with his own SEVEN. After that, the game continues as usual.\n" +
                    "* An EIGHT means: skip a round - unless you can counter the attack with your own eight \n" +
                    "and let the following player skip. This does not continue after that.\n" +
                    "* A JACK is a wish card, it gives a player the right to wish a suit of card.\n" +
                    "The next player have to play with wished suit or take 2 cards. Moreover, JACK can be played \n" +
                    "on any card. But JACK on JACK is forbidden.");
         */

        LOGGER.debug(String.format("*** VALIDATING %s of %s AGAINST %s of %s USING SPECIAL RULES",
                card.getRank().toString(), card.getSuit().toString(),
                openCard.getRank().toString(), openCard.getSuit().toString()));


        if (((RulesResultSpecial) rulesResult).isSevenPlayed()) {
            validateSeven(card, ((RulesResultSpecial) rulesResult));
        } else if ((card.getSuit() == openCard.getSuit())
                || (card.getRank() == openCard.getRank())) {
            if (card.getRank() == Rank.SEVEN) {
                rulesResult.setSuccess(true);
                rulesResult.setMessage("SEVEN is played! Next player must play SEVEN or draw two cards!");
                ((RulesResultSpecial) rulesResult).setSevenPlayed(true);
                ((RulesResultSpecial) rulesResult).setSevenCounter(1);
            } else {
                int randomNum = ThreadLocalRandom.current().nextInt(0, messages.length);
                String successMessage = messages[randomNum];

                rulesResult.setSuccess(true);
                rulesResult.setMessage(successMessage);
            }
            LOGGER.debug("*** CARDS MATCH");
        } else {
            rulesResult.setSuccess(false);
            rulesResult.setMessage("You can't play this card now. Choose another card from " +
                    "your hand or \n" +
                    "draw a new card from the drawing stack, please.");
        }

        return rulesResult;
    }

    /**
     * Validates Player's move if SEVEN has been played.
     *
     * @param card played card
     * @param rulesResult object containing rules validation result and text message
     */
    private void validateSeven(Card card, RulesResultSpecial rulesResult) {
        if (card.getRank() == Rank.SEVEN) {
            LOGGER.debug("*** CARDS MATCH - SEVEN");
            rulesResult.setSuccess(true);
            if (rulesResult.getSevenCounter() == 1) {
                rulesResult.setMessage("SEVEN is played again! Next Player must play SEVEN or draw four cards!\n");
                rulesResult.setSevenCounter(2);
            } else if (rulesResult.getSevenCounter() == 2) {
                rulesResult.setMessage("SEVEN is played third time! Game continues as usual.\n");
                rulesResult.setSevenPlayed(false);
                rulesResult.setSevenCounter(0);
            }
        } else {
            rulesResult.setSuccess(false);
            if (rulesResult.getSevenCounter() == 1) {
                rulesResult.setMessage("Play SEVEN or draw 2! Choose SEVEN if you have it or " +
                        "draw (you will be given two cards).");
            } else if (rulesResult.getSevenCounter() == 2) {
                rulesResult.setMessage("Play SEVEN or draw 4! Choose another card or " +
                        "draw (you will be given four cards).");
            }
        }
    }

    @Override
    public int countPenaltyCards(RulesResult rulesResult) {
        if (((RulesResultSpecial) rulesResult).isSevenPlayed()) {
            if (((RulesResultSpecial) rulesResult).getSevenCounter() == 1) {
                ((RulesResultSpecial) rulesResult).setSevenPlayed(false);
                ((RulesResultSpecial) rulesResult).setSevenCounter(0);
                return 2;
            } else if (((RulesResultSpecial) rulesResult).getSevenCounter() == 2) {
                ((RulesResultSpecial) rulesResult).setSevenPlayed(false);
                ((RulesResultSpecial) rulesResult).setSevenCounter(0);
                return 4;
            }
        }

        return 1;
    }

    @Override
    public RulesResult setUpRules(Card openCard) {
        RulesResultSpecial rulesResultSpecial = new RulesResultSpecial(false, "");
        if (openCard.getRank() == Rank.SEVEN) {
            rulesResultSpecial.setSevenCounter(1);
            rulesResultSpecial.setSevenPlayed(true);
        }

        return rulesResultSpecial;
    }
}
