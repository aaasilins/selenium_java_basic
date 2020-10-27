package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


public class Task1 {
    WebDriver driver;

    //NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMAN);
    //Locale currentLocale = Locale.getDefault();

    private static DecimalFormat df = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US));





    @Before
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");

    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void errorOnText() {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        WebElement numberInput = driver.findElement(By.id("numb"));
        String origNumber = "dad";

        numberInput.clear();
        numberInput.sendKeys(origNumber);

        assertEquals(numberInput.getText(), "");
        assertEquals(numberInput.getAttribute("value"), origNumber);
        driver.findElement(By.xpath("/html/body/div[@class='w3-row']/div[@class='w3-third']/div[@class='w3-margin']/div[@class='w3-container w3-card-4']/button[@class='w3-btn w3-orange w3-margin']")).click();
        assertEquals("Please enter a number", driver.findElement(By.className("error")).getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        WebElement numberInput = driver.findElement(By.id("numb"));
        String origNumber = "45";

        numberInput.clear();
        numberInput.sendKeys(origNumber);

        assertEquals(numberInput.getText(), "");
        assertEquals(numberInput.getAttribute("value"), origNumber);
        driver.findElement(By.xpath("/html/body/div[@class='w3-row']/div[@class='w3-third']/div[@class='w3-margin']/div[@class='w3-container w3-card-4']/button[@class='w3-btn w3-orange w3-margin']")).click();
        assertEquals("Number is too small", driver.findElement(By.className("error")).getText());
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement numberInput = driver.findElement(By.id("numb"));
        String origNumber = "110";

        numberInput.clear();
        numberInput.sendKeys(origNumber);

        assertEquals(numberInput.getText(), "");
        assertEquals(numberInput.getAttribute("value"), origNumber);
        driver.findElement(By.xpath("/html/body/div[@class='w3-row']/div[@class='w3-third']/div[@class='w3-margin']/div[@class='w3-container w3-card-4']/button[@class='w3-btn w3-orange w3-margin']")).click();
        assertEquals("Number is too big", driver.findElement(By.className("error")).getText());
    }

    @Test
    public void correctSquareRootWithoutRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        WebElement numberInput = driver.findElement(By.id("numb"));
        String origNumber = "64";

        numberInput.clear();
        numberInput.sendKeys(origNumber);

        assertEquals(numberInput.getText(), "");
        assertEquals(numberInput.getAttribute("value"), origNumber);
        driver.findElement(By.xpath("/html/body/div[@class='w3-row']/div[@class='w3-third']/div[@class='w3-margin']/div[@class='w3-container w3-card-4']/button[@class='w3-btn w3-orange w3-margin']")).click();
        Alert alert1 = driver.switchTo().alert();
        assertEquals("Square root of " + origNumber + " is " + Math.sqrt(Double.parseDouble(origNumber)) + "0", alert1.getText());
        alert1.accept();
    }

    @Test
    public void correctSquareRootWithRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        WebElement numberInput = driver.findElement(By.id("numb"));
        String origNumber = "72";

        numberInput.clear();
        numberInput.sendKeys(origNumber);
        WebElement er1 = driver.findElement(By.className("error"));
        assertEquals(numberInput.getText(), "");
        assertEquals(numberInput.getAttribute("value"), origNumber);
        driver.findElement(By.xpath("/html/body/div[@class='w3-row']/div[@class='w3-third']/div[@class='w3-margin']/div[@class='w3-container w3-card-4']/button[@class='w3-btn w3-orange w3-margin']")).click();
        Alert alert1 = driver.switchTo().alert();
        assertEquals("Square root of " + origNumber + " is " + df.format(Math.sqrt(Double.parseDouble((origNumber)))), alert1.getText());
        alert1.accept();
    }
}
