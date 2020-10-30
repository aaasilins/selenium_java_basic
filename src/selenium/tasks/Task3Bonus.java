package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.objects.PersonWithJob;
import selenium.pages.FormPage;
import selenium.pages.ListPage;
import static org.junit.Assert.*;
import java.util.List;

public class Task3Bonus {
    WebDriver driver;
    ListPage listPage;
    FormPage formPage;
//    Bonus:
//    try storing people via an Object/separate class
    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/list_of_people_with_jobs");
        listPage = PageFactory.initElements(driver, ListPage.class);
        // should contain what you see when you just open the page (the table with names/jobs)
        formPage = PageFactory.initElements(driver, FormPage.class);
        // should be what you see if you click "Add" or "Edit" (2 input field and a button (Add/Edit) and (Cancel)
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void addPerson() throws InterruptedException {
        /* TODO:
         * implement adding new person using page object
         *
         * in order: store the list of people and jobs currently on page
         * add a person via "Add person button"
         * check the list again, that non of the people where changes, but an additional one with correct name/job was added
         */
        List<PersonWithJob> listBefore = listPage.storePersonList();
        listPage.clickAdd();
        formPage.enterNameJobAndSubmit("Vitalijs", "Engineer");
        List<PersonWithJob> listAfter = listPage.storePersonList();
        listPage.validateListAfterAdding(listBefore,listAfter, "Vitalijs", "Engineer");

    }

    @Test
    public void editPerson() throws InterruptedException{
        /* TODO:
         * implement editing a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * edit one of existing persons via the edit link
         * check the list again and that 2 people stayed the same and the one used was changed
         */
        List<PersonWithJob> listBefore = listPage.storePersonList();
        listPage.clickEdit();
        formPage.editNameJobAndSubmit("Vitalijs", "Engineer");
        List<PersonWithJob> listAfter = listPage.storePersonList();
        listPage.validateListAfterEditing(listBefore,listAfter, "Vitalijs", "Engineer");
    }

    @Test
    public void editPersonCancel() {
        /* TODO:
         * implement editing a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * edit one of existing persons via the edit link then click "Cancel" on edit form instead of "Edit"
         * check the list again and that no changes where made
         */
        List<PersonWithJob> listBefore = listPage.storePersonList();
        listPage.clickEdit();
        formPage.editNameJobAndCancel("Vitalijs", "Engineer");
        List<PersonWithJob> listAfter = listPage.storePersonList();
        listPage.validateListAfterCancel(listBefore,listAfter, "Vitalijs", "Engineer");
    }


    @Test
    public void deletePerson() {
        /* TODO:
         * implement deleting a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * delete 1 person see that there are now 2 people in the table with correct data
         */
        List<PersonWithJob> listBefore = listPage.storePersonList();
        listPage.clickDelete();
        List<PersonWithJob> listAfter = listPage.storePersonList();
        listPage.validateListAfterDelete(listBefore,listAfter);
    }


    @Test
    public void deletePersonAll() throws InterruptedException{
        /* TODO:
         * implement deleting a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * delete all people and check that there is no one on table on page, but the button Add is still present and working
         */
        List<PersonWithJob> listBefore = listPage.storePersonList();
        listPage.deleteAll();
        List<PersonWithJob> listAfter = listPage.storePersonList();
        assertTrue(listPage.validateEmptyList(listAfter));
        assertTrue(listPage.addButtonExistsAndWorking());
    }
}
