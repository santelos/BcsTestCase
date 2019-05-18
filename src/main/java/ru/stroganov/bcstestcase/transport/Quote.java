package ru.stroganov.bcstestcase.transport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Котировка
 * @author pstroganov
 * Date: 16/05/2019
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    /** Текущая котировка */
    private double latestPrice;

    /** Сектор */
    private String sector;

    public double getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(double latestPrice) {
        this.latestPrice = latestPrice;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
