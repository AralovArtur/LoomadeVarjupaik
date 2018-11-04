package ee.ut.loomadevp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoomadevpApplicationTests {
    private WebDriver driver;
    private Random random = new Random();
    private String demo = "http://localhost:8080/";

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(demo);
    }

    @After
    public void tearDown() {
        try {
            logoutClick();
        } catch (Exception ignored) {
        }
        driver.close();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void languageTest() {
        setEstLang();

        assertEquals("Meist",
                driver.findElements(By.tagName("h1")).get(0).getText());
                assertEquals("Liitu uudiskirjaga",
                        driver.findElements(By.tagName("h1")).get(1).getText());

        setEngLang();
        assertEquals("About us",
                driver.findElements(By.tagName("h1")).get(0).getText());
        assertEquals("Join the newsletter",
                driver.findElements(By.tagName("h1")).get(1).getText());
    }

    @Test
    public void loginTest() {
        goToPage("Login");
        String email = "lalala@gmail.com";
        String password = "makaka555";
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        loginClick();
    }

    @Test
    public void loomadVarjupaigasTest() {
        loginTest();
        setEstLang();
        goToPage("Loomad varjupaigas");
        assertEquals("http://localhost:8080/loomadvp",
                driver.getCurrentUrl());
    }

    @Test
    public void viiOLSTest() {
        loginTest();
        setEstLang();
        goToPage("Vii oma loom siia");
        assertEquals("http://localhost:8080/viiols",
                driver.getCurrentUrl());
    }

    @Test
    public void kontaktTest() {
        loginTest();
        setEstLang();
        goToPage("Kontakt");
        assertEquals("http://localhost:8080/kontakt",
                driver.getCurrentUrl());
    }

    private void loginClick() {
        driver.findElement(By.id("sign_in")).click();
    }

    private void logoutClick() {
        driver.findElement(By.id("sign_out")).click();
    }

    private void setEstLang() {
        driver.findElement(By.id("et")).click();
    }

    private void setEngLang() {
        driver.findElement(By.id("en")).click();
    }

    private void goToPage(String page) {
        driver.findElement(By.linkText(page)).click();
    }
}
