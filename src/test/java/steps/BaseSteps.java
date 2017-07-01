package steps;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.MainPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class BaseSteps {
    private MainPage mainPage;
    private WebDriver driver;

    @Before
    public void initDriver () {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/win/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Given("^Site \"([^\"]*)\"$")
    public void getUrl(String URL)  {
        driver.get("http://" + URL);
        mainPage = new MainPage(driver);
    }

    @When("^I search \"([^\"]*)\"$")
    public void searchItem(String itemName) {
        mainPage.setItemForSearch(itemName);
    }

    @Then("^Search results should contain \"([^\"]*)\"$")
    public void checkResults(String resultName) {
        Assert.assertTrue(mainPage.checkResultsContain(resultName));

    }

    @When("^I Sort results DESC$")
    public void SearchSortedResultsDESC() {
        mainPage.sortResultsDESC();
    }

    @Then("^Results have to be sorted$")
    public void resultsHaveToBeSorted() {
        List<Double> values = mainPage.getPriceList();
        List<Double> actual = new ArrayList(values);
        Collections.sort(values, Collections.reverseOrder());
        assertThat(actual, equalTo(values));
    }

    @And("^Product \"([^\"]*)\" topic contains \"([^\"]*)\"$")
    public void productTopicContains(int rowNumber, String expectedTopic) throws Throwable {
        // row numbers start from 0, check that one contains expected keyword
        String itemTopic = mainPage.getRowDetails(rowNumber);
        Assert.assertTrue(itemTopic.contains(expectedTopic.toUpperCase()));
    }

    @After
    public void closeDriver() {
        driver.quit();
    }
}
