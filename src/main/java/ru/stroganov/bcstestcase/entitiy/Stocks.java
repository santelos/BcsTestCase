package ru.stroganov.bcstestcase.entitiy;

import java.util.List;

/**
 * Обертка над списком акций
 * @author pstroganov
 *         Date: 16/05/2019
 */
public class Stocks {

    /** Список акций */
    private List<Stock> stocks;

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    /**
     * Класс акции
     */
    public static class Stock {

        /** Сокраенное название организации */
        private String symbol;

        /** Количество акций */
        private long volume;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public long getVolume() {
            return volume;
        }

        public void setVolume(long volume) {
            this.volume = volume;
        }
    }
}
