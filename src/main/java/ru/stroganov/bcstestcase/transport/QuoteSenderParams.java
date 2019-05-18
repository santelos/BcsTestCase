package ru.stroganov.bcstestcase.transport;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Параметры соединения с удаленным сервисом
 * @author pstroganov
 * Date: 16/05/2019
 */
@Component
@ConfigurationProperties("quote")
public class QuoteSenderParams {

    /** Имя хоста */
    private String host;

    /** Таймаут соединения */
    private long timeout;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
