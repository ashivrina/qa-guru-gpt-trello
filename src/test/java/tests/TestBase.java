package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.*;

//TestBase created using ChatGPT: https://chat.openai.com/share/fb0c34b6-a6a5-4b9f-b9fb-b148cbc698b4
public class TestBase {

    protected static final String BASE_URL = "https://trello.com/";
    static final String EMAIL = "annatochkag@gmail.com";
    static final String PASSWORD = "gjikbyf1";

    @BeforeAll
    public static void setUp() {
        // Configure Chrome browser
        Configuration.browser = "chrome";

        // Set initial browser size
        Configuration.browserSize = "1920x1080"; // or your preferred size

        // Set base URL
        Configuration.baseUrl = BASE_URL;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }

    @BeforeEach
    public void openTrelloMainPage() {
        // Open base URL
        openPage("");
    }

    @AfterEach
    public void tearDown() {
        addAttachments();
        // Clean up after each test if needed
        closeWebDriver();
    }

    public void openPage(String path) {
        Selenide.open(path);
    }

    void addAttachments() {
        if (getWebDriver() != null) {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();
            Attach.addVideo();
            closeWebDriver();
        }
    }
}
