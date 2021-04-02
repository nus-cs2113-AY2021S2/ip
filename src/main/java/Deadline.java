/**
 * Subclass of Task object, requires deadline date/time.
 *
 * @author Calvin
 * @version 0.2
 * @since 2021-02-28
 */
public class Deadline extends Task{
	private String by;

	/**
	 * Deadline object constructor.
	 *
	 * @param description string description of task
	 * @param by string date/time of when task is due
	 */
	public Deadline(String description, String by) {
		super(description);
		this.by = by;
	}

	/**
	 * Formats data of Deadline object into standard used by the Duke application.
	 *
	 * @return formatted string of Deadline data
	 */
	public String toString() {
		return "[D]" + super.toString() + " (by: " + this.by + ")";
	}

	/**
	 * Get type of task
	 *
	 * @return string "D"
	 */
	public String getType(){
		return "D";
	}

	/**
	 * Formats data of Deadline object into standard used by the Storage class.
	 *
	 * @return formatted string of Deadline data
	 */
	@Override
	public String saveFormatString(){
		return "D | " + super.saveFormatString() + " | " + this.by;
	}
}
