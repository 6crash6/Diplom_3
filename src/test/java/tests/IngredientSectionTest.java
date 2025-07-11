package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageobject.MainPage;

import static org.junit.Assert.assertTrue;

public class IngredientSectionTest extends BaseTest {

    @Test
    @DisplayName("check switch Buns section")
    public void switchBunsTest() {
        MainPage mainPage = new MainPage(webDriver);
        assertTrue(mainPage.checkBunsSection());
    }

    @Test
    @DisplayName("check switch Sauces section")
    public void switchSaucesTest(){
        MainPage mainPage = new MainPage(webDriver);
        mainPage.saucesClick();
        assertTrue(mainPage.checkSaucesCheckSection());
    }

    @Test
    @DisplayName("check switch Filings section")
    public void switchFilingsTest(){
        MainPage mainPage = new MainPage(webDriver);
        mainPage.fillingsClick();
        assertTrue(mainPage.checkFillingsSection());
    }

}