package org.commonlibrary;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class GenericConfigClass {

	protected static WebDriver driver;
	protected WebDriverUtils WdUtils;
	protected ExcelUtils excelUtils=new ExcelUtils();
	
	protected String excelFilePath;
	private String excelSheetName;
	
	protected EventFiringWebDriver eDriver;

	@BeforeMethod
	public void setUp() {
		eDriver = new EventFiringWebDriver(driver);
		eDriver.register(new WebDriverListeners());
	}
	
	public GenericConfigClass(){
		Reporter.log("This result coming from Parent class constructor", true);
	}
	public GenericConfigClass(String message){
		Reporter.log("This result coming from Parent class constructor", true);
	}
	
	@BeforeClass
	@Parameters({"excelFilePath","excelSheetName"})
	public void excelPaths(String excelPath, String sheetName){
		this.excelFilePath= excelPath;
		this.excelSheetName= sheetName;
	}
	
	
	@DataProvider(name = "row-based-data")
	public Object[][] createRowBasedData() {
		 Reporter.log("trying to create row based data");
		Object[][] testData =excelUtils.readExcelData(excelFilePath, excelSheetName);
		return testData;
	}

	@BeforeClass
	@Parameters({ "browserName", "url" })
	public void setUp(String browserName, String url) {
		if (browserName.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			Reporter.log("Script started execution on " + browserName + " browser", true);
		} else if (browserName.equals("IExplore")) {
			System.setProperty("webdriver.ie.driver", "src/test/resources/IEDriverServer32Bit.exe");
			driver = new InternetExplorerDriver();
			Reporter.log("Script started execution on " + browserName + " browser", true);
		} else if (browserName.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
			driver = new ChromeDriver();
			Reporter.log("Script started execution on " + browserName + " browser", true);
		} else if (browserName.equals("Edge")) {
			System.setProperty("webdriver.edge.driver", "src/test/resources/MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
			Reporter.log("Script started execution on " + browserName + " browser", true);
		} else {
			// System.exit(0);
			System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			Reporter.log("Script started execution on default firefox browser", true);
		}
		WdUtils = new WebDriverUtils(driver);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
   @AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public void printResult(){
		Reporter.log("This result coming from Parent class", true);
	}
	
	
	public void printResultTest(){
		Reporter.log("This result coming from Parent class", true);
	}
	
	

}














