package pe.com.niubiz.services.channel.demo.business.impl;

import io.reactivex.Observable;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.niubiz.services.channel.demo.business.ChannelDemoService;
import pe.com.niubiz.services.channel.demo.business.port.SupportDemoService;
import pe.com.niubiz.services.support.demo.model.ParameterResponse;

/**
 * Channel Demo Service Implements.
 * @author alan.cabrera
 */
@Service
@Slf4j
public class ChannelDemoServiceImpl implements ChannelDemoService {

  @Autowired
  private SupportDemoService supportDemoService;

  @Override
  public Observable<ParameterResponse> getParameters(String typeId) {
    log.info("Starting getParameters");
    return supportDemoService.getParameters(typeId)
        .doFinally(() -> log.info("Finished getParameters"))
        .doOnError(ex -> log.error("Error on getParameters: ", ex));
  }

  

}
