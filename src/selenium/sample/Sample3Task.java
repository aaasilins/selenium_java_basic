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
//         check that value of second button is "This is also a button"
        int expectedLength=5;
        int receivedLength=driver.findElements(By.className("test")).size();
        String expectedValue="This is also a button";
        String receivedValue=driver.findElement(By.name("randomButton2")).getAttribute("value");
        assertEquals(expectedLength, receivedLength);
        assertEquals(expectedValue, receivedValue);
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//         check that it is True that value of second button is
//         "this is Also a Button" if you ignore Caps Locks
//         fail with custom error message:
            String expectedValue="this is Also a Button";
            String receivedValue=driver.findElement(By.name("randomButton2")).getAttribute("value");
            try{
                assertTrue(expectedValue==receivedValue);

            }catch(AssertionError e){
                fail("This test was a failure!");

            }
    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//        check that it is False that value of second button is "This is a button"
        String expectedValue="This is a button";
        String receivedValue=driver.findElement(By.name("randomButton2")).getAttribute("value");
        assertFalse(expectedValue==receivedValue);
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//        check that none of items with class "test"
//        contain number 190
        List<WebElement> elementList = driver.findElements(By.className("test"));
        String tempValue="";
        for(int i=0; i<elementList.size();i++){
            tempValue=elementList.get(i).getText();
            for(int j=2; j< tempValue.length();j++){
                if(tempValue.charAt(j)==0&&tempValue.charAt(j-1)==9&&tempValue.charAt(j-2)==1)fail("test class values contain 190 at index"+(j-2));
            }
        }
    }


}
