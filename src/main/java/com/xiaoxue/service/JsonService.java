package com.xiaoxue.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.xiaoxue.domain.Event;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import java.io.IOException;


public class JsonService {

  public static final ObjectMapper MAPPER =
    new ObjectMapper()
      .disable(WRITE_DATES_AS_TIMESTAMPS)
      .registerModule(new JavaTimeModule());

  private JsonService(){};

  public static Event toEvent(JsonNode json) throws JsonProcessingException {
    return MAPPER.treeToValue(json, Event.class);

  }

  public static Event toEvent(String json) throws IOException {
    return MAPPER.readValue(json, Event.class);
  }


  public static String toString(Event event) throws JsonProcessingException {
   return MAPPER.writeValueAsString(event);

  }

  public static JsonNode toJson(Event event) throws IOException {
     String json =MAPPER.writeValueAsString(event);
    return MAPPER.readTree(json);
  }


}
