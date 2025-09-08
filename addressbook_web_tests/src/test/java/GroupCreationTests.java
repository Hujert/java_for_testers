import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GropeCreationTests {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("http://localhost/addressbook/index.php");
        driver.manage().window().setSize(new Dimension(1389, 693));
        driver.findElement(By.xpath("//input[@name='user']")).click();
        driver.findElement(By.xpath("//input[@name='user']")).sendKeys("admin");
        driver.findElement(By.xpath("//form[@id='LoginForm']/label")).click();
        driver.findElement(By.xpath("//input[@name='pass']")).click();
        driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("secret");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
    }

    @AfterEach
    public void tearDown() {
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
        driver.quit();
    }

    @Test
    public void creationTest() {
        driver.findElement(By.xpath("//a[contains(text(),'groups')]")).click();
        driver.findElement(By.xpath("//input[@name='new']")).click();
        driver.findElement(By.xpath("//input[@name='group_name']")).click();
        driver.findElement(By.xpath("//input[@name='group_name']")).sendKeys("Test");
        driver.findElement(By.xpath("//textarea[@name='group_header']")).sendKeys("Test Heder");
        driver.findElement(By.xpath("//textarea[@name='group_footer']")).sendKeys("Test Footer");
        driver.findElement(By.xpath("//input[@name='submit']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'group page')]")).click();
    }
}
