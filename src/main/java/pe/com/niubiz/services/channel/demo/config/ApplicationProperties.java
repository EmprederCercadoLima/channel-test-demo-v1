package pe.com.niubiz.services.channel.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Class for Application Properties.
 * @author alan.cabrera
 */
@Configuration
@ConfigurationProperties(prefix = "application.demo")
@Getter
@Setter
public class ApplicationProperties {

  private String dummy;

}
