package pe.com.niubiz.services.channel.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Class for Swagger Properties.
 * @author alan.cabrera
 */
@Configuration
@ConfigurationProperties(prefix = "application.swagger")
@Getter
@Setter
public class SwaggerProperties {

  private String title;

  private String description;

  private String version;

}
