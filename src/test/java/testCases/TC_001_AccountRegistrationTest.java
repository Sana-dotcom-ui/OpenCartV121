package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups = {"Master","Regression"})
	public void test_AccountRegistration()
	{
		try 
		{
			logger.info("***Starting the execution of testcase TC_001_AccountRegistrationTest***");
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account");
			hp.clickRegister();
			logger.info("Clicked on Rgister");
			
			logger.info("Provided the account details for registration");
			AccountRegistrationPage reg_account = new AccountRegistrationPage(driver);
			reg_account.setFirstName(getRandomString());
			reg_account.setLastName(getRandomString());
			reg_account.setEmail(getRandomString()+"@gmail.com");
			reg_account.setTelephone(getRandomNumbers());
			
			String password = getRandomNumbers();
			reg_account.setPassword(password);
			reg_account.setConfirmPassword(password);
			
			reg_account.clickPolicy();
			reg_account.click_Continuebtn();
			
			logger.info("Validation of the account created");
			if(reg_account.getConfirmMessage().equals("Your Account Has Been Created!"))
			{
				Assert.assertTrue(true);
			}
			else
			{
				logger.error("Account registration failed...");
				Assert.assertTrue(false);
			}
			
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***Finished Execution of testcase TC_001_AccountRegistrationTest***");
	}

}
