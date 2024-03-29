package appmanager;


import APPDAO.MaintenanceDAO;
import io.qameta.allure.Allure;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.*;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static APPPages..;
import static appmanager.ApplicationManager.*;
import static appmanager.ExtentCucumberFormatter.*;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.Keys.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


public class HelperBase {
    public static Connection con;
    public static Statement stmt;
    public static ResultSet resultSet;
    public static WebDriver wd;
    public static WebDriver driver;
    public static Properties obj = new Properties();

    public static boolean screenShotSwitch = false;

    public static String loggedInUser = "";
//    public String btnHelp;
    public String path;
    public String uploadingfilename;
    public String pathMultiple;
    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }



//    @Value("${weToasterMessage}")
//    public String weToasterMessage;


    @Value("${btnHelp}")
    public String btnHelp;

//    public HelperBase(WebDriver wd) {
//        this.wd = wd;
//    }


    public HelperBase() {
    }


    public boolean checkLogInUser(String user) {
        ApplicationManager app = null;
        if (reader.get("browser.type").toLowerCase().contains("chrome")) {
            app = new ApplicationManager((System.getProperty("browser", BrowserType.CHROME)));
        } else if (reader.get("browser.type").toLowerCase().contains("edge")) {
            app = new ApplicationManager((System.getProperty("browser", BrowserType.EDGE)));
        } else {
            app = new ApplicationManager((System.getProperty("browser", BrowserType.IE)));
        }
        boolean blnLogIn = false;
        WebDriver wd = ApplicationManager.driver;
        if (wd != null) {
            if (isElementPresent(By.xpath("//div[contains(@class,'toolbar-user')]"))) {
                WebElement we = wd.findElement(By.xpath("//div[contains(@class,'toolbar-user')]"));
                String userLoggged = getText(we);
                if (user.contains(userLoggged)) {
                    testStepPassed("User is already logged into the ETS application, Username is -->" + user);
                    blnLogIn = true;
                }
            }
            if (!blnLogIn) {
                stop();
                try {
                   app.initUrl(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                blnLogIn = true;
                app.initUrl(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return blnLogIn;

    }


    public boolean checkLogInUserToOKTA(String user) {
        sleep(2000);
        ApplicationManager app = null;
        if (reader.get("browser.type").toLowerCase().contains("chrome")) {
            app = new ApplicationManager((System.getProperty("browser", BrowserType.CHROME)));
        } else if (reader.get("browser.type").toLowerCase().contains("edge")) {
            app = new ApplicationManager((System.getProperty("browser", BrowserType.EDGE)));
        } else {
            app = new ApplicationManager((System.getProperty("browser", BrowserType.IE)));
        }
        boolean blnLogIn = false;
        WebDriver wd = ApplicationManager.driver;
        if (wd != null) {
            if (isElementPresent(By.xpath("//div[contains(@class,'toolbar-user')]"))) {
                WebElement we = wd.findElement(By.xpath("//div[contains(@class,'toolbar-user')]"));
                String userLoggged = getText(we);
                if (user.contains(userLoggged)) {
                    testStepPassed("User is already logged into the ETS application, Username is -->" + user);
                    blnLogIn = true;
                }
            }
            if (!blnLogIn) {
                stop();
                try {
                    app.initUrlOKTA(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                blnLogIn = true;
                app.initUrlOKTA(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return blnLogIn;

    }




    public boolean checkLogInvalidUserToOKTA(String user) {
        ApplicationManager app = null;
        if (reader.get("browser.type").toLowerCase().contains("chrome")) {
            app = new ApplicationManager((System.getProperty("browser", BrowserType.CHROME)));
        } else if (reader.get("browser.type").toLowerCase().contains("edge")) {
            app = new ApplicationManager((System.getProperty("browser", BrowserType.EDGE)));
        } else {
            app = new ApplicationManager((System.getProperty("browser", BrowserType.IE)));
        }
        boolean blnLogIn = false;
        WebDriver wd = ApplicationManager.driver;
        if (wd != null) {
            if (isElementPresent(By.xpath("//div[contains(@class,'toolbar-user')]"))) {
                WebElement we = wd.findElement(By.xpath("//div[contains(@class,'toolbar-user')]"));
                String userLoggged = getText(we);
                if (user.contains(userLoggged)) {
                    testStepPassed("User is already logged into the ETS application, Username is -->" + user);
                    blnLogIn = true;
                }
            }
            if (!blnLogIn) {
                stop();
                try {
                    app.initUrlOKTAInvalid(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                blnLogIn = true;
                app.initUrlOKTAInvalid(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return blnLogIn;

    }
    public boolean checkLogInActiveMQ( ) {
        ApplicationManager app = null;
        if (reader.get("browser.type").toLowerCase().contains("chrome")) {
            app = new ApplicationManager((System.getProperty("browser", BrowserType.CHROME)));
        } else if (reader.get("browser.type").toLowerCase().contains("edge")) {
            app = new ApplicationManager((System.getProperty("browser", BrowserType.EDGE)));
        } else {
            app = new ApplicationManager((System.getProperty("browser", BrowserType.IE)));
        }
        boolean blnLogIn = false;
        WebDriver wd = ApplicationManager.driver;
        if (wd != null) {
            if (isElementPresent(By.xpath("//div[contains(@class,'toolbar-user')]"))) {
                WebElement we = wd.findElement(By.xpath("//div[contains(@class,'toolbar-user')]"));
                String userLoggged = getText(we);
//                if (user.contains(userLoggged)) {
//                    testStepPassed("User is already logged into the ETS application, Username is -->" );
//                    blnLogIn = true;
//                }
            }
            if (!blnLogIn) {
                stop();
                try {
                    app.initUrlActiveMQ();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                blnLogIn = true;
                app.initUrlActiveMQ();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return blnLogIn;

    }




    public boolean isElementPresent(WebElement we) {
        try {
            return we.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public boolean isElementPresent(By by) {
        try {
            return getWebDriver().findElement(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean waitTillLoadingCompleted() {
        if (isElementPresent(By.xpath("//*[contains(@class,'spinner')]"))) {
            return elementToBeInvisible(By.xpath("//*[contains(@class,'spinner')]"));
        } else {
            return true;
        }
    }

    public void jsScrolllefttoright(WebElement by) {
        try {
            for (int i = 0; i <=1; i++) {
                JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
                js.executeScript("arguments[0].scrollBy(-300,0)", by);

            }
        } catch(Exception e){
            testStepFailed("Exception occured in scrollToElement() " + e.getMessage());
        }
    }

    public String getText(WebElement we) {
        try {
            if (isElementPresent(we)) {
                return we.getText();

            }else {
                return "";
            }
        } catch (Exception ex) {
            testStepFailed("Unable to get the text element, message is->" + ex.getMessage());
            return "";
        }
    }


    public void validateColumnNamesinTable(WebElement table, List<String> coloumNames) {
        try {
            boolean blnPass = true;
//            List<WebElement> weColumnNames = table.findElements(By.xpath(".//thead/tr/th/span[1]//following::span[2]"));
//            if (weColumnNames.size() == coloumNames.size()) {
            List<WebElement> weColumnNames = table.findElements(By.xpath(".//thead/tr/th/span[1]//following::span[2]"));
            if ((weColumnNames.size()-1) == coloumNames.size()) {
                //int i=0;
                for (int i = 0; i < coloumNames.size(); i++) {
                    //for(WebElement we: weColumnNames){
//                    weColumnNames = table.findElements(By.xpath(".//thead/tr/th/span[1]"));
                    WebElement we = weColumnNames.get(i);
                    if (getText(we).contains(coloumNames.get(i))) {
                        testStepInfo("Column Name : " + coloumNames.get(i) + " is displayed as expected in Column Number " + (i + 1));
                    } else {
                        blnPass = false;
                        testStepFailed("Column Name : " + coloumNames.get(i) + " is not displayed in Column Number " + (i + 1));
                    }
                }
                if (blnPass) {
                    testStepPassed("All the required columns are displayed in table as expected");
                }
            } else {
                testStepFailed("All the required columns are not displayed in table, Expected Columns is " + coloumNames.size() + ", But actual is ->" + weColumnNames.size());
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while validating the generic elements in page, Message is->" + ex.getMessage());
        }
    }

    public static void waitFor(String object) {
        try {
            By by = By.xpath(object);
            WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception ex) {
//do nothing
        }
    }

    public static void waitForAlertDismiss() {
        for (int i = 0; i < 10; i++) {
            try {
                WebDriver wd = getWebDriver();
                wd.switchTo().defaultContent();
                break;
            } catch (UnhandledAlertException ex) {
                sleep(500);
            }
        }
    }



    public static void waitFor(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(wd, 60);
            wait.until(presenceOfElementLocated(by));
        } catch (Exception ex) {
            //do nothing
        }
    }

    public void clickOnTillDisapears(String obj, String elemName){
        try {
            sleep(1000);
            while (isElementPresent(obj)) {
                jsClick(obj,"Update Button");
                sleep(2000);
            }
            testStepInfo("Clicked on Element-" + elemName);
        } catch (Exception ex) {
            testStepFailed("Unable to Click on Element- " + elemName);
        }
    }



    public void clickOn(String obj, String elemName) {
        try {
            sleep(1000);
            WebElement we = getWebElement(obj);
            jsMoveToElement(we);
            waitFor(obj);
            we.click();
            testStepInfo("Clicked on Element-" + elemName);
        } catch (Exception ex) {
            testStepFailed("Unable to Click on Element- " + elemName);
        }
    }


    public static void type(String object, String data) {
        By locator = xpath(obj.getProperty(object));
        if (data != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!data.equals(existingText)) {
                wd.findElement(locator).click();
                wd.findElement(locator).clear();
                wd.findElement(locator).click();
                wd.findElement(locator).sendKeys(data);
            }
        }
    }


    public static void type(By by, String data) {

        try {
            WebElement elem = wd.findElement(by);
            String existingText = elem.getAttribute("value");
            if (!data.equals(existingText)) {
                elem.clear();
                elem.sendKeys(data);
            }
        } catch (Exception e) {
            String method = new Exception().getStackTrace()[0].getMethodName();
            testStepFailed("Exception Caught in method " + method + ", and Error Message is->" + e.getMessage());
        }
    }

    public static void typeValue(String object, String data) {
        By locator = xpath(obj.getProperty(object));
        if (data != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!data.equals(existingText)) {
                wd.findElement(locator).click();
                wd.findElement(locator).clear();
                wd.findElement(locator).click();
                wd.findElement(locator).sendKeys(BACK_SPACE);
                wd.findElement(locator).sendKeys(DELETE);
                wd.findElement(locator).sendKeys(data);
                wd.findElement(locator).sendKeys(TAB);
            }
        }
    }

    public static void typeText(String object, String data) {
        try {
            By locator = xpath(obj.getProperty(object));
            if (data != null) {
                String existingText = wd.findElement(locator).getAttribute("value");
                if (!data.equals(existingText)) {
                    wd.findElement(locator).click();
                    sleep(2000);
                    wd.findElement(locator).sendKeys(END);
                    wd.findElement(locator).sendKeys(CONTROL + "a");
                    wd.findElement(locator).sendKeys(DELETE);
                    wd.findElement(locator).sendKeys(data);
                }
            }
        } catch (Exception e) {
            String method = new Exception().getStackTrace()[0].getMethodName();
            testStepFailed("Exception Caught in method " + method + ", and Error Message is->" + e.getMessage());
        }
    }

    public static void checkSpecialChracter(String object, String data) {
        try {
            By locator = xpath(obj.getProperty(object));

            wd.findElement(locator).click();
            sleep(2000);
            wd.findElement(locator).sendKeys(END);
            wd.findElement(locator).sendKeys(CONTROL + "a");
            wd.findElement(locator).sendKeys(DELETE);
            wd.findElement(locator).sendKeys(data);

            String existingText = wd.findElement(locator).getAttribute("value");
            if (existingText.equals("")) {
                testStepPassed("Special Chracter and alphabet is not allowed as expected " + existingText);

            } else {
                testStepFailed("Special Chracter and alphabet is  allowed  " + existingText);

            }
        } catch (Exception e) {
            String method = new Exception().getStackTrace()[0].getMethodName();
            testStepFailed("Exception Caught in method " + method + ", and Error Message is->" + e.getMessage());
        }
    }

    public String validateErrorMessageForField(WebElement elem, String errorMessage) {
        try {
            WebElement we = elem.findElement(By.xpath("./following-sibling::div/div[1]"));
            return we.getText();
        } catch (Exception ex) {
            return "";
        }
    }
    public void selectFromAngularDropDown(String object, String strVisibleText) {
        try {

            WebElement we = getWebElement(object);
            clickOn(object,"Dropdown");
            clear(object);
            sleep(2000);
            WebElement we1=getWebElement(By.xpath("//mat-option//span[text()=' "+strVisibleText+" ']"));
            jsMoveToElement(we1);
            clickOn(we1,"Value");
            testStepPassed("Selected the value->" +  " from the dropdown " + strVisibleText);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

    }
    public void selectFromAngularDropDownForMember(String object, String strVisibleText) {
        try {

            WebElement we = getWebElement(object);
            clickOn(object,"Dropdown");
            clear(object);
            sleep(2000);
            WebElement we1=getWebElement(By.xpath("//mat-option//span[contains(text(),  ' "+strVisibleText+" ')]"));
            jsMoveToElement(we1);
            clickOn(we1,"Value");
            testStepPassed("Selected the value->" +  " from the dropdown " + strVisibleText);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

    }
    public void selectFromAngularDropDownForSaveFilter(String object, String strVisibleText) {
        try {

            WebElement we = getWebElement(object);
            jsClick(object,"Dropdown");
            jsclear(object,strVisibleText);
            sleep(2000);
            WebElement we1=getWebElement(By.xpath("//mat-option//span[text()='"+strVisibleText+"']"));
            jsMoveToElement(we1);
            jsClick(we1,"Value");
            testStepPassed("Selected the value->" +  " from the dropdown " + strVisibleText);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void isElementNotPresentInDropdown(String obj, String ElementName) {
        try {
            sleep(1000);
            if(!isElementPresent(obj)){
                testStepPassed(ElementName + " option is not present as expected");
            }else{
                testStepFailed(ElementName  + " option is present");
            }
        } catch (Exception ex) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), ex);
        }
    }

    public void selectFromDropdown(String object, String strVisibleText, String strElementName) {
        try {
            sleep(1000);
            WebElement element = getWebElement(object);
            Select sel = new Select(element);
            sel.selectByVisibleText(strVisibleText);
            testStepInfo("Selected the value->" + strVisibleText + " from the dropdown " + strElementName);

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public static String text(String object) {
        String text = "";
        try {
            text = wd.findElement(xpath(obj.getProperty(object))).getText();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return text;
    }


    public String gettext(String object) {
        String text = "";
        try {
            sleep(1000);
            text = getWebElement(object).getAttribute("value");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return text;
    }



    public String getTextValue(String object) {
        String text = "";
        try {
            text = getWebElement(object).getText();
        } catch (Exception e) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), e);
        }
        return text;
    }

    public static boolean waitForElementNotPresent(String object) {
        try {
            wd.findElement(xpath(obj.getProperty(object)));
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }


    public boolean isElementPresent(String obj) {
        try {
            return getWebElement(obj).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public void isElementNotPresent(String obj, String ElementName) {
        try {
            sleep(3000);
            clickOn(weEllipse,"Ellipse");
            if(!isElementPresent(obj)){
                testStepPassed(ElementName + " option is not present as expected");
            }else{
                testStepFailed(ElementName  + " option is present");
            }
        } catch (Exception ex) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), ex);
        }
    }

    public boolean isEnabled(String object) {
        try {
            WebElement we = getWebElement(object);

            we.isEnabled();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void selectFromDropdownInTs(String object, String strElementName, String strVisibleText) {
        try {
            WebElement element = wd.findElement(xpath(obj.getProperty(object)));
            Select sel = new Select(element);
            sel.selectByVisibleText(strVisibleText);
            testStepInfo("Selected the value->" + strVisibleText + " from the dropdown " + strElementName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void selectRadioButton(String object) {
        try {
            WebElement we = getWebElement(object);
            sleep(2000);
            Boolean Status=we.isSelected();
            if (Status == true) {
                we.click();
                testStepPassed("Sucessfully uncheck the Radio button");
            }else{
                we.click();
                testStepPassed("Sucessfully check the Radio button");
            }
        } catch (Exception e) {
            String method = new Exception().getStackTrace()[0].getMethodName();
            testStepFailed("Exception Caught in method " + method + ", and Error Message is->" + e.getMessage());
        }
    }


    public void jsEnterText(String object, String text, String elemName) {
        try {
            sleep(2000);
            WebElement we = getWebElement(object);
            jsclear(object,elemName);
            sleep(1000);
            JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
            jsMoveToElement(we);
            js.executeScript("arguments[0].value='" + text + "';", we);
        } catch (Exception ex) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), ex);
        }
    }

    public void jsEnterText(WebElement object, String text, String elemName) {
        try {
            sleep(2000);
            JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
            js.executeScript("arguments[0].value='" + text + "';", object);
            sleep(2000);
        } catch (Exception ex) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), ex);
        }
    }

    public static String selectcurrentDate() {
        String CurrentDate = "";
        try {
            SimpleDateFormat currentDate = new SimpleDateFormat("MM/dd/yyyy");
            Date date = new Date();
            CurrentDate = currentDate.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return CurrentDate;

    }



    public void clear(String object) {
        try {
            WebElement we = getWebElement(object);
            we.sendKeys("t");
            we.sendKeys(END);
            we.sendKeys(CONTROL + "a");
            we.sendKeys(DELETE);
        } catch (Exception e) {
            String method = new Exception().getStackTrace()[0].getMethodName();
            testStepFailed("Exception Caught in method " + method + ", and Error Message is->" + e.getMessage());
        }
    }

    public static String getMessage() {
        String ErrMsg = wd.findElement(xpath("//ul[@class = 'message']/li[contains(@class,'msg')]")).getText();
        return ErrMsg;
    }

    public void JsClickByVisibleText(String elementName) {
        try {
            WebElement elem = getWebElementByPartialText(elementName);
            JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
            js.executeScript("arguments[0].click();", elem);
            testStepInfo("Successfully clicked on element -> " + elementName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void JsClick(By by, String elementName) {
        WebElement elem = wd.findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("arguments[0].click();", elem);
        testStepInfo("Successfully clicked on element->" + elementName);
    }

    public String getRandomAlphabetString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public String getRandomAlphaNumbericString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public String getRandomNumbericString(int length) {
        return RandomStringUtils.randomNumeric(length);
    }


    public static void sleep(int miliSec) {
        try {
            Thread.sleep(miliSec);
        } catch (InterruptedException e) {
            // do nothing
        }
    }

    public String getToastMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(wd, 300);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//mat-menu[contains(@class,'ng-tns-c91')]")));
            String message = getText(getWebElement(By.xpath("//mat-menu[contains(@class,'ng-tns-c91')]")));
            return message;
        } catch (NoSuchElementException e) {
            return "";
        }
    }
//check this one
    public void enterTextAndSelect(String textObject, String elemName, String partialText, String fullText) {
        try {
            enterText(getWebElement(textObject),partialText,elemName);
            WebElement we = getWebElement(By.xpath("//mat-option/span[contains(text(),'" + fullText + "')]"));
            clickOn(we, fullText);
            testStepPassed("Successfully Selected the option "+fullText);
        } catch (NoSuchElementException e) {
            testStepFailed("Exception Caught in method and Error Message is->" + e.getMessage());
        }
    }

    public void scroll(String object) {
        WebElement element = getWebElement(object);
        ((JavascriptExecutor) ApplicationManager.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        sleep(2000);
    }
    public void scroll(WebElement object) {
//        WebElement element = getWebElement(object);
        ((JavascriptExecutor) ApplicationManager.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", object);
        sleep(2000);
    }

    public void validateFieldsErrorMessage(String object, String element, String errormessage) {
        try {
            WebElement locator = getWebElement(object);
            locator.click();
            sleep(2000);
            locator.sendKeys(END);
            locator.sendKeys(CONTROL + "a");
            locator.sendKeys(DELETE);
            locator.sendKeys(HOME);
            locator.sendKeys(element);
            locator.sendKeys(TAB);
            sleep(2000);
            String appMesssage = getWebElement(By.xpath("//div[contains(text(),'" + errormessage + "')]")).getText();
            if (appMesssage.trim().contains(errormessage)) {
                testStepPassed("Application error message is displayed as expected-> " + errormessage);
            } else {
                testStepFailed("Application Error Message-> " + appMesssage + " is not as expected Message -> " + errormessage);
            }
        } catch (Exception e) {
            String method = new Exception().getStackTrace()[0].getMethodName();
            testStepFailed("Exception Caught in method " + method + ", and Error Message is->" + e.getMessage());
        }
    }

    public Boolean validateToastMessage(String expMessage) {
        try {
            sleep(4000);
            String infoMessage = getToastMessage();
            if (!infoMessage.equals("")) {
                if (infoMessage.toLowerCase().contains(expMessage.toLowerCase())) {
                    testStepInfo("Toaster message is displayed as expected -> " + expMessage);
                    takeScreenshot();
                    return true;
                } else {
                    testStepFailed("Toaster message displayed is not valid , Expected is->" + expMessage + "  but actual is-> " + infoMessage);
                    return false;
                }
            } else {
                testStepFailed("Toaster message is not displayed");
                return false;
            }
        } catch (Exception ex) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), ex);
            return false;
        }
    }

    public Boolean validateErrorToasterMessage(String expMessage) {
        try {
            String message = getText(getWebElement(weToastMessage));
            if (!message.equals("")) {
                if (message.toLowerCase().contains(expMessage.toLowerCase())) {
                    testStepInfo("Toaster message is displayed as expected -> " + expMessage);
                    takeScreenshot();
                    return true;
                } else {
                    testStepFailed("Toaster message displayed is not valid , Expected is->" + expMessage + "  but actual is-> " + message);
                    return false;
                }
            } else {
                testStepFailed("Toaster message is not displayed");
                return false;
            }
        } catch (Exception ex) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), ex);
            return false;
        }
    }

    public Boolean validateInfoMessage(String expMessage) {
        String infoMessage = getToastMessage();
        if (infoMessage.toLowerCase().contains(expMessage.toLowerCase())) {
            testStepInfo("Successfully validated the message-> " + expMessage);
            takeScreenshot();
            return true;
        } else {
            testStepFailed("Expected Message->" + expMessage + " is not displayed,instead " + infoMessage + " is displayed");
            return false;
        }
    }


    public boolean validateSearchResults() {
        if (isElementPresent("table")) {
           // List<WebElement> row = wd.findElements(xpath("//div/table[contains(@class,'table')]//tbody/tr"));
            List<WebElement> row = getWebElements(xpath("//div/table[contains(@class,'table')]//tbody/tr"));
            if ((row.size() > 0))
                return true;
            else
                return false;
        } else
            return false;
    }

    public void validateRecordsforShowDropDown(String value) {
        int Value = Integer.parseInt(value);
//        if (isElementPresent("table")) {
//            List<WebElement> row = wd.findElements(xpath("//div/table[contains(@class,'table')]//tbody/tr"));
        if (isElementPresent("//table")) {
                List<WebElement> row = getWebElements(By.xpath("//div/table[contains(@class,'table')]//tbody/tr"));
            int rowSize = row.size();
            if (rowSize == Value) {
                testStepPassed("Sucessfully validated for " + value + " records to be displayed in the page");
                takeScreenshot();
            }
        } else
            testStepFailed("No records found");
    }


    public static boolean fileExists(String fileName) {
        for (int i = 0; i < 30; i++) {
            File file = new File(fileName);
            if (file.exists() && file.isFile()) {
                return true;
            } else {
                sleep(3000);
            }
        }
        return false;
    }

    public void clickOnParticularRowofTable(String value) {
        boolean record = validateSearchResults();
        if (record) {
            if (isElementPresent("table")) {
                WebElement Mnemonic = wd.findElement(xpath("//table/tbody/tr/td[text()='" + value + "']/parent::tr/td[2]"));
                String custNo = Mnemonic.getText();
                JsClick(xpath("//table/tbody/tr/td[text()='" + custNo + "']"), "record");
                testStepPassed("Successfully clicked on the record");
                takeScreenshot();
                sleep(2000);
            } else {
                testStepFailed("Failed to click on the record");
            }
        } else {
            testStepFailed("No records are found with the search filter data");
        }
    }

    public void selectRandomRowFromTable() {
        try {
            sleep(2000);
            List<WebElement> lst = getWebElements(By.xpath("//table[contains(@class,table)]//tbody/tr"));
            int table_size = lst.size();
            if (table_size >= 1) {
                Random number = new Random();
                int row_Number = number.nextInt(table_size) + 1;
                WebElement we = getWebElement(By.xpath("//table[contains(@class,table)]//tbody/tr[" + row_Number + "]"));
                JsClick(we, "element");

            } else {
                //Check for no data message
            }
        } catch (Exception e) {
            String method = new Exception().getStackTrace()[0].getMethodName();
            testStepFailed("Exception Caught in method " + method + ", and Error Message is->" + e.getMessage());
        }
    }


    public void validateErrormessage(String object, String expMessage) {
        try {
            waitFor(object);
            String message = getWebElement(object).getText();
            if (message.toLowerCase().contains(expMessage.toLowerCase())) {
                testStepPassed("Expected message is Displayed ->" + message);
            } else {
                testStepFailed("Expected Message->" + expMessage + " is not displayed,instead " + message + " is displayed");

            }
        } catch (Exception e) {
            String method = new Exception().getStackTrace()[0].getMethodName();
            testStepFailed("Exception Caught in method " + method + ", and Error Message is->" + e.getMessage());
        }

    }
    public  void invaliduser(String user) {
        enterText(invalidusertext, user,"username");
        enterText(invalidpasstext, "1325546554","password");
        JsClick(invalidLogin, "login");

    }


    public void validateErrormessage(String expMessage) {
        try {

            WebElement ele = getWebElementByPartialText(expMessage);
            String message=ele.getText();
            if (message.toLowerCase().contains(expMessage.toLowerCase())) {
                testStepPassed("Expected message is Displayed ->" + message);
            } else {
                testStepFailed("Expected Message->" + expMessage + " is not displayed,instead " + message + " is displayed");

            }
        } catch (Exception e) {
            String method = new Exception().getStackTrace()[0].getMethodName();
            testStepFailed("Exception Caught in method " + method + ", and Error Message is->" + e.getMessage());
        }

    }

    public boolean verifyAmountPledgeRecords() {
        List<WebElement> recordsCount = wd.findElements(xpath("//table[@id='IncreasePledgeTable']//tr"));
        int size = recordsCount.size();
        if (size > 0) {
            return true;
        } else
            return false;
    }


    public void VerifyPaginationFunctionality(String tableName) {
        try {
            sleep(2000);
            if (getFirstRecordCount() != 0) {
                if (isElementPresent(weRecordCount)) {
                    int last = getLastRecordCount();
                    int total = getTotalRecordCount();
                    if (isElementPresent(lnkNextPage)) {
                        JsClick(lnkNextPage, "Next Page Link");
                        sleep(2000);
                        int init = getFirstRecordCount();
                        if (init == last + 1) {
                            testStepPassed("Sucessfully Validated NextPage button for " + tableName);
                        } else {
                            testStepFailed("Failed To Validate NextPage button for" + tableName);
                        }
                    } else {
                        testStepFailed("Failed To Validate NextPage button for" + tableName);
                    }
                }

                if (isElementPresent(lnkPreviousPage)) {
                    JsClick(lnkPreviousPage, "Previous Page Link");
                    sleep(2000);
                    int init = getFirstRecordCount();
                    if (init == 1) {
                        testStepPassed("Sucessfully Validated Previous Page button for" + tableName);
                    } else {
                        testStepFailed("Failed To Validate Previous Page button for" + tableName);
                    }
                } else {
                    testStepFailed("Failed To Validate Previous Page  button for" + tableName);
                }

                if (isElementPresent(lnkLastPage)) {
                    JsClick(lnkLastPage, "Last Page Link");
                    sleep(2000);
                    int total = getTotalRecordCount();
                    int last = getLastRecordCount();
                    if (total == last) {
                        testStepPassed("Sucessfully Validated LastPage button for " + tableName);
                    } else {
                        testStepFailed("Failed To Validate LastPage button for" + tableName);
                    }
                } else {
                    testStepFailed("Failed To Validate LastPage button for " + tableName);
                }

                if (isElementPresent(lnkFirstPage)) {
                    JsClick(lnkFirstPage, "First Page Link");
                    sleep(2000);
                    int init = getFirstRecordCount();
                    if (init == 1) {
                        testStepPassed("Sucessfully Validated First Page button for" + tableName);
                    } else {
                        testStepFailed("Failed To Validate First Page button for " + tableName);
                    }
                } else {
                    testStepFailed("Failed To Validate First Page button for" + tableName);
                }
            } else {
                testStepInfo("unable to verify pagination Functionality as no records found for " + tableName);
                takeScreenshot();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // returns WebElement object please use instead of wd.findElement(By.locator) method  (created by Akbar 02.01.19)
    public WebElement getWebElement(String object) {
        return getWebElement(By.xpath(object));
    }


    // returns WebElement object please use instead of wd.findElement(By.locator) method  (created by Akbar 02.01.19)
    public List<WebElement> getWebElements(String object) {
        return getWebElements(By.xpath(object));
    }

    // returns WebElement object please use this method when you have multiple similar web objects with different values
    // Use when you going to get the web object by xpath containing text (created by Akbar 02.01.19)
    public WebElement getWebElementByPartialText(String objectValue) {
        return getWebElement(By.xpath("//*[contains(text(), '" + objectValue + "')]"));
    }


    public WebElement getWebElementByPartialText(String objectValue, String tagName) {
        return getWebElement(By.xpath("//" + tagName + "[contains(text(), '" + objectValue + "')]"));
    }

    // returns WebElement object please use this method when you have multiple similar web objects with different values
    // Use when you going to get the web object by xpath containing text (created by Akbar 02.01.19)
    public WebElement getWebElementByText(String objectValue) {
        return getWebElement(By.xpath("//*[text()='" + objectValue + "']"));
    }

    public WebElement getWebElementByText(String objectValue, String tagName) {
        return getWebElement(By.xpath("//" + tagName + "[text()='" + objectValue + "']"));
    }

//    public WebElement getWebElement(By locator) {
//
//        FluentWait<WebDriver> wait = new FluentWait<>(getWebDriver())
//                .withTimeout(java.time.Duration.ofSeconds(20))
//                .pollingEvery(java.time.Duration.ofSeconds(5))
//                .ignoring(NoSuchElementException.class)
//                .ignoring(StaleElementReferenceException.class)
//                .ignoring(ElementClickInterceptedException.class)
//                .ignoring(ElementNotVisibleException.class)
//                .ignoring(ElementNotSelectableException.class)
//                .ignoring(ElementNotInteractableException.class)
//                .ignoring(ElementNotVisibleException.class)
//                .ignoring(ElementClickInterceptedException.class)
//                .ignoring(NullPointerException.class);
//        WebElement element = wait.until(new com.google.common.base.Function<WebDriver, WebElement>() {
//            public WebElement apply(WebDriver driver) {
//                return driver.findElement(locator);
//            }
//        });
//        //highlightAndTakeScreenShot(element);
//        return element;
//    }


    public WebElement getWebElement(By locator) {
        {
//            WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(15));
//            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            return getWebDriver().findElement(locator);
        }
    }

    // returns list of WebElement objects please use instead of wd.findElements(By.locator) method (created by Akbar)
//    public List<WebElement> getWebElements(By locator) {
//
//        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(getWebDriver())
//                .withTimeout(java.time.Duration.ofSeconds(15))
//                .pollingEvery(java.time.Duration.ofSeconds(3))
//                .ignoring(NoSuchElementException.class)
//                .ignoring(StaleElementReferenceException.class)
//                .ignoring(ElementClickInterceptedException.class)
//                .ignoring(ElementNotVisibleException.class)
//                .ignoring(ElementNotSelectableException.class)
//                .ignoring(ElementNotInteractableException.class)
//                .ignoring(ElementNotVisibleException.class);
//
//        List<WebElement> element = wait.until(new com.google.common.base.Function<WebDriver, List<WebElement>>() {
//            public List<WebElement> apply(WebDriver driver) {
//                return driver.findElements(locator);
//            }
//        });
//        return element;
//    }

    public List<WebElement> getWebElements(By locator) {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return getWebDriver().findElements(locator);
    }


    public String getPaginationText() {
        try {
            String strText = getText(getWebElement(weRecordCount));
            return strText;
        } catch (Exception ex) {
            return null;
        }
    }

    public int getTotalRecordCount() {
        String strText = getPaginationText();
        if (strText != null) {
            String[] strTexts = strText.split(" ");
            return Integer.parseInt(strTexts[5]);
        } else {
            testStepFailed("Unable to get the record count from Pagination text");
            return -1;
        }
    }

    public int getFirstRecordCount() {
        String strText = getPaginationText();
        if (strText != null) {
            String[] strTexts = strText.split(" ");
            return Integer.parseInt(strTexts[1]);
        } else {
            testStepFailed("Unable to get the record count from Pagination text");
            return -1;
        }
    }

    public int getLastRecordCount() {
        String strText = getPaginationText();
        if (strText != null) {
            String[] strTexts = strText.split(" ");
            return Integer.parseInt(strTexts[3]);
        } else {
            testStepFailed("Unable to get the record count from Pagination text");
            return -1;
        }
    }

    public void verifyRefreshButton(String TableName) {
        int init = getFirstRecordCount();
        if ((init) > 0) {
            if (isElementPresent(btnRefresh)) {
                JsClick(btnRefresh, "Refresh Button");
                if ((isElementPresent(By.xpath("//div[contains(@class,'table')]/table"))) && (isElementPresent(btnRefresh))) {
                    testStepPassed("Successfully validated Refresh button for table->" + TableName);
                } else {
                    testStepFailed("Successfully validated Refresh button for table->" + TableName);
                }
            } else {
                testStepFailed(" Refresh button does not exist for table->" + TableName);

            }
        } else {
            testStepInfo(" No records found,Hence Refresh Button is disabled for the table->" + TableName);
        }


    }


    public String getClass(WebElement we) {
        try {
            return we.getAttribute("class");
        } catch (Exception ex) {
            return "";
        }
    }

    public void jsclear(String elem, String elementName) {
        try {
            WebElement weElem = getWebElement(elem);
            JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
            js.executeScript("arguments[0].clear();", weElem);
            testStepInfo("Successfully clicked on element->" + elementName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void jsClickByVisibleText(String elementName) {
        try {
            WebElement elem = getWebElementByPartialText(elementName);
            JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
            js.executeScript("arguments[0].click();", elem);
            testStepInfo("Successfully clicked on element -> " + elementName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void selectRecordFromTable(String objTable, int rowNum) {
        WebElement tbl = getWebElement(objTable);
        List<WebElement> weRows = tbl.findElements(By.xpath("//tbody//tr//a"));
        int rowCount = weRows.size();
        if (rowCount >= rowNum) {
            clickOn(weRows.get(rowNum - 1), rowNum + " row in the table");
        } else {
            testStepFailed("Total row count is only " + rowCount + " , hence row " + rowNum + " is not available");
        }
    }


    public void clickOn(WebElement we, String elemName) {
        try {
            we.click();
            testStepInfo("Clicked on Element-" + elemName);
        } catch (Exception ex) {
            testStepFailed("Unable to Click on Element- " + elemName + ", Exception is ->" + ex.getMessage());
        }
    }

    public void validateDefaultSorting(int columnNumber, String ColumName, String Type, String TableName, String sortOrder) {
        try {
            WebDriver wd = getWebDriver();
            List<WebElement> row = wd.findElements(By.xpath("//div[contains(@class,'table')]/table//tr"));
            sleep(4000);
            int i = row.size();
            if (i > 0) {
                sleep(3000);
                if (sortOrder.toLowerCase().contains("a")) {
                    validateAscendingOrder(columnNumber, ColumName, Type);
                } else {
                    validateDescendingOrder(columnNumber, ColumName, Type);
                }
            } else {
                testStepInfo("No records found,Hence Sorting functionality cannot be validated");

            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while validating the default sorting functionality, Message is ->" + ex.getMessage());
        }
    }


    public void ValidateSortingFunctionality(int columnNumber, String ColumName, String Type, String AppName) {
        try {
            WebDriver wd = getWebDriver();
            List<WebElement> row = wd.findElements(By.xpath("//div[contains(@class,'table')]/table//tr"));
            int i = row.size();
            if (i > 0) {
                sleep(3000);
                By bySort = By.xpath("//div[contains(@class,'table')]/table//span[contains(text(),'" + ColumName + "')]/following::span[contains(@class,'sort')][1]");

                if (!getClass(getWebElement(bySort)).contains("asc")) {
                    JsClick(getWebElement(bySort), "Ascending");
                }
                sleep(5000);
                validateAscendingOrder(columnNumber, ColumName, Type);
                if (!getClass(getWebElement(bySort)).contains("desc")) {
                    JsClick(getWebElement(bySort), "Descending");
                }
                sleep(5000);
                validateDescendingOrder(columnNumber, ColumName, Type);
                sleep(3000);
            } else {
                testStepInfo("No records found,Hence Sorting functionality cannot be validated");

            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while validating the sorting functionality, Message is ->" + ex.getMessage());
        }
    }

    public int searchValueInTable(String firstColumnValue, String secondColumnValue) throws InterruptedException {
        boolean status = false;
        int rowNumber = 0;
        List<WebElement> row = wd.findElements(xpath("//table[contains(@class,'table table-bordered')]//tbody/tr"));
        int rowCount = row.size();
        if (rowCount > 0) {
            Thread.sleep(3000);
            for (int i = 1; i <= rowCount; i++) {
                String appFirstColumnValue = wd.findElement(xpath("//table[contains(@class,'table table-bordered')]//tbody/tr[" + i + "]/td[1]/a")).getText();
                String newelementName2 = wd.findElement(xpath("//table[contains(@class,'table table-bordered')]//tbody/tr[" + i + "]/td[2]")).getText();
                if (appFirstColumnValue.contains(firstColumnValue) && newelementName2.contains(secondColumnValue)) {
                    status = true;
                    testStepPassed("Values Found in Row number " + i);
                    rowNumber = i;
                    break;
                } else {
                    status = false;
                }
            }
            if (!status) {
                testStepFailed("Values  Not Found in Table");
            }
        } else {
            testStepInfo("No records found,Hence Can not Search For Values");
        }
        return rowNumber;
    }

    public void validateAscendingOrder(int columnNumber, String ColumName, String Type) {
        try {
            WebDriver wd = getWebDriver();
            int value = columnNumber + 1;
            WebElement icon = wd.findElement(By.xpath("//div[contains(@class,'table')]/table/thead/tr/th[" + value + "]/span[2]"));
            if (!icon.getAttribute("class").contains("asc")) {
//                testStepInfo(icon.getAttribute("class").toString());
                testStepFailed(ColumName + " column's icon is not changed to Ascending" );
            }
            List<WebElement> defaultRow = wd.findElements(By.xpath("//div[contains(@class,'table')]/table//tr"));
//            int itCount = 10;
            int itCount=defaultRow.size() - 1;
            List<WebElement> elementName = new LinkedList<>();
            if (Type == "STRING") {
                ArrayList<String> obtainedEleList = new ArrayList<>();
                ArrayList<String> resultEleNameList = new ArrayList<>();
                elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                for (int i = 0; i < itCount; i++) {
                    elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                    //String newelementName = (String) ((JavascriptExecutor) wd).executeScript("return arguments[0].text;", elementName.get(i));
                    String newelementName = elementName.get(i).getText().trim();
                    if (!newelementName.trim().equals("")) {
                        obtainedEleList.add(newelementName.toUpperCase().trim());
                        resultEleNameList.add(newelementName.toUpperCase().trim());
                       // obtainedEleList.add(newelementName);
                        //resultEleNameList.add(newelementName);
                    }
                }
                Collections.sort(obtainedEleList);
                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Ascending order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Ascending order");

                }
            }
            if (Type == "STRINGCUST") {
                ArrayList<String> obtainedEleList = new ArrayList<>();
                ArrayList<String> resultEleNameList = new ArrayList<>();
                elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                for (int i = 0; i < itCount; i++) {
                    elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                    //String newelementName = (String) ((JavascriptExecutor) wd).executeScript("return arguments[0].text;", elementName.get(i));
                    String newelementName = elementName.get(i).getText().trim();
                    if (!newelementName.trim().equals("")) {
                        obtainedEleList.add(newelementName);
                        resultEleNameList.add(newelementName);
                    }
                }
                Collections.sort(obtainedEleList);
                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Ascending order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Ascending order");

                }
            } else if (Type == "INTEGER") {
                ArrayList<Integer> obtainedEleList = new ArrayList<>();
                ArrayList<Integer> resultEleNameList = new ArrayList<>();
                elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                int count = elementName.size();
                for (int i = 0; i < itCount; i++) {
                    elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                    String newelementName1 = elementName.get(i).getText().trim();
                    if (newelementName1.contains("-")) {
                        newelementName1 = newelementName1.split("-")[0];
                    }
                    String newelementName = newelementName1.replaceAll(",", "");
                    if (!newelementName.equals("")) {
                        obtainedEleList.add(Integer.parseInt(newelementName));
                        resultEleNameList.add(Integer.parseInt(newelementName));
                    }
                }
                Collections.sort(obtainedEleList);
                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Ascending  order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Ascending order");

                }
            } else if (Type == "DOUBLE") {
                ArrayList<Double> obtainedEleList = new ArrayList<>();
                ArrayList<Double> resultEleNameList = new ArrayList<>();
                elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                int count = elementName.size();
                for (int i = 0; i < itCount; i++) {
                    elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                    String newelementName2 = elementName.get(i).getText().trim();
                    String newelementName1 = newelementName2.replaceAll(",", "");
                    String newelementName = newelementName1.replace("$", "").replace(")", "").replace("(", "-");
                    if (!newelementName.equals("")) {
                        obtainedEleList.add(Double.valueOf(newelementName));
                        resultEleNameList.add(Double.valueOf(newelementName));
                    }
                }
                Collections.sort(obtainedEleList);
                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Ascending  order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Ascending order");

                }
            } else if (Type == "DATE") {
                ArrayList<Date> obtainedEleList = new ArrayList<>();
                ArrayList<Date> resultEleNameList = new ArrayList<>();
                elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                int count = elementName.size();
                for (int i = 0; i < itCount; i++) {
                    elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                    String newelementName = elementName.get(i).getText().trim();
                    SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
                    if (!newelementName.equals("")) {
                        try {
                            obtainedEleList.add(date.parse(newelementName));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        try {
                            resultEleNameList.add(date.parse(newelementName));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }

                Collections.sort(obtainedEleList);
                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Ascending order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Ascending order");

                }
            } else if (Type == "DATETIME") {
                ArrayList<Date> obtainedEleList = new ArrayList<>();
                ArrayList<Date> resultEleNameList = new ArrayList<>();
                elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                int count = elementName.size();
                for (int i = 0; i < itCount; i++) {
                    elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                    String newelementName = elementName.get(i).getText().trim();
                    boolean flag = true;
                    SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
                    if (!newelementName.equals("")) {
                        try {
                            resultEleNameList.add((date.parse(newelementName)));
                            obtainedEleList.add((date.parse(newelementName)));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                Collections.sort(resultEleNameList);
                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Ascending order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Ascending  order");

                }
            } else if (Type == "DROPDOWN") {
                ArrayList<String> obtainedEleList = new ArrayList<>();
                ArrayList<String> resultEleNameList = new ArrayList<>();
                elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]/select"));
                for (int i = 0; i < itCount; i++) {
                    elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]/select"));
                    String newelementName = getSelectedOptionFromDropdown(elementName.get(i));
                    if (!newelementName.trim().equals("")) {
                        obtainedEleList.add(newelementName.toUpperCase());
                        resultEleNameList.add(newelementName.toUpperCase());
                    }
                }
                Collections.sort(obtainedEleList);
                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Ascending order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Ascending order");

                }
            }
            if (Type == "CHECKBOX") {
                ArrayList<String> obtainedEleList = new ArrayList<>();
                ArrayList<String> resultEleNameList = new ArrayList<>();
                elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]/input"));
                for (int i = 0; i < itCount; i++) {
                    elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]/input"));
                    String newelementName;
                    if (elementName.get(i).isSelected()) {
                        newelementName = "B";
                    } else {
                        newelementName = "A";
                    }
                    if (!newelementName.equals("")) {
                        obtainedEleList.add(newelementName.toUpperCase());
                        resultEleNameList.add(newelementName.toUpperCase());
                    }
                }
                Collections.sort(obtainedEleList);

                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Descending order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Descending order");

                }
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while validating the Ascending sorting functionality for column " + ColumName + ", Message is ->" + ex.getMessage());
        }
    }

    public List<WebElement> getSelectedOptionFromdropdown(WebElement dropdown) {
        Select sel = new Select(dropdown);
        return sel.getOptions();
    }

    public String getSelectedOptionFromDropdown(WebElement dropdown) {
        Select sel = new Select(dropdown);
        return sel.getFirstSelectedOption().getText();
    }


    public void validateDescendingOrder(int columnNumber, String ColumName, String Type) {
        WebDriver wd = getWebDriver();
        List<WebElement> defaultRow = wd.findElements(xpath("//div[contains(@class,'table')]/table//tr"));
        int itCount = defaultRow.size() - 1;
        int value = columnNumber + 1;
        List<WebElement> elementName = new LinkedList<>();
        if (Type == "STRING") {
            ArrayList<String> obtainedEleList = new ArrayList<>();
            ArrayList<String> resultEleNameList = new ArrayList<>();
            for (int i = 0; i < itCount; i++) {
                elementName = wd.findElements(xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                String newelementName = elementName.get(i).getText().trim();
                if (!newelementName.equals("")) {
                    obtainedEleList.add(newelementName.toUpperCase());
                    resultEleNameList.add(newelementName.toUpperCase());
                }
            }
            Collections.sort(obtainedEleList, Collections.reverseOrder());

            if (resultEleNameList.equals(obtainedEleList)) {
                testStepPassed(ColumName + " column is sorted in Descending order");
                takeScreenshot();
            } else {
                testStepFailed(ColumName + " column is not sorted in Descending order");

            }
        } else if (Type == "INTEGER") {
            ArrayList<Integer> obtainedEleList = new ArrayList<>();
            ArrayList<Integer> resultEleNameList = new ArrayList<>();
            for (int i = 0; i < itCount; i++) {
                elementName = wd.findElements(xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                String newelementName = elementName.get(i).getText().trim();
                if (newelementName.contains("-")) {
                    newelementName = newelementName.split("-")[0];
                }
                if (!newelementName.equals("")) {
                    obtainedEleList.add(Integer.parseInt(newelementName));
                    resultEleNameList.add(Integer.parseInt(newelementName));
                }
            }
            Collections.sort(obtainedEleList, Collections.reverseOrder());
            if (resultEleNameList.equals(obtainedEleList)) {
                testStepPassed(ColumName + " column is sorted in Descending order");
                takeScreenshot();


            } else {
                testStepFailed(ColumName + " column is not sorted in Descending order");

            }
        } else if (Type == "DOUBLE") {
            ArrayList<Double> obtainedEleList = new ArrayList<>();
            ArrayList<Double> resultEleNameList = new ArrayList<>();
            elementName = wd.findElements(xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
            int count = elementName.size();
            for (int i = 0; i < itCount; i++) {
                elementName = wd.findElements(xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                String newelementName2 = elementName.get(i).getText().trim();
                String newelementName1 = newelementName2.replaceAll(",", "");
                String newelementName = newelementName1.replace("$", "");
                if (!newelementName.equals("")) {
                    obtainedEleList.add(Double.valueOf(newelementName));
                    resultEleNameList.add(Double.valueOf(newelementName));
                }
            }
            Collections.sort(obtainedEleList, Collections.reverseOrder());
            if (resultEleNameList.equals(obtainedEleList)) {
                testStepPassed(ColumName + " column is sorted in Descending order");
                takeScreenshot();


            } else {
                testStepFailed(ColumName + " column is not sorted in Descending order");
            }
        } else if (Type == "DATE") {
            ArrayList<Date> obtainedEleList = new ArrayList<>();
            ArrayList<Date> resultEleNameList = new ArrayList<>();
            elementName = wd.findElements(xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
            int count = elementName.size();
            for (int i = 0; i < itCount; i++) {
                elementName = wd.findElements(xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                String newelementName = elementName.get(i).getText().trim();
                SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
                if (!newelementName.equals("")) {
                    try {
                        obtainedEleList.add(date.parse(newelementName));
                        resultEleNameList.add(date.parse(newelementName));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            Collections.sort(obtainedEleList, Collections.reverseOrder());
            if (resultEleNameList.equals(obtainedEleList)) {
                testStepPassed(ColumName + " column is sorted in descending order");
                takeScreenshot();
            } else {
                testStepFailed(ColumName + " column is not sorted in descending  order");

            }
        } else if (Type == "DATETIME") {
            ArrayList<Date> obtainedEleList = new ArrayList<>();
            ArrayList<Date> resultEleNameList = new ArrayList<>();
            elementName = wd.findElements(xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
            int count = elementName.size();

            for (int i = 0; i < itCount; i++) {
                elementName = wd.findElements(xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                String newelementName = elementName.get(i).getText().trim();
                boolean flag = true;
                SimpleDateFormat dateTime = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
                if (!newelementName.equals("")) {
                    try {
                        resultEleNameList.add((dateTime.parse(newelementName)));
                        obtainedEleList.add((dateTime.parse(newelementName)));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            Collections.sort(resultEleNameList, Collections.reverseOrder());
            if (resultEleNameList.equals(obtainedEleList)) {

                testStepPassed(ColumName + " column is sorted in Descending   order");
                takeScreenshot();
            } else {
                testStepFailed(ColumName + " column is not sorted in Descending   order");

            }
        }

    }

    public String getPageUrl() {
        try {
            return getWebDriver().getCurrentUrl();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public void validateBreadcrumb(String breadcrumb) throws Throwable {
        try {
            boolean blnTrue = true;
            String[] strBreadCrumbs = breadcrumb.split("/");
            List<WebElement> weBreadcrumb = getWebElements(wesBreadCumb);
            int i = 0;
            if (strBreadCrumbs.length == weBreadcrumb.size()) {
                for (WebElement we : weBreadcrumb) {
                    if (we.getText().contains(strBreadCrumbs[i])) {
                        testStepInfo(strBreadCrumbs[i] + " breadcrumb is displayed");
                    } else {
                        testStepFailed(strBreadCrumbs[i] + " breadcrumb is not displayed, instead " + we.getText() + " is displayed ");
                    }
                    i++;
                }
                if (blnTrue) {
                    testStepPassed("All the breadcumbs are displayed as expected ");
                }
                WebElement weBreadcrumbLink1 = getWebElement(By.xpath("//nav[@aria-label='breadcrumb']/ol/li/a[contains(text(),'" + strBreadCrumbs[2] + "')]"));
                JsClick(weBreadcrumbLink1, strBreadCrumbs[2] + " Breadcrumb");
                //verifyPartialLabelDisplayed(strBreadCrumbs[3], "h5");
                if (strBreadCrumbs.length == 4) {
                    getWebDriver().navigate().back();
                    sleep(3000);
                }
                WebElement weBreadcrumbLink2 = getWebElement(By.xpath("//nav[@aria-label='breadcrumb']//a[contains(text(),'" + strBreadCrumbs[0] + "')]"));
                JsClick(weBreadcrumbLink2, strBreadCrumbs[0] + " Breadcrumb");
                sleep(3000);
                if (getPageUrl().endsWith("home")) {
                    testStepPassed("Navigated to home page successfully");
                } else {
                    testStepFailed("Unable to navigate to home page");
                }
            } else {
                testStepFailed("Expected breadcrumb is not displayed in page, Expected is->" + breadcrumb);
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while validating the generic elements in page, Message is->" + ex.getMessage());
        }
    }


    public static String getFileName() {
        String timeFile = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss a").format(new Date());
        return timeFile.replace(" ", "");
    }

 public void movetoElement(String object){
        Actions action = new Actions(ApplicationManager.driver);
        WebElement ele=getWebElement(object);
        action.moveToElement(ele).perform();
    }

    public static void verifySelectDeselect(String option, String app) {
        List<WebElement> optionList;
        int count;
        if (app.equalsIgnoreCase("Advance")) {
            count = 1;
        } else
            count = 2;
        if (option.equalsIgnoreCase("Select All")) {
            optionList = wd.findElements(xpath("//a[contains(@class,'active') and not(text()='Create Upload Files')]"));
            int totOptions = optionList.size();
            String checkedCount = wd.findElement(xpath("(//div[@class='dropdown d-block']//span[@class='caret']/parent::button)[" + count + "]")).getText();
            String[] array = checkedCount.split(" ");
            int chckd = Integer.valueOf(array[0]);
            if (chckd == totOptions) {
                testStepPassed("Successfully validated for selectAll functionality");
                takeScreenshot();
            } else {
                testStepFailed("Failed to validate for selectAll functionality");
            }
        } else {
            optionList = wd.findElements(xpath("//a[contains(@class,'active') and not(text()='Create Upload Files')]"));
            int totOptions = optionList.size();
            if (totOptions == 0) {
                testStepPassed("Successfully validated for DeselectAll functionality");
                takeScreenshot();
            } else {
                testStepFailed("Failed to validate for DeselectAll functionality");
            }
        }
    }

    public static void selectCheckBox(String value) {
        String val = value;
        String[] array = val.split(",");
        int length = array.length;
        for (int i = 0; i < length; i++) {
            if (array[i] != " ") {
                String option = array[i].trim();
                WebElement element = wd.findElement(xpath("//label/parent::div/input[contains(@id,'" + option + "')]"));
                element.click();
                testStepPassed("Successfully clicked on " + option + " checkbox");
            }
        }
        takeScreenshot();
    }

    public static boolean elementToBeInvisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(wd, 80);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void saveExportedFile(String fileName) {
        try {
            waitTillLoadingCompleted();
            Robot robot = new Robot();
            robot.delay(5000);
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_N);
            robot.delay(1000);
            robot.keyRelease(KeyEvent.VK_ALT);
            robot.keyRelease(KeyEvent.VK_N);

            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_ENTER);

            robot.delay(2000);
            String path = outputDirectory.replace(".", "").replace("/", "\\");

            boolean file = new File(path + File.separator + "TestDocuments").mkdir();
            String fileCompletePath = path + File.separator + "TestDocuments\\" + fileName;
            robotType(robot, fileCompletePath);
            String lnk_path = ".\\TestDocuments\\" + fileName;
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_N);
            robot.delay(1000);
            robot.keyRelease(KeyEvent.VK_ALT);
            robot.keyRelease(KeyEvent.VK_N);

            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_ESCAPE);
            if (fileExists(fileCompletePath)) {
                testReporter("blue", "<a href=" + lnk_path + ">View Exported " + fileName + "</a>");



                Path content = Paths.get(fileCompletePath);
                try (InputStream is = Files.newInputStream(content)) {
                    Allure.step("Exported Report Is Here", new Allure.ThrowableContextRunnableVoid<Allure.StepContext>() {
                        @Override
                        public void run(Allure.StepContext context) throws Throwable {
                            Allure.addAttachment("Download Exported Reports For " + fileName, "document/csv",is, "csv");
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                testStepFailed("File is not downloaded successfully->" + fileName);
            }
        } catch (Exception e) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), e);
        }
    }


    public void saveCrystalReportExportedFile(String fileName) throws AWTException {
        waitTillLoadingCompleted();
        sleep(20000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_N);
        robot.delay(1000);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_N);

        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(5000);

        String path = outputDirectory.replace(".", "").replace("/", "\\");

        boolean file = new File(path + File.separator + "TestDocuments").mkdir();
        String fileCompletePath = path + File.separator + "TestDocuments\\" + fileName;
        robotType(robot, fileCompletePath);
        String lnk_path = ".\\TestDocuments\\" + fileName;
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_N);
        robot.delay(1000);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_N);

        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_ESCAPE);
        if (fileExists(fileCompletePath)) {
            testReporter("blue", "<a href=" + lnk_path + ">View Exported " + fileName + "</a>");
            Path content = Paths.get(fileCompletePath);
            try (InputStream is = Files.newInputStream(content)) {
                Allure.step("Exported Report Is Here", new Allure.ThrowableContextRunnableVoid<Allure.StepContext>() {
                    @Override
                    public void run(Allure.StepContext context) throws Throwable {
                        Allure.addAttachment("Download Exported Reports For " + fileName, "document/pdf",is, "pdf");
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            testStepFailed("File is not downloaded successfully->" + fileName);
        }

    }

    public boolean waitForCrystalReport(String object) {
        for (int i = 0; i < 4; i++) {
            waitFor(object);
            if (isElementPresent(object)) {
                return true;
            }
        }
        return false;
    }

    public void ExportCrystalReport(String UserName, String fileName) throws InterruptedException {
        handleReportAuth(UserName);
        try {
            if (isElementPresent(lnkReportError) || isElementPresent(lnkReportError)) {
                takeScreenshot();
                stop();
            } else {
                WebDriverWait wait = new WebDriverWait(getWebDriver(), 15);
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("openDocChildFrame")));
                if (waitForCrystalReport(btnExportCrystalReport)) {
                    JsClick(btnExportCrystalReport, "Export");
                    JsClick(lstFileFormat, " File Format dropdown");
                    if (fileName.contains("pdf")) {
                        JsClick(wePDFFormat, "Pdf");
                    } else if (fileName.contains(".xls")) {
                        JsClick(weXLSFormat, "xls");
                    } else
                        JsClick(weCSVFormat, "csv");
                    sleep(2000);
                    JsClick(lnkExport, "Export");
                    sleep(10000);
                    saveCrystalReportExportedFile(fileName);
                } else {
                    takeScreenshot();
                    stop();
                }
            }
        } catch (Exception e) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), e);
        }


    }


    public static boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


    public static boolean waitForAlert() {
        try {
            WebDriverWait wait = new WebDriverWait(ApplicationManager.getWebDriver(), 15);
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    public void robotType(Robot robot, String characters) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(characters);
        clipboard.setContents(stringSelection, null);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(1000);
    }

    public void handleReportAuth(String UserName) {
        waitForAlert();
        try {
//            if (isAlertPresent()) {
            String target = System.getProperty("target", "local");
            obj.load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/local.properties"));
            String pwdDecode = new String(Base64.decodeBase64(obj.getProperty("" + UserName + "")));
            Robot robot = null;
            robot = new Robot();
            robot.delay(2000);
            robotType(robot, UserName);
            robot.delay(3000);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.delay(1000);
            robotType(robot, pwdDecode);
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(3000);
            getWebDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void switchToWindows(String user) throws InterruptedException {
        List<String> browserTabs = new ArrayList<String>(wd.getWindowHandles());
        if (browserTabs.size() > 2) {
            for (int i = browserTabs.size() - 1; i >= 0; i--) {
                wd.switchTo().window(browserTabs.get(i));
                String fileName = getFileName();
                String saveFilename = fileName + ".pdf";
                ExportCrystalReport(user, saveFilename);
                wd.close();
            }
        } else {
            wd.switchTo().window(browserTabs.get(1));
            String fileName = getFileName();
            String saveFilename = fileName + ".pdf";
            ExportCrystalReport(user, saveFilename);
            wd.close();
            wd.switchTo().window(browserTabs.get(0));
        }

    }


    public static String getIssueDate() {
        String issueDate = "";
        issueDate = wd.findElement(xpath("//input[@name='request_date' and @formcontrolname='issueDttm']")).getAttribute("value");
        return issueDate;
    }

    public static String getMaturityDate() {
        String maturityDate = "";
        maturityDate = wd.findElement(xpath("//input[@name='request_date' and @formcontrolname='maturDttm']")).getAttribute("value");
        return maturityDate;
    }

    public static String getTheBusinessDate() {
        String newDate = "";
        String issueDate = getIssueDate();
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            //issueDate = "10/08/2018";
            cal.setTime(sdf.parse(issueDate));
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                cal.add(Calendar.DATE, 3);
                newDate = sdf.format(cal.getTime());

            } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                cal.add(Calendar.DATE, 2);
                newDate = sdf.format(cal.getTime());
            } else {
                cal.add(Calendar.DATE, 1);
                newDate = sdf.format(cal.getTime());
            }

        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return newDate;
    }


    public static String textValue(String object) {
        String text = "";
        try {
            text = wd.findElement(xpath(obj.getProperty(object))).getAttribute("value");
        } catch (Exception e) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), e);
        }
        return text;
    }

    public String gettextValue(WebElement we) {
        try {
            if (isElementPresent(we)) {
                return we.getAttribute("value");
            } else {
                return "";
            }
        } catch (Exception ex) {
            testStepFailed("Unable to get the text element, message is->" + ex.getMessage());
            return "";
        }
    }

    public static void verifyColor(String object, String elemName, String color) {
        String Darkblue = "#0c306d";
        String red = "#ffffff";
        String btncolor = wd.findElement(xpath(obj.getProperty(object))).getCssValue("background-color");
        String hex = Color.fromString(btncolor).asHex().trim();
        switch (color.toLowerCase()) {
            case "red":
                if (hex.equalsIgnoreCase(red)) {
                    testStepPassed(elemName + " button color is red as expected");
                    takeScreenshot();
                } else {
                    testStepFailed(elemName + " button color is not red, Expected->" + red + " but Actual is->" + hex);
                }
                break;
            case "darkblue":
                if (hex.equalsIgnoreCase(Darkblue)) {
                    testStepPassed(elemName + " button color is Dark blue as expected");
                    takeScreenshot();
                } else {
                    testStepFailed(elemName + " button color is not dark blue, Expected->" + red + " but Actual is->" + hex);
                }
                break;
            default:
                testStepFailed("color paramter is not valid");
        }
    }

    public static String DecimalvalueRoundof(String Number) {
        double d = Double.parseDouble(Number);
        DecimalFormat df = new DecimalFormat("############.####");
        df.setRoundingMode(RoundingMode.CEILING);
        String roundedvalue = df.format(d);
        return roundedvalue;
    }

    public static void jsType(String object, String value) {
        try {
            WebElement elem = wd.findElement(xpath(obj.getProperty(object)));
            JavascriptExecutor js = (JavascriptExecutor) wd;
            js.executeScript("arguments[0].setAttribute('value', '" + value + "')", elem);
        } catch (Exception e) {
            testStepFailed("Exception Occured in JsType() " + e.getMessage());
        }
    }


    public static void fileUpload(String filepath) throws AWTException {
        try {
            Alert alert = wd.switchTo().alert();
            alert.sendKeys(filepath);
            alert.accept();
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            waitForAlertDismiss();

        } catch (Exception e) {
            String method = new Exception().getStackTrace()[0].getMethodName();
            testStepFailed("Exception Caught in method " + method + ", and Error Message is->" + e.getMessage());
        }

    }


    public static void updateExcelSheet(String filePath, String fileName, String cellAddress, String value) throws AWTException {
        try {
            String worksheet = filePath + fileName + ".xlsx";
            String tempWorksheet = filePath + fileName + "1.xlsx";
            XSSFWorkbook wb = new XSSFWorkbook(new File(worksheet));
            Sheet sh = wb.getSheetAt(1);

            FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
            CellReference cellReference = new CellReference(cellAddress);
            Row row = sh.getRow(cellReference.getRow());
            Cell cell = row.getCell(cellReference.getCol());
            cell.setCellValue(value);
            CellValue cellValue = evaluator.evaluate(cell);
            System.out.println(cellValue);
            FileOutputStream fos = new FileOutputStream(new File(tempWorksheet));
            wb.write(fos);
            fos.close();
            wb.close();
            Files.delete(Paths.get(worksheet));
            new File(tempWorksheet).renameTo(new File(worksheet));

        } catch (Exception ex) {
            testStepFailed("Exception caught while updating the excel" + ex.getMessage());
        }
    }


    public void clickOnSearchButton() {
        clickOn(btnSearch, "Search Button");
    }

    public void clickOnResetButton() {
        JsClick(btnReset, "Reset Button");
        sleep(4000);
    }

    public void clickOnHelp(){
        JsClick(lnkHelp,"Help");
        sleep(3000);
    }


    public void clickOnAddButton() {
        try {
            sleep(2000);
            JsClick(btnAdd, "Add Button");
            //   clickOn(btnAdd, "Add Button");
        } catch (Exception ex) {
            testStepFailed("Exception caught while updating the excel" + ex.getMessage());
        }
    }

    public void JsClick(String elem, String elementName) {
        try {
            sleep(2000);
            WebElement weElem = getWebElement(elem);
            JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
            js.executeScript("arguments[0].click();", weElem);
            testStepInfo("Successfully clicked on element->" + elementName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void JsClick(WebElement elem, String elementName) {
        try {
            sleep(1000);
            JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
            js.executeScript("arguments[0].click();", elem);
            testStepInfo("Successfully clicked on element->" + elementName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Jsclick(WebElement we, String elementName) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
            js.executeScript("arguments[0].click();", we);
            testStepInfo("Successfully clicked on element->" + elementName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validateDeafultElements(String object, String expMsg) {
        String message = getWebElement(object).getText();
        if (message.toLowerCase().contains(expMsg.toLowerCase())) {
            testStepPassed("Default value in fields is:> " + message);
            takeScreenshot();
        } else {
            testStepFailed("Default value: " + expMsg + " is not displayed,instead " + message + " is displayed");

        }

    }

    public void refreshPage()
    {
        try {
            getWebDriver().navigate().refresh();
            sleep(2000);
        } catch (Exception e) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), e);
        }
    }

    public void jsClick(String object, String elementName) {
        try {
            sleep(1000);
            WebElement weElem = getWebElement(object);
            JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
            js.executeScript("arguments[0].click();", weElem);
            testStepInfo("Successfully clicked on element->" + elementName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void jsClick(WebElement object, String elementName) {
        try {
            sleep(1000);
            JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
            js.executeScript("arguments[0].click();", object);
            testStepInfo("Successfully clicked on element->" + elementName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void clickOnSaveButton() {
        JsClick(btnSave, "Save Button");
    }

    public void clickOnUpdateButton() {
        waitFor(btnUpdate);
        JsClick(btnUpdate, "Update Button");
    }

    public void clickOnDeleteButton() {
        JsClick(btnDelete, "Delete Button");
    }

    public void clickOnCancelButton() {
        JsClick(btnCancel, "Cancel Button");
    }

    public void clickOnConfirmDeleteButton() {
        JsClick(btnDeleteConfirm, "Confirm Delete Button");
    }



    public void clickOnCancelDeleteButton() {
        JsClick(btnDeleteCancel, "Confirm Delete Button");
    }


    public void validateHomePageLoaded(String pageName) {
        if (isElementPresent(drpShowEntry)) {
            testStepPassed(pageName + " home page is displayed as expected");
        } else {
            testStepFailed(pageName + " home page is not displayed as expected");
        }
    }

    public void validateAddOrEditPageLoaded() {
        if (isElementPresent(btnCancel)) {
            testStepPassed(" Add/Edit page is displayed as expected");
        } else {
            testStepFailed("Add/Edit page is not displayed as expected");
        }
    }


    public static void validatetostarmessage(String object, String expMessage) {
        try {
            waitFor(object);
            String message = wd.findElement(By.xpath(obj.getProperty(object))).getText();
            if (message.toLowerCase().contains(expMessage.toLowerCase())) {
                testStepPassed("Expected message is Displayed ->" + message);
            } else {
                testStepFailed("Expected Message->" + expMessage + " is not displayed,instead " + message + " is displayed");

            }
        } catch (Exception e) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), e);
        }
    }


    public void clickOnCheckBox(String obj, String value, String elemName) {
        try {
            WebElement we = getWebElement(obj);

            if (value.equals("Y")) {
                if (we.isSelected()) {
                    testStepInfo("element already checked " + elemName);
                } else {
//                    we.click();
                    testStepInfo("Clicked on Element-" + elemName);
                }
            } else if (value.equals("N")) {
                if (we.isSelected()) {
//                    we.click();
                    testStepInfo("Clicked on Element-" + elemName);
                } else {
                    testStepInfo("Element is unchecked");
                }
            }
        } catch (Exception ex) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), ex);
        }
    }

    public void verifyCheckboxChecked(String object,String ElementName)
    {
        try {
            scroll(object);
            String CheckBxStatus = getWebElement(object).getAttribute("aria-checked");
            if(CheckBxStatus.equalsIgnoreCase("true")){
                testStepPassed("Successfully validated that "+ElementName+" is checked ");
            }else{
                testStepFailed("Failed to validate"+ElementName+"is checked");
            }
        } catch (Exception e) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), e);
        }

    }

    public void clickOnCheckBox(WebElement we, String value, String elemName) {
        try {
            if (value.equals("Y")) {
                if (we.isSelected()) {
                    testStepInfo("elemnet already checked " + elemName);
                } else {
                    we.click();
                    testStepInfo("Clicked on Element-" + elemName);
                }
            } else if (value.equals("N")) {
                if (we.isSelected()) {
                    we.click();
                    testStepInfo("Clicked on Element-" + elemName);
                } else {

                }

            }
        } catch (Exception ex) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), ex);
        }
    }


    public boolean verifyisEnabledandisDisabled(String object, String elementname, boolean status) {
        try {
            WebElement webElement = getWebElement(object);
            if (status) {
                if (webElement.isEnabled()) {
                    clickOn(btnSaveAd,"Save");
                    testStepPassed(elementname + " is enabled as expected");
                    return true;
                } else {
                    testStepInfo(elementname + " is Disabled as expected");
                    return false;
                }
            } else {
                if (webElement.isEnabled() == status) {
                    testStepPassed(elementname + " is Disabled as expected");
                    return true;
                } else {
                    testStepFailed(elementname + " is enabled not working as expected");
                    return false;
                }
            }
        } catch (Exception e) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), e);

        }
        return status;
    }
    public String getlastUpdateDetails() {
        String UploadedFilename=""; String lastUpdateTime;
        try {
            Date date = new Date();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//            SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            String lastUpdateTime1 = sdf1.format(date);
            String[] time1 = lastUpdateTime1.split(":");
            lastUpdateTime = time1[0] + ":" + time1[1];
            String UpdatedTimeStamp= lastUpdateTime.replaceAll("[-:\\s]","");
            UploadedFilename= "JsonConfig_sqetst01_"+UpdatedTimeStamp+".json";
            // System.out.println(UploadedFilename);


        } catch (Exception ex) {
            testStepFailed("Exception ->" + ex.getMessage());
        }
        return UploadedFilename;
    }

    public String getLatestFilefromDir(String dirPath){
        String lastModifiedFileName="";
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
                //lastModifiedFileName=lastModifiedFile.toString();
                lastModifiedFileName =  lastModifiedFile.getName();
            }
        }
        return lastModifiedFileName;
    }



    public static String getFutureDate(int Days) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
        //Getting current date
        Calendar cal = Calendar.getInstance();
        //Displaying current date in the desired format
        System.out.println("Current Date: " + sdf.format(cal.getTime()));

        //Number of Days to add
        cal.add(Calendar.DAY_OF_MONTH, Days);
        //Date after adding the days to the current date
        String newDate = sdf.format(cal.getTime());
        //Displaying the new Date after addition of Days to current date
        return newDate;

    }


    public void selectFromDropdown(WebElement we, int index, String dropdownName) {
        try {
            Select weSel = new Select(we);
            weSel.selectByIndex(index);
            testStepInfo("Selected the value with index " + index + " from the dropdown " + dropdownName);
        } catch (Exception ex) {
            testStepFailed("Unable to select the index " + index + " from dropdown " + dropdownName + " Exception message is->" + ex.getMessage());
        }
    }

    public void selectFromDropdownByValue(WebElement we, String value, String dropdownName) {
        try {
            Select weSel = new Select(we);
            weSel.selectByValue(value);
            testStepInfo("Selected the value " + value + " from the dropdown " + dropdownName);
        } catch (Exception ex) {
            testStepFailed("Unable to select the value " + value + " from dropdown " + dropdownName + " Exception message is->" + ex.getMessage());
        }
    }

    public void getvaluesFromDropdown(WebElement we, String value, String dropdownName) {
        try {
            int count = 0;
            String exp[] = null;
            Select weSel = new Select(we);
            List<WebElement> options = weSel.getOptions();
            for (WebElement we1 : options) {
                for (int i = 0; i < exp.length; i++) {
                    if (we.getText().equals(exp[i])) {
                        count++;
                    }
                }
                testStepInfo("Selected the value " + value + " from the dropdown " + dropdownName);
            }
        } catch (Exception ex) {
            testStepFailed("Unable to select the value " + value + " from dropdown " + dropdownName + " Exception message is->" + ex.getMessage());
        }
    }


    public String getSelectedValueFromDropdown(WebElement we) {
        try {
            Select weSel = new Select(we);
            return weSel.getFirstSelectedOption().getText();
        } catch (Exception ex) {
            testStepFailed("Unable to get the selected value from dropdown , Exception message is->" + ex.getMessage());
            return "";
        }
    }


    public void selectFromDropdown(String select, int index, String dropdownName) {
        try {
            Select weSel = new Select(getWebElement(select));
            weSel.selectByIndex(index);
            testStepInfo("Selected the value with index " + index + " from the dropdown " + dropdownName);
        } catch (Exception ex) {
            testStepFailed("Unable to select the index " + index + " from dropdown " + dropdownName + " Exception message is->" + ex.getMessage());
        }
    }

    public void selectFromDropdownByValue(String select, String value, String dropdownName) {
        try {
            Select weSel = new Select(getWebElement(select));
            weSel.selectByValue(value);
            testStepInfo("Selected the value " + value + " from the dropdown " + dropdownName);
        } catch (Exception ex) {
            testStepFailed("Unable to select the value " + value + " from dropdown " + dropdownName + " Exception message is->" + ex.getMessage());
        }
    }


    public String getSelectedValueFromDropdown(String select) {
        try {
            Select weSel = new Select(getWebElement(select));
            return weSel.getFirstSelectedOption().getText();
        } catch (Exception ex) {
            testStepFailed("Unable to get the selected value from dropdown " + select + ", Exception message is->" + ex.getMessage());
            return "";
        }
    }

    public void Backspace(String object) {
        try {
            WebElement we = getWebElement(object);
            we.click();
            sleep(2000);
            String value = we.getAttribute("value");
            for (int i = 0; i <= value.length(); i++) {
                we.sendKeys(BACK_SPACE);
            }
        } catch (Exception ex) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), ex);
        }
    }


    public void validateExportFunctionality(String format) {
        String saveFile = getFileName();
        if (format.equals("XLS")) {
            String fileName = saveFile + ".xlsx";
            JsClick(btnXLS, "XLS button");
            sleep(4000);
          //  saveExportedFile(fileName);
        }
        if (format.equals("CSV")) {
            String fileName = saveFile + ".csv";
            JsClick(btnCSV, "CSV button");
         //   saveExportedFile(fileName);
        }
        if (format.equals("PDF")) {
            String fileName = saveFile + ".pdf";
            JsClick(btnPDF, "PDF button");
           // saveExportedFile(fileName);
        }
    }

    public void validateGeneralComponentsInPage() {
        try {
            verifyElementDisplayed(webreadcrumb, "breadcrumb");
            //verifyElementDisplayed(btnCSV, "CSV Button");
            //verifyElementDisplayed(btnXLS, "XLS Button");
            //verifyElementDisplayed(btnPDF, "PDF Button");
            verifyElementDisplayed(btnRefresh, "Refresh Button");
            verifyElementDisplayed(lnkFirstPage, "First Page Link");
            verifyElementDisplayed(lnkNextPage, "Next Page Link");
            verifyElementDisplayed(lnkPreviousPage, "Previous Page Link");
            verifyElementDisplayed(lnkLastPage, "Last Page Link");
            verifyElementDisplayed(btnSearch, "Search Button");
            verifyElementDisplayed(btnReset, "Reset Button");
            if (isElementPresent(drpShowEntry)) {
                String strValue = getSelectedValueFromDropdown(drpShowEntry);
                if (strValue.contains("10")) {
                    testStepInfo("Show dropdown is displayed and default value is set to 10 as expected");
                } else {
                    testStepFailed("Show dropdown is displayed and default value is not set to 10");
                }
            } else {
                testStepFailed("Show dropdown is not displayed");
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while validating the generic elements in page, Message is->" + ex.getMessage());
        }
    }
    public boolean verifyFieldText(String object, String elemName) {
        try {
            sleep(2000);
            WebElement we = getWebElement(object);
            String S1=we.getAttribute("value");
            if(S1.trim().equals(""))
            {
                testStepFailed(elemName + " is not displayed with expected value");
                return  false;
            }else {
                testStepPassed(elemName + " is Populated with "+ S1 );
                return true;
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while verifying the element " + elemName + ", Exception message is->" + ex.getMessage());
            return false;
        }
    }

    public boolean verifyFieldInText(String object, String elemName) {
        try {
            sleep(2000);
            WebElement we = getWebElement(object);
            String S1=we.getText();
            if(S1.trim().equals(""))
            {
                testStepFailed(elemName + " is not displayed with expected value");
                return  false;
            }else {
                testStepPassed(elemName + " is Populated with "+ S1 );
                return true;
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while verifying the element " + elemName + ", Exception message is->" + ex.getMessage());
            return false;
        }
    }





    public boolean verifyElementDisplayed(String elem, String elemName) {
        try {
            sleep(2000);
            WebElement webElement = getWebElement(elem);
            if (isElementPresent(webElement)) {
                testStepPassed("WebElement " + elemName + " is displayed in the page as expected");
                return true;
            } else {
                testStepFailed("WebElement " + elemName + " is not displayed in the page ");
                return false;
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while verifying the element " + elemName + ", Exception message is->" + ex.getMessage());
            return false;
        }
    }


    public boolean verifyPartialLabelDisplayed(String elementText) {
        try {
            WebElement webElement = getWebElementByPartialText(elementText, "span");
            if (isElementPresent(webElement)) {
                testStepPassed("WebElement with label :" + elementText + " is displayed in the page as expected");
                return true;
            } else {
                testStepFailed("WebElement with label :" + elementText + " is not displayed in the page ");
                return false;
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while verifying the element " + elementText + ", Exception message is-" + ex.getMessage());
            return false;
        }
    }

    public boolean verifyPartialLabelDisplayed(String elementText, String tagName) {
        try {
            sleep(2000);
            WebElement webElement = getWebElementByPartialText(elementText, tagName);
            if (isElementPresent(webElement)) {
                testStepPassed("WebElement with label :" + elementText + " is displayed in the page as expected");
                return true;
            } else {
                testStepFailed("WebElement with label :" + elementText + " is not displayed in the page ");
                return false;
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while verifying the element " + elementText + ", Exception message is-" + ex.getMessage());
            return false;
        }
    }

    public boolean verifyLabelDisplayed(String labelText) {
        try {
            WebElement webElement = getWebElementByText(labelText);
            if (isElementPresent(webElement)) {
                testStepPassed("WebElement with label :" + labelText + " is displayed in the page as expected");
                return true;
            } else {
                testStepFailed("WebElement with label :" + labelText + " is not displayed in the page ");
                return false;
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while verifying the element " + labelText + ", Exception message is->" + ex.getMessage());
            return false;
        }
    }

    public boolean verifyLabelDisplayed(String labelText, String tagName) {
        try {
            //WebElement webElement = ApplicationManager.getWebDriver().findElement(By.xpath("//"+tagName+"[contains(text(),'"+labelText+"')]"));
            WebElement webElement = getWebElementByPartialText(labelText, "h5");
            if (isElementPresent(webElement)) {
                testStepPassed("WebElement with label :" + labelText + " is displayed in the page as expected");
                return true;
            } else {
                testStepFailed("WebElement with label :" + labelText + " is not displayed in the page ");
                return false;
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while verifying the element " + labelText + ", Exception message is->" + ex.getMessage());
            return false;
        }
    }
    public void clickonHelpButton() {
        try {
            waitFor(btnHelp);
            JsClick(btnHelp, "Show Help Button");
            testStepPassed("Help document is displayed");
        } catch (Exception ex) {
            testStepFailed("Exception caught  Message is->" + ex.getMessage());
        }
    }




    public boolean verifyElementNotDsiaplyed(String element, String elementName) {
        try {
            WebElement webElement = getWebElement(element);
            if (isElementPresent(webElement)) {
                testStepFailed("WebElement :" + elementName + " is displayed in the page");
                return false;
            } else {
                testStepPassed("WebElement :" + elementName + " is not displayed in the page as expected");
                return true;
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while verifying the element :" + elementName + ", Exception message is->" + ex.getMessage());
            return false;
        }
    }


    public void validateAuditDetailAndTransactionTable(MaintenanceDAO maintenanceDAO, List<Map<String, Object>> PrevAuditDetailRecordsDB, List<Map<String, Object>> PrevAuditTransactionRecordsBD, String operation) {
        boolean blnAuditDetail = false;
        String systemTxnNoDetail = "";
        switch (operation.toUpperCase()) {
            case "CREATE":
                List<Map<String, Object>> AfterAuditDetailRecordsDB = maintenanceDAO.getAuditDetailTable();
                List<Map<String, Object>> AfterAuditTransactionRecordsDB = maintenanceDAO.getAuditTransactionTable();

                if (Double.parseDouble(AfterAuditDetailRecordsDB.get(0).get("system_txn_no").toString()) > Double.parseDouble(PrevAuditDetailRecordsDB.get(0).get("system_txn_no").toString())) {
                    blnAuditDetail = true;
                    systemTxnNoDetail = AfterAuditDetailRecordsDB.get(0).get("system_txn_no").toString();
                    String systemTxnActionCode = AfterAuditDetailRecordsDB.get(0).get("system_txn_action_code").toString();
                    testStepInfo("New row is added to the Audit Detail table after creating new record");
                    testStepInfo("system_txn_no of Audit detail table is " + systemTxnNoDetail);
                    if (systemTxnActionCode.equals("I")) {
                        testStepInfo("New row added in the Audit Detail table has the valid system_txn_action_code -> I ");
                    } else {
                        testStepFailed("New record added in the Audit Detail table does not have expected system_txn_action_code , Expected is I, But actual is->" + systemTxnActionCode);
                    }
                } else {
                    testStepFailed("New record is not added to the Audit Detail table after creating new record");
                }
                if (Double.parseDouble(AfterAuditTransactionRecordsDB.get(0).get("system_txn_no").toString()) > Double.parseDouble(PrevAuditTransactionRecordsBD.get(0).get("system_txn_no").toString())) {
                    testStepInfo("New record is added to the Audit Transaction table after creating new record");
                    String systemTxnNoTransaction = AfterAuditTransactionRecordsDB.get(0).get("system_txn_no").toString();
                    String empUserId = AfterAuditTransactionRecordsDB.get(0).get("emp_user_id").toString();
                    if (blnAuditDetail) {
                        if (systemTxnNoTransaction.equals(systemTxnNoDetail)) {
                            testStepInfo("New row added in the the Audit transaction table has the valid system_txn_no->" + systemTxnNoDetail);
                        } else {
                            testStepFailed("New row added in the the Audit transaction table does not have the valid system_txn_no, Expected is " + systemTxnNoDetail + " , but actual is ->" + systemTxnNoTransaction);
                        }
                        sleep(3000);
                        if (empUserId.contains(HelperBase.loggedInUser)) {
                            testStepInfo("New row added in the the Audit transaction table has the valid emp_user_id->" + empUserId);
                        } else {
                            testStepFailed("New row added in the the Audit transaction table does not have the valid emp_user_id, Expected ->" + HelperBase.loggedInUser + " But actual is " + empUserId);
                        }
                    }
                } else {
                    testStepFailed("New record is not added to the Audit Transaction table after creating new record");
                }
                break;
            case "UPDATE":
                AfterAuditDetailRecordsDB = maintenanceDAO.getAuditDetailTable();
                AfterAuditTransactionRecordsDB = maintenanceDAO.getAuditTransactionTable();

                // if (AfterAuditDetailRecordsDB.size() > PrevAuditDetailRecordsDB.size()) {
                if (Double.parseDouble(AfterAuditDetailRecordsDB.get(0).get("system_txn_no").toString()) > Double.parseDouble(PrevAuditDetailRecordsDB.get(0).get("system_txn_no").toString())) {

                    blnAuditDetail = true;
                    systemTxnNoDetail = AfterAuditDetailRecordsDB.get(0).get("system_txn_no").toString();
                    String systemTxnActionCode = AfterAuditDetailRecordsDB.get(0).get("system_txn_action_code").toString();
                    testStepInfo("New row is added to the Audit Detail table after Updating record");
                    testStepInfo("system_txn_no of Audit detail table is " + systemTxnNoDetail);
                    if (systemTxnActionCode.equals("U")) {
                        testStepInfo("New row added in the Audit Detail table has the valid system_txn_action_code -> U ");
                    } else {
                        testStepFailed("New record added in the Audit Detail table does not have expected system_txn_action_code , Expected is U, But actual is->" + systemTxnActionCode);
                    }
                } else {
                    testStepFailed("New record is not added to the Audit Detail table after updating record");
                }
                //if (AfterAuditTransactionRecordsDB.size() > PrevAuditTransactionRecordsBD.size()) {
                if (Double.parseDouble(AfterAuditTransactionRecordsDB.get(0).get("system_txn_no").toString()) > Double.parseDouble(PrevAuditTransactionRecordsBD.get(0).get("system_txn_no").toString())) {
                    testStepInfo("New record is added to the Audit Transaction table after creating new record");
                    String systemTxnNoTransaction = AfterAuditTransactionRecordsDB.get(0).get("system_txn_no").toString();
                    String empUserId = AfterAuditTransactionRecordsDB.get(0).get("emp_user_id").toString();
                    if (blnAuditDetail) {
                        if (systemTxnNoTransaction.equals(systemTxnNoDetail)) {
                            testStepInfo("New row added in the the Audit transaction table has the valid system_txn_no->" + systemTxnNoDetail);
                        } else {
                            testStepFailed("New row added in the the Audit transaction table does not have the valid system_txn_no, Expected is " + systemTxnNoDetail + " , but actual is ->" + systemTxnNoTransaction);
                        }
                        if (empUserId.contains(HelperBase.loggedInUser)) {
                            testStepInfo("New row added in the the Audit transaction table has the valid emp_user_id->" + empUserId);
                        } else {
                            testStepFailed("New row added in the the Audit transaction table does not have the valid emp_user_id, Expected ->" + HelperBase.loggedInUser + " But actual is " + empUserId);
                        }
                    }
                } else {
                    testStepFailed("New record is not added to the Audit Transaction table after updating record");
                }
                break;
            case "DELETE":
                AfterAuditDetailRecordsDB = maintenanceDAO.getAuditDetailTable();
                AfterAuditTransactionRecordsDB = maintenanceDAO.getAuditTransactionTable();

                // if (AfterAuditDetailRecordsDB.size() > PrevAuditDetailRecordsDB.size()) {
                if (Double.parseDouble(AfterAuditDetailRecordsDB.get(0).get("system_txn_no").toString()) > Double.parseDouble(PrevAuditDetailRecordsDB.get(0).get("system_txn_no").toString())) {
                    blnAuditDetail = true;
                    systemTxnNoDetail = AfterAuditDetailRecordsDB.get(0).get("system_txn_no").toString();
                    String systemTxnActionCode = AfterAuditDetailRecordsDB.get(0).get("system_txn_action_code").toString();
                    testStepInfo("New row is added to the Audit Detail table after Deleting record");
                    testStepInfo("system_txn_no of Audit detail table is " + systemTxnNoDetail);
                    if (systemTxnActionCode.equals("D")) {
                        testStepInfo("New row added in the Audit Detail table has the valid system_txn_action_code -> D ");
                    } else {
                        testStepFailed("New record added in the Audit Detail table does not have expected system_txn_action_code , Expected is D, But actual is->" + systemTxnActionCode);
                    }
                } else {
                    testStepFailed("New record is not added to the Audit Detail table after Deleting record");
                }
                // if (AfterAuditTransactionRecordsDB.size() > PrevAuditTransactionRecordsBD.size()) {
                if (Double.parseDouble(AfterAuditTransactionRecordsDB.get(0).get("system_txn_no").toString()) > Double.parseDouble(PrevAuditTransactionRecordsBD.get(0).get("system_txn_no").toString())) {
                    testStepInfo("New record is added to the Audit Transaction table after Deleting new record");
                    String systemTxnNoTransaction = AfterAuditTransactionRecordsDB.get(0).get("system_txn_no").toString();
                    String empUserId = AfterAuditTransactionRecordsDB.get(0).get("emp_user_id").toString();
                    if (blnAuditDetail) {
                        if (systemTxnNoTransaction.equals(systemTxnNoDetail)) {
                            testStepInfo("New row added in the the Audit transaction table has the valid system_txn_no->" + systemTxnNoDetail);
                        } else {
                            testStepFailed("New row added in the the Audit transaction table does not have the valid system_txn_no, Expected is " + systemTxnNoDetail + " , but actual is ->" + systemTxnNoTransaction);
                        }
                        if (empUserId.contains(HelperBase.loggedInUser)) {
                            testStepInfo("New row added in the the Audit transaction table has the valid emp_user_id->" + empUserId);
                        } else {
                            testStepFailed("New row added in the the Audit transaction table does not have the valid emp_user_id, Expected ->" + HelperBase.loggedInUser + " But actual is " + empUserId);
                        }
                    }
                } else {
                    testStepFailed("New record is not added to the Audit Transaction table after Deleting record");
                }
                break;
        }
    }


    public void Backspace(WebElement we) {
        try {
            we.click();
            sleep(2000);
            String value = we.getAttribute("value");
            for (int i = 0; i <= value.length() + 2; i++) {
                we.sendKeys(BACK_SPACE);
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught message is->" + ex.getMessage());
        }
    }


    public void enterText(WebElement we, String text, String elemName) {
        try {
            sleep(2000);
            clear(we);
            sleep(1000);
            if (!(we.getAttribute("value").length() == 0)) {
                Backspace(we);
            }
            we.sendKeys(HOME);
            sleep(2000);
            we.sendKeys(text);
            sleep(2000);
            testStepInfo("Entered the value in the  :" + elemName + " field :" + text);
        } catch (Exception ex) {
            testStepFailed("Exception caught while entering the value in :" + elemName + " field, message is->" + ex.getMessage());
        }
    }




//    public void enterText(String object, String text, String elemName) {
//        try {
//            WebElement we = getWebElement(object);
//            sleep(3000);
//            we.clear();
//            if (!(we.getAttribute("value").length() == 0)) {
//                Backspace(object);
//            }
//            we.sendKeys(text);
//            testStepInfo("Entered the value in the  :" + elemName + " field :" + text);
//        } catch (Exception ex) {
//            testStepFailed("Exception caught while entering the value in :" + elemName + " field, message is->" + ex.getMessage());
//        }
//    }


 public void enterText(String object, String text, String elemName) {
     try {
         sleep(2000);
         WebElement we = getWebElement(object);
         we.clear();
         jsMoveToElement(we);
         we.click();
         we.sendKeys(END);
         we.sendKeys(CONTROL + "a");
         we.sendKeys(DELETE);
         we.sendKeys(HOME);
         we.sendKeys(text);
        sleep(2000);
        testStepInfo("Entered the value in the  :" + elemName + " field :" + text);
     } catch (Exception ex) {
         testStepFailed("Exception caught while entering the value in :" + elemName + " field, message is->" + ex.getMessage());
     }
 }

    public boolean isDisabled(String object, String elementname) {
        try {
            boolean status = true;
            WebElement element = getWebElement(object);
            if (!element.isEnabled() == status) {
                testStepInfo(elementname + " is disabled as expected ");
                return true;
            } else {
                testStepFailed(elementname + " is enabled");
                return false;
            }
        } catch (Exception e) {
            String method = new Exception().getStackTrace()[0].getMethodName();
            testStepFailed("Exception Caught in method " + method + ", and Error Message is->" + e.getMessage());
            return false;
        }
    }


    public void enterKEY() {
        Actions action = new Actions(ApplicationManager.driver);
        action.sendKeys(ENTER).perform();
    }

    public boolean isEnabled(String object, String elementname) {
        try {
            boolean status = true;
            WebElement element = getWebElement(object);
            if (element.isEnabled() == status) {
                testStepInfo(elementname + " is enabled");
                return true;
            } else {
                testStepFailed(elementname + " is not working as expected ");
                return false;
            }
        } catch (Exception e) {
            String method = new Exception().getStackTrace()[0].getMethodName();
            testStepFailed("Exception Caught in method " + method + ", and Error Message is->" + e.getMessage());
            return false;
        }
    }


    public void enterInvalidUserPassword() {
        try {
            enterText(txtUsername, "sqetst98", "Username");
            enterText(txtPassword, "sqetst98", "Password");
            clickOn(btnLogin, "Login Button");
        } catch (Exception ex) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), ex);
        }
    }

    public void validateInvalidLogin(String user) {
        try {
            enterInvalidUserPassword();
            waitFor(weLoginError);
            if (isElementPresent(weLoginError)) {
                testStepPassed("Successfully validated error message for invalid user login");
                takeScreenshot();
            } else {
                testStepFailed("Error message is not displayed for invalid user login");
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught  Message is->" + ex.getMessage());
        }
    }


    public void validateDatabaseValuesDouble(String dbValue, String AppValue, String columnName) {
        if (Double.parseDouble(dbValue) == Double.parseDouble(AppValue)) {
            testStepInfo("Database column " + columnName + " has the expected value -> " + dbValue);
        } else {
            testStepFailed("Database column " + columnName + " does not have expected value, Expected is-> " + AppValue + " But actual is->" + dbValue);
        }
    }

    public void validateDatabaseValuesDate(String dbValue, String AppValue, String columnName) {
        try {
            String[] dbDates = dbValue.split(" ");
            String dbDate = dbDates[0];
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dbFormattedDate = (Date) formatter.parse(dbDate);
            SimpleDateFormat newFormat = new SimpleDateFormat("MM/dd/yyyy");
            String strDbDate = newFormat.format(dbFormattedDate);
            if (strDbDate.equals(AppValue)) {
                testStepInfo("Database column " + columnName + " has the expected value -> " + strDbDate);
            } else {
                testStepFailed("Database column " + columnName + " does not have expected value, Expected is-> " + AppValue + " But actual is->" + strDbDate);
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught  Message is->" + ex.getMessage());
        }
    }

    public void validateDatabaseValues(String dbValue, String AppValue, String columnName) {
        try {
            if (dbValue.equals(AppValue)) {
                testStepInfo("Database column " + columnName + " has the expected value -> " + dbValue);
            } else {
                testStepFailed("Database column " + columnName + " does not have expected value, Expected is-> " + AppValue + " But actual is->" + dbValue);
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught  Message is->" + ex.getMessage());
        }
    }

    public void validateDatabasevalues(String dbValue, String AppValue, String columnName) {
        try {
            if (dbValue.contains(AppValue)) {
                testStepInfo("Database column " + columnName + " has the expected value -> " + dbValue);
            } else {
                testStepFailed("Database column " + columnName + " does not have expected value, Expected is-> " + AppValue + " But actual is->" + dbValue);
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught  Message is->" + ex.getMessage());
        }
    }

    public void validateSuccessMessage() {
        validateInfoMessage("Updated successfully");
    }

    public void navigateToSubMenu(String strSubMenu) {
        sleep(2000);
        JsClickByVisibleText(strSubMenu);
        //verifyLabelDisplayed(strSubMenu,"h5");
    }

    public static String generaterandomInt(int count) {
        String value = null;
        //Random rand = new Random();
        Random rand = new Random();
        if (count == 1) {
            value = String.valueOf(rand.nextInt(9));
        } else if (count == 2) {
            value = String.valueOf(rand.nextInt(99));
        } else if (count == 3) {
            value = String.valueOf(rand.nextInt(999));
        } else if (count == 4) {
            value = String.valueOf(rand.nextInt(9999));
        } else {
            value = String.valueOf(rand.nextInt(99999));
        }
        return value;

    }


    public void validateErrorMessageForField(WebElement elem, String elemName, String errorMessage) {
        try {
            WebElement we = elem.findElement(By.xpath("./following-sibling::div[1]"));
            if (we.getText().trim().toLowerCase().contains(errorMessage.trim().toLowerCase())) {
                testStepPassed("Error message is displayed in field " + elemName + " as ->" + errorMessage);
            } else {
                testStepFailed("Error message displayed in field " + elemName + " is not valid, Expceted ->" + errorMessage + ", But actual is->" + we.getText());
            }
        } catch (Exception ex) {
            testStepFailed("Error message is not displayed in field " + elemName);
        }
    }

    public void FileUpload() throws AWTException {
        ClipboardOwner data = null;
        String filepath = "D:\\workspace\\Selenium\\MAP\\RakshithaR\\MAP\\sqe_selenium_mappricing_Merge\\UploadFile\\JsonFile.txt";
        Robot robot = new Robot();
        StringSelection attachment_path = new StringSelection(filepath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(attachment_path, data);
        robot.setAutoDelay(3000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.setAutoDelay(3000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);



    }

    public void jsScrollBack(WebElement by) {
        try {
            for (int i = 0; i <=1; i++) {
                JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
                js.executeScript("arguments[0].scrollBy(-300,0)", by);

            }
        } catch(Exception e){
            testStepFailed("Exception occured in scrollToElement() " + e.getMessage());

        }
    }

    public String generateRandomNumberwithRange(int low, int high) {
        String year = "";
        try {
            Random r = new Random();
            int result = r.nextInt(high - low) + low;
            year = String.valueOf(result);
        } catch (Exception e) {
            testStepFailed("Exception Caught in generateRandomYear()" + e.getMessage());
        }
        return year;
    }

    public void FileUpload(String filepath) throws AWTException {
        try {

            // filepath = " D:\\AutomationFHLBNY\\Sun_SQE\\Phoenix\\Eforms\\validEform3.xlsx";
            //String filepath = " D:\\AutomationFHLBNY\\Sun_SQE\\Phoenix\\eforms\\darshan.txt";
            ClipboardOwner data = null;
            WebDriver wd = getWebDriver();
            Alert alert = wd.switchTo().alert();
            alert.sendKeys(filepath);

            alert.accept();
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            //sleep(3000);
            waitForAlertDismiss();

        } catch (Exception e) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), e);
        }

    }

    public void tabOut() {
        try {
            Robot robot = new Robot();
            robot.delay(3000);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public String getTextValue(WebElement we) {
        try {
            if (isElementPresent(we)) {
                return we.getAttribute("value");
            } else {
                return "";
            }
        } catch (Exception ex) {
            testStepFailed("Unable to get the text element, message is->" + ex.getMessage());
            return "";
        }
    }


    public String validatePopUpMessage(String ExpectedMessage, String object) {
        sleep(1000);
        String appMessage = "";
        try {
            WebElement we = getWebElement(By.xpath("//div/p[contains(text(),'" + ExpectedMessage + "')]"));
            appMessage = we.getText().trim();
            if (appMessage.contains(ExpectedMessage.trim())) {
                testStepPassed("Application message -> " + appMessage + " is same as expected Message ->" + ExpectedMessage);
                if (isElementPresent(object)) {
                    sleep(4000);
                    JsClick(object, "Delete");
                    sleep(3000);
                } else if (isElementPresent(btnCancelPopup)) {
                    JsClick(btnCancelPopup, "Cancel");
                }
            } else {
                testStepFailed("Application message -> " + appMessage + " is not same as expected Message ->" + ExpectedMessage);
                if (isElementPresent(object)) {
                    JsClick(object, "Close");
                } else if (isElementPresent(btnCancelPopup)) {
                    JsClick(btnCancelPopup, "Cancel");
                }
            }
        } catch (Exception e) {
            testStepFailed("Exception occured in validatePopUpMessage()-> " + e.getMessage());
        }
        return appMessage;
    }



    public void deleteData(String object) {
        try {

            WebElement we = getWebElement(object);
            // By locator = By.xpath(obj.getProperty(object));
            we.findElement(By.xpath(object)).sendKeys(CONTROL);
            we.findElement(By.xpath(object)).sendKeys(HOME);

            //  we.findElement(locator).sendKeys(Keys.HOME);
            for (int i = 0; i <= 10; i++) {
                // we.findElement(locator).sendKeys(Keys.DELETE);
                we.findElement(By.xpath(object)).sendKeys(DELETE);

            }
        } catch (Exception e) {
            String method = new Exception().getStackTrace()[0].getMethodName();
            testStepFailed("Exception Caught in method " + method + ", and Error Message is->" + e.getMessage());
        }
    }


    public void selectsRecordFromTable(String objTable, int rowNum) {
        WebElement tbl = getWebElement(objTable);
        List<WebElement> weRows = tbl.findElements(By.xpath("./tbody/tr"));
        int rowCount = weRows.size();
        if (rowCount >= rowNum) {
            JsClick(weRows.get(rowNum - 1), rowNum + " row in the table");
        } else {
            testStepFailed("Total row count is only " + rowCount + " , hence row " + rowNum + " is not available");
        }
    }


    public void clear(WebElement we) {
        try {
            we.clear();
        } catch (Exception ex) {

        }
    }


//    public Boolean validatesInfoMessage(String expMessage) {
//        String infoMessage = getToasterMessage();
//        if (infoMessage.toLowerCase().contains(expMessage.toLowerCase())) {
//            testStepInfo("Successfully validated the message-> " + expMessage);
//            takeScreenshot();
//            return true;
//        } else {
//            testStepFailed("Expected Message->" + expMessage + " is not displayed,instead " + infoMessage + " is displayed");
//            return false;
//        }
//    }


//    public String getToasterMessage() {
//        try {
//            String message = getText(getWebElement(weToasterMessage));
//            return message;
//        } catch (NoSuchElementException e) {
//            return "";
//        }
//    }

    public String generateRandomAmount() {
        String year = "";
        try {



            Random r = new Random();
            int low = 1000;
            int high = 999999;
            int result = r.nextInt(high - low) + low;
            year = String.valueOf(result);
        } catch (Exception e) {
            testStepFailed("Exception Caught in generateRandomYear()" + e.getMessage());
        }
        return year;
    }



    public int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }


    public  void getDatafromfieldsAndEnter(String object) {
        try{
            //By locator = wd.findElement(xpath(obj.getProperty(object)));
            // String existingText = wd.findElement(locator).getAttribute("value");
            String existingText = gettextValue(getWebElement(object));
            if (!existingText.isEmpty()) {


                // wd.findElement(locator).sendKeys(Keys.TAB);
                enterText(object,existingText,"");
                sleep(2000);
            }
        }catch (Exception e) {
            String method = new Exception().getStackTrace()[0].getMethodName();
            testStepFailed("Exception Caught in method " + method + ", and Error Message is->" + e.getMessage());
        }
    }

    public void validateAddEditBreadCrumb(String option) {
        if (isElementPresent(breadcrumbE)) {
            WebElement add = wd.findElement(xpath("//nav[@aria-label='breadcrumb']//ol/li/a[contains(text(),'" + option + "')]"));
            String text = add.getText();
            if (text.contains(option)) {
                testStepPassed("Successfully validated " + option + " breadcrumb link in the page");
                takeScreenshot();
            } else {
                testStepFailed("Failed to validate " + option + " breadcrumb link in the page");
            }
        } else {
            testStepFailed("BreadCrumb link is not displayed as expected");
        }
    }

    public String buildQuery(String tableName, HashMap<String, String> record) {
        String query = "Select * from " + tableName + " where ";
        for (Map.Entry<String, String> entry : record.entrySet()) {
            if (!entry.getValue().trim().equals("")) {
                query = query + entry.getKey() + "= '" + entry.getValue() + "' and ";
            }
        }
        if (query.endsWith("and ")) {
            query = query.substring(0, query.length() - 4);
        }
        if(query.endsWith("where ")){
            query = query.substring(0,query.length()-6);
        }
        return query;
    }


    public  void enterKEY(WebElement we){
        we.sendKeys(ENTER);
    }


    public void clickOnShowDropDown(String Value) {
        if (isElementPresent(drpShow)) {
            sleep(2000);
            WebElement entriesText = getWebElement(xpath("//div[@class='row']//div[contains(text(),'Showing')]"));
            String count = entriesText.getText();
            String arr[] = count.split(" ");
            int totalCount = Integer.valueOf(arr[5]);
            int value = Integer.valueOf(Value);
            if (totalCount > value) {
//                selectFromDropdownInTs(drpShow, "Show DropDown", Value);
                sleep(2000);
                selectFromDropdown(drpShow,Value,"Show DropDown");
                testStepPassed("Successfully selected the show drop down value " + Value);
            } else {
                testStepInfo("Show Drop down cannot be validated for: " + Value + " as the records are: " + totalCount);
            }
        } else
            testStepFailed("Failed to select the show drop down value " + Value);
    }


    public int calculatePgmId(String pgmName) {
        int pgmId = 0;
        switch (pgmName) {
            case "First Home Club": {
                pgmId=1;
                break;
            }
            case "Homebuyer Dream Program": {
                pgmId=2;
                break;
            }

            default:
                testStepInfo("Invalid Field Name");

        }
        return pgmId;
    }



    public  void selectTheProgramType(String ProgramType){
        sleep(1000);
        WebElement elem = getWebElement(ProgramType);
        if (ProgramType.equalsIgnoreCase("Homebuyer Dream Program")) {
            elem.click();
            System.out.println("Clicked and now going down element /n");
            elem.sendKeys(ENTER);
        } else if (ProgramType.equalsIgnoreCase("First Home Club")) {
            System.out.println("Clicking on element/n");
            elem.click();
            System.out.println("Clicked and now going up element/n");
            elem.sendKeys(UP);
            elem.sendKeys(ENTER);
        } else if (ProgramType.equalsIgnoreCase("None")) {
            testStepInfo("Data is not hidden in both set-aside program");
        }
    }


    public void validateViolationErrorMessage(String object, String expMessage, String flagObject, String ExpectedFlag) {
        try {
            String message = getWebElement(object).getText();
            if (message.toLowerCase().contains(expMessage.toLowerCase())) {
                testStepPassed("Expected message is Displayed ->" + message);
                takeScreenshot();
            } else {
                testStepFailed("Expected Message->" + expMessage + " is not displayed,instead " + message + " is displayed");

            }
            String flag = getWebElement(flagObject).getText();
            if (flag.toLowerCase().contains(ExpectedFlag.toLowerCase())) {
                testStepPassed("Expected Flag is Displayed ->" + flag);
                takeScreenshot();
            } else {
                testStepFailed("Expected Flag->" + ExpectedFlag + " is not displayed,instead " + flag + " is displayed");

            }
        }catch (Exception e){
            String method = new Exception().getStackTrace()[0].getMethodName();
            testStepFailed("Exception Caught in method "+method +", and Error Message is->"+e.getMessage());
        }

    }

    public String getValueFromAnExcel(String filePath, String sheetName, int rowNo, String cellReference) throws InterruptedException {
        String cellValue="";
        try {
            //Read the spreadsheet that needs to be updated
            FileInputStream fsIP = new FileInputStream(new File(filePath));

//Access the workbook
            Workbook wb = WorkbookFactory.create(fsIP);
//Access the worksheet, so that we can update / modify it.
            Sheet worksheet = wb.getSheet(sheetName);
            Row r = worksheet.getRow(rowNo);
// declare a Cell object
            Cell cell = null;
// Access the second cell in second row to update the value
            cell = r.getCell(CellReference.convertColStringToIndex(cellReference));
            System.out.println("Cell\t" + cell);
            cellValue =cell.getStringCellValue ();
//Close the InputStream
            fsIP.close();
            //close the workbook
            wb.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return cellValue;
    }


//    public String getNextBusinessDate(String date) {
//        String nextBusineesDate = "";
//        Date dates1 = java.sql.Date.valueOf("2/02/2020");
//            SimpleDateFormat newFormate = new SimpleDateFormat("MM/dd/yyyy");
//            String nextBusineesDate1 = newFormate.format(dates1);
//            LocalDate convertedDate1 = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
//            LocalDate nextDate = convertedDate1.minusDays(Integer.parseInt(getRandomNumbericString(1)));
//            Date nextDateFormatted = Date.from(nextDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//            String[] dayname = nextDateFormatted.toString().split(" ");
//            if (dayname[0].equals("Sat")) {
//                nextDate = nextDate.plusDays(2);
//                Date dates = java.sql.Date.valueOf(nextDate);
//                SimpleDateFormat newFormat = new SimpleDateFormat("MM/dd/yyyy");
//                nextBusineesDate = newFormat.format(dates);
//            } else if (dayname[0].equals("Sun")) {
//                // c.add(Calendar.DAY_OF_MONTH, 7);
//                nextDate = nextDate.plusDays(1);
//                Date dates = java.sql.Date.valueOf(nextDate);
//                SimpleDateFormat newFormat = new SimpleDateFormat("MM/dd/yyyy");
//                nextBusineesDate = newFormat.format(dates);
//            } else {
//                SimpleDateFormat newFormat = new SimpleDateFormat("MM/dd/yyyy");
//                Date dates = java.sql.Date.valueOf(nextDate);
//                nextBusineesDate = newFormat.format(dates);
//            }
//        } catch (Exception ex) {
//            testStepFailed("Exception caught  Message is->" + ex.getMessage());
//        }
//        return nextBusineesDate;
//    }


  /*  LocalDate convertedDate1;
    public String getNextBusinessDate(String date) {
        String nextBusineesDate = "";
        try{
            LocalDate date1 =date;
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
            String text = date1.format(formatters);
            LocalDate parsedDate = LocalDate.parse(text, formatters);
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
            String text = date.format(formatters);
            LocalDate parsedDate = LocalDate.parse(text, formatters);
            LocalDate convertedDate1 = LocalDate.(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            DateTimeFormatter formatters1 = DateTimeFormatter.ofPattern("d/MM/uuuu");
            String text = convertedDate1.format(formatters1);
            LocalDate convertedDate1 = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

            LocalDate nextDate = convertedDate1.plusDays(1);
            Date nextDateFormatted = Date.from(nextDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            String[] dayname = nextDateFormatted.toString().split(" ");
            if (dayname[0].equals("Sat")) {
                nextDate = nextDate.plusDays(2);
                Date dates = java.sql.Date.valueOf(nextDate);
                SimpleDateFormat newFormat = new SimpleDateFormat("MM/dd/yyyy");
                nextBusineesDate = newFormat.format(dates);
            } else if (dayname[0].equals("Sun")) {
                // c.add(Calendar.DAY_OF_MONTH, 7);
                nextDate = nextDate.plusDays(1);
                Date dates = java.sql.Date.valueOf(nextDate);
                SimpleDateFormat newFormat = new SimpleDateFormat("MM/dd/yyyy");
                nextBusineesDate = newFormat.format(dates);
            } else {
                SimpleDateFormat newFormat = new SimpleDateFormat("MM/dd/yyyy");
                Date dates = java.sql.Date.valueOf(nextDate);
                nextBusineesDate = newFormat.format(dates);
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught  Message is->" + ex.getMessage());
        }
        return nextBusineesDate;
    }
*/
  public String getNextBusinessDate(String date) {
      String nextBusineesDate = "";
      try{
          LocalDate convertedDate1 = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
          LocalDate nextDate = convertedDate1.plusDays(1);
          Date nextDateFormatted = Date.from(nextDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
          String[] dayname = nextDateFormatted.toString().split(" ");
          if (dayname[0].equals("Sat")) {
              nextDate = nextDate.plusDays(2);
              Date dates = java.sql.Date.valueOf(nextDate);
              SimpleDateFormat newFormat = new SimpleDateFormat("MM/dd/yyyy");
              nextBusineesDate = newFormat.format(dates);
          } else if (dayname[0].equals("Sun")) {
              // c.add(Calendar.DAY_OF_MONTH, 7);
              nextDate = nextDate.plusDays(1);
              Date dates = java.sql.Date.valueOf(nextDate);
              SimpleDateFormat newFormat = new SimpleDateFormat("MM/dd/yyyy");
              nextBusineesDate = newFormat.format(dates);
          } else {
              SimpleDateFormat newFormat = new SimpleDateFormat("MM/dd/yyyy");
              Date dates = java.sql.Date.valueOf(nextDate);
              nextBusineesDate = newFormat.format(dates);
          }
      } catch (Exception ex) {
          testStepFailed("Exception caught  Message is->" + ex.getMessage());
      }
      return nextBusineesDate;
  }
//  public String getTheBusinessDate(String date) {
//      String newDate = "";
//
//      try {
//          Calendar cal = Calendar.getInstance();
//          SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//
//          cal.setTime(sdf.parse(date));
//
//          if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
//              cal.add(Calendar.DATE, 2);
//              newDate = sdf.format(cal.getTime());
//
//          } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
//              cal.add(Calendar.DATE, 3);
//              newDate = sdf.format(cal.getTime());
//          } else {
//              cal.add(Calendar.DATE, 1);
//              newDate = sdf.format(cal.getTime());
//          }
//
//      } catch (ParseException e) {
//          System.out.println(e.getMessage());
//      }
//      return newDate;
//  }


    public void validateNoErrorMessageForField(WebElement elem, String elemName){
        try {
            List<WebElement> wes = elem.findElements(By.xpath("./following-sibling::div/div[1]"));
            if(wes.size()==0){
                testStepPassed("Error message is not displayed in field "+elemName+" as expected");
            }else{
                testStepFailed("Error message displayed in field "+elemName+" ,Message is ->"+wes.get(0).getText());
            }
        }catch (Exception ex){
            testStepException(new Exception().getStackTrace()[0].getMethodName(),ex);
        }
    }

    //-----------------------------

    public void selectRecordFromTableValue(String Value) {
//        WebElement tbl = getWebElement(objTable);
        waitTillLoadingCompleted();
        List<WebElement> weRows = getWebElements("//tbody/tr/td[text()='" + Value + "']");
        int rowCount = weRows.size();
        if (rowCount == 1) {
//            clickOn(getWebElementByText("//tbody/tr/td[text()='" + Value + "']"), Value + " in the table");
            clickOn(getWebElement("//tbody/tr/td[text()='" + Value + "']"),Value + " in the table");
        } else {
            testStepFailed("Total row count is only " + rowCount + " , hence row " + Value + " is not available");
        }
    }

    public void validateSortingFunctionalityAllColumns(String AppName){
        try {
            sleep(2000);
            List<WebElement> ls = getWebElements(By.xpath("//thead/tr/th/span[1]"));

            int size = ls.size() ;

            for (int i = 1; i <= size; i++) {

                ValidateSortingFunctionality(i,ls.get(i).getText(),"STRING",AppName);
            }

        }catch (Exception e){
            testStepFailed("Error in sorting functionality "+e.getMessage());
        }

    }

    public void clickOnClosePopup(){
        sleep(2000);
        jsClick(btnClosePopUp,"Close PopUP");

    }

    public void validateMandatoryFieldErrorMessages() {
        clickOnSaveButton();
        sleep(2000);
        try {
            List<WebElement> we = getWebElements(By.xpath("//div[@class='position-absolute ng-star-inserted']"));

            for (WebElement e : we) {
                String text = e.getText();
                if (text != null) {
                    testStepInfo("Expected Error message is : " + text);
                } else {
                    testStepFailed("Error message is : " + text);
                }
            }

        } catch (Exception e) {
            testStepFailed("Exception Error message is " + e.getStackTrace());
        }

    }




public void validateGeneralComponentsInConfigFunding() {
        try {
            verifyElementDisplayed(webreadcrumb, "breadcrumb");
            verifyElementDisplayed(btnRefresh, "Refresh Button");
            verifyElementDisplayed(lnkFirstPage, "First Page Link");
            verifyElementDisplayed(lnkNextPage, "Next Page Link");
            verifyElementDisplayed(lnkPreviousPage, "Previous Page Link");
            verifyElementDisplayed(lnkLastPage, "Last Page Link");
            if (isElementPresent(drpShowEntry)) {
                String strValue = getSelectedValueFromDropdown(drpShowEntry);
                if (strValue.contains("10")) {
                    testStepInfo("Show dropdown is displayed and default value is set to 10 as expected");
                } else {
                    testStepFailed("Show dropdown is displayed and default value is not set to 10");
                }
            } else {
                testStepFailed("Show dropdown is not displayed");
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while validating the generic elements in page, Message is->" + ex.getMessage());
        }
    }

    public void validateErrorMessageForFields(WebElement elem, String elemName, String errorMessage) {
        try {
            if (elem.getText().trim().toLowerCase().contains(errorMessage.trim().toLowerCase())) {
                testStepPassed("Error message is displayed in field " + elemName + " as ->" + errorMessage);
            } else {
                testStepFailed("Error message displayed in field " + elemName + " is not valid, Expceted ->" + errorMessage + ", But actual is->" + elem.getText());
            }
        } catch (Exception ex) {
            testStepFailed("Error message is not displayed in field " + elemName);
        }
    }



    public void validateGeneralComponentsInReportsPage() {
        try {
            verifyElementDisplayed(webreadcrumb, "breadcrumb");
            verifyElementDisplayed(btnCSV, "CSV Button");
            verifyElementDisplayed(btnXLS, "XLS Button");
            verifyElementDisplayed(btnPDF, "PDF Button");
            verifyElementDisplayed(btnRefresh, "Refresh Button");
            verifyElementDisplayed(lnkFirstPage, "First Page Link");
            verifyElementDisplayed(lnkNextPage, "Next Page Link");
            verifyElementDisplayed(lnkPreviousPage, "Previous Page Link");
            verifyElementDisplayed(lnkLastPage, "Last Page Link");
            verifyElementDisplayed(btnSearch, "Search Button");
            verifyElementDisplayed(btnReset, "Reset Button");
            if (isElementPresent(drpShowEntry)) {
                String strValue = getSelectedValueFromDropdown(drpShowEntry);
                if (strValue.contains("10")) {
                    testStepInfo("Show dropdown is displayed and default value is set to 10 as expected");
                } else {
                    testStepFailed("Show dropdown is displayed and default value is not set to 10");
                }
            } else {
                testStepFailed("Show dropdown is not displayed");
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while validating the generic elements in page, Message is->" + ex.getMessage());
        }
    }

 public  void  verifyDropdownValue(String object, String data) {
        try {
            Select weSel = new Select(getWebElement(object));
            List<WebElement> options = weSel.getOptions();
            for (WebElement item : options) {
                String existingText = item.getText();
                if (data != null) {
                    if (data.equals(existingText)) {
                        testStepPassed("Added data verified Sucessfully ->" + existingText);
                    }
                }
            }



        }catch (Exception e) {
            String method = new Exception().getStackTrace()[0].getMethodName();
            testStepFailed("Exception Caught in method " + method + ", and Error Message is->" + e.getMessage());
        }



    }
    public void openReport(String type) {
        try {
            if (type.equalsIgnoreCase("PDF")){
                Robot robot = new Robot();


                robot.delay(5000);
                robot.keyPress(KeyEvent.VK_ALT);
                robot.keyPress(KeyEvent.VK_N);
                robot.delay(1000);
                robot.keyRelease(KeyEvent.VK_ALT);
                robot.keyRelease(KeyEvent.VK_N);
                robot.delay(1000);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                robot.delay(3000);
                enterDataToExcel();
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.keyPress(KeyEvent.VK_W);
                robot.delay(1000);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_SHIFT);
                robot.keyRelease(KeyEvent.VK_W);


            }else{
                Robot robot = new Robot();


                robot.delay(5000);
                robot.keyPress(KeyEvent.VK_ALT);
                robot.keyPress(KeyEvent.VK_N);
                robot.delay(1000);
                robot.keyRelease(KeyEvent.VK_ALT);
                robot.keyRelease(KeyEvent.VK_N);
                robot.delay(1000);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                robot.delay(3000);
                enterDataToExcel();



                robot.keyPress(KeyEvent.VK_ALT);
                robot.keyPress(KeyEvent.VK_SPACE);
                robot.keyPress(KeyEvent.VK_C);


            }} catch (Exception ex) {
            ex.getStackTrace();


        }
    }
    public void enterDataToExcel(){
        String excelFilePath = "C:\\Users\\o-gowdat\\Downloads\\Trade_Browser.csv";

        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheetAt(0);

            Object[][] bookData = {
                    {"GN", 30, "C","NY","HDFC","Test","Test2","Test3","Test4"},

            };

            int rowCount = sheet.getLastRowNum();

            for (Object[] aBook : bookData) {
                Row row = sheet.createRow(++rowCount);

                int columnCount = 0;
                Cell cell = row.createCell(columnCount);

                // cell.setCellValue(rowCount);

                for (Object field : aBook) {
                    cell = row.createCell(columnCount++);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    }
                }

            }

            inputStream.close();

            FileOutputStream outputStream = new FileOutputStream("C:\\Users\\rakshithar\\Desktop\\Geeks.xlsx");
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public void validateColumnNamesinTableForReports(WebElement table, List<String> coloumNames) {
        try {
            boolean blnPass = true;
            List<WebElement> weColumnNames = table.findElements(By.xpath(".//thead/tr/th/span[1]//following::span[2]"));
            if ((weColumnNames.size()) == coloumNames.size()) {
                for (int i = 0; i < coloumNames.size(); i++) {
                     WebElement we = weColumnNames.get(i);
                    if (getText(we).contains(coloumNames.get(i))) {
                        testStepInfo("Column Name : " + coloumNames.get(i) + " is displayed as expected in Column Number " + (i + 1));
                    } else {
                        blnPass = false;
                        testStepFailed("Column Name : " + coloumNames.get(i) + " is not displayed in Column Number " + (i + 1));
                    }
                }
                if (blnPass) {
                    testStepPassed("All the required columns are displayed in table as expected");
                }
            } else {
                testStepFailed("All the required columns are not displayed in table, Expected Columns is " + coloumNames.size() + ", But actual is ->" + weColumnNames.size());
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while validating the generic elements in page, Message is->" + ex.getMessage());
        }
    }
    public void jsMoveToElement(WebElement by) {
        try {

            JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
            js.executeScript("arguments[0].scrollIntoView()", by);
        } catch (Exception e) {
            testStepFailed("Exception occured in scrollToElement() " + e.getMessage());
        }
    }

    public void jsScroll(WebElement by) {
        try {
            for (int i = 0; i <=1; i++) {
                JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
                js.executeScript("arguments[0].scrollBy(300,0)", by);

            }
        } catch(Exception e){
            testStepFailed("Exception occured in scrollToElement() " + e.getMessage());

        }
    }
    public  void verifyPlaceHolder(String object, String Elementname) {
        try {
            WebElement we = getWebElement(object);
            if(we.getAttribute("placeholder").contains("Enter")){
                sleep(2000);
                String existingText = we.getAttribute("placeholder");
                testStepPassed("Expected message is Displayed ->" + existingText);
            }
        } catch (Exception e) {
            String method = new Exception().getStackTrace()[0].getMethodName();
            testStepFailed("Exception Caught in method " + method + ", and Error Message is->" + e.getMessage());
        }
    }

    public void ValidateSortingFunctionalityPGR(int columnNumber, String ColumName, String Type, String AppName) {
        try {
            while (true) {
                if (isElementPresent(By.xpath("//span[@class='ag-header-cell-text' and text()='" + ColumName+ "']"))) {
                    break;
                } else {
                    WebElement ele2 = getWebElement(By.xpath("//div[@class='ag-body-horizontal-scroll-viewport']"));
                    jsScroll(ele2);
                    sleep(2000);
                }
            }
        } catch (Exception e) {
            testStepFailed(e.getMessage());
        }
        try {
            WebDriver wd = getWebDriver();
            List<WebElement> row = wd.findElements(By.xpath("//div[@class='ag-root ag-unselectable ag-layout-auto-height']"));
            int i = row.size();
            if (i > 0) {
                sleep(3000);
                By bySort = By.xpath("//span[@class='ag-header-cell-text' and text()='" + ColumName + "']");
                if (!getClass(getWebElement(bySort)).contains("asc")) {
                    JsClick(getWebElement(bySort), "Ascending");
                }
                sleep(5000);
                validateAscendingOrderPGR(columnNumber, ColumName, Type);
                if (!getClass(getWebElement(bySort)).contains("desc")) {
                    JsClick(getWebElement(bySort), "Descending");
                }
                sleep(5000);
                validateDescendingOrder(columnNumber, ColumName, Type);
                sleep(3000);
            } else {
                testStepInfo("No records found,Hence Sorting functionality cannot be validated");

            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while validating the sorting functionality, Message is ->" + ex.getMessage());
        }
    }

    public void validateAscendingOrderPGR(int columnNumber, String ColumName, String Type) {
        try {
            WebDriver wd = getWebDriver();
            int value = columnNumber + 1;
            WebElement icon = wd.findElement(By.xpath("//span[@class='ag-icon ag-icon-asc']"));
            if (!icon.getAttribute("class").contains("asc")) {
//                testStepInfo(icon.getAttribute("class").toString());
                testStepFailed(ColumName + " column's icon is not changed to Ascending" );
            }
            List<WebElement> defaultRow = wd.findElements(By.xpath("//div[contains(@class,'table')]/table//tr"));
//            int itCount = 10;
            int itCount=defaultRow.size() - 1;
            List<WebElement> elementName = new LinkedList<>();
            if (Type == "STRING") {
                ArrayList<String> obtainedEleList = new ArrayList<>();
                ArrayList<String> resultEleNameList = new ArrayList<>();
                elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                for (int i = 0; i < itCount; i++) {
                    elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                    //String newelementName = (String) ((JavascriptExecutor) wd).executeScript("return arguments[0].text;", elementName.get(i));
                    String newelementName = elementName.get(i).getText().trim();
                    if (!newelementName.trim().equals("")) {
                        obtainedEleList.add(newelementName.toUpperCase().trim());
                        resultEleNameList.add(newelementName.toUpperCase().trim());
                        // obtainedEleList.add(newelementName);
                        //resultEleNameList.add(newelementName);
                    }
                }
                Collections.sort(obtainedEleList);
                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Ascending order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Ascending order");

                }
            }
            if (Type == "STRINGCUST") {
                ArrayList<String> obtainedEleList = new ArrayList<>();
                ArrayList<String> resultEleNameList = new ArrayList<>();
                elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                for (int i = 0; i < itCount; i++) {
                    elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                    //String newelementName = (String) ((JavascriptExecutor) wd).executeScript("return arguments[0].text;", elementName.get(i));
                    String newelementName = elementName.get(i).getText().trim();
                    if (!newelementName.trim().equals("")) {
                        obtainedEleList.add(newelementName);
                        resultEleNameList.add(newelementName);
                    }
                }
                Collections.sort(obtainedEleList);
                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Ascending order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Ascending order");

                }
            } else if (Type == "INTEGER") {
                ArrayList<Integer> obtainedEleList = new ArrayList<>();
                ArrayList<Integer> resultEleNameList = new ArrayList<>();
                elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                int count = elementName.size();
                for (int i = 0; i < itCount; i++) {
                    elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                    String newelementName1 = elementName.get(i).getText().trim();
                    if (newelementName1.contains("-")) {
                        newelementName1 = newelementName1.split("-")[0];
                    }
                    String newelementName = newelementName1.replaceAll(",", "");
                    if (!newelementName.equals("")) {
                        obtainedEleList.add(Integer.parseInt(newelementName));
                        resultEleNameList.add(Integer.parseInt(newelementName));
                    }
                }
                Collections.sort(obtainedEleList);
                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Ascending  order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Ascending order");

                }
            } else if (Type == "DOUBLE") {
                ArrayList<Double> obtainedEleList = new ArrayList<>();
                ArrayList<Double> resultEleNameList = new ArrayList<>();
                elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                int count = elementName.size();
                for (int i = 0; i < itCount; i++) {
                    elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                    String newelementName2 = elementName.get(i).getText().trim();
                    String newelementName1 = newelementName2.replaceAll(",", "");
                    String newelementName = newelementName1.replace("$", "").replace(")", "").replace("(", "-");
                    if (!newelementName.equals("")) {
                        obtainedEleList.add(Double.valueOf(newelementName));
                        resultEleNameList.add(Double.valueOf(newelementName));
                    }
                }
                Collections.sort(obtainedEleList);
                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Ascending  order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Ascending order");

                }
            } else if (Type == "DATE") {
                ArrayList<Date> obtainedEleList = new ArrayList<>();
                ArrayList<Date> resultEleNameList = new ArrayList<>();
                elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                int count = elementName.size();
                for (int i = 0; i < itCount; i++) {
                    elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                    String newelementName = elementName.get(i).getText().trim();
                    SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
                    if (!newelementName.equals("")) {
                        try {
                            obtainedEleList.add(date.parse(newelementName));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        try {
                            resultEleNameList.add(date.parse(newelementName));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }

                Collections.sort(obtainedEleList);
                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Ascending order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Ascending order");

                }
            } else if (Type == "DATETIME") {
                ArrayList<Date> obtainedEleList = new ArrayList<>();
                ArrayList<Date> resultEleNameList = new ArrayList<>();
                elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                int count = elementName.size();
                for (int i = 0; i < itCount; i++) {
                    elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]"));
                    String newelementName = elementName.get(i).getText().trim();
                    boolean flag = true;
                    SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
                    if (!newelementName.equals("")) {
                        try {
                            resultEleNameList.add((date.parse(newelementName)));
                            obtainedEleList.add((date.parse(newelementName)));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                Collections.sort(resultEleNameList);
                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Ascending order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Ascending  order");

                }
            } else if (Type == "DROPDOWN") {
                ArrayList<String> obtainedEleList = new ArrayList<>();
                ArrayList<String> resultEleNameList = new ArrayList<>();
                elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]/select"));
                for (int i = 0; i < itCount; i++) {
                    elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]/select"));
                    String newelementName = getSelectedOptionFromDropdown(elementName.get(i));
                    if (!newelementName.trim().equals("")) {
                        obtainedEleList.add(newelementName.toUpperCase());
                        resultEleNameList.add(newelementName.toUpperCase());
                    }
                }
                Collections.sort(obtainedEleList);
                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Ascending order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Ascending order");

                }
            }
            if (Type == "CHECKBOX") {
                ArrayList<String> obtainedEleList = new ArrayList<>();
                ArrayList<String> resultEleNameList = new ArrayList<>();
                elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]/input"));
                for (int i = 0; i < itCount; i++) {
                    elementName = wd.findElements(By.xpath("//table[contains(@class,'table table-bordered')]//tr/td[" + value + "]/input"));
                    String newelementName;
                    if (elementName.get(i).isSelected()) {
                        newelementName = "B";
                    } else {
                        newelementName = "A";
                    }
                    if (!newelementName.equals("")) {
                        obtainedEleList.add(newelementName.toUpperCase());
                        resultEleNameList.add(newelementName.toUpperCase());
                    }
                }
                Collections.sort(obtainedEleList);

                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Descending order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Descending order");

                }
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while validating the Ascending sorting functionality for column " + ColumName + ", Message is ->" + ex.getMessage());
        }
    }

    public String validatePopUpMessagePGR(String ExpectedMessage, String object) {
        sleep(5000);
        String appMessage = "";
        try {
            WebElement we = getWebElement(By.xpath("//div/h5[contains(text(),'" + ExpectedMessage + "')]"));
            appMessage = we.getText().trim();
            if (appMessage.contains(ExpectedMessage.trim())) {
                testStepPassed("Application message -> " + appMessage + " is same as expected Message ->" + ExpectedMessage);
                if (isElementPresent(object)) {
                    sleep(4000);
                    JsClick(object, "Close");
                    takeScreenshot();
                    sleep(3000);
                } else if (isElementPresent(btnCancelPopup)) {
                    JsClick(btnCancelPopup, "Cancel");
                }
            } else {
                testStepFailed("Application message -> " + appMessage + " is not same as expected Message ->" + ExpectedMessage);
                if (isElementPresent(object)) {
                    JsClick(object, "Close");
                } else if (isElementPresent(btnCancelPopup)) {
                    JsClick(btnCancelPopup, "Cancel");
                }
            }
        } catch (Exception e) {
            testStepFailed("Exception occured in validatePopUpMessage()-> " + e.getMessage());
        }
        return appMessage;
    }

    public void clickOnStatusOption(String object , String element) {
        try {
            sleep(3000);
            clickOn(weEllipse, "Ellipse");
            if (isEnabled(object)) {
                clickOn(object,element);
                takeScreenshot();
            }else {
                testStepFailed(element + " is not enabled as expected");
            }
        } catch (Exception ex) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), ex);
        }
    }


    public void doubleClickOnRecord() {
        WebDriver wd = getWebDriver();
        Actions actions = new Actions(wd);
        try {
            sleep(2000);
           List<WebElement> we1 = getWebElements(By.xpath("(//div[@col-id='SecurityDesc'])"));
           WebElement we = getWebElement(By.xpath("(//div[@ref='eBodyViewport']//div[@col-id='SecurityDesc'])[1]"));
           int length=we1.size();
            if (length>2)
            {
                WebElement we2 = getWebElement(By.xpath("(//div[@ref='eBodyViewport']//span[@ref='eValue'])[2]"));
                jsMoveToElement(we2);
                actions.doubleClick(we2).build().perform();
            }
            jsMoveToElement(we);
            actions.doubleClick(we).build().perform();
            sleep(2000);
            testStepPassed("Successfully Click on to the Record");
        } catch (Exception ex) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), ex);
        }
    }
    public void doubleClickOnRecordOnTrade() {
        WebDriver wd = getWebDriver();
        Actions actions = new Actions(wd);
        try {
            sleep(2000);
            WebElement we = getWebElement(By.xpath("(//div[@ref='eBodyViewport']//span[@ref='eValue'])[2]"));
            jsMoveToElement(we);
            actions.doubleClick(we).build().perform();
            testStepPassed("Successfully Click on to the Record");
        } catch (Exception ex) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), ex);
        }
    }
    public void FileUploads(String filepath) throws AWTException {
        File filename = new File(filepath);
        uploadingfilename = filename.getName();
//        ClipboardOwner data = null;
//        String filepath = "D:\\workspace\\Selenium\\MAP\\RakshithaR\\MAP\\sqe_selenium_mappricing_Merge\\UploadFile\\JsonFile.txt";
        Robot robot = new Robot();
        StringSelection attachment_path = new StringSelection(filepath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(attachment_path, null);
        robot.setAutoDelay(3000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.setAutoDelay(3000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);


    }
    public void multipleFileUploads(String filepath) throws AWTException {
//        File filename = new File(filepath);
//        uploadingMultiplefilename = filename.getName();
        pathMultiple = filepath;
        ClipboardOwner data = null;
//        String filepath = "D:\\workspace\\Selenium\\MAP\\RakshithaR\\MAP\\sqe_selenium_mappricing_Merge\\UploadFile\\JsonFile.txt";
        Robot robot = new Robot();
        StringSelection attachment_path = new StringSelection(filepath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(attachment_path, data);
        robot.setAutoDelay(3000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.setAutoDelay(3000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        for (int i = 0; i <9; i++) {
            robot.setAutoDelay(1000);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        }

        robot.setAutoDelay(3000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_A);
        robot.setAutoDelay(3000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }

    public void verifygetTextValue(String Object,String ExpectedValue,String ElementName)
    {
        try {
            scroll(Object);
            sleep(2000);
            String actualValue;

            if(ElementName.contains("Trade Date")||ElementName.contains("Event Date")||ElementName.contains("Settlement Date"))
            {
                String actualValue1 = getWebElement(Object).getText();

                actualValue = removeZeroesAppended(actualValue1)  ;
            }else{
                actualValue = getWebElement(Object).getText();
            }

            if(actualValue.contains(ExpectedValue))
            {
                testStepPassed("Value present in "+ElementName+" is matching with Expected value ");
            }else {
                testStepFailed("Value present in "+ElementName+" is not matching with expected value  actual-->"+actualValue+" but expected---->"+ExpectedValue);
            }

        } catch (Exception e) {
            testStepFailed("Exception caught while verifying the Text value");
        }


    }

    public void ScrollHarizontallyLeftTillElement(String ScrollBarObject,String Element) {
        WebDriver wd = getWebDriver();
        Actions actions = new Actions(wd);
        while (true) {
            if (isElementPresent(By.xpath(Element))) {
                break;
            } else {
                WebElement ele2 = getWebElement(By.xpath(ScrollBarObject));
                jsScrollToLeft(ele2);
                sleep(2000);
            }
        }

    }

    public void ScrollHarizontallyTillElement(String ScrollBarObject,String Element) {
        WebDriver wd = getWebDriver();
        Actions actions = new Actions(wd);
        while (true) {
            if (isElementPresent(By.xpath(Element))) {
                break;
            } else {
                WebElement ele2 = getWebElement(By.xpath(ScrollBarObject));
                jsScroll(ele2);
                sleep(2000);
            }
        }

    }
    public void jsScrollToLeft(WebElement by) {
        try {
            for (int i = 0; i <=1; i++) {
                JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
                js.executeScript("arguments[0].scrollBy(-300,0)", by);

            }
        } catch(Exception e){
            testStepFailed("Exception occured in scrollToElement() " + e.getMessage());

        }
    }
    public void verifyAttributeValue(String Object,String ExpectedValue,String ElementName)
    {
        try {
            scroll(Object);
            sleep(1000);
            String actualValue = getWebElement(Object).getAttribute("value");

            if(!actualValue.equalsIgnoreCase(""))
            {
                if(actualValue.contains(ExpectedValue))
                {
                    testStepPassed("Value present in "+ElementName+" is matching with Expected value ");
                }else {
                    testStepFailed("Value present in "+ElementName+" is not matching with expected value because actual value displayed is-->"+actualValue+" but expected--->"+ExpectedValue);
                }
            }
            else {
                testStepFailed("There is no value inside the "+ElementName+" ");
            }
        } catch (Exception e) {
            testStepFailed("Exception caught while verifying the Text value");
        }


    }
//------------//
    public void selectFromAngularDropDownAD(String object,String strVisibleText) {
        try {

            WebElement we = getWebElement(object);

            clickOn(object,"Dropdown");
            clear(object);
            sleep(2000);
            WebElement we1=getWebElement(By.xpath("//mat-option//span[contains(text(),' "+strVisibleText+"')]"));
//            WebElement we1=getWebElement(By.xpath("//mat-option//span[text()='"+strVisibleText+"']"));
            jsMoveToElement(we1);
            clickOn(we1,"Value");
            testStepPassed("Selected the value->" +  " from the dropdown " + strVisibleText);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

    }

    public void isElement_Label_Present(String obj, String eleName) {
        try {
            if (getWebElement(obj).isDisplayed()) {
                testStepPassed("Element is displayed as expected -> " + eleName);
            } else {
                testStepFailed("Element is not displayed as expected -> " + eleName + "\n. Locator -> " + obj);

            }
        } catch (Exception e) {
            testStepFailed("Exception Caught in method and Error Message is->" + e.getMessage());
        }
    }

    public void gettextAndCompareValue(String obj, String value, String eleName) {
        try {
            String appValue = gettext(obj).trim();
            if (appValue.equalsIgnoreCase(value)) {
                testStepPassed("Value int the element is as expected -> " + eleName);
            } else {
                testStepFailed("Value int the element is not as expected. " +
                        "\nUI value -> " + appValue + " Expected value->" + value);
            }
        } catch (Exception e) {
            testStepFailed("Exception Caught in method and Error Message is->" + e.getMessage());
        }
    }

    public void selectDropDownValueFromTradeElements(String Object,String textValueOfOPtion,String ElementName)
    {
        try {
            WebDriver wd = getWebDriver();
            Actions actions = new Actions(wd);
            WebElement weValue = getWebElement(Object);
            actions.moveToElement(weValue).click(weValue).doubleClick(weValue).build().perform();
            WebElement we1 = getWebElement(By.xpath("//mat-option//span[text()='"+textValueOfOPtion+"']"));
            jsMoveToElement(we1);
            clickOn(we1, ElementName);
            sleep(1000);
            testStepPassed("successfully selected the option-->"+textValueOfOPtion+" from dropdown");
        } catch (Exception e) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), e);
        }

    }

    public void enterTextValueToTradeElements(String Object,String value,String ElementName)
    {
        try {
            WebDriver wd = getWebDriver();
            Actions actions = new Actions(wd);
            WebElement weValue = getWebElement(Object);
            actions.moveToElement(weValue).click(weValue).doubleClick(weValue).build().perform();
            String value1 = ""+Object+"//input";
            enterText(value1, value, ElementName);
            sleep(1000);
            testStepPassed("successfully entered the value-->"+value+" to the textbox-->"+ElementName+" ");
        } catch (Exception e) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), e);
        }
    }
//////////////////////sprint-3-ra/////////////////
    /////////////

    public void selectFromAngularDropDownForpartialValue(String object, String strVisibleText) {
        try {

            WebElement we = getWebElement(object);
            clickOn(object,"Dropdown");
            clear(object);
            sleep(2000);
            WebElement we1=getWebElement(By.xpath("//mat-option//span[contains(text(),'"+strVisibleText+"')]"));
            jsMoveToElement(we1);
            clickOn(we1,"Value");
            testStepPassed("Selected the value->" +  " from the dropdown " + strVisibleText);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

    }

    /////swe
    public void selectOptionFromDropDown(String object, String strVisibleText) {
        try {
            WebElement we = getWebElement(object);
            clickOn(object,"Dropdown");
            sleep(2000);
            WebElement we1=getWebElement(By.xpath("//mat-option//span[text()='"+strVisibleText+"']"));
            jsMoveToElement(we1);
            clickOn(we1,"Value");
            testStepPassed("Selected the value->" +  " from the dropdown " + strVisibleText);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

    }

    public void verifyTextValueOfWebElement(String Object,String ExpectedValue,String ElementName)
    {
        try {
            scroll(Object);
            sleep(1000);
            String actualValue = getWebElement(Object).getText();

            if(!actualValue.equalsIgnoreCase(""))
            {
                if(actualValue.contains(ExpectedValue))
                {
                    testStepPassed("Value present in "+ElementName+" is matching with Expected value ");
                }else {
                    testStepFailed("Value present in "+ElementName+" is not matching with expected value because actual value displayed is-->"+actualValue+" but expected--->"+ExpectedValue);
                }
            }
            else {
                testStepFailed("There is no value inside the "+ElementName+" ");
            }
        } catch (Exception e) {
            testStepFailed("Exception caught while verifying the Text value");
        }


    }

    public List<String> getValueFromExcel(int intColumnNo) {
        List<String> strExcelCode = new ArrayList<String>();
        try {
            FileInputStream file = new FileInputStream(new File("./src/main/resources/BAI_prod.xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                strExcelCode.add(sheet.getRow(i).getCell(intColumnNo).toString());
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
        return strExcelCode;
    }

    public void doubleClickOnElement(String Element, String ElementName) {
        WebDriver wd = getWebDriver();
        Actions actions = new Actions(wd);
        try {
            sleep(2000);
            WebElement we = getWebElement(Element);
            jsMoveToElement(we);
            actions.doubleClick(we).build().perform();
            testStepPassed("Successfully double Click on " + ElementName);
        } catch (Exception ex) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), ex);
        }
    }

    public void doubleClickOnElement(WebElement Element, String ElementName) {
        WebDriver wd = getWebDriver();
        Actions actions = new Actions(wd);
        try {
            sleep(2000);
            jsMoveToElement(Element);
            actions.doubleClick(Element).build().perform();
            testStepPassed("Successfully double Click on " + ElementName);
        } catch (Exception ex) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), ex);
        }
    }

    public void getAttributeValueAndCompare(String element, String ExpectedValue, String Element_Name) {

        try {
            String Actualvalue = getWebElement(element).getAttribute("value");

            if (Actualvalue.contains(ExpectedValue)) {
                testStepPassed("Successfully verified the expected value in " + Element_Name);
            } else {
                testStepFailed("Failed to verify expected value because expected-->>" + ExpectedValue + " but actual value displayed-->" + Actualvalue);
            }
        } catch (Exception e) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), e);
        }
    }

    public void getTextAndCompareTheValue(String obj, String value, String eleName) {
        try {
            String appValue = getWebElement(obj).getText();
            if (appValue.equalsIgnoreCase(value)) {
                testStepPassed("Value int the element is as expected -> " + eleName);
            } else {
                testStepFailed("Value int the element is not as expected. " + "\nUI value -> " + appValue + " Expected value->" + value);
            }
        } catch (Exception e) {
            testStepFailed("Exception Caught in method and Error Message is->" + e.getMessage());
        }
    }
////////////////////////sprint5////////////////////

    public void VerifygetText(String Object, String Expected, String ElemetName) {
        try {
            String ActualValue = getWebElement(Object).getText();

            if (Expected.contains(ActualValue)) {
                testStepPassed("Successfully value dispalyed is same as expected ");
            } else {
                testStepFailed("Failed to verify that dispalayed value is same as expected");
            }
        } catch (Exception e) {
            testStepFailed("Exception caught  Message is->" + e.getMessage());
            e.printStackTrace();
        }
    }

    /////////////////////////////////shushmaSprint6////////////////////////////////

    public void ValidateSortingFunctionalityforPage(String Element, String Element1, String Sort, String Type, String ColumName) {
        try {
            //WebDriver wd = getWebDriver();
            //List<WebElement> row = wd.findElements(By.xpath("//div//div[@col-id='TradeTradeID']"));
            List<WebElement> row = getWebElements(Element);
            int i = row.size();
            if (i > 0) {
                sleep(3000);
                // By bySort = By.xpath("(//div//div//span[text()='Trade ID'])[1]");

                if (!getClass(getWebElement(Element1)).contains("asc")) {
                    //clickOn("(//div//div//span[text()='Trade ID'])[1]","Ascending");
                    JsClick(getWebElement(Element1), "Ascending");
                }
                sleep(3000);
                validateAscendingOrderForTable(Sort, Element, ColumName, Type);
                if (!getClass(getWebElement(Element1)).contains("desc")) {
                    JsClick(getWebElement(Element1), "Descending");
                }
                sleep(5000);
                validateDescendingOrderForTable(Element, ColumName, Type);
                sleep(5000);
            } else {
                testStepInfo("No records found,Hence Sorting functionality cannot be validated");

            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while validating the sorting functionality, Message is ->" + ex.getMessage());
        }
    }

    public void validateTradeTableElements(String scrollBar, String Element, String ElementName) {
        sleep(3000);
        JavascriptExecutor js = null;
        js = (JavascriptExecutor) getWebDriver();
        js.executeScript("document.body.style.zoom='75%';");
        while (true) {
            if (isElementPresent(Element)) {
                testStepPassed("Element" + ElementName + "is displayed as expected");

                break;
            } else {
                WebElement ele2 = getWebElement(scrollBar);

                jsScroll2(ele2);
                sleep(2000);
            }
        }
    }
    public void jsScroll2(WebElement by) {
        try {
            for (int i = 0; i <= 1; i++) {
                JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
                js.executeScript("arguments[0].scrollBy(100,0)", by);

            }
        } catch (Exception e) {
            testStepFailed("Exception occured in scrollToElement() " + e.getMessage());

        }
    }

    public void verifyElementEnabledDisabled(String Element, String ElementName, String type) {
        try {
            sleep(3000);
            if (type.equalsIgnoreCase("disabled")) {
                String x = getWebElement(Element).getAttribute("aria-disabled");
                String y = getWebElement(Element).getAttribute("disabled");
                if (x.equalsIgnoreCase("true") || y.equalsIgnoreCase("true")) {
                    testStepPassed("element" + ElementName + "is disabled");
                } else {
                    testStepFailed("element" + ElementName + "is not disabled");
                }
            } else if (type.equalsIgnoreCase("enabled")) {
                String x = getWebElement(Element).getAttribute("aria-disabled");
                if (x.equalsIgnoreCase("false")) {
                    testStepPassed("element" + ElementName + "is enabled");
                } else {
                    testStepFailed("element" + ElementName + "is not enabled");
                }
            }
        } catch (Exception ex) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), ex);
        }
    }

    public void validateAndClickOnRequestLiveQuote(String scrollBar, String Element, String txttradeStatus, String TradeStatusValue,String LiveQuote,String status) {
        try {
            sleep(3000);
            JavascriptExecutor js = null;
            js = (JavascriptExecutor) getWebDriver();
            // js.executeScript("document.body.style.zoom='75%';");
            WebElement ele2 = getWebElement(scrollBar);
            while (true) {
                if (isElementPresent("//div[@aria-rowindex='3']//div[text()='"+status+"']")) {
                    //jsScroll2(ele2);
                    break;
                } else {

                    jsScroll(ele2);
                    sleep(2000);
                }
            }
            WebElement se = getWebElement("//div[@aria-rowindex='3']//div[text()='"+status+"']");
            Actions actions = new Actions(getWebDriver());
            actions.contextClick(se).perform();
//            Robot robot=new Robot();
//            robot.mousePress(InputEvent.BUTTON3_MASK);
//            robot.mouseRelease(InputEvent.BUTTON3_MASK);
            sleep(3000);
            clickOn(LiveQuote, "Quote");
            waitTillLoadingCompleted();
            sleep(7000);
            verifyTextValueOfWebElement(txttradeStatus, TradeStatusValue, "Status");
            //js.executeScript("document.body.style.zoom='100%';");
        } catch (Exception ex) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), ex);
        }
    }
    public void jsScroll1(WebElement by) {
        try {
            for (int i = 0; i <= 1; i++) {
                JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
                js.executeScript("arguments[0].scrollBy(200,0)", by);

            }
        } catch (Exception e) {
            testStepFailed("Exception occured in scrollToElement() " + e.getMessage());

        }
    }

    public void validateTradingeventStatusFromTable(String scrollBar, String Element, String txttradeStatus, String TradeStatusValue) {
        try {
            sleep(3000);
            JavascriptExecutor js = null;
            js = (JavascriptExecutor) getWebDriver();
            js.executeScript("document.body.style.zoom='75%';");
            while (true) {
                if (isElementPresent(Element)) {
                    break;
                } else {
                    WebElement ele2 = getWebElement(scrollBar);
                    jsScroll(ele2);
                    sleep(2000);
                }
            }

            verifyTextValueOfWebElement(txttradeStatus, TradeStatusValue, "Status");
            js.executeScript("document.body.style.zoom='100%';");
        } catch (Exception ex) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), ex);
        }
    }
    public void validateAscendingOrderForTable(String Icon, String Element, String ColumName, String Type) {
        try {
            // WebDriver wd = getWebDriver();
            // int value = columnNumber + 1;
            // WebElement icon = wd.findElement(By.xpath("(//span[@class='ag-icon ag-icon-asc'])[2]"));
            WebElement icon = getWebElement(Icon);
            if (!icon.getAttribute("class").contains("asc")) {
//                testStepInfo(icon.getAttribute("class").toString());
                testStepFailed(ColumName + " column's icon is not changed to Ascending");
            }
            // List<WebElement> defaultRow = wd.findElements(By.xpath("(//div//div[@col-id='TradeTradeID'])"));
            List<WebElement> defaultRow = getWebElements(Element);
            int itCount = defaultRow.size() - 1;
            List<WebElement> elementName = new LinkedList<>();
            if (Type == "STRING") {
                ArrayList<String> obtainedEleList = new ArrayList<>();
                ArrayList<String> resultEleNameList = new ArrayList<>();
                elementName = getWebElements(Element);
                for (int i = 1; i <= itCount; i++) {
                    //String newelementName = (String) ((JavascriptExecutor) wd).executeScript("return arguments[0].text;", elementName.get(i));
                    String newelementName = elementName.get(i).getText().trim();
                    if (!newelementName.trim().equals("")) {
                        obtainedEleList.add(newelementName.toUpperCase().trim());
                        resultEleNameList = obtainedEleList;
                        // obtainedEleList.add(newelementName);
                        //resultEleNameList.add(newelementName);
                    }
                }
                Collections.sort(obtainedEleList);
                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Ascending order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Ascending order");

                }
            }
            if (Type == "STRINGCUST") {
                ArrayList<String> obtainedEleList = new ArrayList<>();
                ArrayList<String> resultEleNameList = new ArrayList<>();
                elementName = wd.findElements(By.xpath(Element));
                for (int i = 1; i <= itCount; i++) {
                    //String newelementName = (String) ((JavascriptExecutor) wd).executeScript("return arguments[0].text;", elementName.get(i));
                    String newelementName = elementName.get(i).getText().trim();
                    if (!newelementName.trim().equals("")) {
                        obtainedEleList.add(newelementName);
                        resultEleNameList = obtainedEleList;
                    }
                }
                Collections.sort(obtainedEleList);
                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Ascending order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Ascending order");

                }
            } else if (Type == "INTEGER") {
                ArrayList<Long> obtainedEleList = new ArrayList<>();
                ArrayList<Long> resultEleNameList = new ArrayList<>();
//                for (int j=2;j<itCount;j++){
                //               //elementName = wd.findElements(By.xpath("(//div//div[@col-id='TradeTradeID'])["+j+"]"));
                //               elementName=getWebElements("(//div//div[@col-id="+ColumnId+"])["+j+"]")
//                int count = elementName.size();
//                for (WebElement we : elementName) {
//                    String newelementName1=we.getText().trim();
//                    if (newelementName1.contains("-")) {
//                        newelementName1 = newelementName1.split("-")[0];
//                    }
//                    String newelementName = newelementName1.replaceAll(",", "");
//                    if (!newelementName.equals("")) {
//                        obtainedEleList.add(Integer.parseInt(newelementName));
//                        resultEleNameList=obtainedEleList;
//                    }
//
//                }}
//                Collections.sort(obtainedEleList);


                elementName = getWebElements(Element);
                for (int i = 1; i <= itCount; i++) {
                    String newelementName1 = elementName.get(i).getText().trim();
                    newelementName1 = newelementName1.replace("-", "");
                    // if (newelementName1.contains("([A-Za-z])")) {
                    newelementName1 = newelementName1.split("([A-Za-z])")[0];
                    //  }
                    String newelementName2 = newelementName1.replaceAll(",", "");
                    String newelementName = newelementName2.replace(".", "");
                    if (!newelementName.equals("")) {
                        obtainedEleList.add(Long.parseLong(newelementName));
                        resultEleNameList = obtainedEleList;
                    }
                }
                Collections.sort(obtainedEleList);
                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Ascending  order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Ascending order");

                }
            } else if (Type == "DOUBLE") {
                ArrayList<Double> obtainedEleList = new ArrayList<>();
                ArrayList<Double> resultEleNameList = new ArrayList<>();
                elementName = getWebElements(Element);
                for (int i = 1; i <= itCount; i++) {
                    String newelementName2 = elementName.get(i).getText().trim();
                    String newelementName1 = newelementName2.replaceAll(",", "");
                    String newelementName = newelementName1.replace("%", "").replace(")", "").replace("(", "-");
                    if (!newelementName.equals("")) {
                        obtainedEleList.add(Double.valueOf(newelementName));
                        resultEleNameList = obtainedEleList;
                    }
                }
                Collections.sort(obtainedEleList);
                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Ascending  order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Ascending order");

                }
            } else if (Type == "DATE") {
                ArrayList<Date> obtainedEleList = new ArrayList<>();
                ArrayList<Date> resultEleNameList = new ArrayList<>();
                elementName = getWebElements(Element);
                for (int i = 1; i <= itCount; i++) {
                    String newelementName = elementName.get(i).getText().trim();
                    SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
                    if (!newelementName.equals("")) {
                        try {
                            obtainedEleList.add(date.parse(newelementName));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        resultEleNameList = obtainedEleList;
                    }
                }

                Collections.sort(obtainedEleList);
                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Ascending order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Ascending order");

                }
            } else if (Type == "DATETIME") {
                ArrayList<Date> obtainedEleList = new ArrayList<>();
                ArrayList<Date> resultEleNameList = new ArrayList<>();
                elementName = getWebElements(Element);
                for (int i = 1; i <= itCount; i++) {
                    String newelementName = elementName.get(i).getText().trim();
                    boolean flag = true;
                    SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
                    if (!newelementName.equals("")) {
                        try {
                            resultEleNameList.add((date.parse(newelementName)));
                            obtainedEleList = resultEleNameList;

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                Collections.sort(resultEleNameList);
                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Ascending order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Ascending  order");

                }
            } else if (Type == "DROPDOWN") {
                ArrayList<String> obtainedEleList = new ArrayList<>();
                ArrayList<String> resultEleNameList = new ArrayList<>();
                elementName = getWebElements(Element);
                for (int i = 1; i <= itCount; i++) {
                    String newelementName = getSelectedOptionFromDropdown(elementName.get(i));
                    if (!newelementName.trim().equals("")) {
                        obtainedEleList.add(newelementName.toUpperCase());
                        resultEleNameList = obtainedEleList;
                    }
                }
                Collections.sort(obtainedEleList);
                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Ascending order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Ascending order");

                }
            }
            if (Type == "CHECKBOX") {
                ArrayList<String> obtainedEleList = new ArrayList<>();
                ArrayList<String> resultEleNameList = new ArrayList<>();
                elementName = getWebElements(Element);
                for (int i = 1; i <= itCount; i++) {
                    String newelementName;
                    if (elementName.get(i).isSelected()) {
                        newelementName = "B";
                    } else {
                        newelementName = "A";
                    }
                    if (!newelementName.equals("")) {
                        obtainedEleList.add(newelementName.toUpperCase());
                        resultEleNameList = obtainedEleList;
                    }
                }
                Collections.sort(obtainedEleList);

                if (resultEleNameList.equals(obtainedEleList)) {
                    testStepPassed(ColumName + " column is sorted in Descending order");
                } else {
                    testStepFailed(ColumName + " column is not sorted in Descending order");

                }
            }
        } catch (Exception ex) {
            testStepFailed("Exception caught while validating the Ascending sorting functionality for column " + ColumName + ", Message is ->" + ex.getMessage());
        }
    }



    public void validateDescendingOrderForTable(String Element, String ColumName, String Type) {
        List<WebElement> defaultRow = getWebElements(Element);
        int itCount = defaultRow.size() - 1;
        //int value = columnNumber + 1;
        List<WebElement> elementName = new LinkedList<>();
        if (Type == "STRING") {
            ArrayList<String> obtainedEleList = new ArrayList<>();
            ArrayList<String> resultEleNameList = new ArrayList<>();
            elementName = getWebElements(Element);
            for (int i = 1; i <= itCount; i++) {
                String newelementName = elementName.get(i).getText().trim();
                if (!newelementName.equals("")) {
                    obtainedEleList.add(newelementName.toUpperCase());
                    resultEleNameList = obtainedEleList;
                }
            }
            Collections.sort(obtainedEleList, Collections.reverseOrder());

            if (resultEleNameList.equals(obtainedEleList)) {
                testStepPassed(ColumName + " column is sorted in Descending order");
                takeScreenshot();
            } else {
                testStepFailed(ColumName + " column is not sorted in Descending order");

            }
        } else if (Type == "INTEGER") {
            ArrayList<Long> obtainedEleList = new ArrayList<>();
            ArrayList<Long> resultEleNameList = new ArrayList<>();
            elementName = getWebElements(Element);
            for (int i = 1; i <= itCount; i++) {
                String newelementName1 = elementName.get(i).getText().trim();
                newelementName1 = newelementName1.replace("-", "");
//                    if (newelementName1.contains("-")) {
                newelementName1 = newelementName1.split("([A-Za-z])")[0];
//                    }
                String newelementName2 = newelementName1.replaceAll(",", "");
                String newelementName = newelementName2.replace(".", "");
                if (!newelementName.equals("")) {
                    obtainedEleList.add(Long.parseLong(newelementName));
                    resultEleNameList = obtainedEleList;
                }
            }
            Collections.sort(obtainedEleList, Collections.reverseOrder());
            if (resultEleNameList.equals(obtainedEleList)) {
                testStepPassed(ColumName + " column is sorted in Descending order");
                takeScreenshot();


            } else {
                testStepFailed(ColumName + " column is not sorted in Descending order");

            }
        } else if (Type == "DOUBLE") {
            ArrayList<Double> obtainedEleList = new ArrayList<>();
            ArrayList<Double> resultEleNameList = new ArrayList<>();
            elementName = getWebElements(Element);
            for (int i = 1; i <= itCount; i++) {
                String newelementName2 = elementName.get(i).getText().trim();
                String newelementName1 = newelementName2.replaceAll(",", "");
                String newelementName = newelementName1.replace("%", "");
                if (!newelementName.equals("")) {
                    obtainedEleList.add(Double.valueOf(newelementName));
                    resultEleNameList = obtainedEleList;
                }
            }
            Collections.sort(obtainedEleList, Collections.reverseOrder());
            if (resultEleNameList.equals(obtainedEleList)) {
                testStepPassed(ColumName + " column is sorted in Descending order");
                takeScreenshot();


            } else {
                testStepFailed(ColumName + " column is not sorted in Descending order");
            }
        } else if (Type == "DATE") {
            ArrayList<Date> obtainedEleList = new ArrayList<>();
            ArrayList<Date> resultEleNameList = new ArrayList<>();
            elementName = getWebElements(Element);
            for (int i = 1; i <= itCount; i++) {
                String newelementName = elementName.get(i).getText().trim();
                SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
                if (!newelementName.equals("")) {
                    try {
                        obtainedEleList.add(date.parse(newelementName));
                        resultEleNameList = obtainedEleList;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            Collections.sort(obtainedEleList, Collections.reverseOrder());
            if (resultEleNameList.equals(obtainedEleList)) {
                testStepPassed(ColumName + " column is sorted in descending order");
                takeScreenshot();
            } else {
                testStepFailed(ColumName + " column is not sorted in descending  order");

            }
        } else if (Type == "DATETIME") {
            ArrayList<Date> obtainedEleList = new ArrayList<>();
            ArrayList<Date> resultEleNameList = new ArrayList<>();
            // elementName = wd.findElements(By.xpath("(//div//div[@col-id="+ColumnId+"])"));
            for (int i = 1; i <= itCount; i++) {
                String newelementName = elementName.get(i).getText().trim();
                boolean flag = true;
                SimpleDateFormat dateTime = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
                if (!newelementName.equals("")) {
                    try {
                        resultEleNameList.add((dateTime.parse(newelementName)));
                        obtainedEleList = resultEleNameList;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            Collections.sort(resultEleNameList, Collections.reverseOrder());
            if (resultEleNameList.equals(obtainedEleList)) {

                testStepPassed(ColumName + " column is sorted in Descending   order");
                takeScreenshot();
            } else {
                testStepFailed(ColumName + " column is not sorted in Descending   order");

            }
        }

    }

//    ########################orgMworkflow

    public void verifyTheExpectedValueWithActualValue(WebElement Object,String ExpectedValue,String ElementName)
    {
        try {
            scroll(Object);
            sleep(1000);
            String actualValue = Object.getText();

            if(!actualValue.equalsIgnoreCase(""))
            {
                if(actualValue.equalsIgnoreCase(ExpectedValue))
                {
                    testStepPassed("Value present in "+ElementName+" is matching with Expected value ");
                }else {
                    testStepFailed("Value present in "+ElementName+" is not matching with expected value because actual value displayed is-->"+actualValue+" but expected--->"+ExpectedValue);
                }
            }
            else {
                testStepFailed("There is no value inside the "+ElementName+" ");
            }
        } catch (Exception e) {
            testStepFailed("Exception caught while verifying the Text value");
        }

    }

////sprint9/////
  public void selectrandomOptionDropDown(String object, String dropdownName) {
    clickOn(object,dropdownName);
    clickOn("(//mat-option//span)[1]","dropdown option");
}

    public void doubleClickOnElementAndSelectRandomOptionFromDropDown(String object,String ElementName){
        try {
            doubleClickOnElement(object, ElementName);
            String drpObj = object + "//mat-select";
//             jsClick(drpObj,ElementName);
            jsClick("//ets-product-trading-events//mat-select//span[text()='None']", ElementName);
            sleep(2000);
            int count = getWebElements("//mat-option//span").size();
            int randomNum = getRandomInt(2, count);
            jsClick("(//mat-option//span)[" + randomNum + "]", "dropdown option");
            clickOn("(//div[@ref='eCenterContainer']//div[@col-id='Status'])[1]", "status");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void selectrecordFromAdvanceTab(String noteNum){
        try {
            jsClick("//div[@class='ets-product-title--toolbar']//mat-icon[text()='search']","Search Button");
            enterText("//div[@class='ets-product-title--toolbar']//mat-form-field//input",noteNum,"NoteNumber textbox");
            jsClick("//div[@class='cdk-overlay-pane']//mat-option//span[text()='"+noteNum+"']",noteNum);
            sleep(2000);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
//    public void selectrecordFromAdvanceTab(String noteNum){
//        try {
//            jsClick("//div[@class='ets-product-title--toolbar']//mat-icon[text()='search']","Search Button");
//            enterText("//div[@class='ets-product-title--toolbar']//mat-form-field//input","202202080082","NoteNumber textbox");
//            jsClick("//div[@class='cdk-overlay-pane']//mat-option//span[text()='202202080082']",noteNum);
//        } catch (Exception e) {
//            e.getStackTrace();
//        }
//    }

    public void doubleClickOnRecordInMemberDashboard(String record) {
        WebDriver wd = getWebDriver();
        Actions actions = new Actions(wd);
        try {
            sleep(2000);
            WebElement we = getWebElement(record);
            jsMoveToElement(we);
            actions.doubleClick(we).build().perform();
            testStepPassed("Successfully Click on to the Record");
        } catch (Exception ex) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), ex);
        }
    }
    public void waitTillLoadingElementDisappears(int time)
    {
        try {
            sleep(2000);
//            WebDriverWait w = new WebDriverWait(getWebDriver(),);// invisibilityOfElementLocated condition
            WebDriverWait w=new WebDriverWait(getWebDriver(), Duration.ofSeconds(time));
            w.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[name()='circle']")));
            sleep(2000);
        } catch (Exception e) {
//            testStepException(new Exception().getStackTrace()[0].getMethodName(), e);
        }
    }

    /////sprint10////

    public void verifyWhetherApplicationValueAndDbValueEqual(String ColName,String DbValue,String AppValue){
        try {
            if(DbValue.equalsIgnoreCase(AppValue)){
                testStepPassed("The value present in column "+ColName+" in db i.e "+DbValue+" is Equal to the Application value i.e "+AppValue);
            }else{
                testStepFailed("The value present in column "+ColName+" in db i.e "+DbValue+" is not equal to the Application value i.e "+AppValue);
            }
        } catch (Exception e) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), e);
        }
    }


    public void openTagExpandMoreIcon(String object,String ElementName)
    {
        String   txtObject=getWebElement(object).getAttribute("style");

        if(txtObject.contains("transform: rotate(180deg);")){
            testStepInfo("Tag expand icon is already expanded ");
        }else{
            jsClick(object,ElementName);
            testStepInfo("Tag expand icon expanded");
        }

    }

    public void closeTagExpandMoreIcon(String object,String ElementName)
    {
        String   txtObject=getWebElement(object).getAttribute("style");

        if(txtObject.contains("transform: rotate(180deg);")){
            jsClick(object,ElementName);
            testStepInfo("Tag expand icon is closed");
        }else{
            testStepInfo("Tag expand icon already closed");
        }

    }

    ///////////update///////////////

    public String getTheBusinessDate(String date) {
        String newDate = "";
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            cal.setTime(sdf.parse(date));
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                cal.add(Calendar.DATE, 2);
                newDate = sdf.format(cal.getTime());
            } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                cal.add(Calendar.DATE, 3);
                newDate = sdf.format(cal.getTime());
            } else {
                cal.add(Calendar.DATE, 1);
                newDate = sdf.format(cal.getTime());
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return newDate;
    }
    public String getTheBusinessDateBySubstractingTheDate(String date) {
        String newDate = "";
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            cal.setTime(sdf.parse(date));
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                cal.add(Calendar.DATE, -3);
                newDate = sdf.format(cal.getTime());
            } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                cal.add(Calendar.DATE, -2);
                newDate = sdf.format(cal.getTime());
            } else {
                cal.add(Calendar.DATE, 0);
                newDate = sdf.format(cal.getTime());
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return newDate;
    }

}

