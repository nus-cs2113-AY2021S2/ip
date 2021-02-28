public class Parser {
	private String userInput;

	Ui ui = new Ui();
	public Parser(String userInput){
		this.userInput = userInput;
	}

	public String getTaskType() {
		String[] parsedString = userInput.split(" ",2);
		return parsedString[0].toLowerCase();
	}

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

	public String getDeadlineDescription() {
		String[] parsedString = userInput.split(" /by ",2);
		String deadlineDescription = parsedString[0].split(" ",2)[1];
		return deadlineDescription;
	}

	public String getEventDescription() {
		String[] parsedString = userInput.split(" /at ",2);
		String eventDescription = parsedString[0].split(" ",2)[1];
		return eventDescription;
	}

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
