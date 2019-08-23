package de.szilch.weatherdata.domain;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {
    private String location;
    private double degree;
    private double humidity;
    private double pressure;
}
