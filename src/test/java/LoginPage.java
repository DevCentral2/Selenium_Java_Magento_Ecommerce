import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class LoginPage extends BasePage{

    //public WebDriver driver;

    // create one webdriver wait object for all of the methods in this class
   // private WebDriverWait wait;

   // int waitTimeforWebDriverWait = 15;

    // Constructor
    public LoginPage(WebDriver _driver) {
        this.driver = _driver;

        // instantiate the webdriver wait object
        wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeforWebDriverWait));

    }
    public String validUserName = "grand.quivara@gmail.com";
    public String validPassword = "EcommercePracticeSite22";
    public String invalidPassword = "InvalidPassword";
    public String sampleEmailAddr = "PasswordResetEmail@Test.com";

    String userNameFieldCss = "#email.input-text";
    String passwordFieldCss = "#pass.input-text";
    String signInbutton = "#send2.action.login.primary";
    String signedInLinkCss = "span.logged-in";
    String emailErrorMsg = "#email-error";
    String passwordErrorMsgCss = "#pass-error";
    String signInerrorMsgCss = ".message-error.error.message";
    String forgotPasswordLink = ".action.remind";
    String forgotPasswordFormCss = ".form.password.forget";
    String pswdResetButtonCss = ".action.submit.primary";
    String pawdResetSuccessMsgCss = ".message-success.success.message";
    String pswdResetEmailAddrFieldCss = "#email_address";

    //url
    public String loginPagePartURL = "customer/account/login/";

    // messages
    String incorrectSigninMsg = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
    String loginAndPswdRequiredMsg = "A login and a password are required.";


    // methods

    public String getValidUserName()
    {
        return validUserName;
    }

    public String getValidPassword()
    {
        return validPassword;
    }

    public String getInvalidPassword()
    {
        return invalidPassword;
    }



    public void LoginWithValidCredentials() {
        enterUserName(validUserName);
        enterPassword(validPassword);
        submitLogin();
    }

    public void verifyLoginPageURL() {
        String currentURL = driver.getCurrentUrl();
        String expectedURL = baseURL + loginPagePartURL;
        // Assertion to check if the current URL contains the expected URL
        Assert.assertTrue(currentURL.contains(expectedURL),
                "The current URL does not contain the expected URL. " +
                        "Expected to contain: " + expectedURL + " but was: " + currentURL);

        CloseEthicalAd();
    }

    public void enterUserName(String userName) {
        WebElement usernameField = driver.findElement(By.cssSelector(userNameFieldCss));
        usernameField.sendKeys(userName);
    }

    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(By.cssSelector(passwordFieldCss));
        passwordField.sendKeys(password);
    }

    public void enterValidLoginDetails() {
        WebElement usernameField = driver.findElement(By.cssSelector(userNameFieldCss));
        usernameField.sendKeys(validUserName);

        WebElement passwordField = driver.findElement(By.cssSelector(passwordFieldCss));
        passwordField.sendKeys(validPassword);
    }

    public void enterInValidLoginDetails() {
        WebElement usernameField = driver.findElement(By.cssSelector(userNameFieldCss));
        usernameField.sendKeys(validUserName);

        WebElement passwordField = driver.findElement(By.cssSelector(passwordFieldCss));
        passwordField.sendKeys(invalidPassword);
    }

    public void submitLogin() {
        WebElement loginButton = driver.findElement(By.cssSelector(signInbutton));
        loginButton.click();
    }

    public void clickPswdResetLink() {
        WebElement pswdResetLink = driver.findElement(By.cssSelector(forgotPasswordLink));
        pswdResetLink.click();
    }

    public void pressEnter() {
        WebElement passwordField = driver.findElement(By.cssSelector(passwordFieldCss));
        passwordField.sendKeys(Keys.ENTER);
    }

    public void enterEmailAddress() {
        WebElement pswdResetEmailAddrField = driver.findElement(By.cssSelector(pswdResetEmailAddrFieldCss));
        pswdResetEmailAddrField.sendKeys(sampleEmailAddr);
    }

    public void clickPasswordReset() {
        WebElement pswdResetButton = driver.findElement(By.cssSelector(pswdResetButtonCss));
        pswdResetButton.click();
    }

    public void signOut()
    {
        // Click Login Dropdown and then click the SignOut link if it is present

        List <WebElement>  loginDropDownLinks = driver.findElements(By.cssSelector(".customer-name > button.action.switch"));
        if (!loginDropDownLinks.isEmpty())
            loginDropDownLinks.getFirst().click();


        List <WebElement>  signOuLinks = driver.findElements(By.cssSelector("li.authorization-link"));
        if (!signOuLinks.isEmpty())
            signOuLinks.getFirst().click();

    }

    public void verifySignedIn() {

        WebElement signedInLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(signedInLinkCss)));
        Assert.assertTrue(signedInLink.isEnabled(), "Not Logged In:  Signed In Link Not Found");
    }

    public void verifyNotSignedIn() {
        List<WebElement> signedInLink = driver.findElements(By.cssSelector(signedInLinkCss));
        Assert.assertTrue(signedInLink.isEmpty());
    }

    public void verifyIncorrectSignInMessage() {
        List<WebElement> signedInLinks = driver.findElements(By.cssSelector(signInerrorMsgCss));
        Assert.assertFalse(signedInLinks.isEmpty(),"'Incorrect SignIn' msg expected but not found.");
        String actualErrorMsgText = signedInLinks.getFirst().getText();
        Assert.assertEquals(incorrectSigninMsg, actualErrorMsgText);

    }

    public void verifyLoginAndPswdRequiredMessage() {
        List<WebElement> logindPswdRequired = driver.findElements(By.cssSelector(loginAndPswdRequiredMsg));
        Assert.assertFalse(logindPswdRequired.isEmpty());
    }

    public void verifyRequiredPswdFieldErrorMessage() {
        List<WebElement> requiredPswdField = driver.findElements(By.cssSelector(passwordErrorMsgCss));
        Assert.assertFalse(requiredPswdField.isEmpty());
    }

    public void verifyForgotPswdForm() {
        List<WebElement> forgotPasswordForm =  driver.findElements(By.cssSelector(forgotPasswordFormCss));
        Assert.assertFalse(forgotPasswordForm.isEmpty());
    }

    public void verifyPasswordResetMsg() {
        List<WebElement> pswdResetLinkSentMsgs = driver.findElements(By.cssSelector(pawdResetSuccessMsgCss));
        Assert.assertFalse(pswdResetLinkSentMsgs.isEmpty());
    }

    public void verifyPasswordResetMsgContainsEmailAddr() {
        var pswdResetMsgElement = driver.findElement(By.cssSelector(pawdResetSuccessMsgCss));
        String pswdResetMsgText = pswdResetMsgElement.getText();
        Assert.assertTrue(pswdResetMsgText.contains(sampleEmailAddr),"Expected email address " + sampleEmailAddr + " NOT found in password reset message");
    }
}
