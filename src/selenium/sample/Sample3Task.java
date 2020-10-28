package selenium.sample;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.*;

public class Sample3Task {
    WebDriver driver;

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        // declaration above:
        driver = new ChromeDriver();

        //open page:
        driver.get("https://kristinek.github.io/site/examples/locators");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void assertEqualsTask() throws Exception {
//         TODO:
//         check how many element with class "test" there are on page (5)
            int expectedSize = 5;
            int realSize = driver.findElements(By.className("test")).size();
            assertEquals(expectedSize, realSize);
//         check that value of second button is "This is also a button"
        String expectedValue = "This is also a button";
        String realValue = driver.findElement(By.name("randomButton2")).getAttribute("value");
        assertEquals(expectedValue,realValue);
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
        String secondButtonValue = driver.findElement(By.name("randomButton2")).getAttribute("value");
//         check that it is True that value of second button is
//         "this is Also a Button" if you ignore Caps Locks
//         fail with custom error message:
        try{
            assertTrue(secondButtonValue.equalsIgnoreCase("this is sAlso a Button"));
            assertTrue("Text doesn't match!!! ", true);
        } catch(AssertionError e){
            System.err.println("We failed with custom message!");
            e.printStackTrace();
        }
    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//        check that it is False that value of second button is "This is a button"
        String secondButtonValue = driver.findElement(By.name("randomButton2")).getAttribute("value");
        assertFalse(secondButtonValue.equalsIgnoreCase("This is a button"));
        assertFalse(false);
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
         List <WebElement> allElements = driver.findElements(By.className("test"));
         for (WebElement a : allElements){
             assertFalse(a.getText().contains("190"));
         }
//        check that none of items with class "test"
//        contain number 190
    }
}
