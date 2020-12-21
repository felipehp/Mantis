/*Testes da página e processo de autenticação*/

import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class LoginTest {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args) {
        // ChromeDriver location set up in Utils class
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Login com sucesso")
    public static void Login(){
        driver.get(Utils.BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername();
        loginPage.enterPassword();
        loginPage.pressSubmitButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String displayedText = loginPage.getMessageDisplayedSuccessLogin();
        String expectedText = loginPage.getExpectedMessageSuccessLogin();
        assertEquals(displayedText,expectedText);
    }

    @Test(testName = "Tentativa de Login sem sucesso - Usuário Incorreto")
    public static void NoSuccessLoginIncorrectUser(){
        driver.get(Utils.BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterIncorrectUsername();
        loginPage.enterPassword();
        loginPage.pressSubmitButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String displayedTextNoSuccessLogin = loginPage.getMessageDisplayedNoSuccessLogin();
        String expectedTextNoSuccessLogin = loginPage.getExpectedMessageNoSuccessLogin();
        assertEquals(displayedTextNoSuccessLogin,expectedTextNoSuccessLogin);
    }

    @Test(testName = "Tentativa de Login sem sucesso - Senha Incorreta")
    public static void NoSuccessLoginIncorrectPassword(){
        driver.get(Utils.BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername();
        loginPage.enterIncorrectPassword();
        loginPage.pressSubmitButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String displayedTextNoSuccessLogin = loginPage.getMessageDisplayedNoSuccessLogin();
        String expectedTextNoSuccessLogin = loginPage.getExpectedMessageNoSuccessLogin();
        assertEquals(displayedTextNoSuccessLogin,expectedTextNoSuccessLogin);
    }

    @Test(testName = "Tentativa de Login sem sucesso - Campos obrigatórios não preenchidos")
    public static void LoginFieldsNotFilled(){
        driver.get(Utils.BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.pressSubmitButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String displayedTextNoSuccessLogin = loginPage.getMessageDisplayedNoSuccessLogin();
        String expectedTextNoSuccessLogin = loginPage.getExpectedMessageNoSuccessLogin();
        assertEquals(displayedTextNoSuccessLogin,expectedTextNoSuccessLogin);
    }

    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}