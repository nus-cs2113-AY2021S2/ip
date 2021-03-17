package duke.task;

import duke.parser.Parser;

import java.time.LocalDate;

/**
 * Represents a task with deadline.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Creates a new Deadline task object with specified task content and deadline (in date).
     * The deadline is a LocalDate object in the format "YYYY-MM-DD".
     *
     * @param content  the content of the deadline task.
     * @param deadline the deadline of the deadline task.
     */
    public Deadline(String content, LocalDate deadline) {
        super(content);
        this.taskType = TaskType.DEADLINE;
        this.deadline = deadline;
    }

    /**
     * Checks if a user input contains keyword used to identify should a deadline task be created.
     *
     * @param userInput User's keyboard input in String.
     * @throws Exception an exception object with error message to notify the user that
     *                   the deadline object cannot be create due to the absence of keywords.
     */
    public static void isDeadlineCommandValid(String userInput) throws Exception {
        if (!userInput.contains("/by")) {
            throw new Exception("Invalid deadline command. Check 'help'.\n");
        }
    }

    /**
     * Returns the content of the deadline task.
     *
     * @param userInput User's keyboard input in String.
     * @return a String of the content of the deadline task in a usable format.
     * @throws Exception an exception object with error message to notify the user that
     *                   the task content cannot be found or parsed.
     */
    public static String[] getDeadlineTaskContent(String userInput) throws Exception {
        String[] words = userInput.split(" ");
        int indexOfByWord = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("/by")) {
                indexOfByWord = i;
                break;
            }
        }
        return Parser.parseTaskContentAndTime(words, indexOfByWord);
    }

    /**
     * Gets the deadline of the deadline task.
     *
     * @return a LocalDate object representing the deadline.
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Gets the deadline of the deadline task in String.
     *
     * @return a String of the deadline.
     */
    public String getTimeLimitString() {
        return deadline.toString();
    }

}
