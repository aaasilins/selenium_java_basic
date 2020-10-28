package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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
//         check that all field are empty and no tick are clicked x
//         "Don't know" is selected in "Genre" x
//         "Choose your option" in "How do you like us?"
//         check that the button send is blue with white letters
        WebElement nameField= driver.findElement(By.id("fb_name"));
        WebElement ageField= driver.findElement(By.id("fb_age"));
        List<WebElement> checkBoxes=driver.findElements(By.className("w3-check"));
        List<WebElement> radioCircles=driver.findElements(By.className("w3-radio"));
        Select opinionField= new Select(driver.findElement(By.id("like_us")));
        WebElement submit=driver.findElement(By.cssSelector(".w3-btn-block"));
        assertEquals("", nameField.getAttribute("value"));
        assertEquals("", ageField.getAttribute("value"));
        for(WebElement checkbox:checkBoxes){
            assertFalse(checkbox.isSelected());
        }
        assertTrue(radioCircles.get(2).isSelected());
        assertEquals("Choose your option", opinionField.getFirstSelectedOption().getText());
        assertEquals("rgba(33, 150, 243, 1)", submit.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", submit.getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or null
//         check button colors
//         (green with white letter and red with white letters)
        WebElement submit=driver.findElement(By.cssSelector(".w3-btn-block"));
        submit.click();
        Thread.sleep(1000);
        WebElement nameField= driver.findElement(By.id("name"));
        WebElement ageField= driver.findElement(By.id("age"));
        WebElement languageField= driver.findElement(By.id("language"));
        WebElement genderField= driver.findElement(By.id("gender"));
        WebElement optionField= driver.findElement(By.id("option"));
        WebElement commentField= driver.findElement(By.id("comment"));
        assertEquals("",nameField.getText());
        assertEquals("",ageField.getText());
        assertEquals("",languageField.getText());
        assertEquals("null",genderField.getText());
        assertEquals("null",optionField.getText());
        assertEquals("",commentField.getText());
        WebElement yesButton=driver.findElement(By.cssSelector("button.w3-btn:nth-child(1)"));
        WebElement noButton=driver.findElement(By.cssSelector("button.w3-btn:nth-child(2)"));
        assertTrue("rgba(244, 67, 54, 1)".equals(noButton.getCssValue("background-color"))&&"rgba(255, 255, 255, 1)".equals(noButton.getCssValue("color")));
        assertTrue("rgba(76, 175, 80, 1)".equals(yesButton.getCssValue("background-color"))&&"rgba(255, 255, 255, 1)".equals(yesButton.getCssValue("color")));


    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
        WebElement nameField= driver.findElement(By.id("fb_name"));
        WebElement ageField= driver.findElement(By.id("fb_age"));
        List<WebElement> checkBoxes=driver.findElements(By.className("w3-check"));
        List<WebElement> radioCircles=driver.findElements(By.className("w3-radio"));
        Select opinionField= new Select(driver.findElement(By.id("like_us")));
        WebElement submit=driver.findElement(By.cssSelector(".w3-btn-block"));
        nameField.sendKeys("Chris");
        ageField.sendKeys("18");
        checkBoxes.get(1).click();
        radioCircles.get(1).click();
        opinionField.selectByVisibleText("Good");
        submit.click();
        Thread.sleep(1000);
        WebElement name= driver.findElement(By.id("name"));
        WebElement age= driver.findElement(By.id("age"));
        WebElement languageField= driver.findElement(By.id("language"));
        WebElement genderField= driver.findElement(By.id("gender"));
        WebElement optionField= driver.findElement(By.id("option"));
        WebElement commentField= driver.findElement(By.id("comment"));
        assertEquals("Chris",name.getText());
        assertEquals("18",age.getText());
        assertEquals("French",languageField.getText());
        assertEquals("female",genderField.getText());
        assertEquals("Good",optionField.getText());
        assertEquals("",commentField.getText());
        WebElement yesButton=driver.findElement(By.cssSelector("button.w3-btn:nth-child(1)"));
        WebElement noButton=driver.findElement(By.cssSelector("button.w3-btn:nth-child(2)"));
        assertTrue("rgba(244, 67, 54, 1)".equals(noButton.getCssValue("background-color"))&&"rgba(255, 255, 255, 1)".equals(noButton.getCssValue("color")));
        assertTrue("rgba(76, 175, 80, 1)".equals(yesButton.getCssValue("background-color"))&&"rgba(255, 255, 255, 1)".equals(yesButton.getCssValue("color")));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
        WebElement nameField= driver.findElement(By.id("fb_name"));
        nameField.sendKeys("Chris");
        WebElement submit=driver.findElement(By.cssSelector(".w3-btn-block"));
        submit.click();
        Thread.sleep(1000);
        WebElement yesButton=driver.findElement(By.cssSelector("button.w3-btn:nth-child(1)"));
        yesButton.click();
        Thread.sleep(1000);
        WebElement message= driver.findElement(By.id("message"));
        WebElement messageBox= driver.findElement(By.cssSelector(".w3-panel"));

        assertEquals("Thank you, Chris, for your feedback!", message.getText());
        assertTrue("rgba(76, 175, 80, 1)".equals(messageBox.getCssValue("background-color")));
        assertTrue("rgba(255, 255, 255, 1)".equals(messageBox.getCssValue("color")));




    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
        WebElement submit=driver.findElement(By.cssSelector(".w3-btn-block"));
        submit.click();
        Thread.sleep(1000);
        WebElement yesButton=driver.findElement(By.cssSelector("button.w3-btn:nth-child(1)"));
        yesButton.click();
        Thread.sleep(1000);
        WebElement message= driver.findElement(By.id("message"));
        WebElement messageBox= driver.findElement(By.cssSelector(".w3-panel"));
        assertEquals("Thank you for your feedback!", message.getText());
        assertTrue("rgba(76, 175, 80, 1)".equals(messageBox.getCssValue("background-color")));
        assertTrue("rgba(255, 255, 255, 1)".equals(messageBox.getCssValue("color")));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
        WebElement nameField= driver.findElement(By.id("fb_name"));
        WebElement ageField= driver.findElement(By.id("fb_age"));
        List<WebElement> checkBoxes=driver.findElements(By.className("w3-check"));
        List<WebElement> radioCircles=driver.findElements(By.className("w3-radio"));
        Select opinionField= new Select(driver.findElement(By.id("like_us")));
        WebElement submit=driver.findElement(By.cssSelector(".w3-btn-block"));
        nameField.sendKeys("Chris");
        ageField.sendKeys("18");
        checkBoxes.get(1).click();
        radioCircles.get(1).click();
        opinionField.selectByVisibleText("Good");
        submit.click();
        Thread.sleep(1000);
        WebElement noButton=driver.findElement(By.cssSelector("button.w3-btn:nth-child(2)"));
        noButton.click();
        Thread.sleep(1000);
        nameField= driver.findElement(By.id("fb_name"));
        ageField= driver.findElement(By.id("fb_age"));
        checkBoxes=driver.findElements(By.className("w3-check"));
        radioCircles=driver.findElements(By.className("w3-radio"));
        opinionField= new Select(driver.findElement(By.id("like_us")));
        assertEquals("Chris", nameField.getAttribute("value"));
        assertEquals("18", ageField.getAttribute("value"));
        assertTrue(radioCircles.get(1).isSelected());
        assertTrue(checkBoxes.get(1).isSelected());
        assertEquals(opinionField.getFirstSelectedOption().getText(), "Good");

    }
}
