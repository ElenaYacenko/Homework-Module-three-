package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.nio.file.Paths;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    @BeforeAll
    static void beforeAll() {

        Configuration.baseUrl = "https://lknew.ch-sk.ru";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1000x800";
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }
}