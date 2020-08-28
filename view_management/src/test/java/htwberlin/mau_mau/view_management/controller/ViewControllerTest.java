package htwberlin.mau_mau.view_management.controller;

import htwberlin.mau_mau.game_management.service.GameService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import htwberlin.mau_mau.game_management.data.GameData;
import htwberlin.mau_mau.rules_management.data.GameRulesId;

import static org.easymock.EasyMock.*;

public class ViewControllerTest {

    private ViewController viewController;

    @Before
    public void setUp(){
        viewController = new ViewControllerImpl();
    }

    @Test
    public void testRun() {
        //Arrange
        GameData mockGameData = createNiceMock(GameData.class);
        GameService mockGameService = createNiceMock(GameService.class);
        expect(mockGameService.setupNewGame("test", 5, GameRulesId.SPECIAL)).andReturn(mockGameData);
        replay(mockGameService);

        //Act
        viewController.run();

        //Assert
        // TODO: Implement DI
        //  This test will fail until dependency injection (Blatt 3) is implemented
        //  This is because EasyMock can't mock objects created with new() inside methods
        //  With dependency injection these objects will come from DI container and could be mocked
        //  Currently verify expects gameService.setUpNewGame(...) to be called for mocked object
        //  but that  does not happen because gameService = new GameService() in newGameStarted overwrites the mocked object
        verify(mockGameService);
    }
    @After
    public void tearDown() throws Exception {
    }
}