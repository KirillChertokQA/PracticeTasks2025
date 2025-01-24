package task_vicarius_github_ui_api;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver){
        setDriver(driver);
        driver.get("https://github.com/");
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver, 10), this);
    }
    @FindBy(xpath = "//button[@data-target='qbsearch-input.inputButton']")
    WebElement btnSearch;

    @FindBy(id = "query-builder-test")
    WebElement inputSearch;

    public void typeSearchForm(String repo){
        btnSearch.click();
        inputSearch.sendKeys(repo);
        inputSearch.sendKeys(Keys.ENTER);
    }
}
