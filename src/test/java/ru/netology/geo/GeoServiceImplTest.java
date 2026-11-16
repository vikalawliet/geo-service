package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

class GeoServiceImplTest {

    private final GeoServiceImpl geoService = new GeoServiceImpl();

    @Test
    void shouldReturnRussiaForRussianIp() {
        Location result = geoService.byIp("172.16.0.1");

        // Сравниваем с enum Country.RUSSIA, а не со строкой "RUSSIA" или "Russia"
        Assertions.assertEquals(Country.RUSSIA, result.getCountry());
    }

    @Test
    void shouldReturnUsaForAmericanIp() {
        Location result = geoService.byIp("96.1.1.1");

        // Сравниваем с enum Country.USA
        Assertions.assertEquals(Country.USA, result.getCountry());
    }
}