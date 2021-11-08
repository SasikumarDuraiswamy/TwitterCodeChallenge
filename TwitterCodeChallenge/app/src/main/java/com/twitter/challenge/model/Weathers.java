package com.twitter.challenge.model;

public class Weathers {
    public Coord coord;
    public Weather weather;
    public Wind wind;
    public Clouds clouds;
    public String name;

    public Clouds getClouds() {
        return clouds;
    }

    public Coord getCoord() {
        return coord;
    }

    public String getName() {
        return name;
    }

    public Weather getWeather() {
        return weather;
    }

    public Wind getWind() {
        return wind;
    }
}
