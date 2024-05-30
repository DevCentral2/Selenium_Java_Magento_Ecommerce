import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HomePage extends BasePage {

    //public WebDriver driver;

    // create one webdriver wait object for all of the methods in this class
    //private WebDriverWait wait;

    //int waitTimeforWebDriverWait = 15;
    public HomePage(WebDriver _webdriver) {

        driver = _webdriver;

        // instantiate the webdriver wait object
        wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeforWebDriverWait));
    }

    public String baseURL = "https://magento.softwaretestingboard.com/";

    private String pageTitle = "Home Page";
    private String storeLogo = "[aria-label='store logo']";
    private String loginLinkCss = "li.authorization-link";
    private String shoppingCart = "a.action.showcart";
    private String cookieConsentCss = ".fc-button-label";
    private String  searchField = "input#search";


    public String getStoreLogo ()
    {
        return storeLogo;
    }
    public String getLoginLinkCss()
    {
        return loginLinkCss;
    }
    public String getShoppingCart ()
    {
        return shoppingCart;
    }
    public String getSearchField ()
    {
        return searchField;
    }


    public void navigateToHomePage() {
        driver.navigate().to(baseURL);
        driver.manage().window().maximize();

        CloseEthicalAd();
    }

    public void verifyPageTitle ()
    {
        String actualTitle = driver.getTitle();
        // Verify that the actual title matches the expected title
        Assert.assertEquals(actualTitle, pageTitle, "Page title mismatch. Expected: " + pageTitle + " but found: " + actualTitle);

    }


    public void verifyHomePageURL() {
        String currentURL = driver.getCurrentUrl();
        // Verify that the actual URL matches the expected URL
        Assert.assertEquals(currentURL, baseURL, "URL mismatch. Expected: " + baseURL + " but found: " + currentURL);

    }

    public void acceptCookies() {

        var cookieConsentLinks = driver.findElements(By.cssSelector(cookieConsentCss));

        // no need to click this if the cookie accept dialog doesn't appear
        if (!cookieConsentLinks.isEmpty()) {
            var cookieConsentLink = driver.findElement(By.cssSelector(cookieConsentCss));
            cookieConsentLink.click();
        }

    }

    public void clickLoginLink() {

        WebElement loginLink = driver.findElement(By.cssSelector(loginLinkCss));
        loginLink.click();
    }

}
