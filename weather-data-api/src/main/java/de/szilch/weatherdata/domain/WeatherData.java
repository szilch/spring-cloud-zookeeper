package de.szilch.weatherdata.domain;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {
    private String location;
    private Double temperature;
    private Double humidity;
    private Double pressure;
}
