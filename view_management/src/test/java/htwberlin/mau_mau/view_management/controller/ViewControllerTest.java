package htwberlin.mau_mau.view_management.controller;

import htwberlin.mau_mau.card_management.data.Deck;
import htwberlin.mau_mau.card_management.service.CardService;
import htwberlin.mau_mau.game_management.data.GameData;
import htwberlin.mau_mau.game_management.service.GameService;
import htwberlin.mau_mau.player_management.data.Player;
import htwberlin.mau_mau.real_player_management.data.RealPlayer;
import htwberlin.mau_mau.rules_management.data.GameRulesId;
import htwberlin.mau_mau.rules_management.service.RulesProvider;
import htwberlin.mau_mau.view_management.view.UI;
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

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(EasyMockRunner.class)
public class ViewControllerTest {

    @TestSubject
    private ViewController viewController = new ViewControllerImpl();

    @Mock(MockType.NICE)
    private UI uiMock;

    @Mock(MockType.NICE)
    private GameService gameServiceMock;

    @Mock(MockType.NICE)
    private RulesProvider rulesProviderMock;

    @Mock(MockType.NICE)
    private CardService cardServiceMock;

    @Before
    public void setUp(){
    }

    @Test
    public void testRun() {
        //Arrange
        ArrayList<Player> players = new ArrayList<>();
        players.add(new RealPlayer("test"));
        expect(uiMock.requestGameRules()).andReturn(GameRulesId.STANDARD);
        expect(uiMock.requestPlayerMove(anyInt(), anyInt())).andReturn(400);
        expect(gameServiceMock.setupNewGame(anyString(), anyInt(), anyObject(GameRulesId.class)))
                .andReturn(new GameData(new Deck(), new Deck(), players, GameRulesId.STANDARD));
        replay(uiMock);
        replay(gameServiceMock);
        replay(cardServiceMock);
        //Act
        viewController.run();
        //Assert
        verify(uiMock);
        verify(gameServiceMock);
    }

    @After
    public void tearDown() throws Exception {
    }
}