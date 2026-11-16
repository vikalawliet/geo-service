package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

class LocalizationServiceImplTest {

    private final LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

    @Test
    void shouldReturnRussianTextForRussia() {
        // Проверяем Россию
        String result = localizationService.locale(Country.RUSSIA);
        Assertions.assertEquals("Добро пожаловать", result);
    }

    @Test
    void shouldReturnEnglishTextForUsa() {
        // Проверяем США (и другие страны, так как там default)
        String result = localizationService.locale(Country.USA);
        Assertions.assertEquals("Welcome", result);
    }
}