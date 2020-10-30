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
        String libWithDriversLocation = System.getProperty("user.dir") + "/lib/";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver");
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
        WebElement nameField = driver.findElement(By.id("fb_name"));
        WebElement ageField = driver.findElement(By.id("fb_age"));
        WebElement englishCheckbox = driver.findElement(By.xpath("//*[@id='lang_check']/input[1]"));
        WebElement frenchCheckbox = driver.findElement(By.xpath("//*[@id='lang_check']/input[2]"));
        WebElement spanishCheckbox = driver.findElement(By.xpath("//*[@id='lang_check']/input[3]"));
        WebElement chineseCheckbox = driver.findElement(By.xpath("//*[@id='lang_check']/input[4]"));
        WebElement genderDontKnowRadio = driver.findElement(By.xpath("//*[@class='w3-radio' and @name='gender']"));
        Select likeUsField = new Select(driver.findElement(By.id("like_us")));
        WebElement sendButton = driver.findElement(By.xpath("//button[contains(text(),'Send')]"));

        assertEquals("", nameField.getText());
        assertEquals("", ageField.getText());
        assertFalse(englishCheckbox.isSelected());
        assertFalse(frenchCheckbox.isSelected());
        assertFalse(spanishCheckbox.isSelected());
        assertFalse(chineseCheckbox.isSelected());

//         "Don't know" is selected in "Genre"
        assertTrue(genderDontKnowRadio.isEnabled());
//         "Choose your option" in "How do you like us?"
        assertEquals("Choose your option",likeUsField.getFirstSelectedOption().getText());
//         check that the button send is blue with white letters
        assertEquals("rgba(33, 150, 243, 1)",sendButton.getCssValue("background-color"));



    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
        WebElement sendButton = driver.findElement(By.xpath("//button[contains(text(),'Send')]"));
//         click "Send" without entering any data
        sendButton.click();
//         check fields are empty or null
        List<WebElement> fields = driver.findElements(By.className("description"));
        for (WebElement field : fields) {
            //System.out.println(field.getAttribute("value"));
            assertEquals(null,field.getAttribute("value"));
        }
//         check button colors
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//button[contains(text(),'Yes')]")).getCssValue("background-color"));
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.xpath("//button[contains(text(),'No')]")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//button[contains(text(),'Yes')]")).getCssValue("color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//button[contains(text(),'No')]")).getCssValue("color"));

//         (green with white letter and red with white letters)
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        WebElement nameField = driver.findElement(By.id("fb_name"));
        WebElement ageField = driver.findElement(By.id("fb_age"));
        WebElement englishCheckbox = driver.findElement(By.xpath("//*[@id='lang_check']/input[1]"));
        WebElement frenchCheckbox = driver.findElement(By.xpath("//*[@id='lang_check']/input[2]"));
        WebElement spanishCheckbox = driver.findElement(By.xpath("//*[@id='lang_check']/input[3]"));
        WebElement chineseCheckbox = driver.findElement(By.xpath("//*[@id='lang_check']/input[4]"));
        WebElement genderMale = driver.findElement(By.xpath("//*[@class='w3-radio' and @name='gender' and @value='male']"));
        Select likeUsField = new Select(driver.findElement(By.id("like_us")));
        WebElement sendButton = driver.findElement(By.xpath("//button[contains(text(),'Send')]"));
        WebElement textArea = driver.findElement(By.xpath("//textarea[@name='comment']"));




        nameField.sendKeys("Kelley");
        ageField.sendKeys("53");
        englishCheckbox.click();
        frenchCheckbox.click();
        genderMale.click();
        likeUsField.selectByVisibleText("Good");
        textArea.sendKeys("All is fine");
        sendButton.click();
//         check fields are filled correctly
        WebElement nameResult = driver.findElement(By.cssSelector("#name"));
        WebElement ageResult = driver.findElement(By.cssSelector("#age"));
        WebElement languageResult = driver.findElement(By.cssSelector("#language"));
        WebElement genderResult = driver.findElement(By.cssSelector("#gender"));
        WebElement optionResult = driver.findElement(By.cssSelector("#option"));
        WebElement commentResult = driver.findElement(By.cssSelector("#comment"));

        assertEquals("Kelley",nameResult.getText());
        assertEquals("53",ageResult.getText());
        assertEquals("English,French",languageResult.getText());
        assertEquals("male",genderResult.getText());
        assertEquals("Good", optionResult.getText());
        assertEquals("All is fine",commentResult.getText());

