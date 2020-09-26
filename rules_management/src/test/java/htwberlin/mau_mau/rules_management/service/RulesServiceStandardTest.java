package htwberlin.mau_mau.rules_management.service;

import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.data.Rank;
import htwberlin.mau_mau.card_management.data.Suit;
import htwberlin.mau_mau.rules_management.data.PostAction;
import htwberlin.mau_mau.rules_management.data.RulesResult;
import htwberlin.mau_mau.rules_management.data.RulesResultStandard;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RulesServiceStandardTest {

    private RulesServiceStandard rulesService;
    private RulesResult rulesResult;

    @Before
    public void setUp() throws Exception {
        rulesService = new RulesServiceStandard();
        rulesResult = new RulesResultStandard(false, "");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testValidatePlayerMoveValidSuit() {
        //Arrange
        Card card = new Card(Suit.CLUBS, Rank.ACE);
        Card open = new Card(Suit.CLUBS, Rank.SEVEN);
        // Act
        rulesResult = rulesService.validatePlayerMove(card, open, rulesResult, 3);
        //Assert
        Assert.assertTrue(rulesResult.getSuccess());
    }

    @Test
    public void testValidatePlayerMoveValidRank() {
        //Arrange
        Card card = new Card(Suit.CLUBS, Rank.ACE);
        Card open = new Card(Suit.HEARTS, Rank.ACE);
        // Act
        rulesResult = rulesService.validatePlayerMove(card, open, rulesResult, 3);
        //Assert
        Assert.assertTrue(rulesResult.getSuccess());
    }

    @Test
    public void testValidatePlayerMoveInvalid() {
        //Arrange
        Card card = new Card(Suit.CLUBS, Rank.ACE);
        Card open = new Card(Suit.HEARTS, Rank.SEVEN);
        // Act
        rulesResult = rulesService.validatePlayerMove(card, open, rulesResult, 3);
        //Assert
        Assert.assertFalse(rulesResult.getSuccess());
    }

    @Test
    public void testDefinePostAction() {
        //Arrange
        // Act
        PostAction postAction = rulesService.definePostAction(rulesResult);
        //Assert
        Assert.assertEquals(PostAction.DRAWONE, postAction);
    }
}