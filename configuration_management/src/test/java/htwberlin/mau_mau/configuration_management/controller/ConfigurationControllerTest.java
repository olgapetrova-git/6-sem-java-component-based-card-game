package htwberlin.mau_mau.configuration_management.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;

import static org.junit.Assert.*;

public class ConfigurationControllerTest {
private ConfigurationController configurationController;
    @Before
    public void setUp() throws Exception {
        configurationController = new ConfigurationControllerImpl();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void context() {
        //Arrange
        //Act
        ConfigurableApplicationContext context = configurationController.context();
        //Assert
        Assert.assertNotNull(context);
    }
}