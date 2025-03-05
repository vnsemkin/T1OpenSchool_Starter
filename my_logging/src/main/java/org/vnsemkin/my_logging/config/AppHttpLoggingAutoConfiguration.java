package org.vnsemkin.my_logging.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.vnsemkin.my_logging.aspect.AppHttpLoggingAspect;

@Configuration
@ConditionalOnProperty(
    prefix = "logging.http",
    name = "enabled",
    havingValue = "true",
    matchIfMissing = true)
@ConditionalOnClass(DispatcherServlet.class)
@EnableConfigurationProperties(AppHttpLoggingProperties.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class AppHttpLoggingAutoConfiguration {

  @Bean
  public AppHttpLoggingAspect httpLoggingAspect(AppHttpLoggingProperties appHttpLoggingProperties) {
    return new AppHttpLoggingAspect(appHttpLoggingProperties);
  }
}
