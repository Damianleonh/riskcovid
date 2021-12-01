package com.leon.prueb1.models;

public class User {

    Double longi;
    Double lati;

    public User(Double longi, Double lati) {
        this.longi = longi;
        this.lati = lati;
    }

    public User() {
    }

    public Double getLongi() {
        return longi;
    }

    public void setLongi(Double longi) {
        this.longi = longi;
    }

    public Double getLati() {
        return lati;
    }

    public void setLati(Double lati) {
        this.lati = lati;
    }
}

