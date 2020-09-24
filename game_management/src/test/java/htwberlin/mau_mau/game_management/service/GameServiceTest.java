package htwberlin.mau_mau.game_management.service;

import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.data.Deck;
import htwberlin.mau_mau.card_management.data.Rank;
import htwberlin.mau_mau.card_management.data.Suit;
import htwberlin.mau_mau.card_management.service.CardService;
import htwberlin.mau_mau.game_management.data.GameData;
import htwberlin.mau_mau.player_management.data.Player;
import htwberlin.mau_mau.real_player_management.data.RealPlayer;
import htwberlin.mau_mau.real_player_management.service.RealPlayerService;
import htwberlin.mau_mau.rules_management.data.GameRulesId;
import htwberlin.mau_mau.rules_management.data.PostAction;
import htwberlin.mau_mau.rules_management.data.RulesResult;
import htwberlin.mau_mau.rules_management.data.RulesResultStandard;
import htwberlin.mau_mau.rules_management.service.RulesProvider;
import htwberlin.mau_mau.rules_management.service.RulesServiceStandard;
import htwberlin.mau_mau.virtual_player_management.data.VirtualPlayer;
import htwberlin.mau_mau.virtual_player_management.service.VirtualPlayerService;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;

@RunWith(EasyMockRunner.class)
public class GameServiceTest {

    @TestSubject
    private GameService gameService = new GameServiceImpl();

    @Mock(MockType.NICE)
    private RealPlayerService realPlayerServiceMock;

    @Mock(MockType.NICE)
    private VirtualPlayerService virtualPlayerServiceMock;

    @Mock(MockType.NICE)
    private CardService cardServiceMock;

    @Mock(MockType.NICE)
    private RulesProvider rulesProviderMock;

    @Mock(MockType.NICE)
    private RulesServiceStandard rulesServiceStandardMock;

    private GameData gameData;

    @Before
    public void setUp() throws Exception {
        Deck drawingStack = new Deck();
        drawingStack.getCards().add(new Card(Suit.HEARTS, Rank.ACE));
        drawingStack.getCards().add(new Card(Suit.HEARTS, Rank.SEVEN));
        Deck playingStack = new Deck();
        playingStack.getCards().add(new Card(Suit.CLUBS, Rank.ACE));
        ArrayList<Player> players = new ArrayList<>();
        players.add(new RealPlayer("test"));
        gameData = new GameData(drawingStack, playingStack, players, GameRulesId.STANDARD);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSetupNewGame() {
        //Arrange
        Deck drawingStack = new Deck();
        drawingStack.getCards().add(new Card(Suit.HEARTS, Rank.ACE));
        drawingStack.getCards().add(new Card(Suit.SPADES, Rank.QUEEN));
        expect(realPlayerServiceMock.createRealPlayer(anyString())).andReturn(new RealPlayer("test"));
        expect(virtualPlayerServiceMock.createVirtualPlayer()).andReturn(new VirtualPlayer("test virtual"));
        expect(cardServiceMock.createDrawingStack()).andReturn(drawingStack);
        replay(realPlayerServiceMock);
        replay(virtualPlayerServiceMock);
        replay(cardServiceMock);
        //Act
        gameService.setupNewGame("test", 1, GameRulesId.STANDARD);

        //Assert
        verify(realPlayerServiceMock);
        verify(virtualPlayerServiceMock);
        verify(cardServiceMock);
    }

    @Test
    public void testDealCardsToPlayers() {
        //Arrange
        replay(cardServiceMock);
        //Act
        gameService.dealCardsToPlayers(gameData);
        //Assert
        verify(cardServiceMock);
    }

    @Test
    public void testMakeGameMoveForRealPlayer() {
        //Arrange
        RulesResultStandard rulesResultStandard = new RulesResultStandard(true,"test");
        gameData.setRulesResult(rulesResultStandard);
        expect(rulesProviderMock.getRulesService()).andReturn(rulesServiceStandardMock);
        expect(rulesServiceStandardMock.validatePlayerMove(anyObject(Card.class), anyObject(Card.class),
                anyObject(RulesResult.class))).andReturn(new RulesResultStandard(true,"test2"));
        replay(rulesServiceStandardMock);
        replay(rulesProviderMock);
        gameData.getPlayers().get(0).getHand().getCards().add(new Card(Suit.HEARTS, Rank.ACE));
        gameData.setOpenCard(new Card(Suit.DIAMONDS, Rank.EIGHT));
        //Act
        RulesResult result = gameService.makeGameMoveForRealPlayer(0,gameData);
        //Assert
        verify(rulesServiceStandardMock);
        verify(rulesProviderMock);
        assertTrue(result.getSuccess());
        assertEquals("test2", result.getMessage());
    }

    @Test
    public void testMakeGameMoveForVirtualPlayer() {
        RulesResultStandard rulesResultStandard = new RulesResultStandard(true,"test");
        gameData.setRulesResult(rulesResultStandard);
        expect(rulesProviderMock.getRulesService()).andReturn(rulesServiceStandardMock);
        expect(rulesServiceStandardMock.validatePlayerMove(anyObject(Card.class), anyObject(Card.class),
                anyObject(RulesResult.class))).andReturn(new RulesResultStandard(true,"test2"));
        replay(rulesServiceStandardMock);
        replay(rulesProviderMock);
        gameData.getPlayers().get(0).getHand().getCards().add(new Card(Suit.HEARTS, Rank.ACE));
        gameData.setOpenCard(new Card(Suit.DIAMONDS, Rank.EIGHT));
        //Act
        RulesResult result = gameService.makeGameMoveForVirtualPlayer(gameData.getOpenCard(),
                gameData.getPlayers().get(0).getHand(), gameData);
        //Assert
        verify(rulesServiceStandardMock);
        verify(rulesProviderMock);
        assertTrue(result.getSuccess());
        assertEquals("test2", result.getMessage());
    }

    @Test
    public void testGetPostAction() {
        //Arrange
        expect(rulesProviderMock.getRulesService()).andReturn(rulesServiceStandardMock);
        expect(rulesServiceStandardMock.definePostAction(anyObject(RulesResult.class))).andReturn(PostAction.DRAWONE);
        replay(rulesProviderMock);
        replay(rulesServiceStandardMock);
        //Act
       PostAction postAction = gameService.getPostAction(new RulesResultStandard(false,""));
       //Assert
        verify(rulesProviderMock);
        assertEquals(PostAction.DRAWONE, postAction);
    }
}