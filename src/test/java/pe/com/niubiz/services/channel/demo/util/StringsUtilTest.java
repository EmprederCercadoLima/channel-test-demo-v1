package pe.com.niubiz.services.channel.demo.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pe.com.niubiz.services.channel.demo.util.StringsUtil;
import pe.com.niubiz.services.channel.demo.util.enums.ExternalApi;

class StringsUtilTest {

  @Test
  void testObfuscateCellphone_WhenIsPhoneNumber_ReturnLastThreeNumber() {
    String cellphone = "999888777";

    String result = StringsUtil.obfuscateCellphone(cellphone);

    Assertions.assertEquals("******777", result);
  }

  @Test
  void testObfuscateCellphone_WhenDoesNotPhoneNumber_ReturnAllSymbols() {
    String cellphone = "dfgs";

    String result = StringsUtil.obfuscateCellphone(cellphone);

    Assertions.assertEquals("****", result);
  }

  @Test
  void testReplaceSpaces_WhenSendMessageWithLineSeparator_ShouldBeRemoveSpaces() {
    String msg = "Test line 1." + System.lineSeparator() + "Test line 2";

    String result = StringsUtil.replaceSpaces(msg);

    Assertions.assertEquals("Test line 1.Test line 2", result);
  }

  @Test
  void testCustomExternalCode_WhenIsSimpleFormat_ShouldBeReturnCustomErrorCode() {
    ExternalApi externalApi = ExternalApi.SUPPORT_DEMO;

    String result = StringsUtil.customExternalCode(externalApi, "CIX-305");

    Assertions.assertEquals(ExternalApi.SUPPORT_DEMO.getSuffixCode().concat("305"), result);
  }

  @Test
  void testCustomExternalCode_WhenIsAtlasFormat_ShouldBeReturnCustomErrorCode() {
    ExternalApi externalApi = ExternalApi.SUPPORT_DEMO;

    String result = StringsUtil.customExternalCode(externalApi, "IY00102");

    Assertions.assertEquals(ExternalApi.SUPPORT_DEMO.getSuffixCode().concat("102"), result);
  }

}
