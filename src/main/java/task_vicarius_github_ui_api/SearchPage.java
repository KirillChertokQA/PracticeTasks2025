package task_vicarius_github_ui_api;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;



    public class SearchPage extends BasePage {
        public SearchPage(WebDriver driver) {
            setDriver(driver);
            PageFactory.initElements(
                    new AjaxElementLocatorFactory(driver, 10), this);
        }

        @FindBy(id = "search-results-count")
        WebElement searchResultRepo;
        @FindBy(xpath = "//div[@display='inline-block']/a[last()-1]")
        WebElement lastPageResult;
        @FindBy(xpath = "//div[@data-testid='results-list']/div")
        List<WebElement> quantityRepoOnLastPage;

        public boolean verifySearchResults() {
            int searchResRepo;
            if(searchResultRepo.getText().contains("k")){
                searchResRepo = (int) (Float.parseFloat(searchResultRepo.getText().split("k ")[0])*1000);
            } else if (searchResultRepo.getText().contains("M")) {
                searchResRepo = (int) (Float.parseFloat(searchResultRepo.getText().split("M ")[0])*1000000);
            } else
                searchResRepo = Integer.parseInt(searchResultRepo.getText().split(" ")[0]);
            System.out.println("searchResRepo-->" + searchResRepo);

            System.out.println("quantityRepoOnLastPage --> " + quantityRepoOnLastPage.size());

            if (searchResRepo <= 10) {   // result [0 - 10]
                if (searchResRepo == quantityRepoOnLastPage.size())
                    return true;
                else
                    return false;
            } else {                    // result [11-999]
                int quantityPage = Integer.parseInt(lastPageResult.getText());
                System.out.println("quantityPage --> " + quantityPage);
                lastPageResult.click();
                if (searchResRepo == (quantityPage - 1) * 10 + quantityRepoOnLastPage.size())
                    return true;
                else
                    return false;
            }
        }
    }