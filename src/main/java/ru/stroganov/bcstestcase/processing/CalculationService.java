package ru.stroganov.bcstestcase.processing;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import ru.stroganov.bcstestcase.entitiy.CalculationResult;
import ru.stroganov.bcstestcase.entitiy.Stocks;
import ru.stroganov.bcstestcase.transport.Quote;
import ru.stroganov.bcstestcase.transport.QuoteRequestSender;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Collectors;

/**
 * Сервис подсчета акций
 * @author pstroganov
 *         Date: 16/05/2019
 */
@Service
public class CalculationService {

    /** Логгер */
    private final Logger logger;

    /** Сервис отправки сообщений на удаленный API */
    private final QuoteRequestSender quoteRequestSender;

    /**
     * Конструктор с DI
     * @param quoteRequestSender сервис отправки сообщений на удаленный API
     */
    public CalculationService(Logger logger, QuoteRequestSender quoteRequestSender) {
        this.logger = logger;
        this.quoteRequestSender = quoteRequestSender;
    }

    /**
     * Подсчитать необходимую информцию
     * @param stocks набор акций
     * @return Результат подсчета
     */
    public CalculationResult calculate(Stocks stocks) {
        CalculationResult result = new CalculationResult();
        // Посчитаем текущую стоимость
        result.setAllocations(stocks.getStocks().stream()
                .map(stock -> {
                    Quote quote = quoteRequestSender.getQuote(stock.getSymbol());
                    CalculationResult.Allocation allocation = new CalculationResult.Allocation();
                    allocation.setSector(quote.getSector());
                    allocation.setAssetValue(Math.round(quote.getLatestPrice() * stock.getVolume()));
                    return allocation;
                }).collect(Collectors.toList()));
        // Посчитаем общую стоимость
        result.setValue(result.getAllocations().stream().mapToLong(CalculationResult.Allocation::getAssetValue).sum());
        logger.info("Total cost: " + result.getValue());
        // Посчитаем пропорции к общей стоимости
        result.getAllocations().forEach(allocation ->
                allocation.setProportion(divideWithPrecision(allocation.getAssetValue(), result.getValue())));
        return result;
    }

    /**
     * Разделить два числа с округлением до 3 знака после запятой
     * @param val делимое
     * @param div делитель
     * @return Результат деления
     */
    private BigDecimal divideWithPrecision(long val, long div) {
        return BigDecimal.valueOf((double) val / div)
                .setScale(3, RoundingMode.HALF_UP);
    }

}