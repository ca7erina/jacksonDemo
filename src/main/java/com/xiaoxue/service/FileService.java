package com.xiaoxue.service;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.xiaoxue.domain.Event;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class FileService {



  public FileService(){};

  public  String getJsonString(String path) throws IOException {
    InputStream stream = getClass().getClassLoader().getResourceAsStream(path);

    return CharStreams.toString(new InputStreamReader(stream, Charsets.UTF_8));
  }

  public  JsonNode getJson(String path) throws IOException {
    InputStream stream = getClass().getClassLoader().getResourceAsStream(path);

    return new ObjectMapper().readTree(stream);

  }


}
