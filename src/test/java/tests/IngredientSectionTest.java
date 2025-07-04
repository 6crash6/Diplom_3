package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageobject.MainPage;

public class IngredientSectionTest extends BaseTest {

    @Test
    @DisplayName("check switch ingredients sections")
    @Description("Проверка переключения между секциями ингредиентов")
    public void switchIngredientsSectionsTest() {
        new MainPage(webDriver)
                .saucesClick()
                .fillingsClick()
                .bunsClick();
    }
}