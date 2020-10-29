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
//        check that none of the checkboxes are ticked

        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));

        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected());
        }

//        tick  "Option 2"

        WebElement checkBox2  = driver.findElement(By.id("vfb-6-1"));
        checkBox2.click();

//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        WebElement checkBox1  = driver.findElement(By.id("vfb-6-0"));
        WebElement checkBox3  = driver.findElement(By.id("vfb-6-2"));

        assertFalse(checkBox1.isSelected());
        assertFalse(checkBox3.isSelected());
        assertTrue(checkBox2.isSelected());


//        tick  "Option 3"

        checkBox3.click();

//        click result
        WebElement resultButton  = driver.findElement(By.xpath("//button[@id='result_button_checkbox']"));
        resultButton.click();

//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
        WebElement resultText = driver.findElement(By.xpath("//p[@id='result_checkbox']"));
        assertEquals("You selected value(s): Option 2, Option 3", resultText.getText());
    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//        check that none of the radio are selected
        List<WebElement> radioButtons = driver.findElements(By.cssSelector(".w3-check[type='radio']"));

        for (WebElement radioButton : radioButtons) {
            assertFalse(radioButton.isSelected());
        }

//        select  "Option 3"
        WebElement option3 = driver.findElement(By.id("vfb-7-3"));
        //assertFalse(option3.isSelected());
        option3.click();
        //assertTrue(option3.isSelected());

//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        WebElement option1 = driver.findElement(By.id("vfb-7-1"));
        WebElement option2 = driver.findElement(By.id("vfb-7-2"));

        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertTrue(option3.isSelected());

//        select  "Option 1"
        option1.click();

//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertTrue(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

//        click result
        WebElement resultRadio = driver.findElement(By.id("result_button_ratio"));
        resultRadio.click();

//        check that 'You selected option: Option 1' text is being displayed
        WebElement resultTextRadio = driver.findElement(By.id("result_radio"));
        assertEquals("You selected option: Option 1", resultTextRadio.getText());

    }

    @Test
    public void selectOption() throws Exception {
//        select "Option 3" in Select
        WebElement selectOpt = driver.findElement(By.id("vfb-12"));
        selectOpt.click();

        Select dropdown = new Select(selectOpt);
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());
        dropdown.selectByVisibleText("Option 3");

//        check that selected option is "Option 3"
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());

//        select "Option 2" in Select
       // WebElement selectClickOut = driver.findElement(By.xpath("//h2[contains(text(),'Select')]"));
       // selectClickOut.click();
        selectOpt.click();
        dropdown.selectByValue("value2");

//        check that selected option is "Option 2"
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());

//        click result
        WebElement resultButton = driver.findElement(By.id("result_button_select"));
        resultButton.click();

//        check that 'You selected option: Option 2' text is being displayed
        WebElement resText = driver.findElement(By.id("result_select"));
        assertTrue(resText.isDisplayed());
        assertEquals("You selected option: Option 2", resText.getText());

    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//        enter date '4 of July 2007' using calendar widget

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -159);
        String result = new SimpleDateFormat("MM/04/yyyy").format(cal.getTime());

        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getAttribute("value"));

        dateBox.click();
        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));

        for (int i = 0; i < 159; i++) {
            dateWidget.findElement(By.className("ui-datepicker-prev")).click();
        }

        dateWidget.findElement(By.xpath("//a[text()='4']")).click();

//        check that correct date is added
        assertEquals(result, dateBox.getAttribute("value"));
        dateBox.clear();
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//        enter date '2 of May 1959' using text
        String dateToEnter = "05/02/1959";

        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getAttribute("value"));

        dateBox.clear();
        dateBox.sendKeys(dateToEnter);


//        check that correct date is added
        assertEquals(dateToEnter, dateBox.getAttribute("value"));

    }
}
