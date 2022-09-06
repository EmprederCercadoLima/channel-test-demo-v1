package pe.com.niubiz.services.channel.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

import pe.com.niubiz.services.channel.demo.util.enums.ErrorCode;
import pe.com.niubiz.services.channel.demo.util.enums.ExternalApi;
import pe.com.niubiz.services.channel.demo.util.exception.DemoException;

/**
 * Utils for String's.
 * @author alan.cabrera
 */
@Slf4j
public class StringsUtil {
  
  private static final String[] SPECIAL_CHARACTERS = { "\\", "/", "|", "+", "-", "&", "!", "(", ")",
      "{", "}", "[", "]", "^", "\"", "~", "*", "?", ":" };
  
  private static final String[] REPLACE_SPECIAL_CHARACTERS = { "\\\\", "\\/", "", "\\+", "\\-", "",
      "\\!", "\\(", "\\)", "\\{", "\\}", "\\[", "\\]", "\\^", "\\\"", "\\~", "\\*", "\\?", "\\:" };

  private StringsUtil() {
    ErrorCode errorCode = ErrorCode.ILLEGAL_CREATION;
    throw new DemoException(
        errorCode.getHttpStatus(), errorCode.name(), new Exception(errorCode.name()), errorCode);
  }

  /**
   * Obfuscate String by Pattern.
   * @param regex {@link String}
   * @param toObfuscate {@link String}
   * @param groupToReplace {@link int}
   * @return String obfuscated {@link String}.
   */
  @Nullable
  private static String obfuscateString(String regex, String toObfuscate, int groupToReplace) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(toObfuscate);
    return (matcher.find())
        ? new StringBuilder(toObfuscate).replace(
            matcher.start(groupToReplace), matcher.end(1),
            StringUtils.repeat("*", matcher.group(1).length())).toString()
        : StringUtils.repeat("*", toObfuscate.length());
  }

  /**
   * Obfuscate Cellphone Number.
   * @param cellphone {@link String}
   * @return {@link String}
   */
  @Nullable
  public static String obfuscateCellphone(String cellphone) {
    return obfuscateString(Constants.REGEX_OBFUSCATED_PHONE, cellphone, 1);
  }

  /**
   * Replace Space and new Lines for logs.
   * @param msg {@link String}
   * @return {@link String}
   */
  public static String replaceSpaces(String msg) {
    return msg.replaceAll("[\r\n]","");
  }

  /**
   * Create custom external code.
   * @param externalApi {@link ExternalApi}
   * @param externalCode {@link String}
   * @return {@link String}
   */
  public static String customExternalCode(ExternalApi externalApi, String externalCode) {
    return externalApi.getSuffixCode().concat(
        String.valueOf(Integer.parseInt(externalCode.replaceAll("\\D+",""))));
  }
  
  /**
   * Replace Special characters for azure search.
   * @param toSanitize {@link String}
   * @return {@link String}
   */
  public static String sanitizeSpecialCharacters(String toSanitize) {
    return StringUtils.replaceEach(toSanitize, SPECIAL_CHARACTERS, REPLACE_SPECIAL_CHARACTERS);
  }

}
