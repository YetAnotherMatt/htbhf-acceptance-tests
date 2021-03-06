package uk.gov.dhsc.htbhf.browserstack;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;

import static uk.gov.dhsc.htbhf.browserstack.BrowserStackLauncher.getTestFilePath;

/**
 * Retrieves and builds the BrowserStack capabilities from the values stored in the capabilities files
 * in src/test/resources/browserstack. The path is provided by the ThreadLocal value stored in BrowserStackLauncher.
 */
@Slf4j
public class BrowserStackCapabilities {

    /**
     * Get the browser stack properties from a Properties file for the test specified from the
     * the test name stored in the ThreadLocal variable from the BrowserStack TestLauncher.
     *
     * @return A Map of the properties for the requested test
     */
    public static Map<String, String> getBrowserStackCapabilities() {
        Path capabilitiesPath = getTestFilePath();
        try {
            Properties properties = new Properties();
            log.info("Loading BrowserStack capabilities from: {}", capabilitiesPath);
            properties.load(Files.newInputStream(capabilitiesPath));
            return Maps.fromProperties(properties);
        } catch (Exception e) {
            throw new RuntimeException("Unable to load capabilities from file: " + capabilitiesPath, e);
        }
    }

    /**
     * Builds up the {@link DesiredCapabilities} from the given Map of capabilities and the given BrowserStack username and key to use.
     *
     * @param capabilitiesMap      The Map of key value pairs of capabilities to use
     * @param browserStackUsername The user name to use
     * @param browserStackKey      The key to use
     * @return The built capabilities
     */
    public static DesiredCapabilities buildDesiredCapabilities(Map<String, String> capabilitiesMap, String browserStackUsername, String browserStackKey) {
        log.info("Using config from system properties: {}", capabilitiesMap);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilitiesMap.forEach((key, value) -> {
            //Sadly if we pass a "true" or "false" String into here it doesn't get treated as a boolean so we must convert it.
            Object valueToSet = isTrueOrFalseString(value) ? BooleanUtils.toBooleanObject(value) : value;
            capabilities.setCapability(key, valueToSet);
        });
        capabilities.setCapability("browserstack.user", browserStackUsername);
        capabilities.setCapability("browserstack.key", browserStackKey);
        capabilities.setCapability("browserstack.use_w3c", true);
        return capabilities;
    }

    private static boolean isTrueOrFalseString(String value) {
        return "true".equals(value) || "false".equals(value);
    }

}
