package com.xiaoxue.service;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static com.xiaoxue.domain.Constants.DATE_FORMAT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;


public class DateService {

  public static final ObjectMapper MAPPER =
    new ObjectMapper()
      .disable(WRITE_DATES_AS_TIMESTAMPS)
      .registerModule(new JavaTimeModule());

  private DateService(){};


  public static Date getEventDate() throws ParseException {
    SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
    df.setTimeZone(TimeZone.getTimeZone("UTC"));

    String toParse = "01-01-2019 02:30";
    return df.parse(toParse);
  }

  public static Date getEventDate(String dateTime) throws ParseException {
    SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
    df.setTimeZone(TimeZone.getTimeZone("UTC"));
    return df.parse(dateTime);
  }

  public static LocalDateTime toLocatDateTime(Date date) {
//    LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    Instant instant = Instant.ofEpochMilli(date.getTime());
    return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
  }

  public static Date toDate(LocalDateTime localDateTime) {
    Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
    return Date.from(instant);
  }


}
