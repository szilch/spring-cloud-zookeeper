package de.szilch.weatherdata.service;

import de.szilch.weatherdata.api.WeatherDataApi;
import de.szilch.weatherdata.domain.WeatherData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RefreshScope
public class WeatherDataResource implements WeatherDataApi {

    @Value("${app.location:Default}")
    private String serviceLocation;

    @Value("${humidity.enabled:false}")
    private boolean isHumidityEnabled;
    @Value("${temperature.enabled:false}")
    private boolean isTemperatureEnabled;
    @Value("${pressure.enabled:false}")
    private boolean isPressureEnabled;

    @Override
    public WeatherData getData() {
        WeatherData.WeatherDataBuilder builder = WeatherData.builder();
        builder.location(serviceLocation);
        if (isHumidityEnabled) {
            builder.humidity(ThreadLocalRandom.current().nextDouble(0, 100));
        }
        if (isTemperatureEnabled) {
            builder.temperature(ThreadLocalRandom.current().nextDouble(-20, 42));
        }
        if (isPressureEnabled) {
            builder.pressure(ThreadLocalRandom.current().nextDouble(940, 1100));
        }
        return builder.build();
    }
}
