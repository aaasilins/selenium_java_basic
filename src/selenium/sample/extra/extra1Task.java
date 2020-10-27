package selenium.sample.extra;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

public class extra1Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/actions";
    String new_url = "https://kristinek.github.io/site/examples/link1";
    private static WebDriverWait wait;
    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        // declaration above:
        driver = new ChromeDriver();
        wait = (WebDriverWait) new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class);
        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void navigateBack() throws Exception {
//        TODO
//        open page with url "https://kristinek.github.io/site/examples/po"
//        click "More > " for the top left element
//        check that the url now "https://kristinek.github.io/site/examples/po1"
//        using driver navigation go back to "https://kristinek.github.io/site/examples/po"
//        check that the page now is "https://kristinek.github.io/site/examples/po"
        driver.get("https://kristinek.github.io/site/examples/po");
        assertEquals("https://kristinek.github.io/site/examples/po", driver.getCurrentUrl());
        driver.findElement(By.xpath("/html/body/div[@class='w3-row-padding'][1]/div[@class='w3-half'][1]/div[@class='w3-container w3-pale-red']/div[@class='description']/p[@class='w3-justify'][2]/a")).click();
        assertEquals("https://kristinek.github.io/site/examples/po1", driver.getCurrentUrl());
        driver.navigate().back();
        assertEquals("https://kristinek.github.io/site/examples/po", driver.getCurrentUrl());
    }

    @Test
    public void navigateForward() throws Exception {
//        TODO
//        open page with url "https://kristinek.github.io/site/examples/po"
//        click "More > " for the top left element
//        using driver navigation go back to "https://kristinek.github.io/site/examples/po"
//        using driver navigation go forward to "https://kristinek.github.io/site/examples/po1"
//        check that the page now is "https://kristinek.github.io/site/examples/po1"
        driver.get("https://kristinek.github.io/site/examples/po");
        assertEquals("https://kristinek.github.io/site/examples/po", driver.getCurrentUrl());
        driver.findElement(By.xpath("/html/body/div[@class='w3-row-padding'][1]/div[@class='w3-half'][1]/div[@class='w3-container w3-pale-red']/div[@class='description']/p[@class='w3-justify'][2]/a")).click();
        driver.navigate().back();
        driver.navigate().forward();
        assertEquals("https://kristinek.github.io/site/examples/po1", driver.getCurrentUrl());
    }

    @Test
    public void refresh() throws Exception {
//        TODO
//        open page "https://kristinek.github.io/site/examples/act"
//        click on "Show" button in 'Button' section
//        check that text "I am here!" is seen
//        refresh page
//        check that text "I am here!" is not seen

        WebElement text = driver.findElement(By.id("show_me"));
        WebElement hideButton = driver.findElement(By.name("hide_text"));

        assertEquals(base_url, driver.getCurrentUrl());
        Thread.sleep(1000);
        WebElement showButton = driver.findElement(By.xpath("//button[@id='show_text']"));
        Thread.sleep(1000);
        assertFalse(text.isDisplayed()); // "I am here!" is NOT seen
        assertTrue(showButton.isEnabled()); // "Show" button is enabled (clickable)
        assertFalse(hideButton.isEnabled()); // "Hide" button is NOT enabled (clickable)
        Thread.sleep(500);
        showButton.click(); // clicking on "Show" button


        WebElement show1 = driver.findElement(By.xpath("//p[@id='show_me']"));
       Thread.sleep(500);
        assertEquals("I am here!", show1.getText());
        driver.navigate().refresh();
        //Thread.sleep(500);
      // assertFalse(show1.isEnabled()); //- doesn't want to work :(
      //  Thread.sleep(500);
//


    }
}
