package pe.com.niubiz.services.channel.demo.config;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Class for Support Properties.
 * @author alan.cabrera
 */
@Configuration
@ConfigurationProperties(prefix = "application.support-demo-v1")
@Getter
@Setter
public class DemoProperties {

  private Map<String, String> headers;

  private Map<String, String> httpClient;

}
