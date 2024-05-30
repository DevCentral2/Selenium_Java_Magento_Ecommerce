
import org.testng.Assert;
import org.testng.annotations.Test;

// Shopping cart tests

public class ShoppingPageTests extends BaseTestClass{

    String gender = "Women";
    String topsBottoms = "Tops";
    String typeOfClothing = "Tees";
    String itemToPurchase = "Desiree Fitness Tee";
    String size = "M";
    String colour = "Black";
    String itemPrice = "$24.00";



    @Test
    public void ShoppingCart_AddItem_CorrectNumberOfItemsInCart() {

        homePage.navigateToHomePage();
        homePage.clearAllCookes();
        homePage.acceptCookies();

        shoppingPage.selectItemTypeFromPage(gender, typeOfClothing);
        shoppingPage.selectItemChooseItemPage(itemToPurchase);
        shoppingPage.selectItemSpecifics(size, colour);

        // Verification

        // Assert number of items is blank before adding anything to the shopping cart
        Assert.assertEquals(shoppingPage.GetNumberOfItemsInShoppingCart(), "", String.format("The number of items in shopping cart (before adding items) is expected to be 0 or blank, but is currently %s", shoppingPage.GetNumberOfItemsInShoppingCart()));

        shoppingPage.addToCart();

        // Assert number of items is 1 after adding one item to the shopping cart
        Assert.assertEquals(shoppingPage.GetNumberOfItemsInShoppingCart("1"), "1", String.format("The number of items in shopping cart (after adding items) is expected to be: 1, but is currently %s", shoppingPage.GetNumberOfItemsInShoppingCart("1")));
    }

    @Test
    public void ShoppingCart_AddItem_ClickShoppingCartLink_NavigatesToShoppingCartPage() {

        homePage.navigateToHomePage();
        homePage.acceptCookies();

        shoppingPage.selectItemTypeFromPage(gender, typeOfClothing);
        shoppingPage.selectItemChooseItemPage(itemToPurchase);
        shoppingPage.selectItemSpecifics(size, colour);
        shoppingPage.addToCart();
        shoppingPage.clickShoppingCartLink();

        // Verification

        // Assert whether the current URL contains the expected shopping cart page URL
        Assert.assertTrue(shoppingPage.waitForShoppingCartPageUrl(), "The current URL is not the shopping cart URL.");

        // Assert that the page title element contains the text "Shopping Cart"
        Assert.assertTrue(shoppingPage.verifyShoppingPageTitle(), "The page title does not contain 'Shopping Cart'.");
    }

    @Test
    public void ShoppingCart_HoverMenus_AddItemToCart_NavigatesToShoppingPage() {

        homePage.navigateToHomePage();
        homePage.acceptCookies();

        shoppingPage.selectItemTypeFromMenus(gender, topsBottoms, typeOfClothing);
        shoppingPage.selectItemChooseItemPage(itemToPurchase);
        shoppingPage.selectItemSpecifics(size, colour);
        shoppingPage.addToCart();
        shoppingPage.clickShoppingCartLink();

        // Verification

        // Assert whether the current URL contains the expected shopping cart page URL
        Assert.assertTrue(shoppingPage.waitForShoppingCartPageUrl(), "The current URL is not the shopping cart URL.");

        // Assert that the page title element contains the text "Shopping Cart"
        Assert.assertTrue(shoppingPage.verifyShoppingPageTitle(), "The page title does not contain 'Shopping Cart'.");
    }

    @Test
    public void ShoppingCart_AddItem_CheckOrderTotalOnShoppingCartPage() {

        homePage.navigateToHomePage();
        homePage.acceptCookies();

        shoppingPage.selectItemTypeFromPage(gender, typeOfClothing);
        shoppingPage.selectItemChooseItemPage(itemToPurchase);
        shoppingPage.selectItemSpecifics(size, colour);
        shoppingPage.addToCart();
        shoppingPage.clickShoppingCartLink();

        // Verification

        Assert.assertEquals(shoppingPage.GetTotalPriceShoppingCart(), itemPrice, String.format("The Order Total is not correct. It it shows %s but should be %s", shoppingPage.GetTotalPriceShoppingCart(), itemPrice));

    }

    @Test
    public void ShoppingCart_PlaceOrder_NavigatesToPaymentPage() {

        homePage.navigateToHomePage();
        homePage.acceptCookies();
        homePage.clickLoginLink();

        loginPage.LoginWithValidCredentials();

        shoppingPage.selectItemTypeFromPage(gender, typeOfClothing);
        shoppingPage.selectItemChooseItemPage(itemToPurchase);
        shoppingPage.selectItemSpecifics(size, colour);
        shoppingPage.addToCart();

        // Verification

        shoppingPage.clickShoppingCartLink();
        Assert.assertTrue(shoppingPage.waitForShoppingCartPageUrl(), "Not on Shopping Cart Page");

        shoppingPage.clickProceedToCheckout();
        Assert.assertTrue(shoppingPage.waitForShippingPageUrl(), "Not on Shipping Page");

        shoppingPage.clickNextButtonOnOrderPage();
        Assert.assertTrue(shoppingPage.waitForPaymentPageUrl(), "Not on Payment Page");

        shoppingPage.clickPlaceOrder();
        Assert.assertTrue(shoppingPage.waitForPaymentSuccessUrl(), "Not on Payment Success Page");

        loginPage.signOut();
    }

    @Test
    public void ShoppingCart_PlaceOrder_VerifyCorrectItemOrdered() {

        homePage.navigateToHomePage();
        homePage.acceptCookies();
       

        homePage.clickLoginLink();
        loginPage.LoginWithValidCredentials();

        shoppingPage.selectItemTypeFromPage(gender, typeOfClothing);
        shoppingPage.selectItemChooseItemPage(itemToPurchase);
        shoppingPage.selectItemSpecifics(size, colour);
        shoppingPage.addToCart();

        shoppingPage.clickShoppingCartLink();
        shoppingPage.waitForShoppingCartPageUrl();

        shoppingPage.clickProceedToCheckout();
        shoppingPage.waitForShippingPageUrl();

        shoppingPage.clickNextButtonOnOrderPage();
        shoppingPage.waitForPaymentPageUrl();

        shoppingPage.clickPlaceOrder();
        shoppingPage.waitForPaymentSuccessUrl();

        // Verification

        shoppingPage.clickOrderNumber();
        Assert.assertTrue(shoppingPage.verifyCorrectItemOrdered(itemToPurchase));

        loginPage.signOut();
    }

}
