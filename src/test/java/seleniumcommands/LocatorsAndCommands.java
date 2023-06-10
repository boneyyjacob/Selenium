package seleniumcommands;

import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.Set;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.datatransfer.StringSelection;
 
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;



public class LocatorsAndCommands {

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

	@BeforeMethod
	public void setup() {
		testInitialise("Chrome");
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
	if (result.getStatus() == ITestResult.FAILURE) {
	TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(screenshot, new File("./Screenshots/" + result.getName() + ".png"));
	}
	// driver.close();
	driver.quit();
	}

	@Test // execution starts here
	public void TC_001_verifyObsquraTitle() {
		driver.get("https://selenium.obsqurazone.com/index.php");
		String actTitle = driver.getTitle();
		System.out.println("Title  : " + actTitle);
		String expectedTitle = "Obsqura Testing";
		Assert.assertEquals(actTitle, expectedTitle, "Invalid Title Found");
	}

	@Test
	public void TC_002_verifySingleInputFieldMessage() {
		driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
		WebElement messagefield = driver.findElement(By.id("single-input-field"));
		messagefield.sendKeys("Test");
		WebElement showmessage = driver.findElement(By.id("button-one"));
		showmessage.click();
		WebElement message = driver.findElement(By.id("message-one"));
		String actmsgtext = message.getText();
		String expectedmsgtext = "Your Message : Test";
		Assert.assertEquals(actmsgtext, expectedmsgtext, "Invalid message Found");
	}

	@Test
	public void TC_003_verifyTwoInoutFieldMessage() {
		driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
		WebElement vala = driver.findElement(By.id("value-a"));
		vala.sendKeys("10");
		WebElement valb = driver.findElement(By.id("value-b"));
		valb.sendKeys("20");
		WebElement gettotal = driver.findElement(By.id("button-two"));
		gettotal.click();
		WebElement sum = driver.findElement(By.id("message-two"));
		String actsum = sum.getText();
		String expectedsum = "Total A + B : 30";
		Assert.assertEquals(actsum, expectedsum, "Invalid sum");
	}

	@Test
	public void TC_004_verifyEmptyFieldValidation() {
		driver.get("https://selenium.obsqurazone.com/form-submit.php");
		String expectedFnameMessage = "Please enter First name.";
		String expectedLnameMessage = "Please enter Last name.";
		String expectedUsernameMessage = "Please choose a username.";
		String expectedCityMessage = "Please provide a valid city.";
		WebElement submitButton = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
		submitButton.click();
		WebElement firstNameValidationMsg = driver
				.findElement(By.xpath("//input[@id='validationCustom01']//following-sibling::div[1]"));
		String fnamevalmsg = firstNameValidationMsg.getText();
		Assert.assertEquals(fnamevalmsg, expectedFnameMessage, "Invalid first name message");
		// System.out.println("First name validation message : "+fnamevalmsg);
		WebElement lastNameValidationMsg = driver
				.findElement(By.xpath("//input[@id='validationCustom02']//following-sibling::div[1]"));
		String lnamevalmsg = lastNameValidationMsg.getText();
		Assert.assertEquals(lnamevalmsg, expectedLnameMessage, "Invalid lastname message");
		// System.out.println("Last name validation message : "+lnamevalmsg);
		WebElement userNameValidationMsg = driver
				.findElement(By.xpath("//input[@id='validationCustomUsername']//following-sibling::div[1]"));
		String UnameValMsg = userNameValidationMsg.getText();
		Assert.assertEquals(UnameValMsg, expectedUsernameMessage, "Invalid username message");
		// System.out.println("Username validation message : "+UnameValMsg);
		WebElement cityValidationMsg = driver
				.findElement(By.xpath("//input[@id='validationCustom03']//following-sibling::div[1]"));
		String cityvalmsg = cityValidationMsg.getText();
		Assert.assertEquals(cityvalmsg, expectedCityMessage, "Invalid city message");
	}

