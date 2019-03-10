package com.xiaoxue.domain;

import static com.xiaoxue.domain.Constants.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
public class Event implements Comparable<Event>{

  private  String customer;
  private  int id;
  private  String category;
  @JsonIgnore
  private  String priorityName;
  @JsonIgnore
  private  String code;
  private  String description;
  private  String summary;
  private Instant timestamp;
  private LocalDateTime dateTime;
  @JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private Date date;

  public Event(int id) {
    super();
    this.id = id;
  }

  private Event(){}

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Instant timestamp) {
    this.timestamp = timestamp;
  }

  public String getCustomer() {
    return customer;
  }

  public int getId() {
    return id;
  }

  public String getCategory() {
    return category;
  }

  public String getPriorityName() {
    return priorityName;
  }

  public String getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }

  public String getSummary() {
    return summary;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public void setPriorityName(String priorityName) {
    this.priorityName = priorityName;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Event event = (Event) o;
    return id == event.id &&
      Objects.equals(customer, event.customer) &&
      Objects.equals(category, event.category) &&
      Objects.equals(priorityName, event.priorityName) &&
      Objects.equals(code, event.code) &&
      Objects.equals(description, event.description) &&
      Objects.equals(summary, event.summary) &&
      Objects.equals(timestamp, event.timestamp) &&
      Objects.equals(dateTime, event.dateTime) &&
      Objects.equals(date, event.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customer, id, category, priorityName, code, description, summary, timestamp, dateTime, date);
  }

  @Override
  public String toString() {
    return "Event{" +
      "customer='" + customer + '\'' +
      ", id=" + id +
      ", category='" + category + '\'' +
      ", priorityName='" + priorityName + '\'' +
      ", code='" + code + '\'' +
      ", description='" + description + '\'' +
      ", summary='" + summary + '\'' +
      ", timestamp=" + timestamp +
      ", dateTime=" + dateTime +
      ", date=" + date +
      '}';
  }

  @Override
  public int compareTo(Event o) {
//    if(this.id > o.id) return 1;
//    if(this.id < o.id) return -1;
//    else                   return 0;
    return Integer.compare(this.id, o.id);
  }
}
