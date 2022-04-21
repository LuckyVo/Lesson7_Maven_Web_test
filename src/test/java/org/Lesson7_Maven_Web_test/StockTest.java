package org.Lesson7_Maven_Web_test;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.NoSuchElementException;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Story("Тест-кейсы с акциями")
public class StockTest extends SiteInitialization{

    Logger logger = LoggerFactory.getLogger("Unit test's");

    @Feature("Фича 4")
    @Test
    @DisplayName("Тест-кейс: перейти на акции из каталога")
    @Description("Тест-кейс: перейти на акции из каталога")
    @Link("https://xochy-xochy.com/")
    @Issue("Ссылка на Redmine")
    @Severity(SeverityLevel.TRIVIAL)
    void testGoToStockFromCatalog(){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        try {
            new MainPage(getDriver()).goToCatalogPage();
            assertTrue(getDriver().getTitle().equals("Каталог продукции компании Хочу-Хочу"), "Страница не алё");
            jsExecutor.executeScript("window.scrollBy(0,700)");
            new CatalogPage(getDriver()).goToStock();
            WebElement head = getDriver().findElement(By.xpath(".//h1[@class='heading h1']"));
            String heading = head.getText();
            if (!heading.equals("Акции")) {
                logger.info("А страничка то не та, в каталоге(");
            } else {
                logger.info("Тест-кейс по переходу на акции из каталога пройден!");
            }
//            для примера
            File file = MyUtils.makeScreenshot(getDriver(),
                    "failure- org.Lesson7_Maven_Web_test.Unit_test.StockTest" + System.currentTimeMillis() + ".png");
            saveScreenshot(Files.readAllBytes(file.toPath()));
        } catch (NoSuchElementException | ElementNotInteractableException | StaleElementReferenceException | IOException e) {
            logger.info(e.getMessage());
            MyUtils.makeScreenshot(getDriver(),
                    "failure- org.Lesson7_Maven_Web_test.Unit_test.StockTest" + System.currentTimeMillis() + ".png");
        }
    }

}
