public class TaskManager {
    public static void processTask(String input) throws InvalidInputException {
        String[] inputData = input.split(" ");
        String[] taskDetails;
        switch (inputData[0]) {
            case "done":
                try {
                    int completedTaskIndex = Integer.parseInt(input.split(" ")[1]);
                    completedTaskIndex--;
                    Task.checkDoneTask(completedTaskIndex);
                    Task.completeTask(completedTaskIndex);
                    UI.taskCompletedSuccessfully();
                } catch (NumberFormatException e) {
                    System.out.println("That didn't work...please enter a valid number");
                    UI.showDivider();
                } catch (TaskAlreadyCompletedException e) {
                    System.out.println(e.getMessage());
                    UI.showDivider();
                } catch (TaskNotExistException e) {
                    System.out.println(e.getMessage());
                    UI.showDivider();
                }
                break;
            case "deadline":
                try {
                    taskDetails = parseTaskDetails(inputData);
                    Deadline.checkDeadlineInput(taskDetails);
                    Task.addNewTask(new Deadline(taskDetails[0], taskDetails[1]));
                    UI.taskAddedSuccessfully();
                    break;
                } catch (MissingDueDateException e) {
                    System.out.println(e.getMessage());
                    UI.showDivider();
                } catch (MissingTaskDescriptionException e) {
                    System.out.println(e.getMessage());
                    UI.showDivider();
                }
                break;
            case "event":
                try {
                    taskDetails = parseTaskDetails(inputData);
                    Event.checkEventInput(taskDetails);
                    Task.addNewTask(new Event(taskDetails[0], taskDetails[1]));
                    UI.taskAddedSuccessfully();
                    break;
                } catch (MissingEventDurationException e) {
                    System.out.println(e.getMessage());
                    UI.showDivider();
                } catch (MissingTaskDescriptionException e) {
                    System.out.println(e.getMessage());
                    UI.showDivider();
                }
                break;
            case "todo":
                try {
                    taskDetails = parseTaskDetails(inputData);
                    Todo.checkTodoInput(taskDetails);
                    Task.addNewTask(new Todo(taskDetails[0]));
                    UI.taskAddedSuccessfully();
                    break;
                } catch (MissingTaskDescriptionException e) {
                    System.out.println(e.getMessage());
                    UI.showDivider();
                }
                break;
            default:
                throw new InvalidInputException();
        }
    }

    public static String[] parseTaskDetails(String[] inputData) {
        String[] parsedData = new String[2];
        String prefix = "";
        StringBuilder inputDueDate = new StringBuilder();
        StringBuilder inputDescription = new StringBuilder();
        int separatorIndex = -1;
        // we start with '1' so we don't see the user-input category again
        for (int i = 1; i < inputData.length; i++) {
            if (inputData[i].equals("||")) {
                separatorIndex = i;
                break;
            } else {
                inputDescription.append(prefix).append(inputData[i]);
                prefix = " ";
            }
        }
        parsedData[0] = inputDescription.toString();
        if (parsedData[0].equals("")) {
            parsedData[0] = null;
        }
        // in case there is no indicative '||'
        if (separatorIndex != -1) {
            prefix = "";
            for (int j = separatorIndex + 1; j < inputData.length; j++) {
                inputDueDate.append(prefix).append(inputData[j]);
                prefix = " ";
            }
            // even if '||' is present in the input, but nothing follows is
            parsedData[1] = inputDueDate.toString();
            // validating data input would go here
            if (parsedData[1].equals("")) {
                parsedData[1] = null;
            }
        } else {
            parsedData[1] = null;
        }
        return parsedData;
    }
}
