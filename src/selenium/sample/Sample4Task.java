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
        String libWithDriversLocation = System.getProperty("user.dir") + "/lib/";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver");
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void enterNumber() throws Exception {
WebElement numberField = driver.findElement(By.id("number"));
WebElement numberFieldClearResultbutton = driver.findElement(By.id("clear_result_button_number"));
WebElement numberFieldResultButton = driver.findElement(By.id("result_button_number"));
WebElement numberFieldResultField = driver.findElement(By.id("result_number"));
        //         TODO:
//        enter a number under "Number"
        numberField.clear();
            numberField.sendKeys("8");
//        check that button is not clickable "Clear Result"
        assertFalse(numberFieldClearResultbutton.isEnabled());
//        check that text is not displayed
        assertFalse(numberFieldResultField.isDisplayed());
//        click on "Result" button
        numberFieldResultButton.click();
//        check that text is displayed
        assertTrue(numberFieldResultField.isDisplayed());
//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
        assertEquals("You entered number: \"8\"", numberFieldResultField.getText());
//        check that the button "Clear Result" is clickable now"
        assertTrue(numberFieldClearResultbutton.isEnabled());
//        click on "Clear Result"
        numberFieldClearResultbutton.click();
//        check that the text is still (""), but it is not displayed
        assertEquals("", numberFieldResultField.getText());
    }

    @Test
    public void clickOnLink() throws Exception {
WebElement thisIsaLinkToHomepageLink = driver.findElement(By.id("homepage_link"));
        //         TODO:
//        check current url is base_url
        assertTrue(driver.getCurrentUrl().equals(base_url));
//        click on "This is a link to Homepage"
        thisIsaLinkToHomepageLink.click();
//        check that current url is not base_url
        assertFalse(driver.getCurrentUrl().equals(base_url));
//        verify that current url is homepage
        assertEquals("https://kristinek.github.io/site/",driver.getCurrentUrl());
    }
}
