package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
//
//         check how many element with class "test" there are on page (5)
//         check that value of second button is "This is also a button"

        //1
        int expectedNumberOfElements = 5;
        int actualNumberOfElements = driver.findElements(By.className("test")).size();
        assertEquals(expectedNumberOfElements, actualNumberOfElements);
//2
        String expected = "This is also a button";
        String actual = driver.findElement(By.id("buttonId")).getAttribute("value");
        assertEquals(expected, actual);


    }

    @Test
    public void assertTrueTask() throws Exception {
        //         TODO:
//         check that it is True that value of second button is
//         "this is Also a Button" if you ignore Caps Locks
//         fail with custom error message:
        String elementTextOnPage = driver.findElement(By.cssSelector("#buttonId")).getAttribute("value");
        assertTrue(elementTextOnPage.equalsIgnoreCase("this is Also a Button"));

        try {
            assertTrue(elementTextOnPage.equalsIgnoreCase("THIS IS ALSO A BUTTON"));
        } catch (AssertionError e) {
            System.err.println("We failed with custom message”");
            e.printStackTrace();
        }

    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//        check that it is False that value of second button is "This is a button"
        String elementTextOnPage = driver.findElement(By.id("buttonId")).getAttribute("value");
        assertFalse(!elementTextOnPage.equals("This is also a button"));
     assertFalse(elementTextOnPage.equals("This is a button"));
     assertFalse(elementTextOnPage.contains("This is a button"));
       assertFalse(false);

    }

    @Test
    public void failTask() throws Exception {
//        TODO:

//        check that none of items with class "test"
//        contain number 190
        String expectNumber="190";
        WebElement w1 = driver.findElement(By.xpath("//p[@class='test']"));
        assertFalse(w1.equals(expectNumber));



    }
}
