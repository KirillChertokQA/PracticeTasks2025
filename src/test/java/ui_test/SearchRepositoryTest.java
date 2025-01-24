package ui_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import task_vicarius_github_ui_api.HomePage;
import task_vicarius_github_ui_api.SearchPage;

import java.util.concurrent.TimeUnit;

public class SearchRepositoryTest {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void beforeTest(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @Test(invocationCount = 3, threadPoolSize = 3, timeOut = 15000)
    public void searchRepositoryTestUI(){
        System.out.println("Thread ID for API -->" +Thread.currentThread().getId());
        String repository;   //QA-45 - 200   PhoneBook - 89k   ios - 506k     git - 10.5M
        repository = System.getProperty("repo","QA-45");
        if(repository.isBlank())
            Assert.fail("repository is blank");
        HomePage homePage = new HomePage(getDriver());
        homePage.typeSearchForm(repository.trim());
        SearchPage searchPage = new SearchPage(getDriver());
        Assert.assertTrue(searchPage.verifySearchResults());
    }

    @AfterMethod
    public void tearDown(){
        //driver.quit();
    }
}