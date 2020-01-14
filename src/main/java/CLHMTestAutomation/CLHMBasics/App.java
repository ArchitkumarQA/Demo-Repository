package CLHMTestAutomation.CLHMBasics;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App {
	public static WebDriver driver;
	public static String courseUrl;
	public static String instUrl;

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\architkumar\\WebDriver_Chrome\\ChromeDriver79\\chromedriver.exe");
		// Openurl

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.get("https://s-www.cengage.com/dashboard/#/login");
		// Login instructor
		driver.findElement(By.id("emailId")).sendKeys("inst_evo11n@swlearning.com");
		driver.findElement(By.id("password")).sendKeys("A111111");
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		// select ISBN
		Select select = new Select(driver.findElement(By.xpath("//select[@id='productISBN']")));
		select.selectByValue("9781133314677");

		// Create course
		driver.findElement(By.cssSelector(".fa.fa-plus")).click();
		driver.findElement(By.xpath("//input[@id='createNewCourse']")).click();
		driver.findElement(By.linkText("Continue")).click();

		// Course info
		String coursename = "Test@321#" + System.currentTimeMillis();
		driver.findElement(By.xpath("//input[@id='courseName']")).sendKeys(coursename);
		// driver.findElement(By.xpath("//input[@id='calendar1']")).click();
		DateFormat dt = new SimpleDateFormat("M/d/yyyy");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		driver.findElement(By.xpath("//input[@id='beginDate']")).sendKeys(dt.format(calendar.getTime()));
		calendar.add(Calendar.DATE, 15);
		date = calendar.getTime();
		driver.findElement(By.xpath("//input[@id='endDate']")).sendKeys(dt.format(date));
		driver.findElement(By.linkText("Create Course")).click();
		
		Thread.sleep(5000);
		courseUrl = driver.findElement(By.cssSelector(".distributionOptions>a:nth-child(2)")).getAttribute("href");
		System.out.println(courseUrl);

		instUrl = driver.findElement(By.cssSelector(".stepContent>a[target='_blank']")).getAttribute("href");
		driver.findElement(By.cssSelector(".stepContent>a[target='_blank']")).click();

		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		Thread.sleep(20000);

		try {
			waitExplicitlyCssPath(".QSIPopOver.SI_3K9j9v78itjMYyp_PopOverContainer > div:nth-child(2)").click();
			// driver.findElement(By.cssSelector("img[src*='bwc_close.png']")).click();
			System.out.println("Feedback pop-up found");
		} catch (Exception e) {
			System.out.println("Feedback pop-up not found");
		}

		driver.findElement(By.xpath("//button[@class='close']")).click();
		Thread.sleep(2000);
		driver.findElement(By.className("css-1w5arjy")).click();
		Thread.sleep(2000);
		// Adding activity
		driver.findElement(By.cssSelector("#AddDropdown")).click();
		driver.findElement(By.cssSelector("a[aria-label='Add/Create an activity']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Assessment']")).click();
		Thread.sleep(2000);
		
		
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='ComponentLoader__container']//iframe")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='Simple Assessment']")).click();
		driver.findElement(By.xpath(
				"//span[contains(text(),\"Cengage: ADFs for Uber Coursemode CLHW display of Simple Items, 01e\")]"))
				.click();
		driver.findElement(By.xpath("//span[contains(text(),\"CLHW Example True/False\")]")).click();
		Thread.sleep(2000);
		waitExplicitlyCssPath(".publishButton.ng-scope").click();
		// driver.findElement(By.css("publishButton ng-scope")).click();
		driver.findElement(By.xpath("//button[contains(text(),\"Add\")]")).click();
		Thread.sleep(2000);
		// driver.findElement(By.xpath("//span[contains(text(),\"CLHW Example
		// True/False\")]")).click();

		//set region cookie
		driver.get("https://s-c-login.cengagebrain.com/cb/regionCookie.htm");
		Select select2 = new Select(driver.findElement(By.id("regionCode")));
		select2.selectByValue("USA");
		driver.findElement(By.className("medium_green_button")).click();
		
		
		Thread.sleep(5000);
		// Student
		driver.get(courseUrl);
		// Student signin
		Thread.sleep(10000);
		driver.findElement(By.cssSelector("#email")).sendKeys("stu_evo11n@swlearning.com");
		driver.findElement(By.cssSelector("#password")).sendKeys("A111111");
		driver.findElement(By.cssSelector(".btn.btn-secondary.pill")).click();
		Thread.sleep(5000);
		// driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div/div[1]/coursepageinfo-icon/div/div[2]/div/button")).click();
		// Thread.sleep(2000);

		// adding new student flow

