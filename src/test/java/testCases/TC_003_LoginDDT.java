package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass
{

	@Test(dataProvider = "LoginData" , dataProviderClass = DataProviders.class)
	public void testLoginDDT(String username, String password, String result )
	{
		logger.info("*** Started Execution of Login DataDriven Test****");
		try 
		{
			HomePage hp = new HomePage (driver);
			hp.clickMyAccount();
			hp.clickLoginLink();
			
			LoginPage lp = new LoginPage(driver);
			lp.setEmailAdd(username);
			lp.setPassword(password);
			lp.clickLoginButton();
			
			MyAccountPage macc = new MyAccountPage(driver);
			boolean loginstatus = macc.isMyAccountpagedisplayed();
			
			/*Valid data -> login successful -> Test Pass -> log out
			 *           -> login unsuccessful -> Test Fail
			 * 
			 * Invalid data-> login successful -> Test Fail -> logout
			 *             -> login unsuccessful -> Test Pass
			 */
			
			if(result.equalsIgnoreCase("Valid"))
			{
				if(loginstatus)
				{
					macc.logout();
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
					
				}
				
			}
			else
			{
				if(loginstatus)
				{
					macc.logout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
					
				}
			}
		}
		catch(Exception e)
		{
			logger.error(e);
			Assert.fail();
		}
		
		logger.info("*** Finished Execution of LoginTestCase ***");
	}
	
}
	

