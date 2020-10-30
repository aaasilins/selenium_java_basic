package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sample8Task {
    WebDriver driver;

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + "/lib/";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver");
        // declaration above:
        driver = new ChromeDriver();

        //open page:
        driver.get("https://kristinek.github.io/site/examples/po");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void styleChecks() throws Exception {
//         TODO:
//        check the background of top 2 sections
        WebElement leftBlock = driver.findElement(By.xpath("//*[contains(@class, 'w3-pale-red')]"));
        WebElement rightBlock = driver.findElement(By.xpath("//*[contains(@class, 'w3-pale-yellow')]"));
        WebElement h1Header = driver.findElement(By.cssSelector(".w3-jumbo"));


        System.out.println(leftBlock.getCssValue("background-color"));
        System.out.println(rightBlock.getCssValue("background-color"));
//        rgba(255, 221, 221, 1);
        assertEquals("rgba(255, 221, 221, 1)", leftBlock.getCssValue("background-color"));
        assertNotEquals("rgba(255, 221, 221, 1)", rightBlock.getCssValue("background-color"));
//        check h1 element font-size 64px
        System.out.println(h1Header.getCssValue("font-size"));
    }
}
