package commands;

import tasks.DeadlineTask;
import tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SearchCommand extends Command {

    private final String input;

    public SearchCommand(String input) {
        this.input = input.substring(constants.COMMAND_SEARCH.length());
    }


    /**
     * Prints all tasks that matches date.
     */
    @Override
    public void execute() {
        try {
            LocalDate date = extractDate(input);
            int count = 0;
            for (int i = 0; i < taskManager.getTaskCount(); i++) {
                Task task = taskManager.getTask(i);
                if (!(task instanceof DeadlineTask)) {
                    continue;
                }
                DeadlineTask deadlineTask = (DeadlineTask) task;
                if (!deadlineTask.getDueDate().equals(date)) {
                    continue;
                } else if (count == 0) {
                    System.out.println(constants.LINE);
                }
                count++;
                System.out.print((i+1) + ".");
                deadlineTask.printStatus();
                System.out.println();
            }
            if (count == 0) {
                //No matching task
                System.out.println(constants.MESSAGE_TASK_NOT_FOUND);
            } else {
                System.out.println(constants.LINE);
            }
        } catch (DateTimeParseException e) {
            System.out.println(constants.MESSAGE_INVALID_DATE);
        }
    }


    /**
     * Returns LocalDate of input date.
     * Throws an exception if input date is invalid.
     *
     * @param input Raw date input.
     * @return LocalDate from raw date input.
     * @throws DateTimeParseException Invalid date.
     */
    private LocalDate extractDate(String input) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(constants.DATE_IO_FORMAT);
        return LocalDate.parse(input, formatter);
    }
}