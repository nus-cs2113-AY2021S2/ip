public class Deadline extends Task{
	private String by;

	public Deadline(String description, String by) {
		super(description);
		this.by = by;
	}

	public String toString() {
		return "[D]" + super.toString() + " (by: " + this.by + ")";
	}

	public String getType(){
		return "D";
	}
	@Override
	public String saveFormatString(){
		return "D | " + super.saveFormatString() + " | " + this.by;
	}
}
