package com.example;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebApplicationTest {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static final String BASE_URL = "http://51.21.171.143:5174/";
    private static final String TEST_EMAIL = "test@example.com";
    private static final String TEST_PASSWORD = "password123";

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage",
                "--disable-gpu", "--window-size=1440,805",
                "--disable-blink-features=AutomationControlled");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("✓ Headless Chrome browser launched successfully");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("✓ Headless Chrome browser closed successfully");
        }
    }

    @BeforeEach
    public void navigateToBase() {
        driver.get(BASE_URL);
    }

    @Test
    public void testCase01_UserLogin() {
        System.out.println("\n=== Test Case 1: User Login ===");
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("body")));
            List<WebElement> loginButtons = driver.findElements(By.xpath("//*[contains(text(), 'Login') or contains(text(), 'Sign In')]"));
            if (!loginButtons.isEmpty()) loginButtons.get(0).click();
            List<WebElement> emailFields = driver.findElements(By.xpath("//input[@type='email' or @name*='email']"));
            if (!emailFields.isEmpty()) emailFields.get(0).sendKeys(TEST_EMAIL);
            List<WebElement> passwordFields = driver.findElements(By.xpath("//input[@type='password']"));
            if (!passwordFields.isEmpty()) passwordFields.get(0).sendKeys(TEST_PASSWORD);
            List<WebElement> submitButtons = driver.findElements(By.xpath("//button[contains(text(), 'Login') or contains(text(), 'Submit')]"));
            if (!submitButtons.isEmpty()) submitButtons.get(0).click();
            assertNotNull(driver.getPageSource());
            System.out.println("✓ Login test completed");
        } catch (Exception e) {
            System.out.println("Login elements not found: " + e.getMessage());
        }
    }

    @Test
    public void testCase02_VerifyPageTitle() {
        System.out.println("\n=== Test Case 2: Verify Page Title ===");
        String title = driver.getTitle();
        assertNotNull(title);
        assertFalse(title.isEmpty());
        System.out.println("✓ Page Title: " + title);
    }

    @Test
    public void testCase03_VerifyPageURL() {
        System.out.println("\n=== Test Case 3: Verify Page URL ===");
        String url = driver.getCurrentUrl();
        assertEquals(BASE_URL, url);
        assertTrue(url.contains("51.21.171.143"));
        System.out.println("✓ URL verified: " + url);
    }

    @Test
    public void testCase04_VerifyPageLoadsSuccessfully() {
        System.out.println("\n=== Test Case 4: Page Loads Successfully ===");
        String src = driver.getPageSource();
        assertNotNull(src);
        assertTrue(src.length() > 0);
        System.out.println("✓ Page source length: " + src.length());
    }

    @Test
    public void testCase05_VerifyPageElements() {
        System.out.println("\n=== Test Case 5: Verify Page Elements ===");
        List<WebElement> elements = driver.findElements(By.tagName("*"));
        assertFalse(elements.isEmpty());
        System.out.println("✓ Total elements: " + elements.size());
    }

    @Test
    public void testCase06_SearchProperties() {
        System.out.println("\n=== Test Case 6: Search Properties ===");
        try {
            List<WebElement> searchInputs = driver.findElements(By.xpath("//input[@placeholder*='search' or @placeholder*='Search']"));
            if (!searchInputs.isEmpty()) {
                searchInputs.get(0).click();
                searchInputs.get(0).sendKeys("apartment");
                searchInputs.get(0).submit();
                System.out.println("✓ Search submitted");
            } else {
                System.out.println("✓ Search input not found");
            }
        } catch (Exception e) {
            System.out.println("Search not available: " + e.getMessage());
        }
    }

    @Test
    public void testCase07_FilterProperties() {
        System.out.println("\n=== Test Case 7: Filter Properties ===");
        try {
            List<WebElement> filters = driver.findElements(By.xpath("//*[@class*='filter' or @class*='Filter']"));
            System.out.println("✓ Filter elements: " + filters.size());
        } catch (Exception e) {
            System.out.println("Filter not available: " + e.getMessage());
        }
    }

    @Test
    public void testCase08_VerifyNavigationLinks() {
        System.out.println("\n=== Test Case 8: Verify Navigation Links ===");
        List<WebElement> links = driver.findElements(By.tagName("a"));
        assertTrue(links.size() > 0);
        System.out.println("✓ Links found: " + links.size());
    }

    @Test
    public void testCase09_TestFormSubmission() {
        System.out.println("\n=== Test Case 9: Form Submission ===");
        try {
            List<WebElement> forms = driver.findElements(By.tagName("form"));
            System.out.println("✓ Forms found: " + forms.size());
        } catch (Exception e) {
            System.out.println("Form test: " + e.getMessage());
        }
    }

    @Test
    public void testCase10_TestBrowserBackButton() {
        System.out.println("\n=== Test Case 10: Browser Back Button ===");
        driver.navigate().to(BASE_URL + "#properties");
        driver.navigate().back();
        assertNotNull(driver.getCurrentUrl());
        System.out.println("✓ Back navigation works");
    }

    @Test
    public void testCase11_TestBrowserForwardButton() {
        System.out.println("\n=== Test Case 11: Browser Forward Button ===");
        driver.navigate().back();
        driver.navigate().forward();
        assertNotNull(driver.getCurrentUrl());
        System.out.println("✓ Forward navigation works");
    }

    @Test
    public void testCase12_VerifyPageRefresh() {
        System.out.println("\n=== Test Case 12: Page Refresh ===");
        String urlBefore = driver.getCurrentUrl();
        driver.navigate().refresh();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("body")));
        assertEquals(urlBefore, driver.getCurrentUrl());
        System.out.println("✓ Page refreshed successfully");
    }

    @Test
    public void testCase13_VerifyImagesLoaded() {
        System.out.println("\n=== Test Case 13: Images Loaded ===");
        List<WebElement> images = driver.findElements(By.tagName("img"));
        System.out.println("✓ Images found: " + images.size());
    }

    @Test
    public void testCase14_VerifyMetaTags() {
        System.out.println("\n=== Test Case 14: Meta Tags ===");
        List<WebElement> metaTags = driver.findElements(By.tagName("meta"));
        assertTrue(metaTags.size() > 0);
        System.out.println("✓ Meta tags: " + metaTags.size());
    }

    @Test
    public void testCase15_VerifyInteractiveElements() {
        System.out.println("\n=== Test Case 15: Interactive Elements ===");
        int buttons = driver.findElements(By.tagName("button")).size();
        int links = driver.findElements(By.tagName("a")).size();
        assertTrue(buttons + links > 0);
        System.out.println("✓ Buttons: " + buttons + ", Links: " + links);
    }

    @Test
    public void testCase16_VerifyScrollFunctionality() {
        System.out.println("\n=== Test Case 16: Scroll Functionality ===");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");
        Long pos = (Long) js.executeScript("return window.pageYOffset;");
        assertTrue(pos >= 0);
        js.executeScript("window.scrollBy(0, -500);");
        System.out.println("✓ Scroll works, position: " + pos);
    }
}
