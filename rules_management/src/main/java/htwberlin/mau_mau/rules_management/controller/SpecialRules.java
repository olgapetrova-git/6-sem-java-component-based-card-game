package htwberlin.mau_mau.rules_management.controller;


import htwberlin.mau_mau.card_management.model.Card;
import htwberlin.mau_mau.card_management.model.Deck;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("SpecialRules")
public class SpecialRules implements GameRules{
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
