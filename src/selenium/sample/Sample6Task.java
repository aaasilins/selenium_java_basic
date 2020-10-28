package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
//         TODO:!!!I presume that due to the name of the method the idea is to use different valid xpaths!!!
//        2 ways to find text: "Heading 2 text":
        System.out.println(driver.findElement(By.xpath("//*[@id=\"heading_2\"]")).getText());
        System.out.println(driver.findElement(By.xpath("//body//h2[2]")).getText());
//        1-2 ways to find text: "Test Text 1"
        System.out.println(driver.findElement(By.xpath("/html/body/div[4]/p[1]")).getText());
//        1-2 ways to find text: "Test Text 2"
        System.out.println(driver.findElement(By.xpath("/html/body/div[4]/p[2]")).getText());
//        1-2 ways to find text: "Test Text 3"
        System.out.println(driver.findElement(By.xpath("/html/body/div[5]/p[1]")).getText());
//        1-2 ways to find text: "Test Text 4"
        System.out.println(driver.findElement(By.xpath("/html/body/div[5]/p[2]")).getText());
//        1-2 ways to find text: "Test Text 5"
        System.out.println(driver.findElement(By.xpath("/html/body/div[6]/p[1]")).getText());
//        1-2 ways to find text: "This is also a button"
        System.out.println(driver.findElement(By.xpath("/html/body/input[2]")).getAttribute("value"));
        System.out.println(driver.findElement(By.xpath("//*[@id=\"buttonId\"]")).getAttribute("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
        System.out.println(driver.findElement(By.cssSelector("#heading_2")).getText());
//        1-2 ways to find text: "Test Text 1"
        System.out.println(driver.findElement(By.cssSelector("#test1 > p:nth-child(1)")).getText());
//        1-2 ways to find text: "Test Text 2"
        System.out.println(driver.findElement(By.cssSelector(".twoTest")).getText());
//        1-2 ways to find text: "Test Text 3"
        System.out.println(driver.findElement(By.cssSelector("#test3 > p:nth-child(1)")).getText());
//        1-2 ways to find text: "This is also a button"
        System.out.println(driver.findElement(By.cssSelector("#buttonId")).getAttribute("value"));
    }
}
