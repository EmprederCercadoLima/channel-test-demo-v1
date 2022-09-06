package pe.com.niubiz.services.channel.demo.config;

import static com.fasterxml.jackson.core.JsonGenerator.Feature.IGNORE_UNKNOWN;
import static com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import lombok.Builder;

import okhttp3.ConnectionPool;

import pe.com.niubiz.services.channel.demo.util.enums.HttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Api Ok Http Client configuration.
 * @author alan.cabrera
 */
@Builder
public class ApiOkHttpClient {

  private Map<String, String> httpClient;

  private okhttp3.OkHttpClient getApiOkHttpClient() {
    return new okhttp3.OkHttpClient.Builder()
        .connectTimeout(Integer.parseInt(
            this.httpClient.get(HttpClient.CONNECT_TIMEOUT.getName())), TimeUnit.SECONDS)
        .readTimeout(Integer.parseInt(
            this.httpClient.get(HttpClient.READ_TIMEOUT.getName())), TimeUnit.SECONDS)
        .writeTimeout(Integer.parseInt(
            this.httpClient.get(HttpClient.WRITE_TIMEOUT.getName())), TimeUnit.SECONDS)
        .connectionPool(new ConnectionPool())
        .build();
  }

  /**
   * Retrofit builder.
   */
  public <T> T buildProxy(Class<T> clazz) {
    return new Retrofit.Builder().baseUrl(this.httpClient.get(HttpClient.BASE_URL.getName()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(JacksonConverterFactory.create(this.getObjectMapper()))
        .client(this.getApiOkHttpClient()).build().create(clazz);
  }

  private ObjectMapper getObjectMapper() {
    return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .setDateFormat(new StdDateFormat()).disable(FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(WRITE_DATES_AS_TIMESTAMPS).enable(ACCEPT_CASE_INSENSITIVE_PROPERTIES)
        .enable(IGNORE_UNKNOWN).enable(ALLOW_COMMENTS);
  }

}
