package pe.com.niubiz.services.channel.demo.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum for External Api.
 * @author alan.cabrera
 */
@Getter
@AllArgsConstructor
public enum ExternalApi {
  SUPPORT_DEMO("DEMO-01");
  
  private String suffixCode;

}
