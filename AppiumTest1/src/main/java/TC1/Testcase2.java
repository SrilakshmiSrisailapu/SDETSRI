package TC1;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

public class Testcase2 {
	public AndroidDriver driver;
	
  @Test
  public void selectcomputers() throws IOException
  {
	  driver.manage().timeouts().implicitlyWait(15,java.util.concurrent.TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[@class='icon']")).click();
		driver.findElement(By.xpath("(//a[contains(text(),'Computers')]//following::span[@class ='expand'])[1]")).click();
		driver.findElement(By.xpath("(//ul[@class='sublist firstLevel']//a[contains(text(),'Notebooks')])[2]")).click();

        File file= new File("C:\\Users\\SrilakshmiSrisailapu\\Desktop\\Upskill\\API\\upskill1.xlsx");
        FileInputStream fis= new FileInputStream(file);
        XSSFWorkbook wb= new XSSFWorkbook(fis);
        XSSFSheet sheet=wb.getSheetAt(0);
        int rc= sheet.getLastRowNum();
        System.out.println(rc);
        for (int i=1;i<=rc;i++) {
            String ListType = sheet.getRow(i).getCell(0).getStringCellValue();
            System.out.println(ListType);            
            Select s1 = new Select(driver.findElement(By.xpath("//select[@id='products-viewmode']")));
    		s1.selectByVisibleText(ListType);
    		Boolean actual = true;
    		Boolean Expected = driver.findElement(By.xpath("//a[text()='14.1-inch Laptop']")).isDisplayed();						
    	    Assert.assertEquals(actual,Expected);	          

        }		

	
 
  }
  

  @BeforeClass
  public void beforeMethod() throws MalformedURLException {	  
	  DesiredCapabilities capability = new DesiredCapabilities();			
		capability.setCapability(MobileCapabilityType.DEVICE_NAME,"SRI");
		capability.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		capability.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capability);
		driver.get("http://demowebshop.tricentis.com/");
  }

  @AfterClass
  public void afterMethod() {
	 
  }

}
