package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class AttachmentsSteps {

    private static final String REPOSITORY = "eviushkova/lesson9_allure";

    @Test
    public void testLambdaAttachments() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open main page", () -> {
            open("https://github.com/");
//            attachment("Source", webdriver().driver().source());
        });
        step("Search repository " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").pressEnter();
        });
        step("Click to repository's link " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Checking for the Issues tab", () -> {
            $("#issues-tab").should(Condition.exist);
        });

    }

    @Test
    public void annotatedAttachments() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        StepsTest steps = new StepsTest();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.findIssueTab();
        steps.takeScreenshot();

    }
}
