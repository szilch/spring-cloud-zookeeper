package de.szilch.weatherdata.client;

import de.szilch.weatherdata.api.WeatherDataApi;
import de.szilch.weatherdata.domain.WeatherData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@FeignClient("weather-data-producer")
public interface WeatherDataClient extends WeatherDataApi {

}
