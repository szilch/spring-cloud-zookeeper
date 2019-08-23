package de.szilch.weatherdata.api;

import de.szilch.weatherdata.domain.WeatherData;
import org.springframework.web.bind.annotation.GetMapping;

public interface WeatherDataApi {

     @GetMapping("/data")
     WeatherData getData();

}
