package de.szilch.weatherdata.service;

import de.szilch.weatherdata.api.WeatherDataApi;
import de.szilch.weatherdata.domain.WeatherData;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RefreshScope
@Slf4j
public class WeatherDataResource implements WeatherDataApi {

    @Value("${app.location:Default}")
    private String serviceLocation;

    @Value("${humidity.enabled:false}")
    private boolean isHumidityEnabled;
    @Value("${temperature.enabled:false}")
    private boolean isTemperatureEnabled;
    @Value("${pressure.enabled:false}")
    private boolean isPressureEnabled;

    private LeadershipService leaderShipService;

    public WeatherDataResource(LeadershipService leaderShipService) {
        this.leaderShipService = leaderShipService;
        leaderShipService.addListener(new LeaderLatchListener() {
            @Override
            public void isLeader() {
                log.info("I am the Leader today :)");
            }

            @Override
            public void notLeader() {
                log.info("I am NOT the leader :(");
            }
        });
    }


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

        if (leaderShipService.isLeader()) {
            log.info("I am also responsible for Rock'N'Roll :)");
        }

        return builder.build();
    }
}
