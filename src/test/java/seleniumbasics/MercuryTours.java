package seleniumbasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MercuryTours {

	public static void main(String[] args) 
	{
		ChromeOptions ops=new ChromeOptions();
		ops.addArguments("--remote-allow-origins=*");
		WebDriver driver=new ChromeDriver(ops);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://demo.guru99.com/test/newtours/");
		//WebElement register=driver.findElement(By.linkText("REGISTER"));
		WebElement register=driver.findElement(By.partialLinkText("REG"));
		register.click();
		//contact information
		WebElement Fname=driver.findElement(By.name("firstName"));
		Fname.sendKeys("Boney");
		WebElement Lname=driver.findElement(By.name("lastName"));
		Lname.sendKeys("Jacob");
		WebElement phone=driver.findElement(By.name("phone"));
		phone.sendKeys("1234567890");
		WebElement email=driver.findElement(By.name("userName"));
		email.sendKeys("boney.jacob@gtmail.com");
		//mailing information
		WebElement address=driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[7]/td[2]/input"));
		address.sendKeys("House no 10");
		WebElement city=driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[8]/td[2]/input"));
		city.sendKeys("TVM");
		WebElement state=driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[9]/td[2]/input"));
		state.sendKeys("Kerala");
		WebElement zip=driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[10]/td[2]/input"));
		zip.sendKeys("695001");
		WebElement country=driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[11]/td[2]/select"));
		country.sendKeys("India");
		//user information
		WebElement username=driver.findElement(By.cssSelector("#email"));
		username.sendKeys("boney_jacob");
		WebElement password=driver.findElement(By.cssSelector("body > div:nth-child(5) > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > form > table > tbody > tr:nth-child(14) > td:nth-child(2) > input[type=password]"));
		password.sendKeys("qwerty1234");
		WebElement cpassword=driver.findElement(By.cssSelector("body > div:nth-child(5) > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > form > table > tbody > tr:nth-child(15) > td:nth-child(2) > input[type=password]"));
		cpassword.sendKeys("qwerty1234");
		//submit
		WebElement submit=driver.findElement(By.name("submit"));
		submit.click();
		
		//using name name phone , email
		//using xpath mailing info
		//user information using cssselector
		//submit button using name	

	}

}
