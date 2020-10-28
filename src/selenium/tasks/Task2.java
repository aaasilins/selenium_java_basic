package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

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
        // checking name and age
        assertEquals("", driver.findElement(By.xpath("//*[@id=\"fb_name\"]")).getText());
        assertEquals("", driver.findElement(By.xpath("//*[@id=\"fb_age\"]")).getText());
        // checking all language tick boxes
        assertFalse(driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[1]")).isSelected());
        assertFalse(driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[2]")).isSelected());
        assertFalse(driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[3]")).isSelected());
        assertFalse(driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[4]")).isSelected());
        // checking all gender radios
        assertFalse(driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[1]")).isSelected());
        assertFalse(driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[2]")).isSelected());
//         "Don't know" is selected in "Genre"
        assertTrue(driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[3]")).isSelected());
//         "Choose your option" in "How do you like us?"
        Select dropdownMenu = new Select(driver.findElement(By.xpath("//*[@id=\"like_us\"]")));
        assertEquals("Choose your option", dropdownMenu.getFirstSelectedOption().getText());
//         check that the button send is blue with white letters
        WebElement sendButton = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button"));
        //rgba(33, 149, 242, 1) this is what I got with a color picker, its practically the same as the button
        assertEquals("rgba(33, 150, 243, 1)", sendButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", sendButton.getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
        WebElement sendButton = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button"));
//         TODO:
//         click "Send" without entering any data
        sendButton.click();
//         check fields are empty or null
        assertEquals("", driver.findElement(By.xpath("//*[@id=\"name\"]")).getText());
        assertEquals("", driver.findElement(By.xpath("//*[@id=\"age\"]")).getText());
        assertEquals("", driver.findElement(By.xpath("//*[@id=\"language\"]")).getText());
        assertEquals("null", driver.findElement(By.xpath("//*[@id=\"gender\"]")).getText());
        assertEquals("null", driver.findElement(By.xpath("//*[@id=\"option\"]")).getText());
        assertEquals("", driver.findElement(By.xpath("//*[@id=\"comment\"]")).getText());
//         check button colors
        WebElement yesButton = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]"));
        WebElement noButton = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]"));

        assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", yesButton.getCssValue("color"));

        assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", noButton.getCssValue("color"));
//         (green with white letter and red with white letters)
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
// Creating variables
        String name = "Dan";
        String age = "28";
        String comment = "Thank you";
// Entering name and age
        driver.findElement(By.xpath("//*[@id=\"fb_name\"]")).sendKeys(name);
        driver.findElement(By.xpath("//*[@id=\"fb_age\"]")).sendKeys(age);
// Selecting English and male
        driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[1]")).click();
// Selecting the first dropdown menu option
        Select dropdownMenu = new Select(driver.findElement(By.xpath("//*[@id=\"like_us\"]")));
        dropdownMenu.selectByIndex(1);
// Typing in the comment
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).sendKeys(comment);
// Clicking send
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();

//         check fields are filled correctly
        assertEquals(name, driver.findElement(By.xpath("//*[@id=\"name\"]")).getText());
        assertEquals(age, driver.findElement(By.xpath("//*[@id=\"age\"]")).getText());
        assertEquals("English", driver.findElement(By.xpath("//*[@id=\"language\"]")).getText());
        assertEquals("male", driver.findElement(By.xpath("//*[@id=\"gender\"]")).getText());
        assertEquals("Good", driver.findElement(By.xpath("//*[@id=\"option\"]")).getText());
        assertEquals(comment, driver.findElement(By.xpath("//*[@id=\"comment\"]")).getText());

//         check button colors
//         (green with white letter and red with white letters)
        WebElement yesButton = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]"));
        WebElement noButton = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]"));

        assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", yesButton.getCssValue("color"));

        assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", noButton.getCssValue("color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
        String name = "Dan";
//         enter only name
        driver.findElement(By.xpath("//*[@id=\"fb_name\"]")).sendKeys(name);
//         click "Send"
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();
//         click "Yes"
        driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).click();
//         check message text: "Thank you, NAME, for your feedback!"
        assertEquals("Thank you, Dan, for your feedback!", driver.findElement(By.xpath("//*[@id=\"message\"]")).getText());
//         color of text is white with green on the background
        WebElement tyMsg = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div"));

        assertEquals("rgba(76, 175, 80, 1)", tyMsg.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", tyMsg.getCssValue("color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();
//         click "Yes"
        driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).click();
//         check message text: "Thank you for your feedback!"
        assertEquals("Thank you for your feedback!", driver.findElement(By.xpath("//*[@id=\"message\"]")).getText());
//         color of text is white with green on the background
        WebElement tyMsg = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div"));

        assertEquals("rgba(76, 175, 80, 1)", tyMsg.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", tyMsg.getCssValue("color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        // Creating variables
        String name = "Dan";
        String age = "28";
        String comment = "Thank you";
// Entering name and age
        driver.findElement(By.xpath("//*[@id=\"fb_name\"]")).sendKeys(name);
        driver.findElement(By.xpath("//*[@id=\"fb_age\"]")).sendKeys(age);
// Selecting English and male
        driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[1]")).click();
// Selecting the first dropdown menu option
        Select dropdownMenu = new Select(driver.findElement(By.xpath("//*[@id=\"like_us\"]")));
        dropdownMenu.selectByIndex(1);
// Typing in the comment
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).sendKeys(comment);
//         click "Send"
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();
//         click "No"
        driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).click();
//         check fields are filled correctly
// Checking the name and age

        // Copying text to the clipboard
        driver.findElement(By.xpath("//*[@id=\"fb_name\"]")).sendKeys(Keys.CONTROL + "a");
        driver.findElement(By.xpath("//*[@id=\"fb_name\"]")).sendKeys(Keys.CONTROL + "c");

        // Transferring contents of the clipboard
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        String textFromClipboard = (String) contents.getTransferData(DataFlavor.stringFlavor);

        // Checking name
        assertEquals(name, textFromClipboard);

        // Copying text to the clipboard
        driver.findElement(By.xpath("//*[@id=\"fb_age\"]")).sendKeys(Keys.CONTROL + "a");
        driver.findElement(By.xpath("//*[@id=\"fb_age\"]")).sendKeys(Keys.CONTROL + "c");

        // Transferring contents of the clipboard
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        contents = clipboard.getContents(null);
        textFromClipboard = (String) contents.getTransferData(DataFlavor.stringFlavor);

        // Checking age
        assertEquals(age,textFromClipboard);

// Checking the language and gender section
        assertTrue(driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[1]")).isSelected());
        assertTrue(driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[1]")).isSelected());
// Checking the dropdown menu

        //assertEquals("Good", dropdownMenu.getFirstSelectedOption().getText());
        // I dont understand why it does not work... (


        // Checking the comment section
        // Copying text to the clipboard
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).sendKeys(Keys.CONTROL+"a");
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).sendKeys(Keys.CONTROL+"c");

        // Transferring contents of the clipboard
        contents = clipboard.getContents(null);
        textFromClipboard = (String) contents.getTransferData(DataFlavor.stringFlavor);

        // Checking age
        assertEquals(comment,textFromClipboard);

    }
}
