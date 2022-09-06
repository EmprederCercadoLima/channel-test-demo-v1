package pe.com.niubiz.services.channel.demo.util.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.http.HttpStatus;

import pe.com.niubiz.services.channel.MockUtils;
import pe.com.niubiz.services.channel.demo.util.enums.ErrorCode;
import pe.com.niubiz.services.channel.demo.util.enums.ExternalApi;
import retrofit2.HttpException;

class ExceptionsUtilsTest {

  @Test
  void testCreateException_WhenIsCustomErrorWithoutEx_ShouldBeReturnLupxException() {
    DemoException result = ExceptionsUtils.createException(ErrorCode.BAD_REQUEST_EXCEPTION);

    Assertions.assertEquals(ErrorCode.BAD_REQUEST_EXCEPTION.name(), result.getReason());
    Assertions.assertEquals(ErrorCode.BAD_REQUEST_EXCEPTION.getHttpStatus(), result.getStatus());
    Assertions.assertEquals(ErrorCode.BAD_REQUEST_EXCEPTION.name(), result.getCause().getMessage());
    Assertions.assertEquals(ErrorCode.BAD_REQUEST_EXCEPTION, result.getErrorCode());
    Assertions.assertFalse(result.isExternalErrorCode());
  }

  @Test
  void testCreateException_WhenIsCustomErrorWithEx_ShouldBeReturnLupxExceptionWithEx() {
    Exception ex = new Exception("External Error");

    DemoException result = ExceptionsUtils.createException(ErrorCode.BAD_REQUEST_EXCEPTION, ex);

    Assertions.assertEquals(ErrorCode.BAD_REQUEST_EXCEPTION.name(), result.getReason());
    Assertions.assertEquals(ErrorCode.BAD_REQUEST_EXCEPTION.getHttpStatus(), result.getStatus());
    Assertions.assertEquals(ex, result.getCause());
    Assertions.assertEquals(ErrorCode.BAD_REQUEST_EXCEPTION, result.getErrorCode());
    Assertions.assertFalse(result.isExternalErrorCode());
  }

  @Test
  void testCreateException_WhenIsCustomErrorWithCustomCode_ShouldBeReturnCustomLupxException() {
    ErrorCode error = ErrorCode.EXTERNAL_API;
    HttpException ex = MockUtils.mockHttpException(error, "CIX-101");

    DemoException result = ExceptionsUtils.createException(ExternalApi.SUPPORT_DEMO, ex);

    Assertions.assertEquals("DEMO-01101", result.getReason());
    Assertions.assertEquals(error.getHttpStatus(), result.getStatus());
    Assertions.assertEquals(error.name(), result.getCause().getMessage());
    Assertions.assertEquals(ErrorCode.EXTERNAL_API, result.getErrorCode());
    Assertions.assertTrue(result.isExternalErrorCode());
  }

  @Test
  void testCreateException_WhenIsCustomErrorWithoutCustomCode_ShouldBeReturnCustomLupxException() {
    HttpStatus customHttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    HttpException ex = MockUtils.mockHttpException(customHttpStatus, "INTERNAL SERVER ERROR");

    DemoException result = ExceptionsUtils.createException(ExternalApi.SUPPORT_DEMO, ex);

    Assertions.assertEquals("DEMO-01000", result.getReason());
    Assertions.assertEquals(ErrorCode.EXTERNAL_API.getHttpStatus(), result.getStatus());
    Assertions.assertEquals(ex.getMessage(), result.getCause().getMessage());
    Assertions.assertEquals(ErrorCode.EXTERNAL_API, result.getErrorCode());
    Assertions.assertTrue(result.isExternalErrorCode());
  }

  @Test
  void testCreateException_WhenIsCustomError_ShouldBeReturnCustomLupxException() {
    Exception ex = new Exception("External Error");

    DemoException result = ExceptionsUtils.createException(ExternalApi.SUPPORT_DEMO, ex);

    Assertions.assertEquals("DEMO-01000", result.getReason());
    Assertions.assertEquals(ErrorCode.EXTERNAL_API_ERROR.getHttpStatus(), result.getStatus());
    Assertions.assertEquals(ex.getMessage(), result.getCause().getMessage());
    Assertions.assertEquals(ErrorCode.EXTERNAL_API_ERROR, result.getErrorCode());
    Assertions.assertTrue(result.isExternalErrorCode());
  }

}
