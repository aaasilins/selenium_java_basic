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
    @FindBy(how = How.ID, using = "surname")
    private WebElement surnameInput;
    @FindBy(how = How.ID, using = "job")
    private WebElement jobInput;
    @FindBy(how = How.CLASS_NAME, using = "dob")
    private WebElement dob;
    @FindBy(how = How.CLASS_NAME, using = "english")
    private WebElement english;
    @FindBy(how = How.CLASS_NAME, using = "french")
    private WebElement french;
    @FindBy(how = How.CLASS_NAME, using = "spanish")
    private WebElement spanish;
    @FindBy(how = How.CLASS_NAME, using = "male")
    private WebElement male;
    @FindBy(how = How.CLASS_NAME, using = "female")
    private WebElement female;
    @FindBy(how = How.CLASS_NAME, using = "status")
    private WebElement status;
    @FindBy(how = How.ID, using = "modal_button")
    private WebElement add;

    public void addPerson(String name, String surname, String job){
        nameInput.sendKeys(name);
        surnameInput.sendKeys(surname);
        jobInput.sendKeys(job);
        add.click();
    }
}

