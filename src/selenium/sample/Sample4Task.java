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

    @Test
    public void enterNumber() throws Exception {
//         TODO:
//        enter a number under "Number" x
//        check that button is not clickable "Clear Result"x
//        check that text is not displayed x
//        click on "Result" button x
//        check that text is displayed x
//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"") x
//        check that the button "Clear Result" is clickable now x
//        click on "Clear Result" x
//        check that the text is still (""), but it is not displayed x
        WebElement numberBox=driver.findElement(By.id("number"));
        WebElement resultButton=driver.findElement(By.id("result_button_number"));
        WebElement clearButton=driver.findElement(By.id("clear_result_button_number"));
        WebElement resultText=driver.findElement(By.id("result_number"));
        String input="15";
        String expectedOutput="You entered number: "+"\""+input+"\"";
        numberBox.clear();
        numberBox.sendKeys(input);
        assertFalse(clearButton.isEnabled());
        assertFalse(resultText.isDisplayed());
        resultButton.click();
        assertTrue(resultText.isDisplayed());
        assertEquals(expectedOutput, resultText.getText());
        assertTrue(clearButton.isEnabled());
        clearButton.click();
        assertTrue(resultText.getText().equals("")&&!resultText.isDisplayed());


        Thread.sleep(1000);
    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//        check current url is base_url
//        click on "This is a link to Homepage"
//        check that current url is not base_url
//        verify that current url is homepage
        String currentUrl=driver.getCurrentUrl();
        WebElement linkToHomepage=driver.findElement(By.id("homepage_link"));
        assertEquals(base_url,currentUrl );
        linkToHomepage.click();
        currentUrl=driver.getCurrentUrl();
        assertNotEquals(base_url, currentUrl);
        assertEquals("https://kristinek.github.io/site/", currentUrl);
    }
}
