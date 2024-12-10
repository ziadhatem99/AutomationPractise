package Tests;

import Base.Assets;
import Base.TestBase;
import Pages.FuelTrackerPage;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class FuelTrackerTest extends TestBase {
    FuelTrackerPage fuelTrackerPage;
    Assets assets;
    Faker faker;

    @BeforeMethod
    public void SetUpMethod() {
        assets = new Assets();
        faker = new Faker();
        driver.get(assets.URL);
        fuelTrackerPage = new FuelTrackerPage(driver);
    }

    @Test(priority = 1)
    public void HappyScenarioAddRecord() {
        String carNumber = faker.number().digits(4);
        String fuelLitres = faker.number().digits(2);
        String fuelCost = faker.number().digits(2);
        String fuelType = "Gasoline";
        String companyId = faker.number().digits(5);
        fuelTrackerPage
                .enterCarNumber(carNumber)
                .enterFuelLitres(fuelLitres)
                .enterFuelCost(fuelCost)
                .enterFuelType("Gasoline")
                .enterDate()
                .enterCompanyId(companyId)
                .clickSubmit();
        WebElement lastRow = driver.findElement(By.cssSelector("table tbody tr:last-child"));
        List<WebElement> columns = lastRow.findElements(By.tagName("td"));
        Assert.assertEquals(columns.get(0).getText(), carNumber, "Car Number doesn't match!");
        Assert.assertEquals(columns.get(1).getText(), fuelLitres, "Fuel Litres doesn't match!");
        Assert.assertEquals(columns.get(2).getText(), fuelCost, "Fuel Cost doesn't match!");
        Assert.assertEquals(columns.get(3).getText(), fuelType, "Fuel Type doesn't match!");
        Assert.assertEquals(columns.get(5).getText(), companyId, "Company ID doesn't match!");
    }
}

