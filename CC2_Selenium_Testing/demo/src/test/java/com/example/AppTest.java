package com.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    WebDriver driver;
    public XSSFWorkbook workbook;
    public String toSearch;
    XSSFSheet sheet;
    FileInputStream file;
    public ExtentTest test1,test2,test3;
    public ExtentReports extentReports;




    @BeforeTest
    public void BeforeTest()throws Exception
    {
        driver=new ChromeDriver();
        extentReports = new ExtentReports();
        file=new FileInputStream("C:\\Users\\ragav\\OneDrive\\Desktop\\CC2_Selenium_Testing\\sample.xlsx");
        workbook=new XSSFWorkbook(file);
        sheet=workbook.getSheet("Sheet1");
    }
    @Test
    public void shouldAnswerWithTrue()throws FileNotFoundException,InterruptedException
    {
        driver.get("https://www.barnesandnoble.com/");
        Thread.sleep(8000);
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[1]/a")).click();
        Thread.sleep(8000);
        driver.findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/div[1]/div/a[2]")).click();
        Thread.sleep(5000);
        toSearch=sheet.getRow(1).getCell(0).getStringCellValue();
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[2]/div/input[1]")).sendKeys(toSearch);;
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/span/button")).click();
        Thread.sleep(5000);
        String searchResult = driver.findElement(By.xpath("//*[@id='searchGrid']/div/section[1]/section[1]/div/div[1]/div[1]/h1/span")).getText();

        System.out.println(searchResult);
        test1 = extentReports.createTest(searchResult);
        if(searchResult.equals(toSearch))
        {
            test1.log(Status.PASS, "Search Results contain the keyword Chetan Bhagat");
        }
        else
        {
            test1.log(Status.FAIL, "Search Results does not contain the keyword Chetan Bhagat");

        }
    }
}