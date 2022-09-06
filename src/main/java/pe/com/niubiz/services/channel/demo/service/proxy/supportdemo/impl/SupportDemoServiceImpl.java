package pe.com.niubiz.services.channel.demo.service.proxy.supportdemo.impl;

import static pe.com.niubiz.services.channel.demo.util.exception.ExceptionsUtils.createException;

import io.reactivex.Observable;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.niubiz.services.channel.demo.business.port.SupportDemoService;
import pe.com.niubiz.services.channel.demo.service.proxy.supportdemo.SupportDemoV1Api;
import pe.com.niubiz.services.channel.demo.util.enums.ExternalApi;
import pe.com.niubiz.services.support.demo.model.ParameterResponse;

/**
 * External Support Demo Service Implements.
 * @author alan.cabrera
 */
@Service
@Slf4j
public class SupportDemoServiceImpl implements SupportDemoService {

  private static final ExternalApi externalApi = ExternalApi.SUPPORT_DEMO;

  @Autowired
  private SupportDemoV1Api supportDemoV1;

  @Override
  public Observable<ParameterResponse> getParameters(String typeId) {
    return Observable.defer(() -> {
      log.info("Starting getParameters");
      return supportDemoV1.getParameters(typeId);
    })
        .flatMap(Observable::fromIterable)
        .doOnError(ex -> log.error("Error on getParameters: ", ex))
        .doFinally(() -> log.info("Finished getParameters"))
        .onErrorResumeNext(ex -> {
          log.error("Error getParameters: External Api");
          return Observable.error(createException(externalApi, ex));
        });
  }

  

}
