package seleniumcommands;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;





public class DemoWebShop 
{
	WebDriver driver;

	public void testInitialise(String browser) {
		if (browser.equals("Chrome")) {
			// System.setProperty("webdriver.chrome.driver","C:\\Users\\hp\\eclipse-workspace\\Selenium\\target\\test-classes\\Drivers\\chromedriver.exe");
			ChromeOptions ops = new ChromeOptions();
			ops.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(ops);
			// driver=new ChromeDriver();
		} else if (browser.equals("Firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("Edge")) {
			driver = new EdgeDriver();
		} else {
			try {
				throw new Exception("Invalid Browser");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	@BeforeMethod(alwaysRun=true)
	@Parameters({"browser", "base_url"})
	public void setup(String browserName, String url) 
	{
		testInitialise(browserName);
		driver.get(url);
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
	if (result.getStatus() == ITestResult.FAILURE) {
	TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(screenshot, new File("./Screenshots/" + result.getName() + ".png"));
	}
	// driver.close();
	//driver.quit();
	}
	@Test(priority=1,enabled=true,description="Verify title for Obsqura application", groups= {"Regression"}) // execution starts here
	public void TC_001_verifyObsquraTitle() {
		driver.get("https://selenium.obsqurazone.com/index.php");
		String actTitle = driver.getTitle();
		System.out.println("Title  : " + actTitle);
		String expectedTitle = "Obsqura Testing";
		Assert.assertEquals(actTitle, expectedTitle, "Invalid Title Found");
	}
	@Test(priority=1, enabled=true, description="Verify login for Obsqura application", groups= {"Sanity"})
	public void TC_002_verifyLogin()
	{
		//driver.get("https://demowebshop.tricentis.com/");
		WebElement login=driver.findElement(By.xpath("//a[@class='ico-login']"));
		login.click();
		String ExpEmailValue="boney.jacob@gsmail.com";
		WebElement email=driver.findElement(By.xpath("//input[@id='Email']"));
		email.sendKeys(ExpEmailValue);
		WebElement password=driver.findElement(By.xpath("//input[@id='Password']"));
		password.sendKeys("12345678");
		WebElement submitButton=driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
		submitButton.click();
		WebElement userAccemail=driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
		String ActEmailValue=userAccemail.getText();
		Assert.assertEquals(ActEmailValue, ExpEmailValue, "Invalid Email");
	}
	@Test(priority=2, enabled=true, description="Verify login for Obsqura application", groups= {"Smoke"})
	public void TC_003_verifyRegistration()
	{
		//driver.get("https://demowebshop.tricentis.com/");
		WebElement register=driver.findElement(By.xpath("//a[@class='ico-register']"));
		register.click();
		String Uemail="john.rop@gtmail.com";
		List<WebElement> gender=driver.findElements(By.xpath("//input[@name='Gender']"));
		selectGender("F", gender);
		WebElement fname=driver.findElement(By.xpath("//input[@id='FirstName']"));
		fname.sendKeys("John");
		WebElement lname=driver.findElement(By.xpath("//input[@id='LastName']"));
		lname.sendKeys("Roe");
		WebElement email=driver.findElement(By.xpath("//input[@id='Email']"));
		email.sendKeys(Uemail);
		WebElement password=driver.findElement(By.xpath("//input[@id='Password']"));
		password.sendKeys("1234567");
		WebElement cpassword=driver.findElement(By.xpath("//input[@id='ConfirmPassword']"));
		cpassword.sendKeys("1234567");
		WebElement registerButton=driver.findElement(By.xpath("//input[@id='register-button']"));
		registerButton.click();
		WebElement userEmail=driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
		String actuserEmail=userEmail.getText();
		Assert.assertEquals(actuserEmail, Uemail, "Invalid Email");	
	}
	public void selectGender(String gen, List<WebElement> gender)
	{
		for(int i=0;i<gender.size();i++)
		{
			String genderValue=gender.get(i).getAttribute("Value");
			if(genderValue.equals(gen))
			{
				gender.get(i).click();
			}
		}
	}
	@Test(priority=1, enabled=true, description="Verify title from excel sheet for Obsqura application", groups= {"Regression"})
	public void TC_004_verifyTitleFromExcelSheet() throws IOException
	{
		//driver.get("https://demowebshop.tricentis.com/");
		String actualTitle=driver.getTitle();
		String excelPath="\\src\\test\\resources\\TestData.xlsx";
		String sheetName="HomePage";
		String expTitle=ExcelUtility.readStringData(excelPath, sheetName, 0, 1);
		Assert.assertEquals(actualTitle, expTitle, "Invalid title");	
	}
	@Test(priority=1, enabled=true, description="Verify registration from excel sheet for Obsqura application", groups= {"Smoke"})
	public void TC_005_verifyRegistrationFromExcelSheet() throws IOException
	{
		//driver.get("https://demowebshop.tricentis.com/");
		String excelPath="\\src\\test\\resources\\TestData.xlsx";
		String sheetName="RegisterPage";
		String expTitle=ExcelUtility.readStringData(excelPath, sheetName, 0, 1);
		WebElement register=driver.findElement(By.xpath("//a[@class='ico-register']"));
		register.click();
		String actualTitle=driver.getTitle();
		Assert.assertEquals(actualTitle, expTitle, "Invalid title");
		List<WebElement> gender=driver.findElements(By.xpath("//input[@name='Gender']"));
		selectGender(ExcelUtility.readStringData(excelPath, sheetName, 6, 1), gender);
		WebElement fname=driver.findElement(By.xpath("//input[@id='FirstName']"));
		fname.sendKeys(ExcelUtility.readStringData(excelPath, sheetName, 1, 1));
		WebElement lname=driver.findElement(By.xpath("//input[@id='LastName']"));
		lname.sendKeys(ExcelUtility.readStringData(excelPath, sheetName, 2, 1));
		WebElement email=driver.findElement(By.xpath("//input[@id='Email']"));
		email.sendKeys(ExcelUtility.readStringData(excelPath, sheetName, 3, 1));
		WebElement password=driver.findElement(By.xpath("//input[@id='Password']"));
		password.sendKeys(ExcelUtility.readStringData(excelPath, sheetName, 4, 1));
		WebElement cpassword=driver.findElement(By.xpath("//input[@id='ConfirmPassword']"));
		cpassword.sendKeys(ExcelUtility.readStringData(excelPath, sheetName, 5, 1));
		WebElement registerButton=driver.findElement(By.xpath("//input[@id='register-button']"));
		registerButton.click();
		WebElement userEmail=driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
		String actuserEmail=userEmail.getText();
		Assert.assertEquals(actuserEmail, ExcelUtility.readStringData(excelPath, sheetName, 3, 1), "Invalid Email");
	}
	@Test(priority=2, enabled=true, description="Verify login with invalid data for  Obsqura application", groups= {"Regression"}, dataProvider="InvalidCredentials")
	public void TC_006_verifyLoginWithInvalidData(String email,String psword)
	{
		//driver.get("https://demowebshop.tricentis.com/");
		WebElement login=driver.findElement(By.xpath("//a[@class='ico-login']"));
		login.click();
		WebElement emailField=driver.findElement(By.xpath("//input[@id='Email']"));
		emailField.sendKeys(email);
		WebElement passwordField=driver.findElement(By.xpath("//input[@id='Password']"));
		passwordField.sendKeys(psword);
		WebElement submitButton=driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
		submitButton.click();
		WebElement errorMessage=driver.findElement(By.xpath("//div[@class='validation-summary-errors']//span"));
		String actErrorMessage=errorMessage.getText();
		String ExpMessage="Login was unsuccessful. Please correct the errors and try again.";
		Assert.assertEquals(actErrorMessage, ExpMessage, "Invalid message");
	}
	@DataProvider(name="InvalidCredentials")
	public Object[][] userCredentials()
	{
		Object[][]data= {{"j.doe3@gmail.com", "sdoepwq"},{"j.doe4@gmail.com", "sdoepw"},{"j.doe31@gmail.com", "sdoepwq"}};
		return data;	
	}
	@Test(priority=3, enabled=true, description="Verify registration with random generator", groups= {"Sanity"})
	public void TC_007_verifyRegsitrationUsingRandomGenerator()
	{
		//driver.get("https://demowebshop.tricentis.com/");
		WebElement register=driver.findElement(By.xpath("//a[@class='ico-register']"));
		register.click();
		String fName=RandomDataUtility.getfName();
		String lName=RandomDataUtility.getlName();
		String emailID=RandomDataUtility.getRandomEmail();
		String psword=fName+"@123";
		List<WebElement> gender=driver.findElements(By.xpath("//input[@name='Gender']"));
		selectGender("F", gender);
		WebElement firstName=driver.findElement(By.xpath("//input[@id='FirstName']"));
		firstName.sendKeys(fName);
		WebElement lastName=driver.findElement(By.xpath("//input[@id='LastName']"));
		lastName.sendKeys(lName);
		WebElement email=driver.findElement(By.xpath("//input[@id='Email']"));
		email.sendKeys(emailID);
		WebElement password=driver.findElement(By.xpath("//input[@id='Password']"));
		password.sendKeys(psword);
		WebElement cpassword=driver.findElement(By.xpath("//input[@id='ConfirmPassword']"));
		cpassword.sendKeys(psword);
		WebElement registerButton=driver.findElement(By.xpath("//input[@id='register-button']"));
		registerButton.click();
		WebElement userEmail=driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
		String actuserEmail=userEmail.getText();
		Assert.assertEquals(actuserEmail, emailID, "Invalid Email");	
	}
	@Test(priority=4, enabled=true, description="Verify login with invalid datas", groups= {"Regression"},dataProvider="ValidCredentials")
	public void TC_008_verifyLoginWithValidData(String email,String psword)
	{
		//driver.get("https://demowebshop.tricentis.com/");
		WebElement login=driver.findElement(By.xpath("//a[@class='ico-login']"));
		login.click();
		WebElement emailField=driver.findElement(By.xpath("//input[@id='Email']"));
		emailField.sendKeys(email);
		WebElement passwordField=driver.findElement(By.xpath("//input[@id='Password']"));
		passwordField.sendKeys(psword);
		WebElement submitButton=driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
		submitButton.click();
		
		WebElement UserEmail=driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
		String actUserEmail=UserEmail.getText();
		String expEmail=email;
		Assert.assertEquals(actUserEmail, expEmail, "Invalid email");
	}
	@DataProvider(name="ValidCredentials")
	public Object[][] userCredentialsValid()
	{
		Object[][]data= {{"j.doe3@gmail.com", "sdoepw"},{"s.j@gmail.com", "qwerty"}};
		return data;	
	}
	@Test(priority=4, enabled=true, description="Verify login with parameters", groups= {"Smoke"})
	@Parameters({"uName","pWord"})
	public void TC_009_verifyLoginWithParameters(String email, String psword)
	{
		WebElement login=driver.findElement(By.xpath("//a[@class='ico-login']"));
		login.click();
		WebElement emailField=driver.findElement(By.xpath("//input[@id='Email']"));
		emailField.sendKeys(email);
		WebElement passwordField=driver.findElement(By.xpath("//input[@id='Password']"));
		passwordField.sendKeys(psword);
		WebElement submitButton=driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
		submitButton.click();
		
		WebElement UserEmail=driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
		String actUserEmail=UserEmail.getText();
		String expEmail=email;
		Assert.assertEquals(actUserEmail, expEmail, "Invalid email");
	}
	@Test
	@Parameters({"uName","pWord"})
	public void TC_010_verifyLoginWithInvalidParameters(String email, String psword)
	{
		WebElement login=driver.findElement(By.xpath("//a[@class='ico-login']"));
		login.click();
		WebElement emailField=driver.findElement(By.xpath("//input[@id='Email']"));
		emailField.sendKeys(email);
		WebElement passwordField=driver.findElement(By.xpath("//input[@id='Password']"));
		passwordField.sendKeys(psword);
		WebElement submitButton=driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
		submitButton.click();
		
		WebElement errorMessage=driver.findElement(By.xpath("//div[@class='validation-summary-errors']//span"));
		String actErrorMessage=errorMessage.getText();
		String ExpMessage="Login was unsuccessful. Please correct the errors and try again.";
		Assert.assertEquals(actErrorMessage, ExpMessage, "Invalid message");
	}
}