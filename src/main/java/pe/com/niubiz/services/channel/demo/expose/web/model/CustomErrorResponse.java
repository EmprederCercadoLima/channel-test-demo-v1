package pe.com.niubiz.services.channel.demo.expose.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Class for Custom Error Response.
 * @author alan.cabrera
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorResponse {

  private String code;

}
