package htwberlin.mau_mau.view_management.controller;

import htwberlin.mau_mau.card_management.data.Card;
import htwberlin.mau_mau.card_management.data.Deck;
import htwberlin.mau_mau.card_management.service.CardService;
import htwberlin.mau_mau.game_management.data.GameData;
import htwberlin.mau_mau.game_management.service.GameService;
import htwberlin.mau_mau.player_management.data.Player;
import htwberlin.mau_mau.real_player_management.data.RealPlayer;
import htwberlin.mau_mau.rules_management.data.GameRulesId;
import htwberlin.mau_mau.rules_management.data.RulesResultSpecial;
import htwberlin.mau_mau.rules_management.service.RulesProvider;
import htwberlin.mau_mau.rules_management.service.RulesService;
import htwberlin.mau_mau.view_management.view.View;
import org.easymock.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;

import static org.easymock.EasyMock.*;

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(EasyMockRunner.class)
public class ViewControllerTest {

    @TestSubject
    private ViewController viewController = new ViewControllerImpl();

    @Mock(MockType.NICE)
    private View viewMock;

    @Mock(MockType.NICE)
    private GameService gameServiceMock;

    @Mock(MockType.NICE)
    private RulesProvider rulesProviderMock;

    @Mock(MockType.NICE)
    private CardService cardServiceMock;

    @Mock(MockType.NICE)
    private RulesService rulesServiceMock;

    @Mock(MockType.NICE)
    private EntityManager entityManagerMock;

    @Mock(MockType.NICE)
    private EntityTransaction entityTransactionMock;

    @Before
    public void setUp() {
    }

    @Test
    public void testRun() {
        //Arrange
        ArrayList<Player> players = new ArrayList<>();
        players.add(new RealPlayer("test"));
        GameData gameData = new GameData(new Deck(), new Deck(), players, GameRulesId.STANDARD);
        gameData.setCurrentPlayer(players.get(0));
        expect(viewMock.requestNumberOfVirtualPlayers()).andReturn(0);
        expect(viewMock.requestGameRules()).andReturn(GameRulesId.STANDARD);
        expect(viewMock.requestPlayerMove(anyInt(), anyInt(), anyBoolean())).andReturn(400);
        expect(gameServiceMock.setupNewGame(anyString(), anyInt(), anyObject(GameRulesId.class)))
                .andReturn(gameData);
        expect(rulesProviderMock.getRulesService()).andReturn(rulesServiceMock);
        expect(rulesServiceMock.setUpRules(EasyMock.<Card>anyObject())).andReturn(new RulesResultSpecial(false, ""));
        expect(entityManagerMock.getTransaction()).andReturn(entityTransactionMock).times(2);

        replay(viewMock);
        replay(gameServiceMock);
        replay(cardServiceMock);
        replay(rulesProviderMock);
        replay(rulesServiceMock);
        replay(entityManagerMock);
        replay(entityTransactionMock);
        //Act
        viewController.run();
        //Assert
        verify(viewMock);
        verify(gameServiceMock);
    }

    @After
    public void tearDown() throws Exception {
    }
}