//         check button colors
//         (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//button[contains(text(),'Yes')]")).getCssValue("background-color"));
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.xpath("//button[contains(text(),'No')]")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//button[contains(text(),'Yes')]")).getCssValue("color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//button[contains(text(),'No')]")).getCssValue("color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        WebElement nameField = driver.findElement(By.id("fb_name"));
        nameField.sendKeys("Kelley");
//         click "Send"
        WebElement sendButton = driver.findElement(By.xpath("//button[contains(text(),'Send')]"));
        sendButton.click();
//         click "Yes"
        WebElement yesButton = driver.findElement(By.xpath("//button[contains(text(),'Yes')]"));
        yesButton.click();
//         check message text: "Thank you, NAME, for your feedback!"
        WebElement textMessage = driver.findElement(By.cssSelector("#message"));
        WebElement textMessageClass = driver.findElement(By.cssSelector(".w3-panel.w3-green"));
        assertEquals("Thank you, Kelley, for your feedback!", textMessage.getText());
//         color of text is white with green on the background
        assertEquals("rgba(255, 255, 255, 1)",textMessage.getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)",textMessageClass.getCssValue("background-color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        WebElement sendButton = driver.findElement(By.xpath("//button[contains(text(),'Send')]"));
        sendButton.click();
//         click "Yes"
        WebElement yesButton = driver.findElement(By.xpath("//button[contains(text(),'Yes')]"));
        yesButton.click();
//         check message text: "Thank you for your feedback!"
        WebElement textMessage = driver.findElement(By.cssSelector("#message"));
        assertEquals("Thank you for your feedback!",textMessage.getText());
//         color of text is white with green on the background
        WebElement textMessageClass = driver.findElement(By.cssSelector(".w3-panel.w3-green"));
        assertEquals("rgba(255, 255, 255, 1)",textMessage.getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)",textMessageClass.getCssValue("background-color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        WebElement nameField = driver.findElement(By.id("fb_name"));
        WebElement ageField = driver.findElement(By.id("fb_age"));
        WebElement englishCheckbox = driver.findElement(By.xpath("//*[@id='lang_check']/input[1]"));
        WebElement frenchCheckbox = driver.findElement(By.xpath("//*[@id='lang_check']/input[2]"));
        WebElement genderMale = driver.findElement(By.xpath("//*[@class='w3-radio' and @name='gender' and @value='male']"));
        Select likeUsField = new Select(driver.findElement(By.id("like_us")));
        WebElement sendButton = driver.findElement(By.xpath("//button[contains(text(),'Send')]"));
        WebElement textArea = driver.findElement(By.xpath("//textarea[@name='comment']"));

        nameField.sendKeys("Kelley");
        ageField.sendKeys("53");
        englishCheckbox.click();
        frenchCheckbox.click();
        genderMale.click();
        likeUsField.selectByVisibleText("Good");
        textArea.sendKeys("All is fine");

//         click "Send"
        sendButton.click();
//         click "No"
        WebElement noButton = driver.findElement(By.xpath("//button[contains(text(),'No')]"));
        noButton.click();
        Thread.sleep(2000);
//         check fields are filled correctly
        WebElement nameField1 = driver.findElement(By.id("fb_name"));
        WebElement ageField1 = driver.findElement(By.id("fb_age"));
        WebElement englishCheckbox1 = driver.findElement(By.xpath("//*[@id='lang_check']/input[1]"));
        WebElement frenchCheckbox1 = driver.findElement(By.xpath("//*[@id='lang_check']/input[2]"));
        WebElement genderMale1 = driver.findElement(By.xpath("//*[@class='w3-radio' and @name='gender' and @value='male']"));
        Select likeUsField1 = new Select(driver.findElement(By.id("like_us")));
        WebElement textArea1 = driver.findElement(By.xpath("//textarea[@name='comment']"));


        assertEquals("Kelley",nameField1.getAttribute("value"));
        assertEquals("53",ageField1.getAttribute("value"));
        assertTrue(englishCheckbox1.isSelected());
        assertTrue(frenchCheckbox1.isSelected());
        assertTrue(genderMale1.isSelected());
        assertEquals("Good", likeUsField1.getFirstSelectedOption().getText());
        assertEquals("All is fine",textArea1.getAttribute("value"));

    }

}
