import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import tests.TestBase;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ParamTests extends TestBase {

    @CsvSource(value = {
            "'', Обязательное поле!",
            "'   ', Введите телефон или почту",
            "7900900909, Введенный номер телефона не соответствует допустимому формату",
            "790090090909, Введенный номер телефона не соответствует допустимому формату",
            "phonenumber, Введите телефон или почту",
            "номертелефона, Введите телефон или почту",
            "123654@#$, Введите телефон или почту",
            "8(999)666-32-32, Пользователь не найден",
            "xxx123ya.ru, Введите телефон или почту",
            "abcdef, Введите телефон или почту",
            "@domain.рф, Введите телефон или почту",
            "user@.рф, Введите телефон или почту",
            "user@domain, Введите телефон или почту"
    })
    @ParameterizedTest(name = "Логин: {0} ошибка: {1}")
    @Tags({
            @Tag("WEB"),
            @Tag("negative")
    })
    void phoneFieldValidation(String searchQuery, String searchMistake) {

        $("#iPhoneOrEmail").setValue(searchQuery);
        $("#iPassword").setValue("123456789");

        $("#cSignIn").click();

        $("#lPhoneOrEmail").shouldHave(text(searchMistake));
        $("#lPhoneOrEmail").shouldHave(cssValue("color", "rgba(227, 0, 0, 1)"));
    }

    @ValueSource(strings = {
            "",
            "      ",
            "123456789",
            "abcdefFGH",
            "пароль",
            "@#$%12589PPP"
    })
    @ParameterizedTest(name = "Проверка валидации поля пароль: {0}")
    @Tags({
            @Tag("WEB"),
            @Tag("negative")
    })
    void passwordFieldValidation(String passwordQuery) {

        $("#iPhoneOrEmail").setValue("79999999999");
        $("#iPassword").setValue(passwordQuery);

        $("#cSignIn").click();

        $("#lPassword").shouldHave(cssValue("color", "rgba(227, 0, 0, 1)"));
    }

    @CsvFileSource(
            resources = "/login-validation-extended.csv",
            numLinesToSkip = 1,
            encoding = "UTF-8"
    )
    @ParameterizedTest(name = "Проверка связки логин-пароль: {0} - {1}, ошибка: {2}")
    @Tags({
            @Tag("WEB"),
            @Tag("negative")
    })
    void passwordLoginFieldValidation(String searchQuery, String passwordQuery, String searchMistake) {

        $("#iPhoneOrEmail").setValue(searchQuery);
        $("#iPassword").setValue(passwordQuery);

        $("#cSignIn").click();

        $(byText(searchMistake))
                .shouldBe(visible)
                .shouldHave(cssValue("color", "rgba(227, 0, 0, 1)"));
    }
}