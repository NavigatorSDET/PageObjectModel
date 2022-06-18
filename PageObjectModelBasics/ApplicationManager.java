package appmanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    public static WebDriver driver;
    private static String browser;
    static String target = System.getProperty("target", "local");
    public static PropertyFileReader reader = new PropertyFileReader(String.format("local.properties", target));

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public ApplicationManager() {
    }

    public static WebDriver getWebDriver() {
        try {
            if (driver == null) {
                if ("".equals(reader.get("selenium.server"))) {
                    if (browser.equals(BrowserType.IE)) {
                        driver = new InternetExplorerDriver();
                        driver.manage().deleteAllCookies();
                    } else if (browser.equals(BrowserType.EDGE)) {
                        File file = new File(ExtentCucumberFormatter.outputDirectory + File.separator + "TestDocuments");

                        file.mkdir();
                        String file1 = file.getAbsolutePath();
                        System.setProperty("webdriver.edge.driver", "./drivers/msedgedriver.exe");


                        Map<String, Object> edgePrefs = new HashMap<String, Object>();
                        edgePrefs.put("profile.default_content_settings.popups", 0);
                        edgePrefs.put("profile.default_content_setting_values.notifications", 2);
                        edgePrefs.put("download.default_directory", file1);
                        edgePrefs.put("profile.default_content_setting_values.automatic_downloads", 1);
                        edgePrefs.put("download.prompt_for_download", false);

                        ChromiumOptions options = new ChromiumOptions(CapabilityType.BROWSER_NAME, BrowserType.EDGE, "ms:edgeOptions");
                        options.setExperimentalOption("prefs", edgePrefs);
                        EdgeOptions op = new EdgeOptions();
                        op.setCapability(EdgeOptions.CAPABILITY, options);
                        //WebDriver browser = new EdgeDriver(op);
                        driver = new EdgeDriver(op);
                        driver.manage().deleteAllCookies();
                    } else if (browser.equals(BrowserType.CHROME)) {
                       //  System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
                       WebDriverManager.chromedriver().setup();
                        File file = new File(ExtentCucumberFormatter.outputDirectory + File.separator + "TestDocuments");

                        file.mkdir();
                        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                        chromePrefs.put("profile.default_content_settings.popups", 0);
                        chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
                        chromePrefs.put("download.prompt_for_download", false);
                        ChromeOptions options = new ChromeOptions();
                        options.setExperimentalOption("prefs", chromePrefs);
                        options.addArguments("--test-type");
                        options.addArguments("--disable-extensions");
                        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                        driver = new ChromeDriver(options);
                        driver.manage().deleteAllCookies();
                    }
                } else {
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setBrowserName(browser);
                    try {
                        driver = new RemoteWebDriver(new URL(reader.get("selenium.server")), capabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

                return driver;
            } else {
                return driver;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void stop() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public String LoggedInUser;
    public void initUrl(String userName) {
        try {
            getWebDriver().get(reader.get("web.Url"));
            String pwd = new String(Base64.decodeBase64(reader.get("" + userName + "")));
            new WebDriverWait(driver, 30)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id("okta-signin-username")));
            if(isElementPresent(By.id("okta-signin-username"))) {
                driver.findElement(By.id("okta-signin-username")).clear();
                driver.findElement(By.id("okta-signin-username")).sendKeys(userName);
                driver.findElement(By.id("okta-signin-password")).clear();
                driver.findElement(By.id("okta-signin-password")).sendKeys(pwd);
                driver.findElement(By.xpath("//input[@id='okta-signin-submit']")).click();
                new WebDriverWait(driver, 30)
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'toolbar-user')]")));
                LoggedInUser=userName;
            }else{
                Assert.assertFalse(true,"Application is down or username filed is not displayed");
            }

        } catch(Exception e) {
            e.printStackTrace();
            Assert.assertFalse(true,"Application is down or username filed is not displayed");
        }
    }


    public void initUrlOKTA(String userName) {
        try {
            getWebDriver().get(reader.get("web.UrlOKTA"));
            String pwd = new String(Base64.decodeBase64(reader.get("" + userName + "")));
            new WebDriverWait(driver, 30)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id("okta-signin-username")));
            if(isElementPresent(By.id("okta-signin-username"))) {
                driver.findElement(By.id("okta-signin-username")).clear();
                driver.findElement(By.id("okta-signin-username")).sendKeys(userName);
                driver.findElement(By.id("okta-signin-password")).clear();
                driver.findElement(By.id("okta-signin-password")).sendKeys(pwd);
                driver.findElement(By.xpath("//input[@id='okta-signin-submit']")).click();
                new WebDriverWait(driver, 30)
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'toolbar-user')]")));
            }else{
                Assert.assertFalse(true,"Application is down or username filed is not displayed");
            }

        } catch(Exception e) {
            e.printStackTrace();
            Assert.assertFalse(true,"Application is down or username filed is not displayed");
        }
    }

    public void initUrlOKTAInvalid(String userName) {
        try {
            getWebDriver().get(reader.get("web.UrlOKTA"));
            String pwd = "abcdertyygfrj";
            new WebDriverWait(driver, 30)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id("okta-signin-username")));
            if(isElementPresent(By.id("okta-signin-username"))) {
                driver.findElement(By.id("okta-signin-username")).clear();
                driver.findElement(By.id("okta-signin-username")).sendKeys(userName);
                driver.findElement(By.id("okta-signin-password")).clear();
                driver.findElement(By.id("okta-signin-password")).sendKeys(pwd);
                driver.findElement(By.xpath("//input[@id='okta-signin-submit']")).click();
            }else{
                Assert.assertFalse(true,"Application is down or username filed is not displayed");
            }

        } catch(Exception e) {
            e.printStackTrace();
            Assert.assertFalse(true,"Application is down or username filed is not displayed");
        }
    }

        public void initUrlActiveMQ() throws InterruptedException {
            try {
                String userid = "epsilonMQ";
                getWebDriver().get(reader.get("web.UrlActiveMQ"));
                String pwdDecode = "capfs";
                Robot robot = new Robot();
                Thread.sleep(5000);
                robotType(robot, userid);
                robot.keyPress(KeyEvent.VK_TAB);
                Thread.sleep(5000);
                robotType(robot, pwdDecode);
                robot.keyPress(KeyEvent.VK_ENTER);
            } catch (AWTException e) {
                e.printStackTrace();
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

    public boolean isElementPresent(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
