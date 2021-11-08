package com.twitter.challenge;

public class TemperatureConverter {
    /**
     * Converts temperature in Celsius to temperature in Fahrenheit.
     *
     * @param temperatureInCelsius Temperature in Celsius to convert.
     * @return Temperature in Fahrenheit.
     */
    public static float celsiusToFahrenheit(double temperatureInCelsius) {
        return (float)temperatureInCelsius * 1.8f + 32;
    }
}
