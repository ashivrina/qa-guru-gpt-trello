package tests;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

//The whole class is created by Chat GPT 3.5, but I needed to tweak the selectors a bit
//Conversation with Chat GPT here: https://chat.openai.com/share/fb0c34b6-a6a5-4b9f-b9fb-b148cbc698b4
public class TrelloCommonTests {

    private static final String EMAIL = "annatochkag@gmail.com";
    private static final String PASSWORD = "gjikbyf1";

    @BeforeEach
    public void setUp() {
        open("https://trello.com/");
    }

    @Test
    public void loginToTrello() {
        $(By.linkText("Log in")).click();
        $(By.xpath(".//input[contains(@data-testid, 'username')]")).setValue(EMAIL);
        $(By.id("login-submit")).click();
        $(By.id("password")).setValue(PASSWORD);
        $(By.id("login-submit")).click();

        // Verify that user is logged in
        $(By.className("home-sticky-container")).should(Condition.exist);
    }

    @Test
    public void createNewBoard() {
        loginToTrello();

        $(By.className("board-tile.mod-add")).click();
        $(By.className("subtle-input")).setValue("Test Board");
        $(By.className("js-confirm")).click();

        // Verify that new board is created
        $(By.className("board-header-btn-name")).shouldHave(Condition.text("Test Board"));
    }

    @Test
    public void addNewListToBoard() {
        createNewBoard();

        $(By.className("open-add-list")).click();
        $(By.className("list-name-input")).setValue("Test List").pressEnter();

        // Verify that new list is added to the board
        $(By.className("list-header-name")).shouldHave(Condition.text("Test List"));
    }
}
