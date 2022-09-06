package pe.com.niubiz.services.channel.demo.util.exception;

import java.util.Date;
import lombok.Getter;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import pe.com.niubiz.services.channel.demo.util.enums.ErrorCode;

/**
 * Custom Exception.
 * @author alan.cabrera
 */
@Getter
public class DemoException extends NestedRuntimeException {

  private final HttpStatus status;

  private final String reason;

  private final ErrorCode errorCode;

  private final Date timeStamp = new Date();

  private final boolean isExternalErrorCode;

  /**
   * Return custom exception.
   * @param status {@link HttpStatus}
   * @param reason {@link String}
   * @param ex {@link Throwable}
   * @param errorCode {@link ErrorCode}
   */
  public DemoException(HttpStatus status, @Nullable String reason, Throwable ex,
                       ErrorCode errorCode) {
    super(reason, ex);
    Assert.notNull(status, "HttpStatus is required");
    Assert.notNull(reason, "Reason is required");
    this.status = status;
    this.reason = reason;
    this.errorCode = errorCode;
    this.isExternalErrorCode = !errorCode.name().equals(reason);
  }

}
