package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class Task2 {
    WebDriver driver;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no tick are clicked
//         "Don't know" is selected in "Genre"
//         "Choose your option" in "How do you like us?"
//         check that the button send is blue with white letters

        WebElement name = driver.findElement(By.id("fb_name"));
        assertEquals(name.getAttribute("value"), "");

        WebElement age = driver.findElement(By.id("fb_age"));
        assertEquals(age.getAttribute("value"), "");


        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));

        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
            checkBox.click();
            assertTrue(checkBox.isSelected()); // checkboxes are selected
            checkBox.click();
            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
        }

        WebElement Dk = driver.findElement(By.cssSelector(".w3-radio[value=''][type='radio'"));
        assertTrue(Dk.isSelected());

        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());

        WebElement com = driver.findElement(By.xpath("//textarea[@class='w3-input w3-border']"));
        assertEquals(com.getAttribute("value"), "");


        WebElement button1 = driver.findElement(By.xpath(("//button")));
        assertEquals("rgba(33, 150, 243, 1)", button1.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",button1.getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or null
//         check button colors
//         (green with white letter and red with white letters)
        WebElement button1 = driver.findElement(By.xpath(("//button")));
        button1.click();
        WebElement name = driver.findElement(By.id("name"));
        assertEquals(name.getText(), "");
        WebElement age = driver.findElement(By.id("age"));
        assertEquals(age.getText(), "");
        WebElement lang = driver.findElement(By.id("language"));
        assertEquals(lang.getText(), "");
        WebElement genr  = driver.findElement(By.id("gender"));
        assertEquals(null, genr.getAttribute("value"));
        WebElement opt = driver.findElement(By.id("option"));
        assertEquals(null, opt.getAttribute("value"));
        WebElement com = driver.findElement(By.id("comment"));
        assertEquals(com.getText(), "");

        WebElement button2 = driver.findElement(By.xpath("//button[@class='w3-btn w3-green w3-xlarge']"));
        assertEquals("rgba(76, 175, 80, 1)", button2.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",button2.getCssValue("color"));

        WebElement button3 = driver.findElement(By.xpath("//button[@class='w3-btn w3-red w3-xlarge']"));
        assertEquals("rgba(244, 67, 54, 1)", button3.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",button3.getCssValue("color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
        WebElement name = driver.findElement(By.id("fb_name"));
        String NameField = "Dmitrijs";
        assertEquals(name.getAttribute("value"), ""); // checking that value is empty or ""
        name.sendKeys(NameField);
        assertEquals(name.getAttribute("value"), NameField);


        WebElement age = driver.findElement(By.id("fb_age"));
        String AgeField="21";
        assertEquals(age.getAttribute("value"), ""); // checking that value is empty or ""
        age.sendKeys(AgeField);
        assertEquals(age.getAttribute("value"), AgeField);

        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));

        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
            checkBox.click();
            assertTrue(checkBox.isSelected()); // checkboxes are selected
            checkBox.click();
            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
        }

        WebElement Eng = driver.findElement(By.cssSelector(".w3-check[value='English'][type='checkbox']"));
        assertFalse(Eng.isSelected());
        Eng.click();
        assertTrue(Eng.isSelected());

        WebElement male = driver.findElement(By.cssSelector(".w3-radio[value='male'][type='radio'"));
        assertFalse(male.isSelected());
        male.click();
        assertTrue(male.isSelected());

        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());
        dropdown.selectByIndex(1);
        assertEquals("Good", dropdown.getFirstSelectedOption().getText());

        WebElement com = driver.findElement(By.xpath("//textarea[@class='w3-input w3-border']"));
        String ComField = "Something";
        assertEquals(com.getAttribute("value"), "");
        com.sendKeys(ComField);
        assertEquals(com.getAttribute("value"), ComField);

        WebElement button1 = driver.findElement(By.xpath(("//button")));
       button1.click();

        WebElement namecheck = driver.findElement(By.id("name"));
        assertEquals("Dmitrijs", namecheck.getText());
        WebElement agecheck = driver.findElement(By.id("age"));
        assertEquals("21", agecheck.getText());
        WebElement langcheck = driver.findElement(By.id("language"));
        assertEquals("English", langcheck.getText());
        WebElement genrcheck  = driver.findElement(By.id("gender"));
        assertEquals("male", genrcheck.getText());
        WebElement optcheck = driver.findElement(By.id("option"));
        assertEquals("Good", optcheck.getText());
        WebElement comment = driver.findElement(By.id("comment"));
        assertEquals("Something", comment.getText());

        WebElement button2 = driver.findElement(By.xpath("//button[@class='w3-btn w3-green w3-xlarge']"));
        assertEquals("rgba(76, 175, 80, 1)", button2.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",button2.getCssValue("color"));

        WebElement button3 = driver.findElement(By.xpath("//button[@class='w3-btn w3-red w3-xlarge']"));
        assertEquals("rgba(244, 67, 54, 1)", button3.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",button3.getCssValue("color"));

    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background

        WebElement name = driver.findElement(By.id("fb_name"));
        String NameField = "Dmitrijs";
        assertEquals(name.getAttribute("value"), "");
        name.sendKeys(NameField);
        assertEquals(name.getAttribute("value"), NameField);

        WebElement button1 = driver.findElement(By.xpath(("//button")));
        button1.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement button2 = driver.findElement(By.xpath("//button[@class='w3-btn w3-green w3-xlarge']"));
        button2.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement message1 = driver.findElement(By.xpath("//h2[@id='message']"));
        assertEquals("Thank you, "+ NameField +", for your feedback!", message1.getText());

        WebElement pannel1 = driver.findElement(By.xpath("//div[@class='w3-panel w3-green']"));
        assertEquals("rgba(76, 175, 80, 1)", pannel1.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",pannel1.getCssValue("color"));

    }


    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
        WebElement name = driver.findElement(By.xpath("//input[@id='fb_name']"));
        String NameField = "Dmitrijs";
       assertEquals(name.getAttribute("value"), "");
        name.sendKeys(NameField);
        assertEquals(name.getAttribute("value"), NameField);


        WebElement age = driver.findElement(By.xpath("//input[@id='fb_age']"));
        String AgeField="21";
       assertEquals(age.getAttribute("value"), "");
        age.sendKeys(AgeField);
       assertEquals(age.getAttribute("value"), AgeField);

        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));

        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
            checkBox.click();
            assertTrue(checkBox.isSelected()); // checkboxes are selected
            checkBox.click();
            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
        }

        WebElement Eng = driver.findElement(By.cssSelector(".w3-check[value='English'][type='checkbox']"));
       assertFalse(Eng.isSelected());
        Eng.click();
       assertTrue(Eng.isSelected());

        WebElement male = driver.findElement(By.cssSelector(".w3-radio[value='male'][type='radio'"));
       assertFalse(male.isSelected());
        male.click();
       assertTrue(male.isSelected());

        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());
        dropdown.selectByIndex(1);
       assertEquals("Good", dropdown.getFirstSelectedOption().getText());

        WebElement com = driver.findElement(By.xpath("//textarea[@class='w3-input w3-border']"));
        String ComField = "Something";
        assertEquals(com.getAttribute("value"), "");
        com.sendKeys(ComField);
        assertEquals(com.getAttribute("value"), ComField);

        WebElement button1 = driver.findElement(By.xpath(("//button")));
        button1.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement button2 = driver.findElement(By.xpath("//button[@class='w3-btn w3-red w3-xlarge']"));
        button2.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        assertEquals(driver.findElement(By.id("fb_name")).getAttribute("value"), NameField);
        assertEquals(driver.findElement(By.id("fb_age")).getAttribute("value"), AgeField);
        assertEquals(driver.findElement(By.cssSelector(".w3-check[value='English'][type='checkbox']")).getAttribute("value"), "English");
        assertEquals(driver.findElement(By.cssSelector(".w3-radio[value='male'][type='radio'")).getAttribute("value"), "male");
        assertEquals(driver.findElement(By.id("like_us")).getAttribute("value"), "Good");
        assertEquals(driver.findElement(By.xpath("//textarea[@class='w3-input w3-border']")).getAttribute("value"), ComField);


    }
}
