#!/bin/sh

if [[ -z "${JAVA_HOME}" ]]; then
  echo "JAVA_HOME не установлена. Пожалуйста установите"
  exit 1
else
  JAVA="${JAVA_HOME}/bin/java"
fi

cd ../;

SERVER_HOME=$PWD
SPRING_OPTS="--spring.config.location=${SERVER_HOME}/config/"

case $1 in
    start)
        if test -f "$SERVER_HOME"/application.pid; then
            echo "Приложение уже запущено";
            exit 1
        else
            "$JAVA" -jar $SERVER_HOME/lib/application.jar $SPRING_OPTS &
        fi

    ;;
    stop)
        if test -f "$SERVER_HOME"/application.pid; then
            kill -9 $(cat "${SERVER_HOME}/application.pid")
            rm -f "${SERVER_HOME}/application.pid"
        else
            echo "Приложение не запущено"
            exit 1
        fi
    ;;
    *)
        echo "Некоректный параметр запуска. Ожидалось {start, stop}"
    ;;
esac
