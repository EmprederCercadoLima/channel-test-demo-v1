package pe.com.niubiz.services.channel.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger configuration.
 * @author alan.cabrera
 */
@Configuration
public class SwaggerConfig {

  /**
   * Custom Swagger Configuration.
   * @param swaggerProperties {@link SwaggerProperties}
   * @return {@link Docket}
   */
  @Bean
  public Docket api(SwaggerProperties swaggerProperties) {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(this.metadata(swaggerProperties))
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo metadata(SwaggerProperties swaggerProperties) {
    String title = swaggerProperties.getTitle();
    String description = swaggerProperties.getDescription();
    String version = swaggerProperties.getVersion();
    return (new ApiInfoBuilder()).title(title).description(description).version(version).build();
  }

}
