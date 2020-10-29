package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
//        click on "To go to alerted page press Ok. Or stay here" button
        driver.findElement(By.xpath("html/body/div[2]/button[4]")).click();
        Thread.sleep(3000);
//        switch to alert
        Alert alert = driver.switchTo().alert();
        Thread.sleep(3000);
//        click ok
        alert.accept();
        Thread.sleep(3000);
//        switch to second alert
        Alert alert2 = driver.switchTo().alert();
        Thread.sleep(3000);
//        verify alert text
        assertEquals("Booooooooo!", alert.getText());
//        click ok on second alert
        alert2.accept();
        Thread.sleep(3000);
//        verify that the correct page is opened
        assertEquals("https://kristinek.github.io/site/examples/alerted_page", driver.getCurrentUrl());
        Thread.sleep(3000);
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
//         TODO:
//        click on "To go to alerted page press Ok. Or stay here" button
        driver.findElement(By.xpath("html/body/div[2]/button[4]")).click();
        Thread.sleep(3000);
//        switch to alert
        Alert alert = driver.switchTo().alert();
        Thread.sleep(3000);
//        click cancel
        alert.dismiss();
        Thread.sleep(3000);
//        verify the text on page
        String elementTextOnPage = driver.findElement(By.id("textForAlerts")).getText();
        assertTrue(elementTextOnPage.equals("So you desided to say? Good!"));
        Thread.sleep(3000);
    }
}
