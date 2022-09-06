package pe.com.niubiz.services.channel.expose.web;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import pe.com.niubiz.services.channel.MockUtils;
import pe.com.niubiz.services.channel.demo.business.ChannelDemoService;
import pe.com.niubiz.services.channel.demo.expose.web.DemoController;
import pe.com.niubiz.services.channel.demo.expose.web.model.ParameterByTypeRequest;
import pe.com.niubiz.services.support.demo.model.ParameterResponse;

@ExtendWith(SpringExtension.class)
class DemoControllerTest {

  @InjectMocks
  private DemoController financialProfilerController;

  @Mock
  private ChannelDemoService channelFinancialProfilerService;

  @Mock
  private FilePart filePart;

  @Test
  void testGetParameters_WhenCall_ShouldBeCallService() {
    ParameterByTypeRequest request = new ParameterByTypeRequest();
    ParameterResponse response = new ParameterResponse();
    when(channelFinancialProfilerService.getParameters(request.getTypeId()))
        .thenReturn(Observable.just(response));

    TestObserver<ParameterResponse> result = financialProfilerController
        .getParameters(request).test();

    MockUtils.validateSuccessResponse(response, result);
    verify(channelFinancialProfilerService).getParameters(request.getTypeId());
  }

  

}
