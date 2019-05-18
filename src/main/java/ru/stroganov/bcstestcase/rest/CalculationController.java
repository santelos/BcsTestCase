package ru.stroganov.bcstestcase.rest;

import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.stroganov.bcstestcase.entitiy.CalculationResult;
import ru.stroganov.bcstestcase.entitiy.Stocks;
import ru.stroganov.bcstestcase.processing.CalculationService;

/**
 * Контроллер
 * @author pstroganov
 *         Date: 16/05/2019
 */
@RestController("bcs")
public class CalculationController {

    /** Логгер */
    private final Logger logger;

    /** Сервис обработки запросов */
    private final CalculationService calculationService;

    /**
     * Конструктор c DI
     * @param logger             логгер
     * @param calculationService сервис обработки запросов
     */
    public CalculationController(Logger logger, CalculationService calculationService) {
        this.logger = logger;
        this.calculationService = calculationService;
    }

    /**
     * Посчитать текущую стоимость портфеля
     * @param stocks список акций
     */
    @PostMapping(name = "calculate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CalculationResult calculate(@RequestBody Stocks stocks) {
        logger.info("Calculate request invoked with params size: " + stocks.getStocks().size());
        return calculationService.calculate(stocks);
    }

}
