package htwberlin.mau_mau.game_management.service;

import htwberlin.mau_mau.card_management.service.CardService;
import htwberlin.mau_mau.card_management.data.Deck;
import htwberlin.mau_mau.game_management.data.GameData;
import htwberlin.mau_mau.rules_management.data.GameRulesId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;

public class GameServiceTest {
    private GameService gameService;

    @Before
    public void setUp() throws Throwable {
        gameService = new GameServiceImpl();
    }

    @Test
    public void setupNewGame() {
        //Arrange
        GameData mockGameData = createNiceMock(GameData.class);
        CardService mockCardService = createNiceMock(CardService.class);

        expect(mockCardService.createDrawingStack()).andReturn(new Deck());
        replay(mockCardService);

        //Act
        GameData data = gameService.setupNewGame("test", 5, GameRulesId.SPECIAL);

        //Assert
        verify(mockCardService);
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