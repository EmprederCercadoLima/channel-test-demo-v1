package pe.com.niubiz.services.channel.demo.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class for Api Error.
 * @author alan.cabrera
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

  private String code;

  private String message;

}
