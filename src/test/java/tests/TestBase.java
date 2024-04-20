package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

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
    }

    @BeforeEach
    public void openTrelloMainPage() {
        // Open base URL
        openPage("");
    }

    @AfterEach
    public void tearDown() {
        // Clean up after each test if needed
        Selenide.closeWebDriver();
    }

    protected void openPage(String path) {
        Selenide.open(path);
    }
}
