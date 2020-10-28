package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class Sample9Task {
    WebDriver driver;
    private static WebDriverWait wait;
    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        wait = (WebDriverWait) new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class);
        driver.get("https://kristinek.github.io/site/examples/loading_color");
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
        WebElement greenButton = driver.findElement(By.id("start_green"));
//         * 1) click on start loading green button
        greenButton.click();
        Thread.sleep(100);
//         * 2) check that button does not appear,
        assertFalse(greenButton.isDisplayed());
//         * but loading text is seen instead   "Loading green..."
        WebElement greenLoad = driver.findElement(By.id("loading_green"));
        assertTrue(greenLoad.isDisplayed());
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        Thread.sleep(5500);
        assertFalse(greenButton.isDisplayed());
        assertFalse(greenLoad.isDisplayed());
        driver.findElement(By.id("finish_green")).isDisplayed();
    }

    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement greenButton = driver.findElement(By.id("start_green"));
//         * 1) click on start loading green button
            greenButton.click();
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
            assertFalse(greenButton.isDisplayed());
            assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
            assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
            assertFalse(greenButton.isDisplayed());
            assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
            WebElement greenButton = driver.findElement(By.id("start_green"));
////         * 1) click on start loading green button
             greenButton.click();
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loading_green")));
            WebElement greenLoad = driver.findElement(By.id("loading_green"));
            assertFalse(greenButton.isDisplayed());
            assertTrue(greenLoad.isDisplayed());
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"finish_green\"]")));
            assertFalse(greenButton.isDisplayed());
            assertFalse(greenLoad.isDisplayed());
            assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
    }

    @Test
    public void loadGreenAndBlueBonus() {
        WebElement greenAndBlueButton;
         //0) wait until button to load green and blue appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"start_green_and_blue\"]")));
         //1) click on start loading green and blue button
        greenAndBlueButton = driver.findElement(By.xpath("//*[@id=\"start_green_and_blue\"]"));
        greenAndBlueButton.click();
         //2) check that button does not appear, but loading text is seen instead for green
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"loading_green_without_blue\"]")));
        assertFalse(greenAndBlueButton.isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
         //3) check that button does not appear, but loading text is seen instead for green and blue
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"loading_green_with_blue\"]")));
        assertFalse(greenAndBlueButton.isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green_with_blue")).isDisplayed());
         //4) check that button and loading green does not appear, but loading text is seen instead for blue and success for green is seen
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"loading_blue_without_green\"]")));
        assertFalse(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
        assertFalse(greenAndBlueButton.isDisplayed());
        assertTrue(driver.findElement(By.id("loading_blue_without_green")).isDisplayed());
         //5) check that both button and loading text is not seen, success is seen instead
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"finish_green_and_blue\"]")));
        assertFalse(greenAndBlueButton.isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green_without_blue")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_blue_without_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green_and_blue")).isDisplayed());
    }

}