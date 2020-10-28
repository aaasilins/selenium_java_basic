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
            WebElement button = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button"));
            List<WebElement> tickList = driver.findElements(By.className("w3-check"));
        //         check that all field are empty and no tick are clicked
            assertTrue(driver.findElement(By.id("fb_name")).getAttribute("value").isEmpty());
            assertTrue(driver.findElement(By.id("fb_age")).getAttribute("value").isEmpty());
            for (WebElement tickBox : tickList) {
                assertFalse(tickBox.isSelected());
            }
//         "Don't know" is selected in "Genre"
            assertTrue(driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[3]")).isSelected());
//         "Choose your option" in "How do you like us?"
            Select dropdown = new Select(driver.findElement(By.id("like_us")));
            dropdown.selectByIndex(1);
//         check that the button send is blue with white letters
            assertEquals("rgba(33, 150, 243, 1)", button.getCssValue("background-color"));
            assertEquals("rgba(255, 255, 255, 1)", button.getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
            WebElement button = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button"));
//         click "Send" without entering any data
            button.click();
//         check fields are empty or null
            assertEquals("", driver.findElement(By.id("name")).getText());
            assertEquals("", driver.findElement(By.id("age")).getText());
            assertEquals("", driver.findElement(By.id("language")).getText());
            assertEquals("null", driver.findElement(By.id("gender")).getText());
            assertEquals("null", driver.findElement(By.id("option")).getText());
            assertEquals("", driver.findElement(By.id("comment")).getText());
//         check button colors
//         (green with white letter and red with white letters)
            WebElement button1 = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]"));
            WebElement button2 = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]"));
            assertEquals("rgba(76, 175, 80, 1)", button1.getCssValue("background-color"));
            assertEquals("rgba(255, 255, 255, 1)", button1.getCssValue("color"));
            assertEquals("rgba(244, 67, 54, 1)", button2.getCssValue("background-color"));
            assertEquals("rgba(255, 255, 255, 1)", button2.getCssValue("color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
            driver.findElement(By.id("fb_name")).sendKeys("Vitalijs");
            driver.findElement(By.id("fb_age")).sendKeys("21");
            driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[1]")).click();
            driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[1]")).click();
            Select dropdown = new Select(driver.findElement(By.id("like_us")));
            dropdown.selectByIndex(1);
            driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).sendKeys("Test");
            driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();
//         check fields are filled correctly
            assertEquals("Vitalijs", driver.findElement(By.id("name")).getText());
            assertEquals("21", driver.findElement(By.id("age")).getText());
            assertEquals("English", driver.findElement(By.id("language")).getText());
            assertEquals("male", driver.findElement(By.id("gender")).getText());
            assertEquals("Good", driver.findElement(By.id("option")).getText());
            assertEquals("Test", driver.findElement(By.id("comment")).getText());
//         check button colors
//         (green with white letter and red with white letters)
            WebElement button1 = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]"));
            WebElement button2 = driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]"));
            assertEquals("rgba(76, 175, 80, 1)", button1.getCssValue("background-color"));
            assertEquals("rgba(255, 255, 255, 1)", button1.getCssValue("color"));
            assertEquals("rgba(244, 67, 54, 1)", button2.getCssValue("background-color"));
            assertEquals("rgba(255, 255, 255, 1)", button2.getCssValue("color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
            driver.findElement(By.id("fb_name")).sendKeys("Vitalijs");
//         click "Send"
            driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();
//         click "Yes"
            driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).click();
//         check message text: "Thank you, NAME, for your feedback!"
             WebElement text = driver.findElement(By.id("message"));
            assertEquals("Thank you, Vitalijs, for your feedback!", text.getText());
//         color of text is white with green on the background
            assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div")).getCssValue("background-color"));
            assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div")).getCssValue("color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
            driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();
//         click "Yes"
            driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).click();
//         check message text: "Thank you for your feedback!"
            WebElement text = driver.findElement(By.id("message"));
            assertEquals("Thank you for your feedback!", text.getText());
//         color of text is white with green on the background
            assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div")).getCssValue("background-color"));
            assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div")).getCssValue("color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
            driver.findElement(By.id("fb_name")).sendKeys("Vitalijs");
            driver.findElement(By.id("fb_age")).sendKeys("21");
            driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[1]")).click();
            driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[1]")).click();
            Select dropdown = new Select(driver.findElement(By.id("like_us")));
            dropdown.selectByIndex(1);
            driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).sendKeys("Test");
//         click "Send"
            driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();
//         click "No"
            driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).click();
//         check fields are filled correctly
            assertEquals("Vitalijs", driver.findElement(By.id("fb_name")).getAttribute("value"));
            assertEquals("21", driver.findElement(By.id("fb_age")).getAttribute("value"));
            assertTrue(driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[1]")).isSelected());
            assertTrue(driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[1]")).isSelected());
            assertEquals("Good", driver.findElement(By.id("like_us")).getAttribute("value"));
            assertEquals("Test", driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).getAttribute("value"));
    }
}
