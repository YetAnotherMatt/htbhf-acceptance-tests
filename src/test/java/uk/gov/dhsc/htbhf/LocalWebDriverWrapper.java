package uk.gov.dhsc.htbhf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Controls creation of WebDriver and WebDriverWait for local testing.
 */
public class LocalWebDriverWrapper implements WebDriverWrapper {

    private final String browser;
    private final boolean headless;
    private final int waitTimeoutInSeconds;
    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;

    public LocalWebDriverWrapper(String browser, boolean headless, int waitTimeoutInSeconds) {
        this.browser = browser;
        this.headless = headless;
        this.waitTimeoutInSeconds = waitTimeoutInSeconds;
        this.webDriver = buildWebDriver();
        this.webDriverWait = buildWebDriverWait();
    }

    @Override
    public WebDriver getWebDriver() {
        return webDriver;
    }

    @Override
    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }

    @Override
    public void initDriver() {
        throw new RuntimeException("This is for BrowserStack WebDrivers only");
    }

    @Override
    public void quitDriver() {
        throw new RuntimeException("Should not be calling quit() on a local WebDriver, this is for BrowserStack WebDrivers only");
    }

    @Override
    public void closeDriver() {
        webDriver.close();
    }

    private WebDriver buildWebDriver() {
        WebDriver webdriver = null;
        switch (browser) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setHeadless(headless);
                webdriver = new FirefoxDriver(firefoxOptions);
                break;

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setHeadless(headless);
                webdriver = new ChromeDriver(chromeOptions);
                break;
        }
        return webdriver;
    }

    private WebDriverWait buildWebDriverWait() {
        return new WebDriverWait(webDriver, waitTimeoutInSeconds);
    }

}
