/**
 * This class handles all user input.
 *
 * @author Calvin
 * @version 0.2
 * @since 2021-02-28
 */
public class Parser {
	private String userInput;

	Ui ui = new Ui();

	/**
	 * Constructor of Parser object
	 *
	 * @param userInput string entered by user.
	 */
	public Parser(String userInput){
		this.userInput = userInput;
	}

	/**
	 * Gets type of task (i.e. Todo, Deadline, Event) from user input.
	 *
	 * @return type of task
	 */
	public String getTaskType() {
		String[] parsedString = userInput.split(" ",2);
		return parsedString[0].toLowerCase();
	}

	/**
	 * Gets description of task from user input.
	 *
	 * @return description of task
	 */
	public  String getTaskDescription() {
		String[] parsedString = userInput.split(" ",2);
		String taskDescription;
		try{
			taskDescription = parsedString[1];
		} catch (ArrayIndexOutOfBoundsException e){
			System.out.println("OOPS!!! The description of a todo cannot be empty.");
			System.out.println("Format: todo (description)");
			return null;
		}

		return taskDescription;
	}

	/**
	 * Gets due date/time of deadline
	 *
	 * @return due/time of deadline if user abides by the format, else returns null and error message.
	 */
	public String getBy() {
		String by;
		String[] parsedString = userInput.split(" /by ",2);
		try {
			by = parsedString[1];
		} catch (ArrayIndexOutOfBoundsException e){
			ui.missingBy();
			return null;
		}
		return by;
	}

	/**
	 * Gets event date/time
	 *
	 * @return event date/time if user abides by the formate, else returns null and error message.
	 */
	public String getAt() {
		String at;
		String[] parsedString = userInput.split(" /at ",2);
		try {
			at = parsedString[1];
		} catch (ArrayIndexOutOfBoundsException e){
			ui.missingAt();
			return null;
		}
		return at;
	}

	/**
	 * Gets task index based on user input.
	 *
	 * @return task index based on user input, returns -1 if task number is not specified or a non-numeric value is entered
	 */
	public int getTaskIndex() {
		String[] parsedString = userInput.split(" ",2);
		String taskIndex;
		try{
			 taskIndex= parsedString[1];
		} catch (ArrayIndexOutOfBoundsException e){
			System.out.println("OOPS!!! Please specify task number.");
			return -1;
		} catch (IndexOutOfBoundsException | NumberFormatException e) {
			System.out.println("Task number not recognised.");
			return -1;
		}

		return Integer.parseInt(taskIndex) - 1;
	}

	/**
	 * Gets description of deadline from user input.
	 *
	 * @return description of deadline
	 */
	public String getDeadlineDescription() {
		String[] parsedString = userInput.split(" /by ",2);
		String deadlineDescription = parsedString[0].split(" ",2)[1];
		return deadlineDescription;
	}

	/**
	 * Gets description of event from user input.
	 *
	 * @return description of event
	 */
	public String getEventDescription() {
		String[] parsedString = userInput.split(" /at ",2);
		String eventDescription = parsedString[0].split(" ",2)[1];
		return eventDescription;
	}

	/**
	 * Gets keyword from user input.
	 *
	 * @return keyword from user input, null if more than one keyword entered.
	 */
	public String getKeyword() {
		String[] parsedString = userInput.split(" ",2);
		String keyword = parsedString[1];
		String[] keywordCheck = keyword.split(" ");
		if (keywordCheck.length > 1){
			ui.invalidKeywordMessage();
			return null;
		} else {
			return keyword;
		}
	}
}
