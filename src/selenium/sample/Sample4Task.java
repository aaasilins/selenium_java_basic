package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

import java.util.List;

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

    @Test
    public void enterNumber() throws Exception {
//         TODO:
        String enteredNumber = "5";

//        enter a number under "Number"
        WebElement numberField = driver.findElement(By.id("number"));
        numberField.clear();
        numberField.sendKeys(enteredNumber);

//        check that button is not clickable "Clear Result"
        WebElement clearResultNumberButton = driver.findElement(By.id("clear_result_button_number"));
        assertFalse(clearResultNumberButton.isEnabled());

//        check that text is not displayed
        WebElement resultNumber = driver.findElement(By.id("result_number"));
        assertFalse(resultNumber.isDisplayed());

//        click on "Result" button
        WebElement resultButton = driver.findElement(By.id("result_button_number"));
        resultButton.click();

//        check that text is displayed
        assertTrue(resultNumber.isDisplayed());

//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
        assertEquals("You entered number: \"" + enteredNumber + "\"", resultNumber.getText());

//        check that the button "Clear Result" is clickable now
        assertTrue(clearResultNumberButton.isEnabled());

//        click on "Clear Result"
        clearResultNumberButton.click();

//        check that the text is still (""), but it is not displayed
        assertEquals("", resultNumber.getText());
    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
        String homepageUrl = "https://kristinek.github.io/site/";
//        check current url is base_url
        assertEquals(driver.getCurrentUrl(), base_url);

//        click on "This is a link to Homepage"
        WebElement homepageLink = driver.findElement(By.id("homepage_link"));
        homepageLink.click();

//        check that current url is not base_url
        assertFalse(driver.getCurrentUrl().equals(base_url));

//        verify that current url is homepage
        System.out.println(driver.getCurrentUrl());
        assertEquals(driver.getCurrentUrl(), homepageUrl);
    }
}
