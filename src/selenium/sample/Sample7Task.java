package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class Sample7Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/actions";

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void selectCheckBox() throws Exception {
//         TODO:
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
//        check that none of the checkboxes are ticked
        for (WebElement checkBox : checkBoxes){
            assertFalse(checkBox.isSelected());
        }
//        tick  "Option 2"
        driver.findElement(By.id("vfb-6-1")).click();
//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        assertFalse(driver.findElement(By.id("vfb-6-0")).isSelected());
        assertTrue(driver.findElement(By.id("vfb-6-1")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-6-2")).isSelected());
//        tick  "Option 3"
        driver.findElement(By.id("vfb-6-2")).click();
//        click result
        driver.findElement(By.id("result_button_checkbox")).click();
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
        assertEquals("You selected value(s): Option 2, Option 3", driver.findElement(By.id("result_checkbox")).getText());
    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
          List<WebElement> radioBoxes = driver.findElements(By.cssSelector(".w3-check[type='radio']"));
//        check that none of the radio are selected
            for (WebElement radioBox : radioBoxes){
                assertFalse(radioBox.isSelected());
            }
//        select  "Option 3"
        driver.findElement(By.id("vfb-7-3")).click();
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(driver.findElement(By.id("vfb-7-1")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-7-2")).isSelected());
        assertTrue(driver.findElement(By.id("vfb-7-3")).isSelected());
//        select  "Option 1"
        driver.findElement(By.id("vfb-7-1")).click();
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertTrue(driver.findElement(By.id("vfb-7-1")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-7-2")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-7-3")).isSelected());
//        click result
        driver.findElement(By.id("result_button_ratio")).click();
//        check that 'You selected option: Option 1' text is being displayed
        assertEquals("You selected option: Option 1", driver.findElement(By.id("result_radio")).getText());
    }

    @Test
    public void selectOption() throws Exception {
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
//        select "Option 3" in Select
            dropdown.selectByIndex(3);
//        check that selected option is "Option 3"
            assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());
//        select "Option 2" in Select
            dropdown.selectByIndex(2);
//        check that selected option is "Option 2"
            assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());
//        click result
            driver.findElement(By.id("result_button_select")).click();
//        check that 'You selected option: Option 2' text is being displayed
            assertEquals("You selected option: Option 2",  driver.findElement(By.id("result_select")).getText());
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        dateBox.click();
//        enter date '4 of July 2007' using calendar widget
        for (int i = 0; i < 159; i++) { //IMPORTANT !!! This test wont pass if current month changes, so for next month value must be 160
            dateWidget.findElement(By.className("ui-datepicker-prev")).click();
        }
        dateWidget.findElement(By.xpath("//a[text()='4']")).click();
//        check that correct date is added
        assertEquals("07/04/2007", dateBox.getAttribute("value"));
        dateBox.clear();
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
//        enter date '2 of May 1959' using text
        dateBox.click();
        dateBox.sendKeys("05/02/1959");
//        check that correct date is added
        assertEquals("05/02/1959", dateBox.getAttribute("value"));
        Thread.sleep(5000);
    }
}
