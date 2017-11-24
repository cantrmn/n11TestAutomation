package n11;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import Helper.n11Helper;

public class n11Test {
	
	static WebDriver driver;
	@Test
	public void titleControl() throws InterruptedException{
		driver = new FirefoxDriver ();
		driver.get(n11Helper.websiteUrl);
		Assert.assertEquals(driver.getTitle(), n11Helper.titleControl);
		driver.findElement(By.xpath(n11Helper.giris)).click(); // Giriþ yap
		Thread.sleep(5000);
		driver.findElement(By.xpath(n11Helper.fbBtnGrs)).click();
		Thread.sleep(5000);
		driver.manage().window().maximize();
		String winHandleBefore = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		driver.findElement(By.xpath(n11Helper.mailId)).sendKeys(n11Helper.mailValue); //KullanýcýAdý
		driver.findElement(By.xpath(n11Helper.passwordId)).sendKeys(n11Helper.passwordValue); //Parola
		Thread.sleep(1000);
		driver.findElement(By.id(n11Helper.fbGrsYapBtn)).click(); //GiriþYap
		Thread.sleep(10000);
		driver.switchTo().window(winHandleBefore);
		//driver.findElement(By.xpath(n11Helper.conBtn)).click(); // Continue Button
		//Thread.sleep(1000);

		WebElement elem = driver.findElement(By.xpath(n11Helper.checkBox1));
		elem.click();
		if (elem.isSelected()) {

			System.out.println("ok");
		} else {
			System.out.println("nok");

		}
			
		driver.findElement(By.id(n11Helper.onaylaBtn)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(n11Helper.searchLine)).sendKeys("iphone7");
		Thread.sleep(2000);
		driver.findElement(By.xpath(n11Helper.clickBtn)).click();
		Thread.sleep(3000);
		WebElement name;
		name = driver.findElement(By.xpath(n11Helper.firstProductName));
		String productName = name.getText();
		System.out.println(productName);
		Thread.sleep(3000);
		driver.findElement(By.xpath(n11Helper.firstRegister)).click();
		Thread.sleep(3000);
		WebElement openName;
		openName = driver.findElement(By.xpath(n11Helper.openProductName));
		String openProductName;
		openProductName = openName.getText();
		Thread.sleep(3000);
		productChecked(productName, openProductName);
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Thread.sleep(3000);

		try {

			FileUtils.copyFile(scrFile, new File("C:\\Users\\PIA-ASUS\\Desktop\\Ekran Görüntüleri\\screenshot.png"));
		}
		catch (IOException e) {
			System.out.println(e.getMessage());

		}
		Thread.sleep(3000);
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.xpath(n11Helper.myAccount));
		action.moveToElement(we).click().perform();
	}

	private static void productChecked(String name, String title){
		
		Assert.assertEquals(name, title);
	}
}
