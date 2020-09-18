package htwberlin.mau_mau.game_management.service;

import htwberlin.mau_mau.game_management.data.GameData;
import htwberlin.mau_mau.real_player_management.data.RealPlayer;
import htwberlin.mau_mau.rules_management.data.GameRulesId;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

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