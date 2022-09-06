package pe.com.niubiz.services.channel.demo.util.exception;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vavr.control.Try;
import java.io.IOException;
import okhttp3.ResponseBody;
import org.springframework.http.HttpStatus;

import pe.com.niubiz.services.channel.demo.expose.web.model.CustomErrorResponse;
import pe.com.niubiz.services.channel.demo.util.StringsUtil;
import pe.com.niubiz.services.channel.demo.util.enums.ErrorCode;
import pe.com.niubiz.services.channel.demo.util.enums.ExternalApi;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * Utils for create custom exceptions.
 * @author alan.cabrera
 */
public class ExceptionsUtils {

  private ExceptionsUtils() {
    throw createException(ErrorCode.ILLEGAL_CREATION);
  }

  private static final ObjectMapper objectMapper = new ObjectMapper()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  /**
   * Create exception from custom validation without exception.
   * @param errorCode {@link ErrorCode}
   * @return {@link DemoException}
   */
  public static DemoException createException(ErrorCode errorCode) {
    return new DemoException(
        errorCode.getHttpStatus(), errorCode.name(), new Exception(errorCode.name()), errorCode);
  }

  /**
   * Create exception with exception.
   * @param errorCode {@link ErrorCode}
   * @param ex {@link Throwable}
   * @return {@link DemoException}
   */
  public static DemoException createException(ErrorCode errorCode,
                                              Throwable ex) {
    return new DemoException(
        errorCode.getHttpStatus(),errorCode.name(), ex, errorCode);
  }

  /**
   * Create exception from external api exception.
   * @param externalApi {@link ExternalApi}
   * @param ex {@link Throwable}
   * @return {@link DemoException}
   */
  public static DemoException createException(ExternalApi externalApi,
                                              Throwable ex) {
    return (ex instanceof HttpException)
        ? createExternalApiException(externalApi, ErrorCode.EXTERNAL_API, (HttpException) ex)
        : createExternalApiException(externalApi, ErrorCode.EXTERNAL_API_ERROR, ex);
  }

  private static DemoException createExternalApiException(ExternalApi externalApi,
                                                          ErrorCode errorCode,
                                                          HttpException ex) {
    Response<?> response = ex.response();
    return Try.of(() -> {
      CustomErrorResponse customErrorResponse = getCustomErrorResponse(response);
      return new DemoException(
          HttpStatus.valueOf(response.code()),
          StringsUtil.customExternalCode(externalApi, customErrorResponse.getCode()),
          new Exception(errorCode.name()), errorCode);
    })
        .getOrElseGet(e -> new DemoException(
            errorCode.getHttpStatus(), externalApi.getSuffixCode().concat("000"), ex, errorCode));
  }

  private static DemoException createExternalApiException(ExternalApi externalApi,
                                                          ErrorCode errorCode,
                                                          Throwable ex) {
    return new DemoException(
        errorCode.getHttpStatus(), externalApi.getSuffixCode().concat("000"), ex, errorCode);
  }

  private static CustomErrorResponse getCustomErrorResponse(Response<?> response)
      throws IOException {
    ResponseBody responseBody = response.errorBody();
    assert responseBody != null;
    return objectMapper.readValue(responseBody.string(), CustomErrorResponse.class);
  }

}
