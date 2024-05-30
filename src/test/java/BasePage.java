import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

abstract public class BasePage {

    public WebDriver driver;

    // create one webdriver wait object for all of the methods in this class
    public WebDriverWait wait;

    int waitTimeforWebDriverWait = 15;


    // Refactoring:  move this to a central point in a config file
    public String baseURL = "https://magento.softwaretestingboard.com/";


    public void clearAllCookes()
    {
        driver.manage().deleteAllCookies();
    }

    public void verifyElementExists(String elementCss) {
        Assert.assertFalse(driver.findElements(By.cssSelector(elementCss)).isEmpty(), "Element not found: " + elementCss);
    }

    public void verifyExpectedPageURL(String expectedPageURL) {
        String currentURL = driver.getCurrentUrl();
        // Verify that the actual URL matches the expected URL
        Assert.assertEquals(currentURL, expectedPageURL, "URL mismatch. Expected: " + expectedPageURL + " but found: " + currentURL);

    }

    public void CloseEthicalAd()
    {

        /*try {
            WebElement ethicalAdsStickyBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ea-stickybox-hide")));
        }
        catch {}*/
        var ethicalAdsStickyBoxes = driver.findElements(By.cssSelector(".ea-stickybox-hide"));

        if ( ethicalAdsStickyBoxes.size() > 0)
            ethicalAdsStickyBoxes.get(0).click();

        sleep(1000);
    }

  /*  public void waitForGoogleAdsToLoad()
    {
        WebElement loginLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.authorization-link")));
        closeGoogleAd();
    }

    public void closeGoogleAd ()
    {  // close ad
        String adCloseButtonCss = "div#cbb.cbb";
        WebElement loginLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.authorization-link")));

        // need to switch to iFrame here
        // WebElement adcloseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(adCloseButtonCss)));

        var adcloseButtons = driver.findElements(By.cssSelector(adCloseButtonCss));
        if (!adcloseButtons.isEmpty()) {
            //adcloseButtons.getFirst().click();

            // Switch to the iframe with id "aswift_1"
            driver.switchTo().frame("aswift_1");

            // Find the button inside the iframe using its CSS selector
            WebElement closeButton = driver.findElement(By.cssSelector("#cbb"));

            // Click on the button
            closeButton.click();

            // Switch back to the default content
            driver.switchTo().defaultContent();

            System.out.print ("Closed the Google Ad");
        }
    } */


    // Refactoring:  consider moving these methods to helper functions class

    // Helper methods
    public void ScrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void sleep (int miliseconds)
    {
        try {
            Thread.sleep(miliseconds); // Sleep for 2 seconds (2000 milliseconds)
        } catch (InterruptedException e) {
            // Handle any potential interruptions
            e.printStackTrace();
        }
    }
}
