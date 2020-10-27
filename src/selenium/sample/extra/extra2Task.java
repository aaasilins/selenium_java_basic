package selenium.sample.extra;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import static org.junit.Assert.assertEquals;


public class extra2Task {
    WebDriver driver;
    String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";

    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void runningOnFirefox() throws Exception {
        System.setProperty("webdriver.gecko.driver", libWithDriversLocation + "geckodriver.exe");
        driver = new FirefoxDriver();
//        TODO
//        driver.get(https://kristinek.github.io/site/examples/po);
//        check the background color of h1 element
        driver.get("https://kristinek.github.io/site/examples/po");
        assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//h1")).getCssValue("background-color"));
        String bckgclr = driver.findElement(By.xpath("//h1")).getCssValue("background-color");
        System.out.println(bckgclr);
//PASSED
    }

    @Test
    public void runningOnChrome() throws Exception {
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
//        TODO
//        go to page https://kristinek.github.io/site/examples/po
//        check the background color of h1 element
        driver.get("https://kristinek.github.io/site/examples/po");
        assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//h1")).getCssValue("background-color"));
        String bckgclr = driver.findElement(By.xpath("//h1")).getCssValue("background-color");
        System.out.println(bckgclr);
    //PASSED
    }

    @Test
    public void runningOnIE() throws Exception {
        System.setProperty("webdriver.ie.driver", libWithDriversLocation + "IEDriverServer.exe");
        driver = new InternetExplorerDriver();
//        TODO
//        go to page https://kristinek.github.io/site/examples/po
//        check the background color of h1 element
        driver.get("https://kristinek.github.io/site/examples/po");
        Thread.sleep(1500);
        assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.className("w3-jumbo")).getCssValue("background-color"));
        Thread.sleep(1500);
        String bckgclr = driver.findElement(By.xpath("//h1")).getCssValue("background-color");
        Thread.sleep(1500);
        System.out.println(bckgclr);
    }
}
//NOT PASSED