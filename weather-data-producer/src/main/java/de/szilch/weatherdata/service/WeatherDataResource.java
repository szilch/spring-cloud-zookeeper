package de.szilch.weatherdata.service;

import de.szilch.weatherdata.api.WeatherDataApi;
import de.szilch.weatherdata.domain.WeatherData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class WeatherDataResource implements WeatherDataApi {

    @Value("${app.location:Default}")
    private String serviceLocation;

    @Override
    public WeatherData getData() {
        return WeatherData.builder()
                .location(serviceLocation)
                .degree(ThreadLocalRandom.current().nextDouble(-20, 42))
                .humidity(ThreadLocalRandom.current().nextDouble(0, 100))
                .pressure(ThreadLocalRandom.current().nextDouble(940, 1100))
                .build();
    }
}
