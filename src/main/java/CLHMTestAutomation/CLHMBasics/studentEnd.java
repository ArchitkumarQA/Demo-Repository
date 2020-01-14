package CLHMTestAutomation.CLHMBasics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class studentEnd {

	public static WebDriver driver;
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\architkumar\\WebDriver_Chrome\\Test\\chromedriver.exe");
        //Openurl
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
driver.get("https://s-www.cengage.com/dashboard/#/course-confirmation/MTPPRHWZSW52/initial-course-confirmation");

Thread.sleep(10000);
driver.findElement(By.cssSelector("#email")).sendKeys("stu_evo11n@swlearning.com");
driver.findElement(By.cssSelector("#password")).sendKeys("A111111");
Thread.sleep(2000);
driver.findElement(By.cssSelector(".btn.btn-secondary.pill")).click();

driver.findElement(By.xpath("//button[contains (text(),\"Open Course\")]")).click();
Thread.sleep(10000);
try {
	 waitExplicitlyCssPath(".QSIPopOver.SI_9QNZajiSCtJ5X1j_PopOverContainer > div:nth-child(2)").click();
	//driver.findElement(By.cssSelector("img[src*='bwc_close.png']")).click();
	System.out.println("Feedback pop-up found");
}
catch(Exception e) {
	System.out.println("Feedback pop-up not found");	
}
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
