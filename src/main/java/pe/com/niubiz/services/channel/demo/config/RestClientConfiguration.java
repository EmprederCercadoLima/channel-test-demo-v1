package pe.com.niubiz.services.channel.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pe.com.niubiz.services.channel.demo.service.proxy.supportdemo.SupportDemoV1Api;

/**
 * Retrofit configuration.
 * @author alan.cabrera
 */
@Configuration
public class RestClientConfiguration {

  @Bean
  SupportDemoV1Api financialProfilerV1Api(DemoProperties properties) {
    return ApiOkHttpClient.builder()
        .httpClient(properties.getHttpClient())
        .build()
        .buildProxy(SupportDemoV1Api.class);
  }

}
