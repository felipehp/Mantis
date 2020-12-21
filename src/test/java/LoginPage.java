/*Elementos da página e processo de autenticação*/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject {
    /*String com os dados para teste de login com sucesso*/
    private final String USER = "felipe.alves";
    private final String PASSWORD = "Mantis2020";

    /*String com os dados para teste de login sem sucesso*/
    private final String INCORRECT_USER = "alves.felipe";
    private final String INCORRECT_PASSWORD = "SenhaIncorreta";

    /*Mapeando os elementos na tela de login*/
    @FindBy(name = "username")
    private WebElement user;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(className = "button")
    private WebElement submit_button;

    /*Interagindo com os elementos na tela de login*/
    public LoginPage(WebDriver driver) {super(driver);}
    public void enterUsername() {this.user.sendKeys(USER);}
    public void enterPassword() {this.password.sendKeys(PASSWORD);}
    public void enterIncorrectUsername() {this.user.sendKeys(INCORRECT_USER);}
    public void enterIncorrectPassword() {this.password.sendKeys(INCORRECT_PASSWORD);}
    public void pressSubmitButton() {this.submit_button.click();}

    /*final static String displayedText = driver.findElement(By.className("login-info-left")).getText();
    final static String expectedText = "Logged in as:";*/

    /*Metodo para retorno da mensagem recebida de login com sucesso*/
    public String getMessageDisplayedSuccessLogin() {
        String displayedText = driver.findElement(By.className("login-info-left")).getText();
        return displayedText.trim();
    }
 
    /*Metodo para retorno da mensagem esperada na tela de login com sucesso (conforme usuário designado para automação)*/
    public String getExpectedMessageSuccessLogin() {
        String expectedText = "Logged in as: felipe.alves (Felipe Henrique Pereira Alves - reporter)";
        return expectedText.trim();
    }

    /*Metodo para retorno da mensagem recebida na exceção da tela de login*/
    public String getMessageDisplayedNoSuccessLogin() {
        String displayedTextNoSuccessLogin = driver.findElement(By.xpath("//*[contains(text(),'Your account may be disabled or blocked or the username/password you entered is incorrect.')]")).getText();
        return displayedTextNoSuccessLogin.trim();
    }

    /*Metodo para retorno da mensagem esperada na exceção da tela de login*/
    public String getExpectedMessageNoSuccessLogin() {
        String expectedTextNoSuccessLogin = "Your account may be disabled or blocked or the username/password you entered is incorrect.";
        return expectedTextNoSuccessLogin.trim();
    }
}