	@Test
	public void TC_005_verifyStateAndZipcode() {
		driver.get("https://selenium.obsqurazone.com/form-submit.php");
		String stateValMsg = "Please provide a valid state.";
		String zipValMsg = "Please provide a valid zip.";
		String TandCValMsg = "You must agree before submitting.";
		WebElement submitButton = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
		submitButton.click();
		WebElement stateValidationMessage = driver
				.findElement(By.xpath("//input[@id='validationCustom04']//following-sibling::div[1]"));
		String actstateValidationMessage = stateValidationMessage.getText();
		Assert.assertEquals(actstateValidationMessage, stateValMsg, "Invalid state message");
		WebElement zipValidationMessage = driver
				.findElement(By.xpath("//input[@id='validationCustom05']//following-sibling::div[1]"));
		String actzipValidationMessage = zipValidationMessage.getText();
		Assert.assertEquals(actzipValidationMessage, zipValMsg, "Invalid Zip message");
		WebElement TandCValmessage = driver
				.findElement(By.xpath("//div[@class='form-check']//following-sibling::div[1]"));
		String actTandCValmessage = TandCValmessage.getText();
		Assert.assertEquals(actTandCValmessage, TandCValMsg, "Invalid Terms and Conditions message");
	}

	@Test
	public void TC_006_verifySuccessfulFormSubmission() {
		driver.get("https://selenium.obsqurazone.com/form-submit.php");
		String expectedFnameMessage = "Looks good!";
		String expectedLnameMessage = "Looks good!";
		String expectedUsernameMessage = "Looks good!";
		String expectedCityMessage = "Looks good!";
		String stateValMsg = "Looks good!";
		String zipValMsg = "Looks good!";
		String expSubmitValMsg = "Form has been submitted successfully!";

		WebElement Fname = driver.findElement(By.xpath("//input[@Id='validationCustom01']"));
		Fname.sendKeys("John");
		WebElement Lname = driver.findElement(By.xpath("//input[@Id='validationCustom02']"));
		Lname.sendKeys("Doe");
		WebElement UName = driver.findElement(By.xpath("//input[@Id='validationCustomUsername']"));
		UName.sendKeys("john.doe");
		WebElement City = driver.findElement(By.xpath("//input[@Id='validationCustom03']"));
		City.sendKeys("Texas");
		WebElement state = driver.findElement(By.xpath("//input[@Id='validationCustom04']"));
		state.sendKeys("FL");
		WebElement zip = driver.findElement(By.xpath("//input[@Id='validationCustom05']"));
		zip.sendKeys("12345");
		WebElement tandc = driver.findElement(By.xpath("//input[@type='checkbox']"));
		tandc.click();

		WebElement submitButton = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
		submitButton.click();

		WebElement firstNameValidationMsg = driver
				.findElement(By.xpath("//input[@id='validationCustom01']//following-sibling::div[2]"));
		String fnamevalmsg = firstNameValidationMsg.getText();
		Assert.assertEquals(fnamevalmsg, expectedFnameMessage, "Invalid first name message");

		WebElement lastNameValidationMsg = driver
				.findElement(By.xpath("//input[@id='validationCustom02']//following-sibling::div[2]"));
		String lnamevalmsg = lastNameValidationMsg.getText();
		Assert.assertEquals(lnamevalmsg, expectedLnameMessage, "Invalid lastname message");

		WebElement userNameValidationMsg = driver
				.findElement(By.xpath("//input[@id='validationCustomUsername']//following-sibling::div[2]"));
		String UnameValMsg = userNameValidationMsg.getText();
		Assert.assertEquals(UnameValMsg, expectedUsernameMessage, "Invalid username message");

		WebElement cityValidationMsg = driver
				.findElement(By.xpath("//input[@id='validationCustom03']//following-sibling::div[2]"));
		String cityvalmsg = cityValidationMsg.getText();
		Assert.assertEquals(cityvalmsg, expectedCityMessage, "Invalid city message");

		WebElement stateValidationMessage = driver
				.findElement(By.xpath("//input[@id='validationCustom04']//following-sibling::div[2]"));
		String actstateValidationMessage = stateValidationMessage.getText();
		Assert.assertEquals(actstateValidationMessage, stateValMsg, "Invalid state message");

		WebElement zipValidationMessage = driver
				.findElement(By.xpath("//input[@id='validationCustom05']//following-sibling::div[2]"));
		String actzipValidationMessage = zipValidationMessage.getText();
		Assert.assertEquals(actzipValidationMessage, zipValMsg, "Invalid Zip message");

		WebElement submitValidationMessage = driver.findElement(By.xpath("//div[@ID='message-one']"));
		String actsubmitValidationMessage = submitValidationMessage.getText();
		Assert.assertEquals(actsubmitValidationMessage, expSubmitValMsg, "Invalid submit message");

	}

