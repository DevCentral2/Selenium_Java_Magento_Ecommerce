/*
THIS PAGE HAS BEEN REPLACED BY lOGINPAGE_TESTS AND SHOPPINGPAGE_TESTS.
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MagentoTests {
    private WebDriver driver;
    private final String baseUrl = "https://magento.softwaretestingboard.com/";
    private final String expectedTitle = "Home Page";

    @BeforeClass
    public void setUp() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Java\\Chromedriver\\chromedriver.exe");

        // Initialize ChromeDriver instance
        driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();
    }

    @Test
    public void TestNavigationToHomePage() {
        // Open homepage
        driver.get(baseUrl);

        String actualTitle = driver.getTitle();
        String currentURL = driver.getCurrentUrl();

        // Verify that the actual URL matches the expected URL
        Assert.assertEquals(currentURL, baseUrl, "URL mismatch. Expected: " + baseUrl + " but found: " + currentURL);

        // Verify that the actual title matches the expected title
        Assert.assertEquals(actualTitle, expectedTitle, "Page title mismatch. Expected: " + expectedTitle + " but found: " + actualTitle);



    }

    @Test
    public void VerifyHomePageElements() {
        // Open homepage
        driver.get(baseUrl);

        var logo = driver.findElements(By.cssSelector("a.logo"));
        var shoppingCartIcon = driver.findElements(By.cssSelector("a.logo"));
        var searchField = driver.findElements(By.cssSelector("input#search"));
        var authorisationLink =  driver.findElements(By.cssSelector("li.authorization-link"));


        Assert.assertFalse(logo.isEmpty(), "Site Logo not found");
        Assert.assertFalse(shoppingCartIcon.isEmpty(), "Shopping Cart Icon not found");
        Assert.assertFalse(searchField.isEmpty(), "Search Field not found");
        Assert.assertFalse(authorisationLink.isEmpty(), "Search Field not found");
    }


    @AfterClass
    public void tearDown() {
        // Close the browser window
        driver.quit();
    }
}
*/