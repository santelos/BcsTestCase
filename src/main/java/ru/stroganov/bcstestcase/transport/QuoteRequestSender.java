package ru.stroganov.bcstestcase.transport;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

/**
 * Сервис отправки сообщений на удаленный сервер
 * @author pstroganov
 *         Date: 16/05/2019
 */
@Service
public class QuoteRequestSender {

    /** Логгер */
    private final Logger logger;

    /** Параметры соединения с сервисом */
    private final QuoteSenderParams params;

    /** Транспорт для отправки запросов */
    private final RestTemplate transport;

    /**
     * Конструктор с DI
     * @param logger    логгер
     * @param params    параметры соединения с сервисом
     * @param transport транспорт для отправки запросов
     */
    public QuoteRequestSender(Logger logger, QuoteSenderParams params, RestTemplate transport) {
        this.logger = logger;
        this.params = params;
        this.transport = transport;
    }

    /** Запрос на получение котировки */
    private static final String QUOTE_REQUEST_PATH = "/stock/{0}/quote";

    /**
     * Пролучить котировку для сектора
     * @param sector сектор
     * @return Текущая котировка
     */
    public Quote getQuote(String sector) {
        String url = MessageFormat.format(params.getHost() + QUOTE_REQUEST_PATH, sector);
        logger.info("Quote request on path " + url);
        return transport.getForObject(url, Quote.class);
    }

}
