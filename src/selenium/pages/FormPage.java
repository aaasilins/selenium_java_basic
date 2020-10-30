package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FormPage {
    protected static WebDriver driver;

    public FormPage(WebDriver driver) {
        this.driver = driver;
    }
    @FindBy(how = How.ID, using = "name") // By.id("name")
    private WebElement nameInput; // WebElement nameInput = driver.findElement(By.id("name"));
    @FindBy(how = How.ID, using = "job")
    private WebElement jobInput;
    @FindBy(how = How.ID, using = "modal_button")
    private WebElement submit;
    @FindBy(how = How.CSS, using = "#addEditPerson > div > button:nth-child(2)")
    private WebElement cancel;

    public void enterName(String name){
        nameInput.clear();
        nameInput.sendKeys(name);
    }
    public void enterJob(String job){
        jobInput.clear();
        jobInput.sendKeys(job);
    }
    public void clickSubmit(){
        submit.click();
    }
    public void enterNameJobAndSubmit(String name, String job){
        enterName(name);
        enterJob(job);
        submit.click();
    }
    public void enterNameAndJob(String name, String job){
        enterName(name);
        enterJob(job);
    }
    public void editNameJobAndSubmit(String name, String job){
        nameInput.clear();
        jobInput.clear();
        enterName(name);
        enterJob(job);
        submit.click();
    }
    public void editNameJobAndCancel(String name, String job){
        nameInput.clear();
        jobInput.clear();
        enterName(name);
        enterJob(job);
        cancel.click();
    }
}

