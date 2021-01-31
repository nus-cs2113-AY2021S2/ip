public class Parser {
    public static void addOrCompleteTask(String input, Task[] tasks){
        String[] inputData = input.split(" ");
        String[] taskDetails;
        switch (inputData[0]) {
            case "done" -> {
                int completedTask = Integer.parseInt(input.split(" ")[1]);
                completedTask--;
                tasks[completedTask].markAsDone();
                System.out.println("The Force is with you! The following task has been marked as done:");
                System.out.println(tasks[completedTask]);
                UI.showDivider();
            }
            case "deadline" -> {
                taskDetails = findTaskDetails(inputData);
                tasks[Task.getTaskCounter()] = new Deadline(taskDetails[0], taskDetails[1]);
                System.out.println("Roger Roger. The following task has been added:");
                System.out.println(tasks[Task.getTaskCounter()]);
                UI.showDivider();
                Task.incrementTaskCounter();

            }
            case "event" -> {
                taskDetails = findTaskDetails(inputData);
                tasks[Task.getTaskCounter()] = new Event(taskDetails[0], taskDetails[1]);
                System.out.println("Roger Roger. The following task has been added:");
                System.out.println(tasks[Task.getTaskCounter()]);
                UI.showDivider();
                Task.incrementTaskCounter();
            }
            case "todo" -> {
                taskDetails = findTaskDetails(inputData);
                tasks[Task.getTaskCounter()] = new Todo(taskDetails[0]);
                System.out.println("Roger Roger. The following task has been added:");
                System.out.println(tasks[Task.getTaskCounter()]);
                UI.showDivider();
                Task.incrementTaskCounter();
            }
        }
    }
    public static String[] findTaskDetails(String[] inputData){
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
            }
            else {
                inputDescription.append(prefix).append(inputData[i]);
                prefix = " ";
            }
        }
        parsedData[0] = inputDescription.toString();

        prefix = "";
        for (int j = separatorIndex + 1; j < inputData.length; j++){
            inputDueDate.append(prefix).append(inputData[j]);
            prefix = " ";
        }
        parsedData[1] = inputDueDate.toString();

        return parsedData;

    }

}
