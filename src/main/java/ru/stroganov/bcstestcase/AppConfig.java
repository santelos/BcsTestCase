package ru.stroganov.bcstestcase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Конфигурация приложения
 * @author pstroganov
 *         Date: 16/05/2019
 */
@Configuration
@EnableWebMvc
@ControllerAdvice
public class AppConfig {

    /**
     * Основной логгер приложения
     * @return бин логгера
     */
    @Bean
    @Primary
    public Logger logger() {
        return LoggerFactory.getLogger("application");
    }

    /**
     * Транспорт для отправки http запросов
     * @return бин транспорта
     */
    @Bean
    @Primary
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Обработка всех возможных исключений
     * @param t ошибка сервера
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void hanldeAllExceptions(Throwable t) {
        logger().error(t.getMessage(), t);
    }

}
