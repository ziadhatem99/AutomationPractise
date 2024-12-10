package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FuelTrackerPage {
    Actions actions;
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By cardNumberField = By.xpath("//input[@name = 'carNumber']");
    private final By fuelInLitresField = By.xpath("//input[@name = 'fuelInLiters']");
    private final By fuelCostField = By.xpath("//input[@name = 'fuelCost']");
    private final By fuelTypeField = By.xpath("//input[@name = 'fuelType']");
    private final By dateField = By.xpath("//input[@name = 'dateAndTime']");
    private final By companyIdField = By.xpath("//input[@name = 'companyId']");
    private final By submitBtn = By.xpath("//button[@type= 'submit' and text()='Edit']");
    public FuelTrackerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    private String getCurrentDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a");
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }

    public FuelTrackerPage enterCarNumber(String cardNumber) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberField));
        element.sendKeys(cardNumber);
        return this;
    }

    public FuelTrackerPage enterFuelLitres(String fuelLitres) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(fuelInLitresField));
        element.sendKeys(fuelLitres);
        return this;
    }

    public FuelTrackerPage enterFuelCost(String fuelCost) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(fuelCostField));
        element.sendKeys(fuelCost);
        return this;
    }

    public FuelTrackerPage enterFuelType(String fuelType) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(fuelTypeField));
        element.sendKeys(fuelType);
        return this;
    }

    public FuelTrackerPage enterDate() {
        String dateTimeValue = "00-00-2024 T09:00";
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(dateField));
        element.clear();
        element.sendKeys(dateTimeValue);
        element.sendKeys(Keys.ENTER);
        return this;
    }

    public FuelTrackerPage enterCompanyId(String CompanyId) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(companyIdField));
        element.sendKeys(CompanyId);
        return this;
    }

    public FuelTrackerPage clickSubmit() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
        element.click();
        return this;
    }
}
