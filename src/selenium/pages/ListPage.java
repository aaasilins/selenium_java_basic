package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class ListPage {
    protected static WebDriver driver;

    public ListPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(how = How.CLASS_NAME, using = "name")
    private WebElement nameInput;
    @FindBy(how = How.CLASS_NAME, using = "surname")
    private WebElement surname;
    @FindBy(how = How.CLASS_NAME, using = "job")
    private WebElement job;
    @FindBy(how = How.CLASS_NAME, using = "dob")
    private WebElement dob;
    @FindBy(how = How.CLASS_NAME, using = "language")
    private WebElement language;
    @FindBy(how = How.CLASS_NAME, using = "gender")
    private WebElement gender;
    @FindBy(how = How.CLASS_NAME, using = "status")
    private WebElement status;
    @FindBy(how = How.ID, using = "addPersonBtn")
    private WebElement addPerson;

    public void addPerson() {
        addPerson.click();
    }

    public List<WebElement> storePersonList() {
            List<WebElement> links = driver.findElements(By.xpath("//li[contains(@id, \"person\")]"));
            return links;
    }
    public void compareList(List<WebElement> first, List<WebElement> second){
                List<WebElement> removeList = new ArrayList<>(second);
                removeList.removeAll(first);
                System.out.println(first.size());
    }
}
