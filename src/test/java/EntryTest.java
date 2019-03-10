import static com.xiaoxue.service.DateService.getEventDate;
import static com.xiaoxue.service.DateService.toLocatDateTime;
import static com.xiaoxue.service.JsonService.MAPPER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.xiaoxue.domain.Constants;
import com.xiaoxue.domain.Event;
import com.xiaoxue.service.FileService;
import com.xiaoxue.service.JsonService;

import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;


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
    JsonNode json = new FileService().getJson("event1.json");
    Event event = JsonService.toEvent(json);

    System.out.println(event);
  }

  @Test
  public void testJsonToEvent2() throws IOException {
    JsonNode json = new FileService().getJson("event2.json");
    Event event = JsonService.toEvent(json);
    System.out.println(event);
  }

  @Test
  public void testStringToEvent1() throws IOException {
    String str = new FileService().getJsonString("event1.json");
    Event event = JsonService.toEvent(str);
    System.out.println(event);
  }

  @Test
  public void testStringToEvent2() throws IOException {
    String str = new FileService().getJsonString("event2.json");
    Event event = JsonService.toEvent(str);
    System.out.println(event);
  }

  @Test
  public void testEvents() throws IOException {
    String str = new FileService().getJsonString("events.json");
    final ArrayNode data = (ArrayNode) MAPPER.readTree(str).get(Constants.EVENTS_KEY);
    final List<Event> events = new ArrayList<>(data.size());

    for (JsonNode json : data) {
      events.add(JsonService.toEvent(json));
    }

//    events.forEach(event->System.out.println(event));
    events.forEach(System.out::println);


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


    Period period = Period.between(ldt3.toLocalDate(), ldt4.toLocalDate());
    Duration duration = Duration.between(ldt3, ldt4);
    long diff = Math.abs(duration.toMinutes());
    assertTrue(date4.after(date3));
    assertEquals(2, period.getDays());
    assertEquals(2, duration.toDays());
  }

  @Test
  public void testIteration() throws ParseException {
    Set<Event> set = new TreeSet<>();
    set.add(getEvent(4));
    set.add(getEvent(2));
    set.add(getEvent(9));

    for (Event e : set) {
      System.out.println(e);
    }

    Map<String, Integer> map = new LinkedHashMap<>();
    map.put("a", 3);
    map.put("b", 2);
    map.put("c", 1);
    for (Map.Entry<String, Integer> pair : map.entrySet()) {
      System.out.println(pair.getKey() + ":" + pair.getValue());
    }

    set.stream().peek(e -> e.setCode("test")).peek(System.out::println).collect(Collectors.toList());
  }


  private Event getEvent(int id) throws ParseException {
    Event event = new Event(id);
    event.setCategory("A");
    event.setCode("C");
    event.setTimestamp(Instant.now());
    event.setDateTime(LocalDateTime.now());
    event.setDate(getEventDate());
    return event;
  }

}
