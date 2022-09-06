package pe.com.niubiz.services.channel.demo.service.proxy.supportdemo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import pe.com.niubiz.services.channel.MockUtils;
import pe.com.niubiz.services.channel.demo.business.ServiceMapper;
import pe.com.niubiz.services.channel.demo.service.proxy.supportdemo.SupportDemoV1Api;
import pe.com.niubiz.services.channel.demo.service.proxy.supportdemo.impl.SupportDemoServiceImpl;
import pe.com.niubiz.services.support.demo.model.ParameterResponse;

@ExtendWith(SpringExtension.class)
class SupportDemoServiceImplTest {

  @InjectMocks
  private SupportDemoServiceImpl financialProfilerServiceImpl;

  @Mock
  private SupportDemoV1Api financialProfilerV1;

  @Mock
  private ServiceMapper mapper;

  @Test
  void testGetParameters_WhenCall_ShouldBeCallApi() {
    String typeId = "DT";
    ParameterResponse response = MockUtils.mockParameterResponse();
    when(financialProfilerV1.getParameters(any()))
        .thenReturn(Observable.just(Collections.singletonList(response)));

    TestObserver<ParameterResponse> result = financialProfilerServiceImpl.getParameters(typeId).test();

    MockUtils.validateSuccessResponse(response, result);
    verify(financialProfilerV1).getParameters(typeId);
  }

  

}
