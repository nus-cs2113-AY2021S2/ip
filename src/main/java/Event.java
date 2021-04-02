/**
 * Subclass of Task object, requires event date/time.
 *
 * @author Calvin
 * @version 0.2
 * @since 2021-02-28
 */
public class Event extends Task{
	private String at;

	/**
	 * Event object constructor.
	 *
	 * @param description string description of task
	 * @param at string date/time of event
	 */
	public Event(String description, String at) {
		super(description);
		this.at = at;
	}

	/**
	 * Formats data of Event object into standard used by the Duke application.
	 *
	 * @return formatted string of event data
	 */
	public String toString() {
		return "[E]" + super.toString() + " (at: " + this.at + ")";
	}

	/**
	 * Formats data of Event object into standard used by the Storage class.
	 *
	 * @return formatted string of Event data
	 */
	@Override
	public String saveFormatString(){
		return "E | " + super.saveFormatString() + " | " + this.at;
	}
}
