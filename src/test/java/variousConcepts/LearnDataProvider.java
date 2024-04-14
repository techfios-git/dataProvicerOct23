package variousConcepts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class LearnDataProvider {

	WebDriver driver;
	ExtentReports extent; 
	
	
	@BeforeClass
	public void reportGenerator() {
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReport.html");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
	}
	
	@AfterClass
	public void tearDown() {
//		driver.close();
		extent.flush();
	}
	
	@BeforeMethod
	public void init() {

		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://cert.codefios.com/login");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	
	@DataProvider(name = "loginDataDifRole")
	public String [][] loginData() {
		
		String [][] data = new String[][] {
			{"demo2@codefios.com", "abc123"},
			{"demo@codefios.com", "abc123"}
		};
		return data;
	}
	
	@Test(dataProvider = "loginDataDifRole")
	public void loginTest(String userName, String password) {
		
		driver.findElement(By.xpath("//*[@id=\"user_name\"]")).sendKeys(userName);
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\"login_submit\"]")).click();
		
		
	}

}