	@Test
	public void TC_007_verifyNewsLetterSubscription() {
		String nlSubscribedMsg = "Thank you for signing up! A verification email has been sent. We appreciate your interest.";
		driver.get("https://demowebshop.tricentis.com");
		WebElement nlEmailField = driver.findElement(By.cssSelector("input#newsletter-email"));
		nlEmailField.sendKeys("john.doe@gmsil.com");
		WebElement nlSubmit = driver.findElement(By.cssSelector("input#newsletter-subscribe-button"));
		nlSubmit.click();
	}

	@Test
	public void TC_008_verifySuccessfulformsubmissionDemo() {
		driver.get("https://phptravels.com/demo/");
		WebElement Fname = driver.findElement(By.cssSelector("input.first_name.input.mb1[name='first_name']"));
		Fname.sendKeys("John");
		WebElement Lname = driver.findElement(By.cssSelector("input.last_name.input.mb1[name='last_name']"));
		Lname.sendKeys("Doe");
		WebElement Business = driver.findElement(By.cssSelector("input.business_name.input.mb1[name='business_name']"));
		Business.sendKeys("Trading Co.");
		WebElement Email = driver.findElement(By.cssSelector("input.email.input.mb1[name='email']"));
		Email.sendKeys("jonh.doe@gsmail.com");
		WebElement Number1 = driver.findElement(By.cssSelector("h2.cw.mw100>span#numb1"));
		int n1 = Integer.parseInt(Number1.getText());
		WebElement Number2 = driver.findElement(By.cssSelector("h2.cw.mw100>span#numb2"));
		int n2 = Integer.parseInt(Number2.getText());
		int Sum = n1 + n2;
		WebElement Result = driver.findElement(By.cssSelector("input#number"));
		Result.sendKeys(Integer.toString(Sum));
		WebElement submit = driver.findElement(By.cssSelector("button#demo"));
		submit.click();
		WebElement successMsg = driver.findElement(By.cssSelector("p.text-center.cw"));
		String msg = successMsg.getText();
		System.out.println("Message : " + msg);
	}

	@Test
	public void TC_009_verifyNavigateTo() {
		// driver.get("https://demowebshop.tricentis.com");
		driver.navigate().to("https://demowebshop.tricentis.com");
	}

	@Test
	public void TC_010_verifyRefresh() {
		driver.get("https://demowebshop.tricentis.com");
		WebElement newsLetter = driver.findElement(By.xpath("//input[@id='newsletter-email']"));
		newsLetter.sendKeys("john.doe@gsmail.com");
		driver.navigate().refresh();
	}

	@Test
	public void TC_011_verifyForwardandBackwardNavigation() throws InterruptedException {
		driver.get("https://demowebshop.tricentis.com");
		WebElement loginMenu = driver.findElement(By.xpath("//a[@class='ico-login']"));
		loginMenu.click();
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(2000);
		driver.navigate().forward();
	}

