package htwberlin.mau_mau.game_management.service;

import htwberlin.mau_mau.game_management.data.GameData;
import htwberlin.mau_mau.rules_management.data.GameRulesId;
import org.easymock.TestSubject;
import org.junit.After;
import org.junit.Before;

public class GameServiceJpaTest {
    @TestSubject
    private GameService gameService = new GameServiceImpl();

/*    @Mock(MockType.NICE)
    private EntityManager entityManagerMock;

    @Mock(MockType.NICE)
    private EntityTransaction entityTransactionMock;*/

    private GameData gameData;

    @Before
    public void setUp() throws Exception {

        gameData = new GameData(null, null, null, GameRulesId.STANDARD);
    }

    @After
    public void tearDown() throws Exception {

    }




/* @Test
    public void saveToDB() {
        expect(entityManagerMock.getTransaction()).andReturn(entityTransactionMock);
        replay(entityTransactionMock);
        replay(entityManagerMock);
        gameService.saveToDB(gameData);
        verify(entityManagerMock);
        verify(entityTransactionMock);


    }*/
}