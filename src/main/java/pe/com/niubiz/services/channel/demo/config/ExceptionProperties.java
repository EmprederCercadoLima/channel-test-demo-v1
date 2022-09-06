package pe.com.niubiz.services.channel.demo.config;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import pe.com.niubiz.services.channel.demo.util.enums.ErrorCode;
import pe.com.niubiz.services.channel.demo.util.exception.ApiError;

/**
 * Class for Exception Properties.
 * @author alan.cabrera
 */
@Configuration
@ConfigurationProperties(prefix = "application")
@Getter
@Setter
public class ExceptionProperties {

  private Map<ErrorCode, ApiError> errors;

}
