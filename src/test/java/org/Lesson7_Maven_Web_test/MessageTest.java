package org.Lesson7_Maven_Web_test;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Story("Тест-кейсы с сообщениями в CRM")
public class MessageTest extends SiteInitialization{

    Logger logger = LoggerFactory.getLogger("Unit test's");

    @Feature("Фича 2")
    @Test
    @DisplayName("Тест-кейс: Отправка текста сообщения в CRM")
    @Description("Тест-кейс: Отправка текста сообщения в CRM")
    @Link("https://xochy-xochy.com/")
    @Issue("Ссылка на Redmine")
    @Severity(SeverityLevel.TRIVIAL)
    void testLike() {
        try {
            new Message(getDriver()).sendMessageToLineCRM("CRM");
            assertTrue(getDriver().findElement(By.xpath(".//span[@class='bx-im-message-content-text' " +
                    "and text()='CRM']")).getText().equals("CRM"));
//            для примера
            File file = MyUtils.makeScreenshot(getDriver(),
                    "failure- org.Lesson7_Maven_Web_test.Unit_test.MessageTest" + System.currentTimeMillis() + ".png");
            saveScreenshot(Files.readAllBytes(file.toPath()));
        } catch (NoSuchElementException | ElementNotInteractableException | StaleElementReferenceException | IOException e) {
            logger.info(e.getMessage());
            MyUtils.makeScreenshot(getDriver(),
                    "failure- org.Lesson7_Maven_Web_test.Unit_test.MessageTest" + System.currentTimeMillis() + ".png");
        }
        logger.info("Тест-кейс по отправлению текста сообщения CRM пройден!");
    }

}