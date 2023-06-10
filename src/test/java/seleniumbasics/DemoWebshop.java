package seleniumbasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DemoWebshop {

	public static void main(String[] args) {
		
	ChromeOptions ops=new ChromeOptions();
	ops.addArguments("--remote-allow-origins=*");
	WebDriver driver=new ChromeDriver(ops);
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	
	driver.get("https://demowebshop.tricentis.com/");
    WebElement loginlink=driver.findElement(By.className("ico-login"));
    loginlink.click();
    WebElement email=driver.findElement(By.name("Email"));
    email.sendKeys("boney.jacob@gtmail.com");
    WebElement password=driver.findElement(By.name("Password"));
    password.sendKeys("qwerty1234");
    WebElement loginb=driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input"));
    loginb.click();
    System.out.println("Logged in successfully");
    //driver.close();
    
    
	}
	
}
