package pe.com.niubiz.services.channel.demo.expose.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class for Parameter By Type Request.
 * @author alan.cabrera
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParameterByTypeRequest {

  @ApiModelProperty(
      name = "typeId",
      value = "Código del Tipo de parametro",
      example = "DT",
      dataType = "String",
      required = true)
  @NotEmpty
  @Pattern(regexp = "^[A-Z]{2}$")
  private String typeId;

}
