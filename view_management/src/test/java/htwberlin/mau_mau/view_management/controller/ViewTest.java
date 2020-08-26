package htwberlin.mau_mau.view_management.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import htwberlin.mau_mau.game_management.controller.GameController;
import htwberlin.mau_mau.game_management.model.GameData;
import htwberlin.mau_mau.rules_management.model.GameRulesId;
import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

public class ViewTest {

    private View view;

    @Before
    public void setUp(){
        view = new ViewImpl();
    }

    @Test
    public void testNewGameStarted() {
        //Arrange
        GameData mockGameData = createNiceMock(GameData.class);
        GameController mockGameController = createNiceMock(GameController.class);
        expect(mockGameController.setupNewGame("test", 5, GameRulesId.SPECIAL)).andReturn(mockGameData);
        replay(mockGameController);

        //Act
        view.newGameStarted("test", 5, GameRulesId.SPECIAL);

        //Assert
        // TODO: Implement DI
        //  This test will fail until dependency injection (Blatt 3) is implemented
        //  This is because EasyMock can't mock objects created with new() inside methods
        //  With dependency injection these objects will come from DI container and could be mocked
        //  Currently verify expects gameController.setUpNewGame(...) to be called for mocked object
        //  but that  does not happen because gameController = new GameContoller() in newGameStarted overwrites the mocked object
        verify(mockGameController);
    }
    @After
    public void tearDown() throws Exception {
    }
}