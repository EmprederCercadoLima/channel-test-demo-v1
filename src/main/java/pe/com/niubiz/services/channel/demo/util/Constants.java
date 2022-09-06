package pe.com.niubiz.services.channel.demo.util;

import static pe.com.niubiz.services.channel.demo.util.exception.ExceptionsUtils.createException;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import java.time.ZoneId;

import pe.com.niubiz.services.channel.demo.util.enums.ErrorCode;

/**
 * Class for applications constants.
 * @author alan.cabrera
 */
public class Constants {

  private Constants() {
    throw createException(ErrorCode.ILLEGAL_CREATION);
  }

  public static final String PROJECT_SCHEMA = "lupx";
  public static final String REGEX_OBFUSCATED_PHONE = "^(\\d+)(\\d{3})$";
  public static final String VELOCITY_MODEL = "model";
  public static final int MB_FACTOR = 1024 * 1024;
  public static final Scheduler SCHEDULER_IO = Schedulers.io();
  public static final ZoneId ZONE_ID = ZoneId.of("America/Lima");
  public static final String EMAIL_TEMPLATE_PATH = "email/";

}