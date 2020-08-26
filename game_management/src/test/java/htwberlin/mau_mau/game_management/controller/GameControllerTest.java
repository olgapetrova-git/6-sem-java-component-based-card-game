package htwberlin.mau_mau.game_management.controller;

import htwberlin.mau_mau.card_management.controller.CardController;
import htwberlin.mau_mau.card_management.model.Deck;
import htwberlin.mau_mau.game_management.model.GameData;
import htwberlin.mau_mau.rules_management.model.GameRulesId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class GameControllerTest {
    private GameController gameController;

    @Before
    public void setUp() throws Throwable {
        gameController = new GameControllerImpl();
    }

    @Test
    public void setupNewGame() {
        //Arrange
        GameData mockGameData = createNiceMock(GameData.class);
        CardController mockCardController = createNiceMock(CardController.class);

        expect(mockCardController.createDeckOfCards()).andReturn(new Deck());
        replay(mockCardController);

        //Act
        GameData data = gameController.setupNewGame("test", 5, GameRulesId.SPECIAL);

        //Assert
        verify(mockCardController);
    }


    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void dealCardsToPlayers() {
    }

    @Test
    public void makeGameMoveForRealPlayer() {
    }

    @Test
    public void makeGameMoveForVirtualPlayer() {
    }
}