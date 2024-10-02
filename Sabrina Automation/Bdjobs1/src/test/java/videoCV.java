import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;

public class videoCV {

    public static void main(String[] arg) {
        // Set up ChromeOptions to include the ad blocker extension
        ChromeOptions options = new ChromeOptions();
        String adblockerPath = "src/main/resources/uBlock-Origin-Chrome-Web-Store.crx";

        // Validate if the extension file exists before adding it to ChromeOptions
        File extensionFile = new File(adblockerPath);

        if (extensionFile.exists() && extensionFile.isFile()) {
            options.addExtensions(extensionFile);
        }

        // Initialize WebDriver with ChromeOptions
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.manage().window().maximize();

            // Navigate to the SignIn page
            driver.get("https://mybdjobs.bdjobs.com/mybdjobs/signin.asp");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Enter username
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='TXTUSERNAME']")));
            usernameField.sendKeys("avary");

            // Click Continue button
            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Continue']")));
            continueButton.click();

            // Enter password
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='TXTPASS']")));
            passwordField.sendKeys("12345678");

            // Click Sign in button
            WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Sign in']")));
            signInButton.click();

            // Wait for the Manage Profile dropdown to be clickable and click it
            WebElement manageProfileDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='manageResumeMenu']")));
            manageProfileDropdown.click();

            // Wait for and click the Video CV link
            WebElement videoCV = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Video CV']")));
            videoCV.click();

            // Switch to the new tab for Video CV
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            // Close the popup tutorial if it appears
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[id='videoTutorialCloseBtn'] span[aria-hidden='true']")));
            closeButton.click();

            // Optionally click the "Take Preparation" button
            WebElement takePreparation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Take Preparation']")));
            takePreparation.click();

            // Close the "Take Preparation" modal
            WebElement crossButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='videoResumeTakePreparation']//button[@type='button'][normalize-space()='×']")));
            crossButton.click();

            // Switch to the "User Guide" tab
            WebElement userGuide = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='User Guide']")));
            userGuide.click();

            // Switch to the newly opened User Guide tab
            tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(2));
            driver.close();

            // Switch back to the main window
            driver.switchTo().window(tabs.get(1));

            // Click on the "Video Guide" link
            WebElement videoGuide = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Video Guide']")));
            videoGuide.click();

            // Switch to the new tab for Video Guide
            tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(2));
            driver.close();

            // Switch back to the main window
            driver.switchTo().window(tabs.get(1));

            // Click the "Edit Video CV" button
            WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Edit Video CV']")));
            editButton.click();
            driver.navigate().back();

            // Close the tutorial popup again
            WebElement crossIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='videoTutorialCloseBtn']//span[@aria-hidden='true'][normalize-space()='×']")));
            crossIcon.click();

            // Hover over an element (tooltip example)
            WebElement elementWithTooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@aria-label='Tooltip']//span[@class='icon-info ico']")));
            Actions actions = new Actions(driver);
            actions.moveToElement(elementWithTooltip).perform();

            // Optionally, capture the tooltip text
            String tooltipText = elementWithTooltip.getAttribute("title");
            System.out.println("Tooltip text: " + tooltipText);

        } catch (Exception e) {
            // Log the error instead of throwing it (improved error handling)
            e.printStackTrace();
        } finally {
            // Ensure the browser is closed after execution
            if (driver != null) {
                //driver.quit();
            }
        }
    }
}
