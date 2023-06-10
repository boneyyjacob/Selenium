package seleniumbasics;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumCommands {
	
	public static void main(String args[])
	{
	//ChromeOptions ops=new ChromeOptions();
	//ops.addArguments("--remote-allow-origins=*");
	//WebDriver driver=new ChromeDriver(ops);
	WebDriver driver=new FirefoxDriver();
	//WebDriver driver=new EdgeDriver();
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	
	driver.get("https://www.selenium.obsqurazone.com/simple-form-demo.php");
	String title=driver.getTitle();
	System.out.println("Title is  : "+title);
	String currenturl=driver.getCurrentUrl();
	System.out.println("URL is :"+currenturl);
	//String pgsource=driver.getPageSource();
	//System.out.println("Source : "+pgsource);
	WebElement messagefield = driver.findElement(By.id("single-input-field"));
	messagefield.sendKeys("Text");
	WebElement showmessage=driver.findElement(By.id("button-one"));
	showmessage.click();
	WebElement message = driver.findElement(By.id("message-one"));
	String msgtext = message.getText();
	System.out.println("Message : "+msgtext);
	WebElement vala = driver.findElement(By.id("value-a"));
	vala.sendKeys("10");
	WebElement valb = driver.findElement(By.id("value-b"));
	valb.sendKeys("20");
	WebElement gettotal=driver.findElement(By.id("button-two"));
	gettotal.click();
	WebElement sum = driver.findElement(By.id("message-two"));
	String sumss = sum.getText();
	System.out.println("Sum is : "+sumss);
	}
}
