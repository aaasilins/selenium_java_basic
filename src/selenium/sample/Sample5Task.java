package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class Sample5Task {
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
        driver.get("https://kristinek.github.io/site/examples/alerts_popups");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void goToAlertedPageViaButton() throws Exception {
//         TODO:
        String correctPage = "https://kristinek.github.io/site/examples/alerted_page";

//        click on "To go to alerted page press Ok. Or stay here" button
        driver.findElement(By.className("w3-blue")).click();

//        switch to alert
        Alert alertWindow1 = driver.switchTo().alert();

//        click ok
        alertWindow1.accept();

//        switch to second alert
        Alert alertWindow2 = driver.switchTo().alert();

//        verify alert text
        assertEquals("Booooooooo!", alertWindow2.getText());

//        click ok on second alert
        alertWindow2.accept();

//        verify that the correct page is opened
        assertEquals(driver.getCurrentUrl(), correctPage);

    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
//         TODO:
//        click on "To go to alerted page press Ok. Or stay here" button
        driver.findElement(By.className("w3-blue")).click();

//        switch to alert
        Alert alertWindow1 = driver.switchTo().alert();

//        click cancel
        alertWindow1.dismiss();

//        verify the text on page
        String pageText = driver.findElement(By.id("textForAlerts")).getText();
        assertEquals("So you desided to say? Good!", pageText);

    }
}
