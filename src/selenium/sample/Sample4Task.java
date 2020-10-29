package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

public class Sample4Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/actions";

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test //check the TEXT
    public void enterNumber() throws Exception {
        WebElement resultButton = driver.findElement(By.id("result_button_number"));
        WebElement clearResult = driver.findElement(By.id("clear_result_button_number"));
        WebElement number = driver.findElement(By.id("number"));
        WebElement resultText = driver.findElement(By.id("result_number" + ""));
//         TODO:
//        enter a number under "Number"
        number.clear();
        Thread.sleep(3000);
        number.sendKeys("7");

//         check that button is not clickable "Clear Result"
        assertFalse(clearResult.isEnabled());

//        check that text is not displayed
        assertFalse(resultText.isDisplayed());

//        click on "Result" button
        resultButton.click();
        Thread.sleep(3000);

//        check that text is displayed
        assertTrue(resultText.isDisplayed());

//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
        assertTrue(resultText.isDisplayed());

//        check that the button "Clear Result" is clickable now
        assertTrue(clearResult.isEnabled());

//        click on "Clear Result"
        clearResult.click();
        Thread.sleep(3000);

//        check that the text is still (""), but it is not displayed
        assertFalse(resultText.isDisplayed());

    }

    @Test
    public void clickOnLink() throws Exception {
        System.out.println("---------2---------");
//         TODO:
//        check current url is base_url
        assertEquals(base_url, driver.getCurrentUrl());
        Thread.sleep(3000);

//        click on "This is a link to Homepage"
        driver.findElement(By.id("homepage_link")).click();
        Thread.sleep(3000);

//        check that current url is not base_url
        assertFalse(driver.getCurrentUrl().equals(base_url));

//        verify that current url is homepage
        String homepage = "https://kristinek.github.io/site/";
        assertEquals(homepage, driver.getCurrentUrl());
    }
}