	@Test
	public void TC_012_verifyWebElementCommands() throws InterruptedException {
		String ExpectedMsg = "Form has been submitted successfully!";
		driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
		WebElement subjectField = driver.findElement(By.xpath("//input[@id='subject']"));
		subjectField.sendKeys("Selenium");
		WebElement descriptionField = driver.findElement(By.xpath("//textarea[@id='description']"));
		descriptionField.sendKeys("Automation Testing");
		subjectField.clear();// to clear an input field
		String classAttributeValue = subjectField.getAttribute("class");
		System.out.println("Class attribute - " + classAttributeValue);
		String tagNameValue = subjectField.getTagName();
		System.out.println("Tag name value - " + tagNameValue);
		subjectField.sendKeys("Selenium Automation");
		WebElement submitButton = driver.findElement(By.xpath("//input[@class='btn btn-primary']"));
		submitButton.click();
		Thread.sleep(2000);

		WebElement successMsg = driver.findElement(By.xpath("//div[@id='message-one']"));
		String sMsg = successMsg.getText();
		Assert.assertEquals(sMsg, ExpectedMsg, "Invalid submit message");
	}

	@Test
	public void TC_013_verifyIsDisplayed() {
		driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
		WebElement subjectField = driver.findElement(By.xpath("//input[@id='subject']"));
		subjectField.sendKeys("Selenium");
		boolean status = subjectField.isDisplayed();
		System.out.println(status);
		Assert.assertTrue(status, "Subject field not displayed");
	}

	@Test
	public void TC_014_veruifyIsSelected() {
		driver.get("https://selenium.obsqurazone.com/check-box-demo.php");
		WebElement singleCheckbox = driver.findElement(By.xpath("//input[@id='gridCheck']"));
		boolean statusbeforeclick = singleCheckbox.isSelected();
		System.out.println(statusbeforeclick);
		Assert.assertFalse(statusbeforeclick, "Checkbox selected before click");
		singleCheckbox.click();
		boolean statusAfterclick = singleCheckbox.isSelected();
		System.out.println(statusAfterclick);
		Assert.assertTrue(statusAfterclick, "Checkbox not selected");
	}

	@Test
	public void TC_015_verifyIsEnabled() {
		driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
		WebElement submitButton = driver.findElement(By.xpath("//input[@class='btn btn-primary']"));
		boolean submitstatus = submitButton.isEnabled();
		System.out.println(submitstatus);
		Assert.assertTrue(submitstatus, "Submit button not enabled");
		Point point = submitButton.getLocation();
		System.out.println(point.x + "," + point.y);
		Dimension Dim = submitButton.getSize();
		System.out.println(Dim.height + "," + Dim.width);
		String bgcolor = submitButton.getCssValue("background-color");
		System.out.println(bgcolor);
		WebElement element = driver.findElement(By.tagName("Input"));
		System.out.println(element);
		List<WebElement> elements = driver.findElements(By.tagName("Input"));
		System.out.println(elements);

	}

	@Test
	public void TC_0016_verifyTheMessageDisplayedInNewTab() {
		driver.get("https://demoqa.com/browser-windows");
		WebElement newTabButton = driver.findElement(By.xpath("//button[@id='tabButton']"));
		boolean status = newTabButton.isEnabled();
		System.out.println(status);
		newTabButton.click();
		driver.navigate().to("https://demoqa.com/sample");
		String expecetedText = "This is a sample page";
		WebElement newTab = driver.findElement(By.xpath("//h1[@id='sampleHeading']"));
		String actText = newTab.getText();
		Assert.assertEquals(actText, expecetedText, "Invalid text displayed");
	}

