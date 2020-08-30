package htwberlin.mau_mau.rules_management.service;

import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.data.Rank;
import htwberlin.mau_mau.card_management.data.Suit;
import htwberlin.mau_mau.rules_management.data.RulesResult;
import htwberlin.mau_mau.rules_management.data.RulesResultSpecial;
import htwberlin.mau_mau.rules_management.data.RulesResultStandard;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RulesServiceSpecialTest {

    private RulesServiceSpecial rulesService;
    private RulesResult rulesResult;

    @Before
    public void setUp() throws Exception {
        rulesService = new RulesServiceSpecial();
        rulesResult = new RulesResultSpecial(false, "");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void validatePlayerMoveValidSuit() {
        //Arrange
        Card card = new Card(Suit.CLUBS, Rank.ACE);
        Card open = new Card(Suit.CLUBS, Rank.EIGHT);
        // Act
        rulesResult = rulesService.validatePlayerMove(card, open, rulesResult);
        //Assert
        Assert.assertTrue(rulesResult.getSuccess());
    }

    @Test
    public void validatePlayerMoveValidRank() {
        //Arrange
        Card card = new Card(Suit.CLUBS, Rank.ACE);
        Card open = new Card(Suit.HEARTS, Rank.ACE);
        // Act
        rulesResult = rulesService.validatePlayerMove(card, open, rulesResult);
        //Assert
        Assert.assertTrue(rulesResult.getSuccess());
    }

    @Test
    public void validatePlayerMoveInvalid() {
        //Arrange
        Card card = new Card(Suit.CLUBS, Rank.ACE);
        Card open = new Card(Suit.HEARTS, Rank.EIGHT);
        // Act
        rulesResult = rulesService.validatePlayerMove(card, open, rulesResult);
        //Assert
        Assert.assertFalse(rulesResult.getSuccess());
    }

    @Test
    public void countPenaltyCardsNormal() {
        //Arrange
        ((RulesResultSpecial)rulesResult).setSevenPlayed(false);
        // Act
        int count = rulesService.countPenaltyCards(rulesResult);
        //Assert
        Assert.assertEquals(1 ,count);
    }

    @Test
    public void countPenaltyCardsSevenFirstTurn() {
        //Arrange
        ((RulesResultSpecial)rulesResult).setSevenPlayed(true);
        ((RulesResultSpecial)rulesResult).setSevenCounter(1);
        // Act
        int count = rulesService.countPenaltyCards(rulesResult);
        //Assert
        Assert.assertEquals(2 ,count);
    }

    @Test
    public void countPenaltyCardsSevenSecondTurn() {
        //Arrange
        ((RulesResultSpecial)rulesResult).setSevenPlayed(true);
        ((RulesResultSpecial)rulesResult).setSevenCounter(2);
        // Act
        int count = rulesService.countPenaltyCards(rulesResult);
        //Assert
        Assert.assertEquals(4 ,count);
    }

}