package selenium.tasks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import selenium.pages.AgeSamplePage;
import selenium.pages.AgeSubmittedSamplePage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.Color;

import static org.junit.Assert.*;

public class Task2 {
    static WebDriver driver;
    static AgeSamplePage agePage;
    static AgeSubmittedSamplePage ageSubmittedPage;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        agePage = PageFactory.initElements(driver, AgeSamplePage.class);
        ageSubmittedPage = PageFactory.initElements(driver, AgeSubmittedSamplePage.class);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no tick are clicked


        List<WebElement> inputFields = driver.findElements(By.cssSelector(".w3-input w3-border[type='text']"));
        for (WebElement inputField : inputFields) {
            assertFalse(inputField.isSelected());
        }
        boolean condition = false;

        for (WebElement inputField : inputFields) {
            if (inputField.getAttribute("value").isEmpty()) {
                condition = true;
            }
            assertTrue(condition);
        }

        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected());
        }

//         "Don't know" is selected in "Genre"


        /*
        List<WebElement> radios = driver.findElements(By.cssSelector(".w3-radio[type='radio']"));
        for (WebElement radio : radios) {
            assertFalse(radio.isSelected());
        }
*/
        WebElement select = driver.findElement(By.id("like_us"));
        assertFalse(select.isSelected());


        WebElement textArea = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/form[1]/div[6]/textarea[1]"));
        assertFalse(textArea.isSelected());


        if (textArea.getAttribute("value").isEmpty()) {
            condition = true;
        }
        assertTrue(condition);

//         "Choose your option" in "How do you like us?"
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Good");

//         check that the button send is blue with white letters
        WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Send')]"));
        System.out.println(button.getCssValue("background-color"));
        //assertEquals("rgba(0, 0, 0, 1)", button.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", button.getCssValue("text-decoration-color"));
        //rgb(33, 150, 243)
        System.out.println(button.getCssValue("text-decoration-color"));
        //.w3-btn, .w3-btn-block
        assertEquals("rgba(255, 255, 255, 1)", button.getCssValue("color"));
        //rgb(33, 150, 243)
        System.out.println(button.getCssValue("color"));



        //final Color colPrint = Color.fromString(button.getCssValue("color"));

                //SystemColor(button.getCssValue("color"));
//etc...



    }


        @Test
        public void emptyFeedbackPage() throws Exception {

            //Color RGB_COLOUR = Color.fromString("rgb(255, 255, 255)");
            //System.out.println(Color.fromString("rgb(255, 255, 255)"));

            //Color RGB_COLOUR = Color.fromString("rgb(255, 255, 255)");
            WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Send')]"));
            System.out.println(button.getCssValue("background-color"));

            ArrayList colorList = new ArrayList();

            colorList.add(new String[] {"255", "0", "0", "RED"});
            colorList.add(new String[] {"0", "128", "0", "GREEN"});
            colorList.add(new String[] {"0", "0", "255", "BLUE"});
            colorList.add(new String[] {"255", "255", "255", "WHITE"});
            colorList.add(new String[] {"0", "0", "0", "BLACK"});
            colorList.add(new String[]{"128", "128", "128", "GRAY"});
            rgb(255, 255, 255)

            System.out.println(button.getCssValue("color")); //test color

            //Color c = new Color(button.getCssValue("color")); //test color
            String name = "Unknown";
            for (String[] color : colorList)
            {
                int r = Integer.parseInt(color[0]); //red value
                int g = Integer.parseInt(color[1]); //green
                int b = Integer.parseInt(color[2]); //blue
                
                String alias = color[3]; //color NAME

                if ((c.getRed() == r) && (c.getGreen() == g) && (c.getBlue() == b))
                {
                    name = alias; //found it
                    break;
                }
                System.out.println(name);
            }
/*
            int rgbDistance = Math.abs(c.getRed() - r.getRed() +
                            Math.abs(c.getGreen() - g.getGreen()) +
                            Math.abs(c.getBlue() - b.getBlue()) +



            int temp = 1000;
            Color c = null;
            for (int j = 0; j < names.length; j++) {
                //Color c = hex2Rgb(names[j][0]);
                int rgbDistance = Math.abs(c.getRed() - r.getRed() +
                        Math.abs(c.getGreen() - g.getGreen()) +
                        Math.abs(c.getBlue() - b.getBlue()) +

                if (rgbDistance < temp) { temp = rgbDistance; colorName = names[j][1]; c = myColor; } }

*/

//         TODO:
//         click "Send" without entering any data
//         check fields are empty or null
//         check button colors
//         (green with white letter and red with white letters)
        }

        @Test
        public void notEmptyFeedbackPage() throws Exception {


//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
        }

        @Test
        public void yesOnWithNameFeedbackPage() throws Exception {


//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
        }

        @Test
        public void yesOnWithoutNameFeedbackPage() throws Exception {


//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
        }

        @Test
        public void noOnFeedbackPage() throws Exception {


//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
        }
    }

