import org.testng.annotations.Test;

public class LoginPageTests extends BaseTestClass{

    @Test
    public void homePageNavigationVerifyURLAndPageTitle() {
        homePage.navigateToHomePage();
        homePage.acceptCookies();

        //Verification
        homePage.verifyHomePageURL();
        homePage.verifyPageTitle();
    }

    @Test
    public void login_verifyMainElements() {

        homePage.navigateToHomePage();
        homePage.acceptCookies();

        //Verification

        // Check presence of main site logo
        homePage.verifyElementExists(homePage.getStoreLogo());
        // Check presence of Login link
        homePage.verifyElementExists(homePage.getLoginLinkCss());
        // Check presence of Shopping Cart link
        homePage.verifyElementExists(homePage.getShoppingCart());
        // Check presence of Search field
        homePage.verifyElementExists(homePage.getSearchField());
    }

    @Test
    public void LoginPage_VerifyNavigation() {

        homePage.navigateToHomePage();
        homePage.acceptCookies();
        homePage.verifyHomePageURL();
        homePage.clickLoginLink();
        //Verification
        loginPage.verifyLoginPageURL();


    }

    @Test
    public void loginWithValidCredentialsSubmitAndVerifySignedIn() {

        homePage.navigateToHomePage();
        homePage.acceptCookies();
        homePage.verifyHomePageURL();
        homePage.clickLoginLink();

        loginPage.enterValidLoginDetails();
        loginPage.submitLogin();

        //Verification
        loginPage.verifySignedIn();

        loginPage.signOut();

    }

    @Test
    public void loginWithValidCredentialsPressEnterAndVerifySignedIn() {

        homePage.navigateToHomePage();
        homePage.acceptCookies();
        
        homePage.clickLoginLink();

        loginPage.enterValidLoginDetails();
        loginPage.pressEnter();

        //Verification
        loginPage.verifySignedIn();

        loginPage.signOut();

    }

    @Test
    public void login_SignOutAndVerifyLoggedOut() {

        homePage.navigateToHomePage();
        homePage.acceptCookies();
        
        homePage.clickLoginLink();

        loginPage.enterValidLoginDetails();
        loginPage.pressEnter();

        loginPage.verifySignedIn();
        loginPage.signOut();

        //Verification
        loginPage.verifyNotSignedIn();

    }

    @Test
    public void loginWithInvalidPasswordVerifyNotSignedIn() {

        homePage.navigateToHomePage();
        homePage.acceptCookies();
        
        homePage.clickLoginLink();

        loginPage.enterInValidLoginDetails();
        loginPage.submitLogin();

        //Verification
        loginPage.verifyNotSignedIn();
    }

    @Test
    public void loginWithInvalidPasswordVerifyInvalidPswdMsg() {

        homePage.navigateToHomePage();
        homePage.acceptCookies();
        
        homePage.clickLoginLink();

        loginPage.enterInValidLoginDetails();
        loginPage.submitLogin();

        //Verification
        loginPage.verifyNotSignedIn();
        loginPage.verifyIncorrectSignInMessage();
    }

    @Test
    public void loginWithBlankPasswordVerifyNotSignedIn() {

        homePage.navigateToHomePage();
        homePage.acceptCookies();
        homePage.clickLoginLink();

        loginPage.enterUserName(loginPage.getValidUserName());
        loginPage.enterPassword("");
        loginPage.submitLogin();

        //Verification
        loginPage.verifyNotSignedIn();
    }

    @Test
    public void loginWithBlankPasswordVerifyCorrectMessage() {

        homePage.navigateToHomePage();
        homePage.acceptCookies();
        
        homePage.clickLoginLink();

        loginPage.enterUserName(loginPage.getValidUserName());
        loginPage.enterPassword("");
        loginPage.submitLogin();

        //Verification
        loginPage.verifyRequiredPswdFieldErrorMessage();
    }

    @Test
    public void loginPageClickResetPswdVerifyPswdResetForm() {

        homePage.navigateToHomePage();
        homePage.acceptCookies();
        homePage.verifyHomePageURL();
        homePage.clickLoginLink();

        loginPage.verifyLoginPageURL();
        loginPage.clickPswdResetLink();

        //Verification
        loginPage.verifyForgotPswdForm();
    }

    @Test
    public void loginPageClickResetPswdVerifyPswdResetMsg() {

        homePage.navigateToHomePage();
        homePage.acceptCookies();
        homePage.verifyHomePageURL();
        homePage.clickLoginLink();

        loginPage.verifyLoginPageURL();
        loginPage.clickPswdResetLink();
        loginPage.verifyForgotPswdForm();
        loginPage.enterEmailAddress();
        loginPage.clickPasswordReset();

        //Verification
        loginPage.verifyPasswordResetMsg();
        loginPage.verifyPasswordResetMsgContainsEmailAddr();
    }

}
