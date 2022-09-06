package pe.com.niubiz.services.channel.demo.expose.web;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON_VALUE;

import io.reactivex.Observable;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pe.com.niubiz.services.channel.demo.business.ChannelDemoService;
import pe.com.niubiz.services.channel.demo.expose.web.model.ParameterByTypeRequest;
import pe.com.niubiz.services.support.demo.model.ParameterResponse;

/**
 * Class for Demo Controller.
 * @author alan.cabrera
 */
@RestController
@RequestMapping("/channel/niubiz/v1.0/demo")
@Slf4j
public class DemoController {

  @Autowired
  private ChannelDemoService channelDemoService;

  /**
   * Method to get Parameters by Type.
   *
   * @param request {@link ParameterByTypeRequest}
   * @return {@link ParameterToResponse}
   */
  @GetMapping(value = "/parameters",
      produces = { APPLICATION_JSON_VALUE, APPLICATION_STREAM_JSON_VALUE })
  @ApiOperation(
      value = "Listado de Parametros por tipo.",
      response = ParameterResponse.class,
      httpMethod = "GET",
      tags = {"Parameter"},
      notes = "classpath:swagger/notes/get-parameters.md", 
      responseContainer = "list"
      )
  @ApiResponses({
      @ApiResponse(code = 200, message = "Proceso Satisfactorio."),
      @ApiResponse(code = 400, message = "El cliente envi&oacute; datos incorrectos."),
      @ApiResponse(code = 500, message = "Error en el proceso.")})
  @ResponseStatus(OK)
  public Observable<ParameterResponse> getParameters(ParameterByTypeRequest request) {
    log.info("Starting getParameters");
    return channelDemoService.getParameters(request.getTypeId())
        .doOnComplete(() -> log.info("Ending getParameters"))
        .doOnError(e -> log.error("Error in getParameters: ", e));
  }

  

}
