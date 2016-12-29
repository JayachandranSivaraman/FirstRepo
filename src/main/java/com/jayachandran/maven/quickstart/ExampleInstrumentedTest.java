package com.jayachandran.maven.quickstart;

import java.net.URL;

import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Pages.calculatorUi;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class ExampleInstrumentedTest extends calculatorUi{
	private static AndroidDriver<MobileElement> driver;
	
	@BeforeTest
	public void Capabilities() throws Exception {
		Runtime.getRuntime().exec("/Users/jayachandran/android-sdks/tools/emulator"+" -avd Nexus4");
		
		Thread.sleep(10000);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Android");
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4.2");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus4");
        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "4000");
        cap.setCapability(MobileCapabilityType.APP, "/Users/jayachandran/Documents/Git/app/build/outputs/apk/app-debug.apk");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
       
	}
	
    @Test
    public void dateLengthValidation() {
        driver.findElement(By.xpath(calculatorUi.dateField)).isDisplayed();
        driver.findElement(By.xpath(calculatorUi.dateField)).sendKeys("1234");
        Assert.assertEquals(driver.findElement(By.xpath(calculatorUi.dateField)).getText().length(), 2);
        driver.findElement(By.xpath(calculatorUi.cancelButton)).click();
    }
    
//    @Test
    public void datefieldTextValidation() {
    	driver.findElement(By.xpath(calculatorUi.dateField)).isDisplayed();
        driver.findElement(By.xpath(calculatorUi.dateField)).sendKeys("test");
        System.out.println(driver.findElement(By.xpath(calculatorUi.dateField)).getText());
        Assert.assertEquals(driver.findElement(By.xpath(calculatorUi.dateField)).getText(), "Enter Date Value");
    }
    
//    @Test
//    public void monthFieldTextValidation() throws Exception {
//    	System.out.println("started month field validation");
//    	driver.wait();
//    	driver.findElement(By.xpath(calculatorUi.monthField)).isDisplayed();
//    	driver.findElement(By.xpath(calculatorUi.monthField)).sendKeys("test");
//    	System.out.println(driver.findElement(By.xpath(calculatorUi.monthField)).getText());
//    	Assert.assertEquals(driver.findElement(By.xpath(calculatorUi.monthField)).getText(), "Enter Month Value");
//    }
//    
//    @Test
    public void functionalTestCase1() {
    	driver.findElement(By.xpath(calculatorUi.dateField)).isDisplayed();
        driver.findElement(By.xpath(calculatorUi.dateField)).sendKeys("11");
        driver.findElement(By.xpath(calculatorUi.monthField)).isDisplayed();
        driver.findElement(By.xpath(calculatorUi.monthField)).sendKeys("11");
        driver.findElement(By.xpath(calculatorUi.yearField)).isDisplayed();
        driver.findElement(By.xpath(calculatorUi.yearField)).sendKeys("1991");
        driver.findElement(By.xpath(calculatorUi.validateButton)).click();
        java.util.List<MobileElement> infoList = driver.findElements(By.xpath(calculatorUi.informationArea));
        int size = infoList.size();
        String informationText = driver.findElement(By.xpath(calculatorUi.informationArea+"["+size+"]")).getText();
        System.out.println(informationText);
        if(informationText.contains("Valid Date"))
        {
        	Assert.assertEquals(true, true);
        }
        else {
        	Assert.assertEquals(true, false);
        }
    }
    
    @AfterTest
    public void driverQuit() throws Exception {
    	driver.quit();
    }
}
