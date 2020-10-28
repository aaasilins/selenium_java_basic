package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

    @Test
    public void enterNumber() throws Exception {
//         TODO:
//        enter a number under "Number"
        WebElement fieldNumber = driver.findElement(By.id("number"));
        fieldNumber.sendKeys(Keys.DELETE);
        fieldNumber.sendKeys("38");
        Thread.sleep(3000);

//        check that button is not clickable "Clear Result"

        WebElement clearResultButton = driver.findElement(By.id("clear_result_button_number"));
        assertFalse(clearResultButton.isEnabled());

//        check that text is not displayed

        WebElement text = driver.findElement(By.id("result_number"));
        assertFalse(text.isDisplayed());

//        click on "Result" button

        WebElement resultButton = driver.findElement(By.id("result_button_number"));
        resultButton.click();
        Thread.sleep(3000);

//        check that text is displayed
        assertTrue(text.isDisplayed());

//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")

        String num38 = "\"38\"";
        String textShouldBe = ("You entered number: " + num38);
        assertEquals(textShouldBe, text.getText());

//        check that the button "Clear Result" is clickable now

        assertTrue(clearResultButton.isEnabled());

//        click on "Clear Result"

        clearResultButton.click();

//        check that the text is still (""), but it is not displayed

        String textNow = "";
        assertEquals(textNow, text.getText());

    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//        check current url is base_url

        assertEquals(base_url, driver.getCurrentUrl());

//        click on "This is a link to Homepage"

        WebElement linkToHome = driver.findElement(By.id("homepage_link"));
        linkToHome.click();

//        check that current url is not base_url

        assertNotEquals(base_url, driver.getCurrentUrl());
        assertFalse(driver.getCurrentUrl().equals(base_url));

//        verify that current url is homepage

        assertEquals("https://kristinek.github.io/site/", driver.getCurrentUrl());
        WebElement textHomePage = driver.findElement(By.id("h1"));
        System.out.println(driver.getTitle());
        System.out.println(textHomePage.getText());


    }
}
