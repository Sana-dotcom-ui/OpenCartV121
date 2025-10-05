package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage  extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_emailadd;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_password;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement btn_Login;
	
	public void setEmailAdd (String email)
	{
		txt_emailadd.sendKeys(email);
	}
	
	public void setPassword (String pwd)
	{
		txt_password.sendKeys(pwd);
	}
	
	public void clickLoginButton ()
	{
		btn_Login.click();
	}
	
	

}
