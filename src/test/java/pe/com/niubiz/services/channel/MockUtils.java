package pe.com.niubiz.services.channel;

import static pe.com.niubiz.services.channel.demo.util.exception.ExceptionsUtils.createException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.reactivex.observers.TestObserver;
import io.vavr.control.Try;

import okhttp3.MediaType;
import okhttp3.ResponseBody;

import org.junit.jupiter.api.Assertions;

import org.springframework.http.HttpStatus;

import pe.com.niubiz.services.channel.demo.expose.web.model.CustomErrorResponse;
import pe.com.niubiz.services.channel.demo.util.enums.ErrorCode;
import pe.com.niubiz.services.channel.demo.util.exception.DemoException;
import pe.com.niubiz.services.support.demo.model.ParameterResponse;
import retrofit2.HttpException;
import retrofit2.Response;

public class MockUtils {

  private static final ObjectMapper objectMapper = new ObjectMapper()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  
  

  public static <T> void validateSuccessResponse(T response, TestObserver<?> result) {
    result.awaitTerminalEvent();
    result.onComplete();
    result.assertNoErrors();
    result.assertValue(r -> r.equals(response));
  }

  public static void validateSuccessResponse(TestObserver<?> result) {
    result.awaitTerminalEvent();
    result.onComplete();
    result.assertNoErrors();
  }

  public static <T> void validateSuccessResponseEmpty(TestObserver<?> result) {
    result.awaitTerminalEvent();
    result.onComplete();
    result.assertNoErrors();
    result.assertNoValues();
  }

  public static void validateErrorResponse(ErrorCode errorCode, TestObserver<?> result) {
    result.awaitTerminalEvent();
    result.assertNotComplete();
    result.assertFailure(ex -> MockUtils.validateException(errorCode, ex));
  }

  public static void validateErrorResponse(ErrorCode errorCode, String code, TestObserver<?> result) {
    result.awaitTerminalEvent();
    result.assertNotComplete();
    result.assertFailure(ex -> MockUtils.validateCustomException(errorCode.getHttpStatus(), code, ex));
  }

  public static boolean validateException(ErrorCode errorCodeExpected, Throwable exceptionActual) {
    Assertions.assertTrue(exceptionActual instanceof DemoException, "validateException InstanceOf");
    Assertions.assertEquals(errorCodeExpected.name(), ((DemoException) exceptionActual).getReason(),
        "validateException Code");
    Assertions.assertEquals(errorCodeExpected.getHttpStatus(), ((DemoException) exceptionActual).getStatus(),
        "validateException HttpStatus");
    return true;
  }

  public static boolean validateCustomException(HttpStatus httpStatusExpected, String errorCodeExpected,
      Throwable exceptionActual) {
    Assertions.assertTrue(exceptionActual instanceof DemoException, "validateCustomException InstanceOf");
    Assertions.assertEquals(errorCodeExpected, ((DemoException) exceptionActual).getReason(),
        "validateCustomException Code");
    Assertions.assertEquals(httpStatusExpected, ((DemoException) exceptionActual).getStatus(),
        "validateCustomException HttpStatus");
    return true;
  }

  public static HttpException mockHttpException(ErrorCode errorCode) {
    return Try.of(() -> {
      var customError = CustomErrorResponse.builder().code(errorCode.name()).build();
      var content = objectMapper.writeValueAsString(customError);
      return mockHttpException(errorCode.getHttpStatus(), content);
    }).getOrElseThrow(ex -> createException(ErrorCode.SERVER_ERROR_THROWABLE, ex));
  }

  public static HttpException mockHttpException(ErrorCode errorCode, String code) {
    return Try.of(() -> {
      var customError = CustomErrorResponse.builder().code(code).build();
      var content = objectMapper.writeValueAsString(customError);
      return mockHttpException(errorCode.getHttpStatus(), content);
    }).getOrElseThrow(ex -> createException(ErrorCode.SERVER_ERROR_THROWABLE, ex));
  }

  public static HttpException mockHttpException(HttpStatus httpStatus, String content) {
    var responseBody = ResponseBody.create(MediaType.parse("application/json"), content);
    return new HttpException(Response.error(httpStatus.value(), responseBody));
  }

  public static ParameterResponse mockParameterResponse() {
    ParameterResponse parameter = new ParameterResponse();
    parameter.setParameterId("DNI");
    parameter.setValue("DNI");
    return parameter;
  }

  public static ParameterResponse mockParameterToResponse() {
    ParameterResponse response = new ParameterResponse();
    response.parameterId("DNI");
    response.value("DNI");
    return response;
  }

}
