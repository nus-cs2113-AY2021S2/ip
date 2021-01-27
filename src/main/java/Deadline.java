public class Deadline extends Task {
	protected String date;

	public Deadline(String description, String date) {
		super(description);
		this.date = date;
	}

	public String getType() {
		return "D";
	}

	public String getDate() {
		return "(by: " + date + ")";
	}

}
