package ru.stroganov.bcstestcase;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.stroganov.bcstestcase.entitiy.CalculationResult;
import ru.stroganov.bcstestcase.entitiy.Stocks;
import ru.stroganov.bcstestcase.processing.CalculationService;
import ru.stroganov.bcstestcase.rest.CalculationController;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тесты контроллера
 * @author pstroganov
 *         Date: 18/05/2019
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CalculationController.class)
@ActiveProfiles("test")
public class TestController {

    /** MVC мок */
    @Autowired
    private MockMvc mvc;

    /** Сериализация/десериализация объектов */
    @Autowired
    private ObjectMapper objectMapper;

    /** Мок на сервис подсчета */
    @MockBean
    private CalculationService service;

    /**
     * Перед каждым тестом нужно обновить мок сервиса
     */
    @Before
    public void beforeTest() {
        reset(service);
    }

    /**
     * Тест контроллера
     * Статус 200
     * @throws Throwable ошибка
     */
    @Test
    public void calculationControllerOkTest() throws Throwable {
        Stocks okStocks = getStocks();
        given(service.calculate(okStocks)).willReturn(any(CalculationResult.class));
        mvc.perform(post("/bcs/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(okStocks)))
                .andExpect(status().isOk());
    }

    /**
     * Тест контроллера
     * Статус 500
     * @throws Throwable ошибка
     */
    @Test
    public void calculationControllerErrorTest() throws Throwable {
        Stocks okStocks = getStocks();
        given(service.calculate(any(Stocks.class))).willThrow(new RuntimeException());
        mvc.perform(post("/bcs/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(okStocks)))
                .andExpect(status().isInternalServerError());
    }

    /**
     * Создание тестовых входных данных
     * @return входные данные
     */
    private Stocks getStocks() {
        Stocks stocks = new Stocks();
        Stocks.Stock stock = new Stocks.Stock();
        stock.setSymbol("AAPL");
        stock.setVolume(100);
        stocks.setStocks(Collections.singletonList(stock));
        return stocks;
    }

}
