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
    static long startTime;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        wait = (WebDriverWait) new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class);
        driver.get("https://kristinek.github.io/site/examples/loading_color");
        startTime = System.currentTimeMillis();

    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
//         * 1) click on start loading green button
        WebElement startGreen = driver.findElement(By.id("start_green"));
        startGreen.click();

//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertFalse(startGreen.isDisplayed());
        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());

//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"

        Thread.sleep(10000);
        assertFalse(startGreen.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());

        WebElement finishGreen = driver.findElement(By.id("finish_green"));
        assertTrue(finishGreen.isDisplayed());

    }

    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//         * 1) click on start loading green button
        WebElement startGreen = driver.findElement(By.id("start_green"));
        startGreen.click();

//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertFalse(startGreen.isDisplayed());
        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());

//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        WebElement finishGreen = driver.findElement(By.id("finish_green"));
        assertTrue(finishGreen.isDisplayed());
        assertEquals("Green Loaded", finishGreen.getText());

        assertFalse(startGreen.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());



    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
//         * 1) click on start loading green button
        WebElement startGreen = driver.findElement(By.id("start_green"));
        startGreen.click();

//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."

        WebElement loadingGreen = driver.findElement(By.id("loading_green"));

        wait.until(ExpectedConditions.textToBePresentInElement(loadingGreen, "Loading green..."));
        assertFalse(startGreen.isDisplayed());
        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());

//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading_green")));

        WebElement finishGreen = driver.findElement(By.id("finish_green"));

        assertFalse(startGreen.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());

        assertTrue(finishGreen.isDisplayed());
        assertEquals("Green Loaded", finishGreen.getText());

    }

    @Test
    public void loadGreenAndBlueBonus() {
        /* TODO:
         * 0) wait until button to load green and blue appears
         * 1) click on start loading green and blue button
         * 2) check that button does not appear, but loading text is seen instead for green
         * 3) check that button does not appear, but loading text is seen instead for green and blue
         * 4) check that button and loading green does not appear,
         * 		but loading text is seen instead for blue and success for green is seen
         * 5) check that both button and loading text is not seen, success is seen instead
         */

   WebElement greenBlueButton = driver.findElement(By.id("start_green_and_blue"));
   wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("start_green_and_blue")));

   greenBlueButton.click();
   assertFalse(greenBlueButton.isDisplayed());

   WebElement greenLabel = driver.findElement(By.id("loading_green_without_blue"));
   assertFalse(greenBlueButton.isDisplayed());
   assertTrue(greenLabel.isDisplayed());

   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

   WebElement blueLabel = driver.findElement(By.id("loading_green_with_blue"));
   assertFalse(greenBlueButton.isDisplayed());
   assertTrue(greenLabel.isDisplayed());
   assertTrue(blueLabel.isDisplayed());

   WebElement blueWithoutGreen = driver.findElement(By.id("loading_blue_without_green"));
   assertFalse(greenBlueButton.isDisplayed());
   assertFalse(greenLabel.isDisplayed());
   assertTrue(blueLabel.isDisplayed());
   assertTrue(blueWithoutGreen.isDisplayed());

   WebElement finishGB = driver.findElement(By.id("finish_green_and_blue"));
   assertFalse(greenBlueButton.isDisplayed());
   assertFalse(greenLabel.isDisplayed());
   assertFalse(blueLabel.isDisplayed());
   assertFalse(blueWithoutGreen.isDisplayed());
   assertTrue(finishGB.isDisplayed());









    }

}