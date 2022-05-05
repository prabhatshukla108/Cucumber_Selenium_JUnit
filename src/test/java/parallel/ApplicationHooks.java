package parallel;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;
import com.qa.util.ScreenRecording;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {

	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;
	

	@Before(order = 0)
	public void getProperty() {
		configReader = new ConfigReader();	
		prop = configReader.init_prop();
	}

	@Before(order = 1)
	public void launchBrowser(Scenario sc) {
		String browserName = prop.getProperty("browser");
		driverFactory = new DriverFactory();
		String methodName =sc.getName();
		try {
			ScreenRecording.startRecord(methodName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver = driverFactory.init_driver(browserName);
		
	}

	@After(order = 0)
	public void quitBrowser() {
		try {
			ScreenRecording.stopRecord();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
		
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			// take screenshot:
			String screenshotName = scenario.getName().replaceAll(" ", "_");
//			System.out.println("******"+s);
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);

		}
		
	}

}
