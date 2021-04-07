package selenium.practice.excel;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

// import org.apache.poi.hssf.usermodel.HSSFSheet;
// import org.apache.poi.hssf.usermodel.HSSFWorkbook;
// import org.openqa.selenium.firefox.FirefoxDriver;

public class SelenSheetLoginTest {

    String url;
    WebDriver driver;

    @Before
    public void setUp() throws Exception
    {
        url = "https://phptravels.com/demo/";
        System.setProperty("webdriver.chrome.driver", "/home/prashant_pk/IdeaProjects/Selevium_Test/driver/chromedriver");
        driver = new ChromeDriver();

//        System.setProperty("webdriver.gecko.driver",driverPath+"geckodriver.exe");
//        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.get(url);
        driver.manage().window().maximize();
//        System.out.println(driver.getTitle());
    }

    @Test
    public void linkTest() throws Exception
    {
        String expPageTitle = "Demo Script Test drive - PHPTRAVELS";
        String actPageTitle = driver.getTitle();
        Assert.assertEquals(actPageTitle, expPageTitle);

        Thread.sleep(1000);
        driver.findElement(By.linkText("Login")).click();
        Thread.sleep(1000);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Set<String> windHand = driver.getWindowHandles();
        Iterator<String> itr = windHand.iterator();
        String homePage = itr.next();
        String logPage = itr.next();
        driver.switchTo().window(logPage);

        String projectPath = System.getProperty("user.dir");
        String filePath = "/home/prashant_pk/IdeaProjects/SelExcelTest/Excel/Book1.xlsx";
        FileInputStream fileStream = new FileInputStream(filePath);
//        System.out.println("Project Path: " +projectPath);

        XSSFWorkbook workBook = new XSSFWorkbook(fileStream);
        XSSFSheet wbSheet = workBook.getSheet("Sheet2");

//        HSSFWorkbook workBook = new HSSFWorkbook(fileStream);
//        HSSFSheet wbSheet = workBook.getSheet("Sheet2");

        int rowCount = wbSheet.getPhysicalNumberOfRows();
        System.out.println("Count of Rows: " +rowCount);

        for(int i = 1; i < rowCount; i++)
        {
            String username = wbSheet.getRow(i).getCell(0).getStringCellValue();
            String password = wbSheet.getRow(i).getCell(1).getStringCellValue();

            driver.findElement(By.id("inputEmail")).sendKeys(username);
            Thread.sleep(1000);
            driver.findElement(By.id("inputPassword")).sendKeys(password);
            Thread.sleep(1000);

            WebElement checkBox = driver.findElement(By.cssSelector("#main-body > div > div > div.col-xs-12.main-content > div > div.row > div.col-sm-12 > form > div.checkbox > label > input[type=checkbox]"));
            checkBox.click();
            Thread.sleep(1000);

            driver.findElement(By.id("login")).click();
            Thread.sleep(1000);

            String titlePage = driver.getTitle();
            // Getting the current row in the sheet where we want to write the data!
            Row newRow = wbSheet.getRow(i);

            // Creating a new cell with reference to the row!
            Cell cell = newRow.createCell(2);
            // Giving the value in the cell!
            cell.setCellValue(titlePage);

            // Typecasting the driver reference variable with TakesScreenshot!
            // Typecasting will allow the methods from TakesScreenshot interface to access the file!
            // getScreenshotAs() will take argument for the output type of the file!
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

            // Using the FileUtils class, we can copy the generated screenshot file to any location!
            FileUtils.copyFile(scrFile, new File(projectPath+ "/Screenshots/SelSheetLogin/Screenshot_" +i));

            List<WebElement> elementCheck = driver.findElements(By.linkText("Logout"));
            if(elementCheck.size() != 0)
            {
                System.out.println("Element is present!");
                driver.findElement(By.linkText("Logout")).click();
                Thread.sleep(2000);
                driver.findElement(By.linkText("Click here to continue..."));
                Thread.sleep(2000);
            }
            else
            {
                System.out.println("Element is present!");
                driver.get("https://phptravels.org/clientarea.php");
            }
        }

        //Creating an output stream with the location where we want to create the file!
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);

        //Writing the workbook!
        workBook.write(fileOutputStream);

        //Closing the workbook!
        workBook.close();

        /*
        // Selecting the Radio buttons by Name
        List RadioButton = driver.findElements(By.name("name_value"));

        // Finding the number of Radio buttons
        int Size = RadioButton.size();

        // Starting the loop from first Radio button to the last one
        for(int i=0; i < Size; i++)
        {
        String val = RadioButton.get(i).getAttribute("value");
        // Radio button name stored to the string variable, using 'Value' attribute

        if (val.equalsIgnoreCase("Green"))   // equalsIgnoreCase ignores case(upper/lower)
        {
            // selecting the Radio button if its value is same as that we are looking for
            RadioButton.get(i).click();
            break;
        }
        */


        /*
        // Fetch the number of opened windows
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println("Number of opened windows: " + windowHandles.size());

		Integer temp=0;

		String msg=null;

		//Iterate through all the available windows
		for (String string : windowHandles) {
			//Switch between windows using the string reference variable
			driver.switchTo().window(string);

			//Fetch the url of the page post successful switch
			String title = driver.getTitle();

			//check whether the url post switch is the desired page
			if (!title.equals("Teller Home")) {
				temp = 1;
				msg="Window found.";
				break;
			} else {
				temp = 0;
			}
		}

		if (temp == 1) {

			System.out.println(msg);

			//Find some element from the switched page to verify that the switch is successful
			WebElement Bankname = driver.findElement(By.xpath("/html/body/div/span[1]"));
			System.out.println(Bankname.getText()+" Bank");
		}
		else if (temp == 0) {
			System.out.println("Desired Window not found.");
		}
         */

        /*
        //Click on Offers Link
		driver.findElement(By.linkText("Offers")).click();

		//Fetch the Account details table
		WebElement tblAccountDetails = driver.findElement(By.id("offersTable"));

		//Fetch all the rows inside the Account details table using the tr tag and store it in rows list
		List<WebElement> rows = tblAccountDetails.findElements(By.tagName("tr"));

		//Print the number of rows
		System.out.println("Number of rows: " + rows.size());

		//Iterate over all the rows
		for (WebElement row : rows) {
			//Find all the cols inside the particular row using the td tag
			List<WebElement> cols = row.findElements(By.tagName("td"));

			//Iterate over all the columns inside the particular row
			for (WebElement col : cols) {
				//Print the cell value
				System.out.print("Column value: " + col.getText());
			}
		}
         */

    }

    @After
    public void tearDown() throws Exception
    {
        driver.quit();
    }
}