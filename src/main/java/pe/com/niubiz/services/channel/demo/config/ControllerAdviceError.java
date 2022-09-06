package pe.com.niubiz.services.channel.demo.config;

import java.nio.file.AccessDeniedException;

import javax.naming.AuthenticationException;
import javax.validation.ValidationException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebInputException;

import pe.com.niubiz.services.channel.demo.expose.web.model.CustomErrorResponse;
import pe.com.niubiz.services.channel.demo.util.StringsUtil;
import pe.com.niubiz.services.channel.demo.util.enums.ErrorCode;
import pe.com.niubiz.services.channel.demo.util.exception.DemoException;

/**
 * Custom Handler error for Controller.
 * @author alan.cabrera
 */
@RestControllerAdvice
@Order(-3)
@Slf4j
public class ControllerAdviceError {

  @Autowired
  private ExceptionProperties exceptionProperties;

  /**
   * Return custom exception with HttpStatus.
   * @param ex {@link DemoException}
   * @return {@link ResponseEntity}
   */
  @ResponseBody
  @ExceptionHandler(value = DemoException.class)
  public ResponseEntity<Object> handleException(DemoException ex) {
    log.error("CUSTOM EXCEPTION => ", ex);
    String code = ex.isExternalErrorCode()
        ? ex.getReason()
        : exceptionProperties.getErrors().get(ex.getErrorCode()).getCode();
    this.logCustomError("CUSTOM EXCEPTION", code);
    return ResponseEntity
        .status(ex.getStatus())
        .body(CustomErrorResponse.builder()
            .code(code)
            .build());
  }

  /**
   * Return CustomErrorResponse with HttpStatus BAD_REQUEST.
   * @param ex {@link ServerWebInputException}
   * @return {@link CustomErrorResponse}
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({ServerWebInputException.class, ValidationException.class})
  public CustomErrorResponse exceptionBadRequest(Exception ex) {
    log.error("EXCEPTION - BAD_REQUEST: ", ex);
    String code = exceptionProperties.getErrors().get(ErrorCode.BAD_REQUEST_EXCEPTION).getCode();
    this.logCustomError("EXCEPTION - BAD_REQUEST", code);
    return CustomErrorResponse.builder()
        .code(code)
        .build();
  }

  /**
   * Return CustomErrorResponse with HttpStatus UNAUTHORIZED.
   * @param ex {@link AuthenticationException}
   * @return {@link CustomErrorResponse}
   */
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(AuthenticationException.class)
  public CustomErrorResponse exception(AuthenticationException ex) {
    log.error("EXCEPTION - UNAUTHORIZED: ", ex);
    String code = exceptionProperties.getErrors().get(ErrorCode.UNAUTHORIZED_EXCEPTION).getCode();
    this.logCustomError("EXCEPTION - UNAUTHORIZED", code);
    return CustomErrorResponse.builder()
        .code(code)
        .build();
  }

  /**
   * Return CustomErrorResponse with HttpStatus FORBIDDEN.
   * @param ex {@link AccessDeniedException}
   * @return {@link CustomErrorResponse}
   */
  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ExceptionHandler(AccessDeniedException.class)
  public CustomErrorResponse exception(AccessDeniedException ex) {
    log.error("EXCEPTION - FORBIDDEN: ", ex);
    String code = exceptionProperties.getErrors().get(ErrorCode.FORBIDDEN_EXCEPTION).getCode();
    this.logCustomError("EXCEPTION - FORBIDDEN", code);
    return CustomErrorResponse.builder()
        .code(code)
        .build();
  }

  /**
   * Return CustomErrorResponse with HttpStatus INTERNAL_SERVER_ERROR.
   * @param ex {@link Exception}
   * @return {@link CustomErrorResponse}
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public CustomErrorResponse exception(Exception ex) {
    log.error("EXCEPTION - INTERNAL_SERVER_ERROR: ", ex);
    String code = exceptionProperties.getErrors().get(ErrorCode.SERVER_ERROR_EXCEPTION).getCode();
    this.logCustomError("EXCEPTION - INTERNAL_SERVER_ERROR", code);
    return CustomErrorResponse.builder()
        .code(code)
        .build();
  }

  /**
   * Return CustomErrorResponse with HttpStatus INTERNAL_SERVER_ERROR.
   * @param ex {@link Throwable}
   * @return {@link CustomErrorResponse}
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Throwable.class)
  public CustomErrorResponse exception(Throwable ex) {
    log.error("THROWABLE - INTERNAL_SERVER_ERROR: ", ex);
    String code = exceptionProperties.getErrors().get(ErrorCode.SERVER_ERROR_THROWABLE).getCode();
    this.logCustomError("EXCEPTION - INTERNAL_SERVER_ERROR", code);
    return CustomErrorResponse.builder()
        .code(code)
        .build();
  }

  private void logCustomError(String msg, String code) {
    log.error(
        StringsUtil.replaceSpaces(msg.concat(" CODE => {}")),
        StringsUtil.replaceSpaces("ERROR-".concat(code)));
  }

}
