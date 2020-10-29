package selenium.sample;

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
        int expectedNumberOfElements = 5;
        int actualNumberOfElements = driver.findElements(By.className("test")).size();
        assertEquals(expectedNumberOfElements, actualNumberOfElements);

        System.out.println(driver.findElements(By.className("test")).size());
    }
        @Test
        public void assertEqualsTask2() throws Exception {
//         TODO:
//         check that value of second button is "This is also a button"
     String expected = "This is also a button";
     String actual = driver.findElement(By.name("randomButton2")).getAttribute("value");
            System.out.println(driver.findElement(By.name("randomButton2")).getAttribute("value"));
     assertEquals(expected, actual);
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//         check that it is True that value of second button is
//         "this is Also a Button" if you ignore Caps Locks

          String secondButtonValue = driver.findElement(By.name("randomButton2")).getAttribute("value");
            assertTrue(secondButtonValue.equalsIgnoreCase("this is Also a Button"));
        }

//         fail with custom error message:
    @Test
public void assertTrueFailWithCustomErrorMessage() throws Exception {
    try {
        String secondButtonValue = driver.findElement(By.name("randomButton2")).getAttribute("value");

        assertFalse("This is my custom message", secondButtonValue.equalsIgnoreCase("this is Also a Button"));

    } catch (AssertionError e) {
        System.err.println("We failed with THE custom message!");
        e.printStackTrace();
    }
}


    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//        check that it is False that value of second button is "This is a button"

        String secondButtonValue = driver.findElement(By.name("randomButton2")).getAttribute("value");
        assertFalse(secondButtonValue.equals("This a button"));

    }



//        TODO:
//        check that none of items with class "test"
//        contain number 190


    // There are two solutions - one which I tried first, and the other which
    // you provided. But I believe they are both wrong, because when you try
    // to test with input "1" or "3" the test should fail as the numbers "1"
    // and "3" are present within the text of elements of "test" class. However
    // in both ways with such input the test is passed.
    // Or am I wrong and "value” attribute of the elements doesn't count
    // for item containing the number?


    @Test
    public void failTask1() throws Exception {

        List<WebElement> allElementsWithClass = driver.findElements(By.className("test"));

        for (WebElement elementWithClass : allElementsWithClass) {

            int z = 190;
            String w = "190";
            Object o1 = 190;
            Object o2 = "190";

            assertFalse(allElementsWithClass.contains(z));
            assertFalse(allElementsWithClass.contains(w));
            assertFalse(allElementsWithClass.contains(o1));
            assertFalse(allElementsWithClass.contains(o2));
        }
    }


    @Test
    public void failTask2() throws Exception {

        List<WebElement> allElementsWithClass = driver.findElements(By.className("test"));

        boolean condition = false;

        for (WebElement elementWithClass : allElementsWithClass) {

            if(elementWithClass.getText()=="190") {
                condition = true;
            }
            assertFalse(condition);
        }


}
}
