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

@Story("Тест-кейсы с избранной продукцией")
public class FavoriteTest extends SiteInitialization{

    Logger logger = LoggerFactory.getLogger("Unit test's");

    @Feature("Фича 1")
    @Test
    @DisplayName("Тест-кейс: Установка продукта в избранное")
    @Description("Тест-кейс: Установка продукта в избранное")
    @Link("https://xochy-xochy.com/")
    @Issue("Ссылка на Redmine")
    @Severity(SeverityLevel.NORMAL)
    void testLike() {
        try {
            new MainPage(getDriver()).goToCatalogPage();
            assertTrue(getDriver().getTitle().equals("Каталог продукции компании Хочу-Хочу"), "Страница не алё");
            new CatalogPage(getDriver()).goToCookiesPage();
            assertTrue(getDriver().getTitle().equals("Купить печенье оптом и в розницу в Москве " +
                    "| Заказать печенье от производителя в интернет-магазине «ХОЧУ-ХОЧУ»"), "Страница не алё");
            WebElement likeNineNegrityat = getDriver().findElement(By.xpath(".//div[@class='elem_wish ' " +
                    "and @data-id='24']"));
            likeNineNegrityat.click();
            new FavoritePage(getDriver()).goFavoritePage();
            assertTrue(getDriver().getTitle().equals("Избранное"), "Страница не алё");
            assertTrue(getDriver().findElement(By.xpath(".//a[@href='/catalog/pechenye/9-negrityat/' " +
                    "and text()='9 НЕГРИТЯТ']")).getText().equals("9 НЕГРИТЯТ"));
//            для примера
            File file = MyUtils.makeScreenshot(getDriver(),
                    "failure- org.Lesson7_Maven_Web_test.Unit_test.FavoriteTest" + System.currentTimeMillis() + ".png");
            saveScreenshot(Files.readAllBytes(file.toPath()));
        } catch (NoSuchElementException | ElementNotInteractableException | StaleElementReferenceException | IOException e) {
            logger.info(e.getMessage());
            MyUtils.makeScreenshot(getDriver(),
                    "failure- org.Lesson7_Maven_Web_test.Unit_test.FavoriteTest" + System.currentTimeMillis() + ".png");
        }
        logger.info("Тест-кейс по добавлению продукта в корзину пройден!");
    }

}

