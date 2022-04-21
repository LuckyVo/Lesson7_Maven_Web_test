package org.Lesson7_Maven_Web_test;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Story("Тест-кейсы с поисковой строкой")
public class SearchTest extends SiteInitialization{

    Logger logger = LoggerFactory.getLogger("Unit test's");

    @Feature("Фича 3")
    @ParameterizedTest
    @CsvSource({ "Слойки","Вафли","Пироги"})
    @DisplayName("Тест-кейс: Поиск на сайте")
    @Description("Тест-кейс: Поиск на сайте")
    @Link("https://xochy-xochy.com/")
    @Issue("Ссылка на Redmine")
    @Severity(SeverityLevel.TRIVIAL)
    void testLike(String textForSearch) {
        try {
            new Search(getDriver()).goToSearchText(textForSearch);
            assertTrue(getDriver().findElement(By.xpath(".//input[@name='q' " +
                    " and @type='text' and @value='" + textForSearch + "']")).getAttribute("value").equals(textForSearch));
//            для примера
            File file = MyUtils.makeScreenshot(getDriver(),
                    "failure- org.Lesson7_Maven_Web_test.Unit_test.SearchTest" + System.currentTimeMillis() + ".png");
            saveScreenshot(Files.readAllBytes(file.toPath()));
        } catch (NoSuchElementException | ElementNotInteractableException | StaleElementReferenceException | IOException e) {
            logger.info(e.getMessage());
            MyUtils.makeScreenshot(getDriver(),
                    "failure- org.Lesson7_Maven_Web_test.Unit_test.SearchTest" + System.currentTimeMillis() + ".png");
        }
        logger.info("Тест-кейс по поиску продукции на сайте пройден!");
    }

}
