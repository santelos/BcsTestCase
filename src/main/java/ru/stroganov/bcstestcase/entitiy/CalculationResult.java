package ru.stroganov.bcstestcase.entitiy;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.util.List;

/**
 * Результат подсчета акций
 * @author pstroganov
 *         Date: 16/05/2019
 */
public class CalculationResult {

    /** Суммарная стоимость набора акций */
    private long value;

    /** Распределение по секторам */
    private List<Allocation> allocations;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public List<Allocation> getAllocations() {
        return allocations;
    }

    public void setAllocations(List<Allocation> allocations) {
        this.allocations = allocations;
    }

    /**
     * Информация о секторе
     */
    public static class Allocation {

        /** Сектор работы компании */
        private String sector;

        /** Текущая стоимость */
        private long assetValue;

        /** Процентное соотношение к общему результату */
        private BigDecimal proportion;

        public String getSector() {
            return sector;
        }

        public void setSector(String sector) {
            this.sector = sector;
        }

        public long getAssetValue() {
            return assetValue;
        }

        public void setAssetValue(long assetValue) {
            this.assetValue = assetValue;
        }

        public BigDecimal getProportion() {
            return proportion;
        }

        public void setProportion(BigDecimal proportion) {
            this.proportion = proportion;
        }
    }

}
