package main;

import org.joda.time.DateTime;

public class User {
  private String username;
  private DateTime creationTime;

  public User(String username) {
    this.username = username;
    //this.creationTime = creationTime;
  }

  public String getUsername() {
    return username;
  }

  public DateTime getCreationTime() {
    return creationTime;
  }
}
