package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class MainPage {

    WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void setItemForSearch(String itemName){
        WebElement searchElement = driver.findElement(By.id("twotabsearchtextbox")) ;
        searchElement.sendKeys(itemName);
        searchElement.submit();
    }

    public void sortResultsDESC() {
        Select dropdown = new Select(driver.findElement(By.id("sort")));
        dropdown.selectByValue("price-desc-rank");
    }

    public String getRowDetails(int rowNumber) {
        String rowDetails = driver.findElement(By.xpath("//*[@id=\"result_"+ rowNumber + "\"]/div/div/div/div[2]/div[1]/div[1]/a/h2")).getText();
        return rowDetails.toUpperCase();
    }

    // check the elements contain keyWord
    public boolean checkResultsContain(String keyWord) {
        List<WebElement> myElements = driver.findElements(By.xpath("//*[starts-with(@id, \"result_\")]/div/div/div/div[2]/div[1]/div[1]/a/h2"));
        for(WebElement el:myElements) {
            if (!el.getText().toUpperCase().contains(keyWord.toUpperCase()))
                return false;
        }
        return true;
    }

    // get price list
     public List<Double> getPriceList() {

         String wholePriceFormat="//*[@class=\"sx-price-whole\"]";
         String fractionalPriceFormat="//*[@class=\"sx-price-fractional\"]";
         //String ComplexPriceFormat="/div/div/div/div[2]/div[2]/div[1]/div/div/a/span[2]";

         List<Double> values = new ArrayList();
         List<WebElement> myElements = driver.findElements(By.xpath("//*[starts-with(@id, \"result_\")]"));

         for(int i=0;i < myElements.size();i++) {
             try {
                 String whole = driver.findElement(By.xpath("//*[@id = \"result_" + i +"\"]" + wholePriceFormat)).getText().replace(",","").toString();
                 String fractional = driver.findElement(By.xpath("//*[@id = \"result_" + i + "\"]" + fractionalPriceFormat)).getText().toString();
                 values.add(Double.valueOf(whole + "." + fractional));
             } catch (Exception e) // item's price does not consist of whole and fractional, skip it
             {
                     continue;
             }
         }
            return values;
    }
}
