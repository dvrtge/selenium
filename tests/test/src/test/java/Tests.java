import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Tests {
    private WebDriver driver;

    @Before
    public void setup()  throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
    }


    @Test
    public void tests() {
        Page page = new Page(this.driver);

        Assert.assertTrue(page.getPageTitle().contains("PCX"));
        Assert.assertTrue(page.getBody().contains("Kapcsolat"));

        Assert.assertTrue(page.loginLogout());
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
