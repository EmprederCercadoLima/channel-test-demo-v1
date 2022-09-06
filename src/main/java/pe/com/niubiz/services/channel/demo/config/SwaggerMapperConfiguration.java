package pe.com.niubiz.services.channel.demo.config;

import static pe.com.niubiz.services.channel.demo.util.exception.ExceptionsUtils.createException;

import io.swagger.models.Operation;
import io.vavr.control.Try;
import java.io.InputStream;
import java.nio.charset.Charset;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import pe.com.niubiz.services.channel.demo.util.enums.ErrorCode;
import springfox.documentation.service.ModelNamesRegistry;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2MapperImpl;

/**
 * Swagger Mapper Notes Configuration.
 * @author alan.cabrera
 */
@Component
@Primary
@Slf4j
public class SwaggerMapperConfiguration extends ServiceModelToSwagger2MapperImpl {

  private static final String CLASSPATH_PREFIX = "classpath:";

  @Nullable
  @Override
  protected Operation mapOperation(springfox.documentation.service.Operation from,
                                   ModelNamesRegistry modelNames) {
    Operation operation = super.mapOperation(from, modelNames);
    if (Strings.isNotBlank(operation.getDescription())
        && operation.getDescription().startsWith(CLASSPATH_PREFIX)) {
      String path = operation.getDescription().replaceFirst(CLASSPATH_PREFIX, "");
      Try.run(() -> {
        InputStream stream = new ClassPathResource(path).getInputStream();
        operation.setDescription(IOUtils.toString(stream, Charset.defaultCharset()));
      }).getOrElseThrow(ex -> createException(ErrorCode.INVALID_SWAGGER_NOTE, ex));
    }
    return operation;
  }

}
