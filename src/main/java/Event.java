public class Event extends Task {
	protected String date;

	public Event(String description, String date) {
		super(description);
		this.date = date;
	}

	public String getType() {
		return "E";
	}

	public String getDate() {
		return "(at: " + date + ")";
	}

}
