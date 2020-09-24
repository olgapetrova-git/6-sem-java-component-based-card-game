package htwberlin.mau_mau.rules_management.service;

import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.data.Rank;
import htwberlin.mau_mau.card_management.data.Suit;
import htwberlin.mau_mau.rules_management.data.PostAction;
import htwberlin.mau_mau.rules_management.data.RulesResult;
import htwberlin.mau_mau.rules_management.data.RulesResultSpecial;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RulesServiceSpecialTest {

    private RulesServiceSpecial rulesService;
    private RulesResult rulesResult;

    @Before
    public void setUp(){
        rulesService = new RulesServiceSpecial();
        rulesResult = new RulesResultSpecial(false, "");
    }

    @After
    public void tearDown(){
    }

    @Test
    public void testValidatePlayerMoveValidSuit() {
        //Arrange
        Card card = new Card(Suit.CLUBS, Rank.ACE);
        Card open = new Card(Suit.CLUBS, Rank.EIGHT);
        // Act
        rulesResult = rulesService.validatePlayerMove(card, open, rulesResult);
        //Assert
        Assert.assertTrue(rulesResult.getSuccess());
    }

    @Test
    public void testValidatePlayerMoveValidRank() {
        //Arrange
        Card card = new Card(Suit.CLUBS, Rank.ACE);
        Card open = new Card(Suit.HEARTS, Rank.ACE);
        // Act
        rulesResult = rulesService.validatePlayerMove(card, open, rulesResult);
        //Assert
        Assert.assertTrue(rulesResult.getSuccess());
    }

    @Test
    public void testValidatePlayerMoveInvalid() {
        //Arrange
        Card card = new Card(Suit.CLUBS, Rank.ACE);
        Card open = new Card(Suit.HEARTS, Rank.EIGHT);
        // Act
        rulesResult = rulesService.validatePlayerMove(card, open, rulesResult);
        //Assert
        Assert.assertFalse(rulesResult.getSuccess());
    }

    @Test
    public void testDefinePostActionNormal() {
        //Arrange
        ((RulesResultSpecial)rulesResult).setSevenPlayed(false);
        // Act
        PostAction postAction = rulesService.definePostAction(rulesResult);
        //Assert
        Assert.assertEquals(PostAction.DRAWONE ,postAction);
    }

    @Test
    public void testDefinePostActionSevenFirstTurn() {
        //Arrange
        ((RulesResultSpecial)rulesResult).setSevenPlayed(true);
        ((RulesResultSpecial)rulesResult).setSevenCounter(1);
        // Act
        PostAction postAction = rulesService.definePostAction(rulesResult);
        //Assert
        Assert.assertEquals(PostAction.DRAWTWO, postAction);
    }

    @Test
    public void testDefinePostActionSevenSecondTurn() {
        //Arrange
        ((RulesResultSpecial)rulesResult).setSevenPlayed(true);
        ((RulesResultSpecial)rulesResult).setSevenCounter(2);
        // Act
        PostAction postAction = rulesService.definePostAction(rulesResult);
        //Assert
        Assert.assertEquals(PostAction.DRAWFOUR ,postAction);
    }
    @Test
    public void testDefinePostActionEight(){
        //Arrange
        ((RulesResultSpecial)rulesResult).setEightPlayed(true);
        ((RulesResultSpecial)rulesResult).setEightCounter(1);
        //Act
        PostAction postAction = rulesService.definePostAction(rulesResult);
        //Assert
        Assert.assertEquals(PostAction.SKIP ,postAction);
    }

}