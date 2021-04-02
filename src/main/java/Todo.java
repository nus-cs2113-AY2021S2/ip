/**
 * Subclass of Task object
 *
 * @author Calvin
 * @version 0.2
 * @since 2021-02-28
 */
public class Todo extends Task{

	/**
	 * Todo object constructor.
	 *
	 * @param description string description of task
	 */
	public Todo(String description) {
		super(description);
	}

	/**
	 * Formats data of Todo object into standard used by the Duke application.
	 *
	 * @return formatted string of Todo data
	 */
	@Override
	public String toString() {
		return "[T]" + super.toString();
	}

	/**
	 * Formats data of Todo object into standard used by the Storage class.
	 *
	 * @return formatted string of Todo data
	 */
	@Override
	public String saveFormatString(){
		return "T | " + super.saveFormatString();
	}
}
