package htwberlin.mau_mau.rules_management.service;


import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.data.Deck;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("SpecialRulesServiceImpl")
public class SpecialRulesServiceImpl implements GameRulesService {
    public static final int NUMBER_OF_CARDS_IN_DECK = 32;

    @Override
    public boolean validatePlayerMove(Card card, Card topmostCard) {
        return false;
    }

    @Override
    public boolean validateWin(Deck hand) {
        return false;
    }


}
