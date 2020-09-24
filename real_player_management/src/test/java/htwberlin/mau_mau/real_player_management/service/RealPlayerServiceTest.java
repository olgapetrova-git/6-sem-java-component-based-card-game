package htwberlin.mau_mau.real_player_management.service;

import htwberlin.mau_mau.real_player_management.data.RealPlayer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RealPlayerServiceTest {
    private RealPlayerService realPlayerService;

    @Before
    public void setUp() throws Exception {
        realPlayerService = new RealPlayerServiceImpl();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testIsSaidMau() {
        //Arrange
        RealPlayer player = new RealPlayer("testName");
        //Act
        boolean result = realPlayerService.isSaidMau(player);
        //Assert
        Assert.assertFalse(result);
    }

    @Test
    public void testSetSaidMau() {
        //Arrange
        RealPlayer player = new RealPlayer("testName");
        //Act
        realPlayerService.setSaidMau(true, player);
        boolean result = realPlayerService.isSaidMau(player);
        //Assert
        Assert.assertTrue(result);
    }

    @Test
    public void testIsSaidMauMau() {
        //Arrange
        RealPlayer player = new RealPlayer("testName");
        //Act
        boolean result = realPlayerService.isSaidMauMau(player);
        //Assert
        Assert.assertFalse(result);
    }

    @Test
    public void testSetSaidMauMau() {
        //Arrange
        RealPlayer player = new RealPlayer("TestName");
        //Act
        realPlayerService.setSaidMauMau(true, player);
        boolean result = realPlayerService.isSaidMauMau(player);
        //Assert
        Assert.assertTrue(result);
    }

    @Test
    public void testCreateRealPlayer() {
        //Arrange
        //Act
        RealPlayer player = realPlayerService.createRealPlayer("TestName");
        //Assert
        Assert.assertEquals("TestName", player.getName());
    }
}