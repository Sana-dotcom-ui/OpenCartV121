package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtpassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmpassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chckPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confirm_msg;
	
	public void setFirstName (String firstname)
	{
		txtFirstName.sendKeys(firstname);
	}
	
	public void setLastName (String lastname)
	{
		txtLastName.sendKeys(lastname);
	}
	
	public void setEmail (String email)
	{
		txtEmail.sendKeys(email);
		
	}
	
	public void setTelephone (String telephone)
	{
		txtTelephone.sendKeys(telephone);
	}
	
	public void setPassword (String password)
	{
		txtpassword.sendKeys(password);
	}
	
	public void setConfirmPassword (String confirm_pwd)
	{
		txtConfirmpassword.sendKeys(confirm_pwd);
	}
	
	public void clickPolicy()
	{
		chckPolicy.click();
	}

	public void click_Continuebtn()
	{
	   btnContinue.click();	
	}
	
	public String getConfirmMessage()
	{
		try {
			String confirm_message = confirm_msg.getText();
			return confirm_message;
		}
		catch (Exception e)
		{
			return e.getMessage();
		}
		
	}
}
