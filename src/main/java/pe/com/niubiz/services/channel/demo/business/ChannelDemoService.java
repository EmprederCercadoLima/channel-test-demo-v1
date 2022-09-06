package pe.com.niubiz.services.channel.demo.business;

import io.reactivex.Observable;

import pe.com.niubiz.services.support.demo.model.ParameterResponse;

/**
 * Class for Channel Service Interface.
 * @author alan.cabrera
 */
public interface ChannelDemoService {

  Observable<ParameterResponse> getParameters(String typeId);

  

}
