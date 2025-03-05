package org.vnsemkin.my_logging.aspect;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.vnsemkin.my_logging.config.AppHttpLoggingProperties;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class AppHttpLoggingAspect {

  private final AppHttpLoggingProperties properties;

  // Точка подключения: все методы классов, аннотированных @RestController
  @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
  public void controllerMethods() {}

  @Around("controllerMethods()")
  public Object logHttpRequestResponse(ProceedingJoinPoint joinPoint) throws Throwable {
    // Получаем HttpServletRequest и HttpServletResponse через RequestContextHolder
    ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (attrs == null) {
      // Если RequestContext отсутствует, продолжаем выполнение
      return joinPoint.proceed();
    }
    HttpServletRequest request = attrs.getRequest();
    HttpServletResponse response = attrs.getResponse();

    String method = request.getMethod();
    String uri = request.getRequestURI();
    String queryString = request.getQueryString();
    String fullUrl = uri + (queryString != null ? "?" + queryString : "");

    // Логирование входящего запроса
    logAtLevel(String.format("[HTTP REQUEST] %s %s", method, fullUrl));

    long startTime = System.currentTimeMillis();
    Object result;
    try {
      result = joinPoint.proceed();
    } catch (Throwable ex) {
      long duration = System.currentTimeMillis() - startTime;
      logAtLevel(String.format("[HTTP RESPONSE] %s %s - Exception: %s, Time: %d ms",
              method, fullUrl, ex.getMessage(), duration));
      throw ex;
    }
    long duration = System.currentTimeMillis() - startTime;

    // Если response равен null, значит статус ответа не определён.
    String status = (response != null) ? String.valueOf(response.getStatus()) : "N/A";

    // Логирование исходящего ответа с замером времени выполнения
    logAtLevel(String.format("[HTTP RESPONSE] %s %s - Status: %s, Time: %d ms", method, fullUrl, status, duration));

    return result;
  }

  // Вспомогательный метод для логирования с учетом выбранного уровня
  private void logAtLevel(String message) {
    switch (properties.getLevel()) {
      case DEBUG -> log.debug(message);
      case WARN-> log.warn(message);
      case ERROR-> log.error(message);
      default-> log.info(message);
    }
  }
}

