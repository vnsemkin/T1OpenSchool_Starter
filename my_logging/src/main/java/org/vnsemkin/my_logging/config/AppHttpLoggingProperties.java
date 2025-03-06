package org.vnsemkin.my_logging.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.logging.LogLevel;

@Getter
@Setter
@ConfigurationProperties(prefix = "logging.http")
public class AppHttpLoggingProperties {
  /**
   * Флаг, определяющий, включено ли логирование HTTP-запросов и ответов.
   * По умолчанию: true.
   */
  private boolean enabled = true;

  /**
   * Уровень логирования: INFO, DEBUG, WARN, ERROR.
   * По умолчанию: INFO.
   */
  private LogLevel level = LogLevel.INFO;
}
