public class Parser {
    public static void processTask(String input) {
        String[] inputData = input.split(" ");
        String[] taskDetails;
        switch (inputData[0]) {
        case "done":
            int completedTaskIndex = Integer.parseInt(input.split(" ")[1]);
            completedTaskIndex--;
            Task.completeTask(completedTaskIndex);
            UI.taskCompletedSuccessfully();
            break;
        case "deadline":
            taskDetails = findTaskDetails(inputData);
            Task.addNewTask(new Deadline(taskDetails[0], taskDetails[1]));
            UI.taskAddedSuccessfully();
            break;
        case "event":
            taskDetails = findTaskDetails(inputData);
            Task.addNewTask(new Event(taskDetails[0], taskDetails[1]));
            UI.taskAddedSuccessfully();
            break;
        case "todo":
            taskDetails = findTaskDetails(inputData);
            Task.addNewTask(new Todo(taskDetails[0]));
            UI.taskAddedSuccessfully();
            break;
        default:
            System.out.println("Please re-check your input! Terminating program now...");
        }
    }
    public static String[] findTaskDetails(String[] inputData) {
        String[] parsedData = new String [2];
        String prefix = "";
        StringBuilder inputDueDate = new StringBuilder();
        StringBuilder inputDescription = new StringBuilder();
        int separatorIndex = -1;
        // we start with '1' so we don't see the user-input category again
        for (int i = 1; i < inputData.length; i++){
            if (inputData[i].equals("||")){
                separatorIndex = i;
                break;
            } else {
                inputDescription.append(prefix).append(inputData[i]);
                prefix = " ";
            }
        }
        parsedData[0] = inputDescription.toString();

        prefix = "";
        for (int j = separatorIndex + 1; j < inputData.length; j++) {
            inputDueDate.append(prefix).append(inputData[j]);
            prefix = " ";
        }
        parsedData[1] = inputDueDate.toString();

        return parsedData;

    }

}
