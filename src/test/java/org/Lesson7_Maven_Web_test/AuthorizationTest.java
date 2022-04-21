package org.Lesson7_Maven_Web_test;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.logging.LogEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Story("Тест-кейсы с авторизацией")
public class AuthorizationTest extends SiteInitialization {
    Logger logger = LoggerFactory.getLogger("Unit test's");

//    @Test
//    @DisplayName("Тест-кейс: Авторизация на сайте")
//    @Description("Тест-кейс: Авторизация на сайте")
//    void testAuthorizationIN(){
//        try {
//            new MainPage(getDriver()).goToAccountPage();
//            assertTrue(getDriver().getTitle().equals("Войти"), "Страница не алё");
//            new ToLoginPage(getDriver()).setLogin("v.kudraivzev@yandex.ru").setPassword("ASDFzxcv1234").loginIn();
//            assertTrue(getDriver().findElement(By.xpath(".//a[@href='/personal/' " +
//                    "and @class='lk_menu_h1']")).getText().equals("Личный кабинет"));
//        } catch (NoSuchElementException | ElementNotInteractableException | StaleElementReferenceException e){
//            logger.info(e.getMessage());
//        }
//        logger.info("Тест-кейс по авторизации пройден!");
//    }

    @Feature("Фича 5")
    @Test
    @DisplayName("Тест-кейс: Авторизация на сайте")
    @Description("Тест-кейс: Авторизация на сайте")
    @Link("https://xochy-xochy.com/")
    @Issue("Ссылка на Redmine")
    @Severity(SeverityLevel.MINOR)
    void testAuthorizationINSecond(){
        try {
            new MainPage(getDriver()).goToAccountPage();
            assertTrue(getDriver().getTitle().equals("Войти"), "Страница не алё");
            new ToLoginPage(getDriver()).loginIn("v.kudraivzev@yandex.ru","ASDFzxcv1234");
            assertTrue(getDriver().findElement(By.xpath(".//a[@href='/personal/' " +
                    "and @class='lk_menu_h1']")).getText().equals("Личный кабинет"));
//            для примера
            File file = MyUtils.makeScreenshot(getDriver(),
                    "failure- org.Lesson7_Maven_Web_test.Unit_test.AuthorizationTest" + System.currentTimeMillis() + ".png");
            saveScreenshot(Files.readAllBytes(file.toPath()));
            MyUtils myUtils = new MyUtils(getDriver());
            List<LogEntry> log = myUtils.getLogs(getDriver());
            logger.info("" + log);
        } catch (NoSuchElementException | ElementNotInteractableException | StaleElementReferenceException | IOException e){
            logger.info(e.getMessage());
            MyUtils.makeScreenshot(getDriver(),
                    "failure- org.Lesson7_Maven_Web_test.Unit_test.AuthorizationTest" + System.currentTimeMillis() + ".png");
        }
        logger.info("Тест-кейс по авторизации пройден!");

    }



}
