package pe.com.niubiz.services.channel.demo.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Enum for Custom Errors.
 * @author alan.cabrera
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

  EXTERNAL_API(HttpStatus.INTERNAL_SERVER_ERROR),
  EXTERNAL_API_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
  BAD_REQUEST_EXCEPTION(HttpStatus.BAD_REQUEST),
  SERVER_ERROR_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR),
  SERVER_ERROR_THROWABLE(HttpStatus.INTERNAL_SERVER_ERROR),
  UNAUTHORIZED_EXCEPTION(HttpStatus.UNAUTHORIZED),
  FORBIDDEN_EXCEPTION(HttpStatus.FORBIDDEN),
  ILLEGAL_CREATION(HttpStatus.INTERNAL_SERVER_ERROR),
  INVALID_SWAGGER_NOTE(HttpStatus.INTERNAL_SERVER_ERROR)
  ;

  private HttpStatus httpStatus;

}
