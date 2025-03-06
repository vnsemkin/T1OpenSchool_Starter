package org.vnsemkin.my_logging.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vnsemkin.my_logging.aspect.AppHttpLoggingAspect;

@Configuration
@ConditionalOnProperty(
    prefix = "logging.http",
    name = "enabled",
    havingValue = "true",
    matchIfMissing = true)
@EnableConfigurationProperties(AppHttpLoggingProperties.class)
public class AppHttpLoggingAutoConfiguration {

  @Bean
  public AppHttpLoggingAspect httpLoggingAspect(AppHttpLoggingProperties appHttpLoggingProperties) {
    return new AppHttpLoggingAspect(appHttpLoggingProperties);
  }
}
