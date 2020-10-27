package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static java.lang.Math.abs;
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

        List<WebElement> radioButtons = driver.findElements(By.xpath("//input[@type='radio']"));

        WebElement opt1 = driver.findElement(By.xpath("//*[@id=\"vfb-6-0\"]"));
        WebElement opt2 = driver.findElement(By.xpath("//*[@id=\"vfb-6-1\"]"));
        WebElement opt3 = driver.findElement(By.xpath("//*[@id=\"vfb-6-2\"]"));

//         TODO:
//        check that none of the checkboxes are ticked
        for (WebElement radioButton : radioButtons) {
            assertFalse(radioButton.isSelected());
        }

//        tick  "Option 2"
        opt2.click();

//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        assertTrue(opt2.isSelected());
        assertFalse(opt1.isSelected() & opt3.isSelected());

//        tick  "Option 3"
        opt3.click();

//        click result
        driver.findElement(By.xpath("//*[@id=\"result_button_checkbox\"]")).click();

//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
        String checkboxResultMsg = driver.findElement(By.xpath("//*[@id=\"result_checkbox\"]")).getText();
        assertEquals("You selected value(s): Option 2, Option 3", checkboxResultMsg);

    }


    @Test
    public void selectRadioButton() throws Exception {

        WebElement opt1 = driver.findElement(By.xpath("//*[@id=\"vfb-7-1\"]"));
        WebElement opt2 = driver.findElement(By.xpath("//*[@id=\"vfb-7-2\"]"));
        WebElement opt3 = driver.findElement(By.xpath("//*[@id=\"vfb-7-3\"]"));

//         TODO:
//        check that none of the radio are selected
        assertFalse(opt1.isSelected() & opt2.isSelected() & opt3.isSelected());

//        select  "Option 3"
        opt3.click();

//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertTrue(opt3.isSelected());
        assertFalse(opt1.isSelected() & opt2.isSelected());

//        select  "Option 1"
        opt1.click();

//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertTrue(opt1.isSelected());
        assertFalse(opt2.isSelected() & opt3.isSelected());

//        click result
        driver.findElement(By.xpath("//*[@id=\"result_button_ratio\"]")).click();

//        check that 'You selected option: Option 1' text is being displayed

        String checkboxResultMsg = driver.findElement(By.xpath("//*[@id=\"result_radio\"]")).getText();
        assertEquals("You selected option: Option 1", checkboxResultMsg);
    }

    @Test
    public void selectOption() throws Exception {

        Select dropdownMenu = new Select(driver.findElement(By.xpath("//*[@id=\"vfb-12\"]")));

//        select "Option 3" in Select
        dropdownMenu.selectByIndex(3);

//        check that selected option is "Option 3"
        assertEquals("Option 3", dropdownMenu.getFirstSelectedOption().getText());

//        select "Option 2" in Select
        dropdownMenu.selectByIndex(2);

//        check that selected option is "Option 2"
        assertEquals("Option 2", dropdownMenu.getFirstSelectedOption().getText());

//        click result
        driver.findElement(By.xpath("//*[@id=\"result_button_select\"]")).click();

//        check that 'You selected option: Option 2' text is being displayed
        assertEquals("You selected option: Option 2", driver.findElement(By.xpath("//*[@id=\"result_select\"]")).getText());
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {

        Calendar cal = Calendar.getInstance();
        WebElement dateBox = driver.findElement(By.xpath("//*[@id=\"vfb-8\"]"));
        //TODO:

        //enter date '4 of July 2007' using calendar widget

        //calculating the amount of clicks needed

        //Simplifying the date
        String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime());

        //Getting todays year and month as an integer
        String yearSt = currentDate.substring(6);
        int year = Integer.parseInt(yearSt.trim());
        String monthSt = currentDate.substring(3, 5);
        int month = Integer.parseInt(monthSt.trim());
        String daySt = currentDate.substring(0, 2);
        int day = Integer.parseInt(daySt.trim());

        //calculating how many times to press the "previous month" button
        int clicksForYear = ((year - 2007) * 12);
        int clicksForMonth = abs(month - 7);

        //total clicks
        int totalClicks = (clicksForYear + clicksForMonth);

        // selecting the date box, so the widget will open
        dateBox.click();

        //creating a web element for the widget
        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));

        //running for command to click the req. amount of times
        for (int i = 0; i < totalClicks; i++) {
            dateWidget.findElement(By.className("ui-datepicker-prev")).click();
        }

        //finding and clicking the correct date button
        dateWidget.findElement(By.xpath("//a[text()='4']")).click();

        assertEquals("07/04/2007",dateBox.getAttribute("value"));

    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
        WebElement dateBox = driver.findElement(By.xpath("//*[@id=\"vfb-8\"]"));
        String dateToEnter = "05/02/1959";
//        enter date '2 of May 1959' using text
        dateBox.click();
        dateBox.sendKeys(dateToEnter);
        assertEquals(dateToEnter,dateBox.getAttribute("value"));

//        check that correct date is added
    }
}
