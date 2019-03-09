
import static com.xiaoxue.domain.Constants.DATE_FORMAT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.xiaoxue.domain.Event;
import com.xiaoxue.service.JsonService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EntryTest {


  @Test
  public void testToJson() throws IOException, ParseException {

    Event event = new Event(1);
    event.setCategory("A");
    event.setCode("C");
    event.setTimestamp(Instant.now());
    event.setDateTime(LocalDateTime.now());
    event.setDate(getEventDate());
    JsonNode json = JsonService.toJson(event);
    System.out.println(json);
  }

  @Test
  public void testToString() throws IOException, ParseException {
    Event event = new Event(1);
    event.setCategory("A");
    event.setCode("C");
    event.setTimestamp(Instant.now());
    event.setDateTime(LocalDateTime.now());
    event.setDate(getEventDate());
    String str = JsonService.toString(event);
    System.out.println(str);
  }

  @Test
  public void testJsonToEvent1() throws IOException {
    JsonNode json = getJson("event1.json");
    Event event = JsonService.toEvent(json);

    System.out.println(event);
  }

  @Test
  public void testJsonToEvent2() throws IOException {
    JsonNode json = getJson("event2.json");
    Event event = JsonService.toEvent(json);
    System.out.println(event);
  }

  @Test
  public void testStringToEvent1() throws IOException {
    String str = getJsonString("event1.json");
    Event event = JsonService.toEvent(str);
    System.out.println(event);
  }

  @Test
  public void testStringToEvent2() throws IOException {
    String str = getJsonString("event2.json");
    Event event = JsonService.toEvent(str);
    System.out.println(event);
  }

  @Test
  public void testDates() throws IOException, ParseException {
    Date date1 = getEventDate();
    Date date2 = getEventDate();
    assertTrue(date1.equals(date2));

    Date date3 = getEventDate("01-08-2019 02:30");
    Date date4 = getEventDate("03-08-2019 02:30");
    LocalDateTime ldt3 = toLocatDateTime(date3);
    LocalDateTime ldt4 = toLocatDateTime(date4);


    Period period = Period.between(ldt3.toLocalDate(),ldt4.toLocalDate());
    Duration duration = Duration.between(ldt3, ldt4);
    long diff = Math.abs(duration.toMinutes());
    assertTrue(date4.after(date3));
    assertEquals(2,period.getDays());
    assertEquals(2,duration.toDays());
  }


  private String getJsonString(String path) throws IOException {
    InputStream stream = getClass().getClassLoader().getResourceAsStream(path);

    return CharStreams.toString(new InputStreamReader(stream, Charsets.UTF_8));
  }

  private JsonNode getJson(String path) throws IOException {
    InputStream stream = getClass().getClassLoader().getResourceAsStream(path);

    return new ObjectMapper().readTree(stream);

  }

  private Date getEventDate() throws ParseException {
    SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
    df.setTimeZone(TimeZone.getTimeZone("UTC"));

    String toParse = "01-01-2019 02:30";
    return df.parse(toParse);
  }

  private Date getEventDate(String dateTime) throws ParseException {
    SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
    df.setTimeZone(TimeZone.getTimeZone("UTC"));
    return df.parse(dateTime);
  }

  private LocalDateTime toLocatDateTime(Date date) {
//    LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    Instant instant = Instant.ofEpochMilli(date.getTime());
    return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
  }

  private Date toDate(LocalDateTime localDateTime) {
    Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
    return Date.from(instant);
  }

}
