package pe.com.niubiz.services.channel.demo.service.proxy.supportdemo;

import io.reactivex.Observable;

import java.util.List;

import pe.com.niubiz.services.support.demo.model.ParameterResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * External Application API - Support Demo.
 * @author alan.cabrera
 */
public interface SupportDemoV1Api {

  @GET("/support/niubiz/v1.0/demo/parameters")
  Observable<List<ParameterResponse>> getParameters(@Query("typeId") String typeId);

  


}
