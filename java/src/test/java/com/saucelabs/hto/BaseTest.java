package com.saucelabs.hto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Optional;
public class BaseTest {
    WebDriver driver;
    String platform;

    @RegisterExtension
    public MyTestWatcher myTestWatcher = new MyTestWatcher();

    @BeforeEach
    public void setUp(TestInfo testinfo) throws MalformedURLException {
        platform = System.getProperty("selenium.platform");
        if ("sauce".equals(platform)) {
            driver = runSauce(testinfo);
        } else {
            driver = runLocal();
        }
    }

    private WebDriver runLocal() {
        return new ChromeDriver(new ChromeOptions());
    }

    private WebDriver runSauce(TestInfo testinfo) throws MalformedURLException {
        Map<String, Object> sauceOptions = Map.of("username", System.getenv("SAUCE_USERNAME"),
                "accessKey", System.getenv("SAUCE_ACCESS_KEY"),
                "name", testinfo.getDisplayName());
        ChromeOptions options = new ChromeOptions();
        options.setCapability("sauce:options", sauceOptions);
        URL url = new URL("https://ondemand.us-west-1.saucelabs.com/wd/hub");

        return new RemoteWebDriver(url, options);
    }

    public class MyTestWatcher implements TestWatcher {
        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            if (driver != null) {
                try {
                    if ("SAUCE".equals(platform)) {
                        ((JavascriptExecutor) driver).executeScript("sauce:job-result=failed");
                    } else {
                        System.out.println("Test Failed!");
                    }
                } catch (Exception ignored) {
                } finally {
                    driver.quit();
                }
            }
        }

        @Override
        public void testSuccessful(ExtensionContext context) {
            if (driver != null) {
                try {
                    if ("SAUCE".equals(platform)) {
                        ((JavascriptExecutor) driver).executeScript("sauce:job-result=passed");
                    } else {
                        System.out.println("Test Passed!");
                    }
                } catch (Exception ignored) {
                } finally {
                    driver.quit();
                }
            }
        }

        @Override
        public void testAborted(ExtensionContext context, Throwable cause) {
        }

        @Override
        public void testDisabled(ExtensionContext context, Optional<String> reason) {
        }
    }
}
