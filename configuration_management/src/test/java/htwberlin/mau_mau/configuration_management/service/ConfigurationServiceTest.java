package htwberlin.mau_mau.configuration_management.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;

public class ConfigurationServiceTest {
private ConfigurationService configurationService;
    @Before
    public void setUp() throws Exception {
        configurationService = new ConfigurationServiceImpl();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testContext() {
        //Arrange
        //Act
        ConfigurableApplicationContext context = configurationService.context();
        //Assert
        Assert.assertNotNull(context);
    }
}