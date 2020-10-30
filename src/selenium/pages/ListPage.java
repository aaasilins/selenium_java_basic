package selenium.pages;

import com.sun.deploy.perf.PerfRollup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import selenium.objects.PersonWithJob;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListPage {
    protected static WebDriver driver;

    public ListPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(how = How.ID, using = "name")
    private WebElement nameInput;
    @FindBy(how = How.ID, using = "job")
    private WebElement job;
    @FindBy(how = How.ID, using = "addPersonBtn")
    private WebElement addPerson;
    @FindBy(how = How.XPATH, using = "//*[@id=\"person0\"]/span[2]")
    private WebElement editPerson;
    @FindBy(how = How.XPATH, using = "//*[@id=\"person2\"]/span[1]")
    private WebElement deletePerson;

    public void clickAdd() {
        addPerson.click();
    }
    public void clickEdit() {
        editPerson.click();
    }
    public void clickDelete() {
       deletePerson.click();
    }
    public void deleteAll() throws InterruptedException{
        List<WebElement> personList = driver.findElements(By.xpath("//li[contains(@id, \"person\")]"));
        for (int i = 0; i < personList.size(); i++){
            driver.findElement(By.xpath("//*[@id=\"person"+i+"\"]/span[1]")).click();
            Thread.sleep(500);
        }
    }

    public List<PersonWithJob> storePersonList() {
            List<WebElement> personList = driver.findElements(By.xpath("//li[contains(@id, \"person\")]"));
            List<PersonWithJob> objectList = new ArrayList<>();
            for (int i = 0; i < personList.size(); i++){
                PersonWithJob a = new PersonWithJob();
                a.setName(driver.findElement(By.xpath("//*[@id=\"person"+i+"\"]/span[3]")).getText());
                a.setJob(driver.findElement(By.xpath("//*[@id=\"person"+i+"\"]/span[4]")).getText());
                objectList.add(a);
            }
            return objectList;
    }
    public void validateListAfterAdding(List<PersonWithJob> before, List<PersonWithJob> after, String newName, String newJob){
            for (int i = 0; i < before.size(); i++){
                assertEquals(before.get(i).getName(),after.get(i).getName());
                assertEquals(before.get(i).getJob(),after.get(i).getJob());
            }
            assertEquals(newName, after.get(after.size()-1).getName());
            assertEquals(newJob, after.get(after.size()-1).getJob());
    }
    public void validateListAfterEditing(List<PersonWithJob> before, List<PersonWithJob> after, String newName, String newJob){
            for (int i = 1; i < before.size(); i++){
                assertEquals(before.get(i).getName(),after.get(i).getName());
                assertEquals(before.get(i).getJob(),after.get(i).getJob());
            }
            assertEquals(newName, after.get(0).getName());
            assertEquals(newJob, after.get(0).getJob());
    }
    public void validateListAfterCancel(List<PersonWithJob> before, List<PersonWithJob> after, String newName, String newJob){
        for (int i = 0; i < before.size(); i++){
            assertEquals(before.get(i).getName(),after.get(i).getName());
            assertEquals(before.get(i).getJob(),after.get(i).getJob());
        }
    }
    public void validateListAfterDelete(List<PersonWithJob> before, List<PersonWithJob> after){
        assertEquals(before.size()-1, after.size());
        for (int i = 0; i < after.size(); i++){
            assertEquals(before.get(i).getName(),after.get(i).getName());
            assertEquals(before.get(i).getJob(),after.get(i).getJob());
        }
    }
    public boolean validateEmptyList(List<PersonWithJob> emptyList){
        return emptyList.isEmpty();
    }
    public boolean addButtonExistsAndWorking(){
        assertTrue(addPerson.isDisplayed());
        addPerson.click();
        assertEquals(driver.getCurrentUrl(), "https://kristinek.github.io/site/tasks/enter_a_new_person_with_a_job.html");
        return true;
    }
}
