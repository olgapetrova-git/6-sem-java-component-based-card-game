package htwberlin.mau_mau.virtual_player_management.controller;

import htwberlin.mau_mau.virtual_player_management.model.VirtualPlayer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VirtualPlayerControllerTest {
    private VirtualPlayerController virtualPlayerController;

    @Before
    public void setUp() throws Exception {
        virtualPlayerController = new VirtualPlayerControllerImpl();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createVirtualPlayer() {
        //Arrange
        //Act
        VirtualPlayer player = virtualPlayerController.createVirtualPlayer();
        //Assert
        Assert.assertNotNull(player.getName());
    }
}