package pe.com.niubiz.services.channel.demo.business.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import pe.com.niubiz.services.channel.MockUtils;
import pe.com.niubiz.services.channel.demo.business.ServiceMapper;
import pe.com.niubiz.services.channel.demo.business.port.SupportDemoService;
import pe.com.niubiz.services.channel.demo.config.ApplicationProperties;
import pe.com.niubiz.services.support.demo.model.ParameterResponse;

@ExtendWith(SpringExtension.class)
class DemoServiceImplTest {

  @InjectMocks
  private ChannelDemoServiceImpl channelFinancialProfilerServiceImpl;

  @Mock
  private SupportDemoService financialProfilerService;

  @Mock
  private ServiceMapper serviceMapper;

  @Mock
  private ApplicationProperties applicationProperties;


  @Captor
  private ArgumentCaptor<String> captorNameFile;

  @Captor
  private ArgumentCaptor<String> captorString;

  @Mock
  private FilePart filePart;

  @Test
  void testGetParameters_WhenCall_ShouldBeCallService() {
    String typeId = "DT";
    ParameterResponse parameter = MockUtils.mockParameterResponse();
    ParameterResponse response = MockUtils.mockParameterToResponse();
    when(financialProfilerService.getParameters(any()))
        .thenReturn(Observable.just(parameter));

    TestObserver<ParameterResponse> result = channelFinancialProfilerServiceImpl
        .getParameters(typeId).test();

    MockUtils.validateSuccessResponse(response, result);
    verify(financialProfilerService).getParameters(typeId);
  }

}
