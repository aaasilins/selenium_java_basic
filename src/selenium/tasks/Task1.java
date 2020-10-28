package selenium.tasks;

import jdk.nashorn.internal.runtime.NumberToString;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;
import org.openqa.selenium.Alert;

import java.text.DecimalFormat;

public class Task1 {
    WebDriver driver;

    @Before
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void errorOnText() throws Exception {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        WebElement input = driver.findElement(By.id("numb"));
        input.sendKeys("abcde");
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button")).click();
        assertEquals("Please enter a number", driver.findElement(By.xpath("//*[@id=\"ch1_error\"]")).getText());

    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        WebElement input = driver.findElement(By.id("numb"));
        input.sendKeys("40");
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button")).click();
        assertEquals("Number is too small", driver.findElement(By.xpath("//*[@id=\"ch1_error\"]")).getText());
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement input = driver.findElement(By.id("numb"));
        input.sendKeys("132");
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button")).click();
        assertEquals("Number is too big", driver.findElement(By.xpath("//*[@id=\"ch1_error\"]")).getText());
    }

    @Test
    public void correctSquareRootWithoutRemainder() {
//        TODO
            double inputVal = 64, square = Math.sqrt(inputVal);
            DecimalFormat df = new DecimalFormat("0.00");
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
            WebElement input = driver.findElement(By.id("numb"));
            input.sendKeys(String.valueOf(inputVal));
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
            driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button")).click();
            Alert alert = driver.switchTo().alert();
            assertEquals("Square root of "+inputVal+" is "+df.format(square), alert.getText());
    }

    @Test
    public void correctSquareRootWithRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        double inputVal = 55, square = Math.sqrt(inputVal);
        DecimalFormat df = new DecimalFormat("0.00");
        WebElement input = driver.findElement(By.id("numb"));
        input.sendKeys(String.valueOf(inputVal));
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button")).click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of "+inputVal+" is "+df.format(square), alert.getText());
        alert.accept();
    }
}
