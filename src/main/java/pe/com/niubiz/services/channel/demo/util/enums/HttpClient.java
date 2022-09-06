package pe.com.niubiz.services.channel.demo.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum for Http Client Properties.
 * @author alan.cabrera
 */
@Getter
@AllArgsConstructor
public enum HttpClient {

  BASE_URL("base-url"),
  CONNECT_TIMEOUT("connect-timeout"),
  READ_TIMEOUT("read-timeout"),
  WRITE_TIMEOUT("write-timeout");

  private String name;

}