	@Test
	public void TC_0017_verifyTheMessageDisplayedInNewWindow() {
		driver.get("https://demoqa.com/browser-windows");
		String parentWindow = driver.getWindowHandle();
		System.out.println("Parent Window ID : " + parentWindow);
		WebElement newWindowButton = driver.findElement(By.xpath("//button[@id='windowButton']"));
		newWindowButton.click();
		Set<String> handles = driver.getWindowHandles();
		System.out.println(handles);
		Iterator<String> handleids = handles.iterator();
		while (handleids.hasNext()) {
			String childWindow = handleids.next();
			if (!childWindow.equals(parentWindow)) {
				driver.switchTo().window(childWindow);
				WebElement samplePage = driver.findElement(By.xpath("//h1[@id='sampleHeading']"));
				String actualMsg = samplePage.getText();
				String expMsg = "This is a sample page";
				Assert.assertEquals(actualMsg, expMsg, "Invalid message");
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
	}

	@Test
	public void TC_018_verifySimpleAlert() {
		driver.get("https://selenium.obsqurazone.com/javascript-alert.php");
		WebElement clickMe = driver.findElement(By.xpath("//button[@class='btn btn-success']"));
		clickMe.click();
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println(alertText);
		alert.accept();
	}

	@Test
	public void TC_019_verifyConfirmAlert() {
		driver.get("https://selenium.obsqurazone.com/javascript-alert.php");
		WebElement clickMe = driver.findElement(By.xpath("//button[@class='btn btn-warning']"));
		clickMe.click();
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println(alertText);
		alert.dismiss();
	}

	@Test
	public void TC_020_verifyPromptAlert() {
		driver.get("https://selenium.obsqurazone.com/javascript-alert.php");
		WebElement clickPromptBox = driver.findElement(By.xpath("//button[@class='btn btn-danger']"));
		clickPromptBox.click();
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println(alertText);
		alert.sendKeys("Ok");
		alert.accept();
	}

	@Test
	public void TC_021_verifyTextInAFrame() {
		driver.get("https://demoqa.com/frames");
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		int noOfFrames = frames.size();
		System.out.println(noOfFrames);
		// driver.switchTo().frame(3);//using index
		// driver.switchTo().frame("frame1");//using name or ID
		WebElement frame1 = driver.findElement(By.id("frame1"));// using WebElement
		driver.switchTo().frame(frame1);
		WebElement heading = driver.findElement(By.id("sampleHeading"));
		String headingText = heading.getText();
		System.out.println(headingText);
		// driver.switchTo().parentFrame();
		driver.switchTo().defaultContent();

	}

	@Test
	public void TC_022_verifyRightClick() {
		driver.get("https://demo.guru99.com/test/simple_context_menu.html");
		WebElement rightClick = driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"));
		Actions action = new Actions(driver);
		action.contextClick(rightClick).build().perform();
	}

	@Test
	public void TC_023_verifyDoubleClick() {
		driver.get("https://demo.guru99.com/test/simple_context_menu.html");
		WebElement doubleClickme = driver.findElement(By.xpath("//button[text()='Double-Click Me To See Alert']"));
		Actions actions = new Actions(driver);
		actions.doubleClick(doubleClickme).build().perform();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	@Test
	public void TC_024_verifyMouseHover() {
		driver.get("https://demoqa.com/menu/");
		WebElement MainItem1 = driver.findElement(By.xpath("//a[text()='Main Item 1']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(MainItem1).build().perform();
	}

	@Test
	public void TC_025_verifyDragAndDrop() {
		driver.get("https://demoqa.com/droppable");
		WebElement dragme = driver.findElement(By.id("draggable"));
		WebElement dropped = driver.findElement(By.id("droppable"));
		Actions actions = new Actions(driver);
		actions.dragAndDrop(dragme, dropped).build().perform();
	}

	@Test
	public void Tc_026_verifyClickHoldAndResize() {
		driver.get("https://demoqa.com/resizable");
		WebElement ResizableBox = driver.findElement(By.xpath("//div[@id='resizableBoxWithRestriction']/child::span"));
		Actions action = new Actions(driver);
		action.clickAndHold(ResizableBox).build().perform();
		action.dragAndDropBy(ResizableBox, 200, 100).build().perform();
	}

	@Test
	public void TC_027_verifyDropDown() {
		driver.get("https://demo.guru99.com/test/newtours/register.php");
		WebElement country = driver.findElement(By.xpath("//select[@name='country']"));
		Select select = new Select(country);
		// select.selectByVisibleText("INDIA");
		// select.selectByIndex(23);
		select.selectByValue("EGYPT");
	}

	@Test
	public void TC_028_verifyMultipleDropDown() {
		driver.get("https://www.softwaretestingmaterial.com/sample-webpage-to-automate/");
		WebElement multipleSelectValues = driver.findElement(By.xpath("//select[@name='multipleselect[]']"));
		Select select = new Select(multipleSelectValues);
		boolean status = select.isMultiple();
		System.out.println(status);
		select.selectByVisibleText("Performance Testing");
		select.selectByValue("msagile");
		// select.deselectAll();

	}

	@Test
	public void Tc_029_verifyFileUploadInSelenium() {
		driver.get("https://demo.guru99.com/test/upload/");
		WebElement chooseFile = driver.findElement(By.id("uploadfile_0"));
		chooseFile.sendKeys("D:\\Selenium\\Test.txt");
		WebElement tAndC = driver.findElement(By.xpath("//input[@id='terms']"));
		tAndC.click();
		WebElement submitButton = driver.findElement(By.xpath("//button[@id='submitbutton']"));
		submitButton.click();
	}

	@Test
	public void TC_030_verifyClickAndSendKeysUsingJavascriptExecutor() {
		driver.get("https://demowebshop.tricentis.com/");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('newsletter-email').value='test@gmsil.com'");
		js.executeScript("document.getElementById('newsletter-subscribe-button').click()");
	}

	@Test
	public void TC_031_verifyScrollDownOfAWebElement() {
		driver.get("https://demo.guru99.com/test/guru99home/");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
	}

	@Test
	public void Tc_032_verifyScrollIntoViewOfAWebElement() {
		driver.get("https://demo.guru99.com/test/guru99home/");
		WebElement linuxText = driver.findElement(By.linkText("Linux"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", linuxText);
	}

	@Test
	public void Tc_033_verifyHorizontalScroll() {
		driver.get("https://demo.guru99.com/test/guru99home/");
		WebElement VbScriptText = driver.findElement(By.linkText("VBScript"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", VbScriptText);
	}

	@Test
	public void TC_034_verifyTable() throws IOException {
		driver.get("https://www.w3schools.com/html/html_tables.asp");
		List<WebElement> rowElement = driver.findElements(By.xpath("//table[@id='customers']//tbody//tr"));
		List<WebElement> columnElement = driver.findElements(By.xpath("//table[@id='customers']//tbody//tr//td"));
		List<ArrayList<String>> actGridData = TableUtility.get_Dynamic_TwoDimension_TablElemnts(rowElement,
				columnElement);
		List<ArrayList<String>> expGridData = ExcelUtility.excelDataReader("\\src\\test\\resources\\TestData.xlsx",
				"Table");
		System.out.println(actGridData);
		System.out.println(expGridData);
		Assert.assertEquals(actGridData, expGridData, "Invalid data found");

	}
	

	@Test
	public void tc_034_verifyFileUploadUsingRobotClass() throws AWTException, InterruptedException
	{
		driver.get("https://www.foundit.in/seeker/registration");
		StringSelection s = new StringSelection("D:\\Selenium\\test.docx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
		WebElement uploadFile = driver.findElement(By.xpath("//div[@class='uploadResume']"));
		uploadFile.click();
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		Thread.sleep(2000);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}
	@Test
	public void TC_035_verifyWaitsInSelenium()
	{
		driver.get("https://demowebshop.tricentis.com/");
		/*pageloadwait*/
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		/*implicitwait*/
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement email=driver.findElement(By.xpath("//input[@id='newsletter-email']"));
		email.sendKeys("test@gmail.com");
		/*explicitwait*/
		WebElement subscribeButton=driver.findElement(By.xpath("//input[@id='newsletter-subscribe-button']"));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(subscribeButton));
		/*fluentwait*/
		FluentWait fwait=new FluentWait<WebDriver>(driver);
		fwait.withTimeout(Duration.ofSeconds(10));
		fwait.pollingEvery(Duration.ofSeconds(1));
		fwait.until(ExpectedConditions.visibilityOf(subscribeButton));
		subscribeButton.click();
	}
}
