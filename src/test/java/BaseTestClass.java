import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


abstract public class BaseTestClass {

        String baseURL = "https://magento.softwaretestingboard.com/";
        String chromedriverPath = "C:\\Java\\Chromedriver\\Chromedriver.exe";
        WebDriver driver;
        HomePage homePage;
        LoginPage loginPage;
        ShoppingPage shoppingPage;

        @BeforeClass
        public void setup() {
            initialiseDriver();
            initialisePages();

        }

        public void initialisePages() {
            homePage = new HomePage(driver);
            loginPage = new LoginPage(driver);
            shoppingPage = new ShoppingPage(driver);
        }

        public void initialiseDriver() {

            System.setProperty("webdriver.chrome.driver", chromedriverPath);
            ChromeOptions option = new ChromeOptions();

            // Add Incognito mode option
            option.addArguments("incognito");
            driver = new ChromeDriver(option);

        }
        public void clearAllCookes()
        {
            driver.manage().deleteAllCookies();
        }

        @AfterClass
        public void tearDown() {
            driver.quit();
        }


    }
