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

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/examples/loading_color");
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
//         * 1) click on start loading green button
        WebElement greenButton = driver.findElement(By.id("start_green"));
        greenButton.click();
        Thread.sleep(5000);

//         * 2) check that button does not appear,
         assertFalse(greenButton.isDisplayed());

//         * but loading text is seen instead   "Loading green..."
        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertEquals("Loading green...", loadingGreen.getText());

//         * 3) check that both button and loading text is not seen, * success is seen instead "Green Loaded"
        WebElement finishGreen = driver.findElement(By.id("finish_green"));
        assertFalse(greenButton.isDisplayed()); //button
        assertFalse(loadingGreen.isDisplayed()); //loading screen
        assertTrue(finishGreen.isDisplayed()); //Green Loaded
    }

    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//         * 1) click on start loading green button
        WebElement greenButton = driver.findElement(By.id("start_green"));
        greenButton.click();

//         * 2) check that button does not appear,
        assertFalse(greenButton.isDisplayed());

//         but loading text is seen instead   "Loading green..."
        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertEquals("Loading green...", loadingGreen.getText());

//         * 3) check that both button and loading text is not seen, success is seen instead "Green Loaded"
        WebElement finishGreen = driver.findElement(By.id("finish_green"));
        assertFalse(greenButton.isDisplayed()); //button
        assertFalse(loadingGreen.isDisplayed()); //loading screen
        assertTrue(finishGreen.isDisplayed()); //Green Loaded
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class);
//         TODO:
//         * 1) click on start loading green button
        WebElement greenButton = driver.findElement(By.id("start_green"));
        greenButton.click();

//         * 2) check that button does not appear,
        assertFalse(greenButton.isDisplayed());

//         * but loading text is seen instead   "Loading green..."
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loading_green")));
        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertEquals("Loading green...", loadingGreen.getText());

//         * 3) check that both button and loading text is not seen, success is seen instead "Green Loaded"
        WebElement finishGreen = driver.findElement(By.id("finish_green"));
        assertFalse(greenButton.isDisplayed()); //button
        assertFalse(loadingGreen.isDisplayed()); //loading screen
        assertTrue(finishGreen.isDisplayed()); //Green Loaded
    }

    @Test
    public void loadGreenAndBlueBonus() {
        // TODO:
        //0) wait until button to load green and blue appears
//
//        // 1) click on start loading green and blue button
////
////         2) check that button does not appear, but loading text is seen instead for green
//        assertFalse(greenBlueButton.isDisplayed());
//

//         3) check that button does not appear, but loading text is seen instead for green and blue


//         4) check that button and loading green does not appear,
//         		but loading text is seen instead for blue and success for green is seen
//         5) check that both button and loading text is not seen, success is seen instead
//
    }

}