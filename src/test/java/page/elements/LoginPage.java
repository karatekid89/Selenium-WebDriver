package page.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	WebDriver driver = null;

	By username = By.xpath(
			"//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[1]/div[1]/div[2]/input[1]");
	By password = By.xpath(
			"//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[2]/div[1]/div[2]/input[1]");
	By loginButton = By
			.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[3]/button[1]");
	By forgotPasswordLink = By
			.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[4]/p[1]");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void EnterUsername(String text) {
		driver.findElement(username).sendKeys(text);
	}

	public void EnterPassword(String text) {
		driver.findElement(password).sendKeys(text);
	}

	public void ClickLoginButton() {
		driver.findElement(loginButton).click();
	}

	public void ClickForgotPasswordLink() {
		driver.findElement(forgotPasswordLink).click();
	}
}
