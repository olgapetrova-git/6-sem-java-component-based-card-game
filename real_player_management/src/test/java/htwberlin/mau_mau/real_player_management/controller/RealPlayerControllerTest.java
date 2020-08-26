package htwberlin.mau_mau.real_player_management.controller;

import htwberlin.mau_mau.player_management.model.Player;
import htwberlin.mau_mau.real_player_management.model.RealPlayer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RealPlayerControllerTest {
    private RealPlayerController realPlayerController;

    @Before
    public void setUp() throws Exception {
        realPlayerController = new RealPlayerControllerImpl();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testIsSaidMau() {
        //Arrange
        RealPlayer player = new RealPlayer("testName");
        //Act
        boolean result = realPlayerController.isSaidMau(player);
        //Assert
        Assert.assertFalse(result);
    }

    @Test
    public void testSetSaidMau() {
        //Arrange
        RealPlayer player = new RealPlayer("testName");
        //Act
        realPlayerController.setSaidMau(true, player);
        boolean result = realPlayerController.isSaidMau(player);
        //Assert
        Assert.assertTrue(result);
    }

    @Test
    public void testIsSaidMauMau() {
        //Arrange
        RealPlayer player = new RealPlayer("testName");
        //Act
        boolean result = realPlayerController.isSaidMauMau(player);
        //Assert
        Assert.assertFalse(result);
    }

    @Test
    public void testSetSaidMauMau() {
        //Arrange
        RealPlayer player = new RealPlayer("TestName");
        //Act
        realPlayerController.setSaidMauMau(true, player);
        boolean result = realPlayerController.isSaidMauMau(player);
        //Assert
        Assert.assertTrue(result);
    }

    @Test
    public void createRealPlayer() {
        //Arrange
        //Act
        RealPlayer player = realPlayerController.createRealPlayer("TestName");
        //Assert
        Assert.assertEquals("TestName", player.getName());
    }
}