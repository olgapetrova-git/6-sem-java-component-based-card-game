package htwberlin.mau_mau.rules_management.service;

import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.data.Deck;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("RulesServiceStandard")
public class RulesServiceStandard implements RulesService {
    public static final int NUMBER_OF_CARDS_IN_DECK = 52;

    private static final Logger LOGGER = LogManager.getLogger(RulesServiceStandard.class);

    @Override
    public boolean validatePlayerMove(Card card, Card openCard) {
        boolean result = false;

        LOGGER.debug(("*** VALIDATING "
                + card.getRank().toString() + " of " + card.getSuit().toString())
                + " AGAINST "
                + openCard.getRank().toString() + " of " + openCard.getSuit().toString()
                + " USING STANDARD RULES"
        );

        if((card.getSuit() == openCard.getSuit())
                || (card.getRank() == openCard.getRank()))
        {
            LOGGER.debug("*** CARDS MATCH");
            result = true;
        }

        return result;
    }

    @Override
    public boolean validateWin(Deck hand) {
        return false;
    }


}
