package pe.com.niubiz.services.channel.demo.business.port;

import io.reactivex.Observable;

import pe.com.niubiz.services.support.demo.model.ParameterResponse;

/**
 * External Support Service.
 * @author alan.cabrera
 */
public interface SupportDemoService {

  Observable<ParameterResponse> getParameters(String typeId);

  

}
