package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Paths;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {
    @BeforeAll
    static void beforeAll() {

        Configuration.baseUrl = "https://lknew.ch-sk.ru";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1000x800";
    }

    @BeforeEach
    void setUp() {
        open("/person-auth-form");

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }
}