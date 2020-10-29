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
        String libWithDriversLocation = System.getProperty("user.dir") + "/lib/";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/examples/loading_color");
        wait = (WebDriverWait) new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class);
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
//         * 2) check that button does not appear,
        assertFalse(greenButton.isDisplayed());
        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
//         * but loading text is seen instead   "Loading green..."

        //assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());
//         * 3) check that both button
        assertFalse(greenButton.isDisplayed());
//         * and loading text is not seen,
        Thread.sleep(5000);
        assertFalse(loadingGreen.isDisplayed());
//         * success is seen instead "Green Loaded"
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());

    }

    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
//         * 1) click on start loading green button
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement greenButton = driver.findElement(By.id("start_green"));
        greenButton.click();

//         * 2) check that button does not appear,
        assertFalse(greenButton.isDisplayed());
//         * but loading text is seen instead   "Loading green..."
        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertEquals("Loading green...", loadingGreen.getText());
//         * 3) check that both button

        assertFalse(greenButton.isDisplayed());
//         * and loading text is not seen,

        assertTrue(loadingGreen.isDisplayed());
//         * success is seen instead "Green Loaded"
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
//         * 1) click on start loading green button
        WebElement greenButton = driver.findElement(By.id("start_green"));
        greenButton.click();
//         * 2) check that button does not appear,
        assertFalse(greenButton.isDisplayed());
//         * but loading text is seen instead   "Loading green..."
        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertEquals("Loading green...", loadingGreen.getText());
//         * 3) check that both button
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green")));
        assertFalse(greenButton.isDisplayed());
//         * and loading text is not seen,
        assertFalse(loadingGreen.isDisplayed());
//         * success is seen instead "Green Loaded"
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
    }

    @Test
    public void loadGreenAndBlueBonus()  throws Exception{
        //* TODO:
        // * 0) wait until button to load green and blue appears
        Thread.sleep(5000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("start_green_and_blue")));
        WebElement greenAndBlueButton = driver.findElement(By.id("start_green_and_blue"));
        // * 1) click on start loading green and blue button
        greenAndBlueButton.click();
         //* 2) check that button does not appear, but loading text is seen instead for green
        assertFalse(greenAndBlueButton.isDisplayed());
        WebElement loadingGreenWithoutBlue = driver.findElement(By.id("loading_green_without_blue"));
        assertTrue(loadingGreenWithoutBlue.isDisplayed());
         //* 3) check that button does not appear, but loading text is seen instead for green and blue
        assertFalse(greenAndBlueButton.isDisplayed());
        assertTrue(loadingGreenWithoutBlue.isDisplayed());
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loading_green_with_blue")));
        WebElement loadingGreenWithBlue = driver.findElement(By.id("loading_green_with_blue"));
        assertTrue(loadingGreenWithBlue.isDisplayed());
         //* 4) check that button and loading green does not appear,
        assertFalse(greenAndBlueButton.isDisplayed());
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loading_blue_without_green")));
        WebElement loadingBlueWithoutGreen = driver.findElement(By.id("loading_blue_without_green"));
        assertFalse(loadingGreenWithoutBlue.isDisplayed());
         //* 		but loading text is seen instead for blue and success for green is seen
        assertTrue(loadingBlueWithoutGreen.isDisplayed());

         //* 5) check that both button and loading text is not seen, success is seen instead
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green_and_blue")));
        WebElement finishGreenAndBlue = driver.findElement(By.id("finish_green_and_blue"));
        assertTrue(finishGreenAndBlue.isDisplayed());
         //*/
    }

}