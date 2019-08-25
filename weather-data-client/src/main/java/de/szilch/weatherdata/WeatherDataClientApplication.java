package de.szilch.weatherdata;

import de.szilch.weatherdata.client.WeatherDataClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
@Component
public class WeatherDataClientApplication {

    private WeatherDataClient weatherDataClient;

    @Autowired
    public WeatherDataClientApplication(WeatherDataClient weatherDataClient) {
        this.weatherDataClient = weatherDataClient;
    }

    @Scheduled(fixedRate = 5000)
    public void receiveWeatherData() {
        System.out.println("WeatherData received: " + weatherDataClient.getData());
    }

    public static void main(String[] args) {
        SpringApplication.run(WeatherDataClientApplication.class, args);
    }
}
