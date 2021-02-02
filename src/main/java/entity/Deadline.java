package entity;

public class Deadline extends Task {
  private String by;

  public String getBy() {
    return by;
  }

  public void setBy(String by) {
    this.by = by;
  }

  public Deadline(String taskDescription, String by) {
    super(taskDescription);
    this.by = by;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + String.format(" (by: %s)", by);
  }
}
