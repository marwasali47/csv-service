FROM dockerproxy-iva.si.francetelecom.fr/openjdk:8-slim

ARG JAR_FILE

ENV ACTIVE_PROFILE ""

ADD ${JAR_FILE} /opt/hubme/gpc-consumer-service.jar

WORKDIR /opt/hubme

RUN mkdir -p /opt/hubme/orange

RUN chown -R 1003540000:1003540000 /opt/hubme

USER 1003540000

ENTRYPOINT ["sh","-c","java -Dspring.profiles.active=${ACTIVE_PROFILE} \
                            -DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector \
                            -Dhttp.proxyHost=cs.pr-proxy.service.sd.diod.tech \
                            -Dhttp.proxyPort=3128 \
                            -Dhttps.proxyHost=cs.pr-proxy.service.sd.diod.tech \
                            -Dhttps.proxyPort=3128 \
                            -Dhttp.nonProxyHosts=\"localhost|127.0.0.1|*.local\" \
                            -jar gpc-consumer-service.jar"]
