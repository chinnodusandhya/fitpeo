package Assignment;

	import java.time.Duration;
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class FitpeoAssignment
	{
	
		public static void main(String[] args) throws InterruptedException {

			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			JavascriptExecutor js = (JavascriptExecutor) driver;

			// Step 1: Navigate to the FitPeo Homepage
			driver.get("https://www.fitpeo.com/");

			// Step 2: Navigate to the Revenue Calculator Page
			driver.findElement(By.xpath("//div[contains(text(),'Revenue Calculator')]")).click();


			// Scroll Down to the Slider Section
			WebElement slider= driver.findElement(By.xpath("//input[@type='range']")); 
			js.executeScript("arguments[0].scrollIntoView();", slider);

			// Adjust the Slider to Set its Value to 820

			Actions act=new Actions(driver);
			int targetValue=820;
			int currentValue=Integer.parseInt(slider.getAttribute("value"));
			int offset=targetValue-currentValue;

			try {
				act.dragAndDropBy(slider, offset, 0);
			}
			catch(Exception e)
			{
			}
			//js.executeScript("arguments[0].value = 820;", slider);

			//validating Text Field
			WebElement textField = driver.findElement(By.xpath("//input[@type='number']"));
			if(textField.getAttribute("value").equals(targetValue))
			{
				System.out.println("Text Field updated correctly");
			}
			else
			{
				System.out.println("Text Field update failed");
			}

			// Update the Text Field with 560

			js.executeScript("arguments[0].click();",textField );
			textField.clear();
			textField.sendKeys("560");

			// Ensure the Slider Value is Updated to Reflect 560

			String sliderValue = slider.getAttribute("value");

			if ("560".equals(sliderValue)) {
				System.out.println("Slider value correctly updated to 560.");
			} else {
				System.out.println("Slider value update failed.");
			}

			// Select CPT Codes


			WebElement cpt99091Checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='57']"))); 
			WebElement cpt99453Checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='19.19']")));
			WebElement cpt99454Checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='63']")));
			WebElement cpt99474Checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='15']")));

			cpt99091Checkbox.click();
			cpt99453Checkbox.click();
			cpt99454Checkbox.click();
			cpt99474Checkbox.click();

			// Validate Total Recurring Reimbursement
			WebElement totalReimbursementHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Total Recurring')]/p"))); 
			String totalReimbursementValue = totalReimbursementHeader.getText();

			if ("$110700".equals(totalReimbursementValue)) 
			{
				System.out.println("Total recurring reimbursement value is correct: " + totalReimbursementValue);
			} 
			else 
			{
				System.out.println("Total recurring reimbursement value is incorrect: " + totalReimbursementValue);
			}


			// closing browser
			driver.quit();

		}
	}



