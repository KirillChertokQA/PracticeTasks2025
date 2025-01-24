package parallel_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Parallel {
    WebDriver driver;

    @Test
    public void chromeTest(){
        System.out.println("Thread ID for Chrome -->" +Thread.currentThread().getId());
        driver = new ChromeDriver();
        driver.get("https://ilcarro.web.app/search");
    }

    @Test
    public void fireFox(){
        System.out.println("Thread ID for Firefox -->" +Thread.currentThread().getId());
        driver = new FirefoxDriver();
        driver.get("https://ilcarro.web.app/search");
    }
}
