package entity;

public class Event extends Task {
  private String at;

  public String getAt() {
    return at;
  }

  public void setAt(String at) {
    this.at = at;
  }

  public Event(String taskDescription, String at) {
    super(taskDescription);
    this.at = at;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + String.format(" (at: %s)", at);
  }
}
