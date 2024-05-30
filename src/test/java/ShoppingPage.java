import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShoppingPage extends BasePage{

    //private WebDriver driver;

    // create one webdriver wait object for all of the methods in this class
    //private WebDriverWait wait;

    //int waitTimeforWebDriverWait = 15;

    // Constructor
    public ShoppingPage(WebDriver _driver) {
        this.driver = _driver;

        // instantiate the webdriver wait object
        wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeforWebDriverWait));
       /* wait.IgnoreExceptionTypes(
                typeof(WebDriverTimeoutException),
                typeof(NoSuchElementException)

        );   */

    }

    public String shippingPageLogo = "Shipping";
    public String shoppingCartLogo = "Shopping Cart";
    public String searchEntireStore = "Search entire store here";
    public String quantityField = "Qty['spinbutton']";

    // confirmation or warning messages
    public String thankYouForYourPurchase = "Thank you for your purchase!";
    public String quantityExceedsMaxPurchase = "The maximum you may purchase is 10000.";
    public String quantityExceededInShoppingCart = "The requested qty exceeds the maximum qty allowed in shopping cart";
    public String quantityNotAvailable = "The requested qty is not available";
    public String youHaveNoItemsInCart = "You have no items in your shopping cart.";

    // Page Methods
    public void selectItemTypeFromMenus(String gender, String topsBottoms, String typeOfClothing) {
        HoverOverMenuItem(gender);
        HoverOverMenuItem(topsBottoms);
        HoverOverMenuItem(typeOfClothing);
        ClickMenuItem(typeOfClothing);
    }

    public void selectItemTypeFromPage(String gender, String typeOfClothing) {
        ClickMenuItem(gender);
        ClickLeftMenuItem(typeOfClothing);
    }

    public void SelectFirstItem() {
        driver.findElement(By.cssSelector(".product-image-photo")).click();
    }

    public void selectItemChooseItemPage(String itemToPurchase) {
        driver.findElement(By.cssSelector(".product-item-link")).click();
    }

    public void selectItemSpecifics(String size, String colour) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("product-options-wrapper")));

        String cssSelectorSize = "[option-label='" + size + "']";
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelectorSize))).click();

        String cssSelectorColour = "[option-label='" + colour + "']";
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelectorColour))).click();
    }

    public void addToCart() {
        driver.findElement(By.id("product-addtocart-button")).click();
    }

    public void clickShoppingCartLink() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'shopping cart')]")))
                .click();
    }

    public void clickProceedToCheckout() {
        CloseEthicalAd();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".item > button.action.primary.checkout")))
                .click();
    }

    public void clickNextButtonOnOrderPage() {
        String cssSelectorNextBtn = ".button.action.continue.primary";
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelectorNextBtn)));
        ScrollIntoView(nextButton);
        CloseEthicalAd();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelectorNextBtn)))
                .click();
    }

    public void clickPlaceOrder() {
        String cssSelectorPlaceOrdrBtn = "button.action.primary.checkout";
        WebElement placeOrderButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelectorPlaceOrdrBtn)));
        ScrollIntoView(placeOrderButton);
        CloseEthicalAd();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelectorPlaceOrdrBtn)))
                .click();
    }

    public void SetQuantity(String quantity) {
        WebElement qtyInput = driver.findElement(By.cssSelector("input#qty.input-text.qty"));
        qtyInput.clear();
        qtyInput.sendKeys(quantity);
    }

    public void clickOrderNumber() {
        driver.findElement(By.cssSelector(".order-number")).click();
    }

    public void SearchByProductId(String productId) {
        WebElement searchInput = driver.findElement(By.cssSelector("#search.input-text"));
        searchInput.clear();
        searchInput.sendKeys(productId + Keys.RETURN);
    }

    // Helper Methods
    private WebElement GetMenuItemElement(String menuItemText) {
        String xPathSelector = "//a/span[contains(text(),'" + menuItemText + "')]";
        WebElement menuItem = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPathSelector)));
        wait.until(ExpectedConditions.textToBePresentInElement(menuItem, menuItemText));
        return menuItem;
    }

    private WebElement GetLeftMenuItemElement(String menuItemText) {
        String xPathSelector = "//li/a[contains(text(),'" + menuItemText + "')]";
        WebElement menuItem = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPathSelector)));
        wait.until(ExpectedConditions.textToBePresentInElement(menuItem, menuItemText));
        return menuItem;
    }

    private void HoverOverMenuItem(String menuItemText) {
        WebElement menuItem = GetMenuItemElement(menuItemText);
        Actions action = new Actions(driver);
        action.moveToElement(menuItem).perform();
    }

    private void ClickMenuItem(String menuItemText) {
        WebElement menuItem = GetMenuItemElement(menuItemText);
        wait.until(ExpectedConditions.elementToBeClickable(menuItem)).click();
    }

    private void ClickLeftMenuItem(String itemText) {
        WebElement link = GetLeftMenuItemElement(itemText);
        wait.until(ExpectedConditions.elementToBeClickable(link)).click();
    }

    // Verifies shipping page URL
    public boolean waitForShippingPageUrl() {
        //CloseEthicalAd();
        return wait.until(ExpectedConditions.urlContains("checkout/#shipping"));
    }

    public boolean VerifyShippingPageMainElement() {
        WebElement shippingElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#shipping")));
        return (shippingElement != null);
    }

    public boolean verifyShoppingPageTitle() {
        WebElement pageTitleElement = driver.findElement(By.cssSelector(".page-title"));
        return pageTitleElement.getText().contains("Shopping Cart");
    }

    // Verifies shopping cart page URL
    public boolean waitForShoppingCartPageUrl() {
        CloseEthicalAd();
        return wait.until(ExpectedConditions.urlContains("checkout/cart"));
    }

    // Verifies payment page
    public boolean waitForPaymentPageUrl() {

        return wait.until(ExpectedConditions.urlContains("checkout/#payment"));
    }

    public boolean waitForPaymentSuccessUrl() {

        return wait.until(ExpectedConditions.urlContains("checkout/onepage/success"));
    }

    // Verifies order complete
    public boolean VerifyOrderComplete() {
        return driver.findElement(By.cssSelector(".title")).getText().contains("Order Summary");
    }

    // Verifies thank you for your purchase
    public boolean VerifyThankYouForYourPurchase() {
        WebElement thankYouElement = driver.findElement(By.cssSelector(".page-title"));
        wait.until(ExpectedConditions.textToBePresentInElement(thankYouElement, "Thank you"));
        return true;
    }

    // Verifies shopping cart is empty
    public boolean VerifyShoppingCartIsEmpty() {
        return driver.findElement(By.cssSelector(".counter.qty.empty")).isDisplayed();
    }

    // Verifies correct item is ordered
    public boolean verifyCorrectItemOrdered(String itemToPurchase) {
        return driver.findElement(By.cssSelector(".product-item-name")).getText().contains(itemToPurchase);
    }

    // Construct the XPath to locate the element containing the item price
    String xpathShoppingCartTotalPrice = "//td[contains(@class, 'amount') and .//*[contains(@class, 'price')]]";

    public boolean VerifyOrderTotalIsCorrectShoppingCart(String itemPrice) {
        try {

            WebElement totalPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathShoppingCartTotalPrice)));
            // return true if itemPrice is found
            return wait.until(ExpectedConditions.textToBePresentInElement(totalPriceElement, itemPrice));

            // alternate method of getting this value
            //String totalPriceText = totalPriceElement.getText();
            //return totalPriceText.contains(itemPrice);

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String GetTotalPriceShoppingCart() {
        WebElement totalPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathShoppingCartTotalPrice)));
        return totalPriceElement.getText();
    }

    public String GetNumberOfItemsInShoppingCart() {
        WebElement numberOfTemsInCartElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".counter-number")));
        return numberOfTemsInCartElement.getText();
    }

    public String GetNumberOfItemsInShoppingCart(String expectedNumberItems) {
        WebElement numberOfItemsInCartElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".counter-number")));
        wait.until(ExpectedConditions.textToBePresentInElement(numberOfItemsInCartElement, expectedNumberItems));
        return numberOfItemsInCartElement.getText();
    }


}