//        	boolean b= waitExplicitlyXPath("//div[contains(text(),'Give Feedback')]").isDisplayed();
//        	System.out.println("On student page");
//        	if(b=true)

		Thread.sleep(10000);
//		driver.findElement(By.cssSelector(".sc-jKJlTe.sc-csuQGl.kjDkUB")).click();
//		//driver.get(driver.getCurrentUrl());
//		//driver.navigate().refresh();
//		//driver.get(driver.getCurrentUrl());
		try {
			if(waitExplicitlyXPath("//a[contains(text(),'End Tour')]").isDisplayed()) {
			driver.findElement(By.cssSelector(".jr_left_buttons")).click();
			System.out.println("Endtour flow");

		}
		}

		catch (Exception e) {
			System.out.println("normal flow");
		}
		
		Thread.sleep(5000);
		   driver.navigate().refresh();
		   driver.get(driver.getCurrentUrl());
		driver.findElement(By.xpath("//div[contains(text(),'Courses')]")).click();
//  
//   String a= driver.findElement(By.xpath("//a[contains(text(),'End Tour')]")).getText();
//   if(a=="End Tour")
//   driver.findElement(By.cssSelector(".jr_left_buttons")).click();
//      
//      //waitExplicitlyCssPath(".jr_left_buttons").click();
//       
//         //driver.findElement(By.cssSelector(".jr_left_buttons")).click();
//   Thread.sleep(5000);
//   driver.get(driver.getCurrentUrl());
//   driver.findElement(By.cssSelector(".sc-jKJlTe.sc-csuQGl.kjDkUB")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[contains(text(),'" + coursename + "')]")).click();
		Thread.sleep(20000);
//     // driver.findElement(By.xpath("//div[contains(text(),'"+coursename+"')]")).click();
//        Thread.sleep(20000);

		try {
			waitExplicitlyCssPath(".QSIPopOver.SI_9QNZajiSCtJ5X1j_PopOverContainer > div:nth-child(2)").click();
			// driver.findElement(By.cssSelector("img[src*='bwc_close.png']")).click();
			System.out.println("Feedback pop-up found");
		} catch (Exception e) {
			System.out.println("Feedback pop-up not found");
		}
		driver.findElement(By.xpath("//button[@class='close']")).click();
		Thread.sleep(2000);
		driver.findElement(By.className("css-1w5arjy")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'CLHW Example True/False')]")).click();
		Thread.sleep(10000);

		driver.switchTo().frame(driver.findElement(By.className("ActivityFrame__iFrame")));
		driver.switchTo().frame("easyXDM_activityService_cxp_Target_provider");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='2']")).click();
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		driver.findElement(By.cssSelector(".next-button")).click();
		driver.findElement(By.xpath("//input[@value='1']")).click();
		driver.findElement(By.xpath("//*[@id=\"rhs-part-assessment1_part2\"]/div[2]/input")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"rhs-part-assessment1_part2\"]/div[2]/button[2]")).click();
		driver.findElement(By.id("rhs-finalsubmit")).click();
	}

	public static WebElement waitExplicitlyCssPath(String CSS) {
		WebDriverWait wait;
		wait = new WebDriverWait(driver, 30);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CSS)));
		return element;
	}

	public static WebElement waitExplicitlyXPath(String xpath) {
		WebDriverWait wait;
		wait = new WebDriverWait(driver, 60);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		return element;
	}
}
