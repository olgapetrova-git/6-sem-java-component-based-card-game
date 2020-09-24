package htwberlin.mau_mau.virtual_player_management.service;

import htwberlin.mau_mau.virtual_player_management.data.VirtualPlayer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VirtualPlayerServiceTest {
    private VirtualPlayerService virtualPlayerService;

    @Before
    public void setUp() throws Exception {
        virtualPlayerService = new VirtualPlayerServiceImpl();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateVirtualPlayer() {
        //Arrange
        //Act
        VirtualPlayer player = virtualPlayerService.createVirtualPlayer();
        //Assert
        Assert.assertNotNull(player.getName());
    }
}