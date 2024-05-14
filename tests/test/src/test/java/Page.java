import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;

public class Page {
    WebDriver driver;
    WebDriverWait wait;

    private By body = By.tagName("body");
    private By showLogin = By.className("logIn");
    private By email = By.xpath("//*[@id=\"logInBox-popup-form\"]/div[3]/input");
    private By password = By.xpath("//*[@id=\"logInBox-popup-form\"]/div[5]/input");
    private By loginButton = By.xpath("//*[@id=\"logInBox-popup-form\"]/div[6]/button");
    private By logOutButton = By.className("logOut");
    private By logOut = By.xpath("//a[@href='/?action=logOut']");

    public Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.driver.get("https://www.pcx.hu");
    }

    private WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    public String getPageTitle() {
        return this.driver.getTitle();
    }

    public String getBody() {
        return this.waitAndReturnElement(body).getText();
    }

    boolean loginLogout() {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(body));
        this.waitAndReturnElement(showLogin).click();
        this.waitAndReturnElement(email).sendKeys("seleniumtest@selenium.com");
        this.waitAndReturnElement(password).sendKeys("selenium");
        this.waitAndReturnElement(loginButton).click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(logOutButton));
            this.waitAndReturnElement(logOutButton).click();
            this.waitAndReturnElement(logOut).click();
            return true;
        } catch (TimeoutException exception) {
            return false;
        }
    }
}
