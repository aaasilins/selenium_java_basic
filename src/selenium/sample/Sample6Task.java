package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class Sample6Task {
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
    public void findElementByXPath() throws Exception {
//         TODO:
//        2 ways to find text: "Heading 2 text":
        // 1st way
        String Heading_2_v1 = driver.findElement(By.xpath("//h2[contains(text(),'Heading 2 text')]")).getText();
        assertEquals("Heading 2 text", Heading_2_v1);
        // 2nd way
        String Heading_2_v2 = driver.findElement(By.xpath("//h2[@id='heading_2']")).getText();
        assertEquals("Heading 2 text", Heading_2_v2);

//        1-2 ways to find text: "Test Text 1"
        assertEquals("Test Text 1",driver.findElement(By.xpath("//p[contains(text(),'Test Text 1')]")).getText());
        assertEquals("Test Text 1",driver.findElement(By.xpath("//p[@class='test']")).getText());

//        1-2 ways to find text: "Test Text 2"
        assertEquals("Test Text 2",driver.findElement(By.xpath("//p[contains(text(),'Test Text 2')]")).getText());
        assertEquals("Test Text 2",driver.findElement(By.xpath("//p[@class='twoTest']")).getText());

//        1-2 ways to find text: "Test Text 3"
        assertEquals("Test Text 3",driver.findElement(By.xpath("//p[contains(text(),'Test Text 3')]")).getText());

//        1-2 ways to find text: "Test Text 4"
        assertEquals("Test Text 4",driver.findElement(By.xpath("//p[contains(text(),'Test Text 4')]")).getText());
        assertEquals("Test Text 4",driver.findElement(By.xpath("//p[2][@class='test']")).getText());

//        1-2 ways to find text: "Test Text 5"
        assertEquals("Test Text 5",driver.findElement(By.xpath("//p[contains(text(),'Test Text 5')]")).getText());

//        1-2 ways to find text: "This is also a button"
        assertEquals("This is also a button",driver.findElement(By.xpath("//*[@name='randomButton2']")).getAttribute("value"));
        assertEquals("This is also a button",driver.findElement(By.xpath("//*[@id='buttonId']")).getAttribute("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
        assertEquals("Heading 2 text", driver.findElement(By.cssSelector("#heading_2")).getText());

//        1-2 ways to find text: "Test Text 1"
        assertEquals("Test Text 1",driver.findElement(By.cssSelector("body:nth-child(2) div:nth-child(6) > p.test")).getText());

//        1-2 ways to find text: "Test Text 2"
        assertEquals("Test Text 2",driver.findElement(By.cssSelector("body:nth-child(2) div:nth-child(6) > p.twoTest")).getText());

//        1-2 ways to find text: "Test Text 3"
        assertEquals("Test Text 3",driver.findElement(By.cssSelector("body:nth-child(2) div:nth-child(7) > p.test:nth-child(1)")).getText());

//        1-2 ways to find text: "This is also a button"
        assertEquals("This is a button",driver.findElement(By.cssSelector("body:nth-child(2) > input:nth-child(9)")).getAttribute("value"));


    }
}
