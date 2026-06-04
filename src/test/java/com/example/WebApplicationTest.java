package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebApplicationTest {

    private static WebDriver driver;        // ← static
    private static WebDriverWait wait;      // ← static
    private static final String BASE_URL = "http://51.21.171.143:5174/";
    private static final long DELAY = 1000;
    private static final String TEST_EMAIL = "test@example.com";
    private static final String TEST_PASSWORD = "password123";

    @BeforeAll                              // ← BeforeEach se BeforeAll
    public static void setUp() {           // ← static
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1440,805");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("start-maximized");
        options.addArguments("enable-automation");
        
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        System.out.println("✓ Headless Chrome browser launched successfully");
    }

    @AfterAll                               // ← AfterEach se AfterAll
    public static void tearDown() {        // ← static
        if (driver != null) {
            driver.quit();
            System.out.println("✓ Headless Chrome browser closed successfully");
        }
    }

    @BeforeEach                            // ← Yeh naya add karo
    public void navigateToBase() {
        driver.get(BASE_URL);              // Har test se pehle sirf navigate karo
    }

    // باقی سارے test cases BILKUL SAME رہیں گے — کوئی change نہیں
    // testCase01 سے testCase16 تک سب copy paste کرو
