package org.Lesson7_Maven_Web_test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class SiteInitialization {

    static EventFiringWebDriver eventDriver;

    @BeforeAll
    static void initDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--headless");
//        options.addArguments("disable-popup-blocking");
        eventDriver = new EventFiringWebDriver(new ChromeDriver(options));
        eventDriver.register(new MyWebDriverEventListener());
        eventDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        eventDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
//        driver.manage().timeouts().pageLoadTimeout(4, TimeUnit.SECONDS);
    }

    @BeforeEach
    void initMainPage() {
        Assertions.assertDoesNotThrow( ()-> eventDriver.navigate().to("https://xochy-xochy.com/"),
                "Страница не доступна");
        Assertions.assertTrue(true);
    }

    @AfterEach
    public void checkBrowser(){
        List<LogEntry> allLogRows = getDriver().manage().logs().get(LogType.BROWSER).getAll();
        if(!allLogRows.isEmpty()){

            if (allLogRows.size() > 0 ) {
                allLogRows.forEach(logEntry -> {
                    System.out.println(logEntry.getMessage());
                });

            }

        }

    }

    @AfterAll
    public static void exit() {
        if(eventDriver !=null) eventDriver.quit();
    }

    public WebDriver getDriver() {
        return this.eventDriver;
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

}
