public class List {
    private int numItems;
    private Task[] TaskList;

    List(){
        TaskList = new Task[100];
        numItems = 0;
    }

    public void addTask(String inputTask) {
        Task userTask = new Task(inputTask);
        TaskList[numItems] = userTask;
        addTaskNum();
        taskAddedMessage(userTask);
    }

    public void addDeadline(String inputDeadline) {
        String[] deadlineTokens = inputDeadline.split("/by", 2);
        Deadline deadline = new Deadline(deadlineTokens[0].trim(), deadlineTokens[1].trim());
        TaskList[numItems] = deadline;
        addTaskNum();
        taskAddedMessage(deadline);
    }

    public void addEvent(String inputEvent) {
        String[] eventTokens = inputEvent.split("/at", 2);
        Event event = new Event(eventTokens[0].trim(), eventTokens[1].trim());
        TaskList[numItems] = event;
        addTaskNum();
        taskAddedMessage(event);
    }

    public void addTaskNum() {
        numItems++;
    }

    public void printList() {
        if (numItems == 0) {
            System.out.println("whoops nothing to see here");
            return;
        }
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < numItems; i++) {
            System.out.println( i+1 + ". " + TaskList[i].toString());
        }
    }

    public void markDone(int index) {
        TaskList[index-1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(TaskList[index-1].toString());
    }

    private void taskAddedMessage(Task inputTask) {
        System.out.println("Got it. I've added this task: \n" + inputTask.toString());
        System.out.println("Now you have " + numItems + " tasks in the list");
    }
}
