package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.Map; // <-- ДОБАВЛЕН ИМПОРТ ДЛЯ MAP

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageSenderImplTest {

    @Mock
    private GeoServiceImpl geoService;

    @Mock
    private LocalizationServiceImpl localizationService;

    @InjectMocks
    private MessageSenderImpl messageSender;

    @Test
    void shouldSendRussianMessageForRussianIp() {
        Location russiaLoc = new Location("Moscow", Country.RUSSIA, "Moscow", 1);

        when(geoService.byIp("172.16.0.1")).thenReturn(russiaLoc);
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        // Исправлено: передаем Map вместо просто строки.
        // Внимание: ключ "x-real-ip" может отличаться в вашем коде!
        // Посмотрите в MessageSenderImpl, какое имя ключа он ищет (например, "X-Forwarded-For").
        Map<String, String> headers = Map.of("x-real-ip", "172.16.0.1");

        String result = messageSender.send(headers);

        Assertions.assertEquals("Добро пожаловать", result);
    }

    @Test
    void shouldSendEnglishMessageForAmericanIp() {
        Location usaLoc = new Location("New York", Country.USA, "New York", 2);

        when(geoService.byIp("96.1.1.1")).thenReturn(usaLoc);
        when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        // Аналогично для США
        Map<String, String> headers = Map.of("x-real-ip", "96.1.1.1");

        String result = messageSender.send(headers);

        Assertions.assertEquals("Welcome", result);
    }
}