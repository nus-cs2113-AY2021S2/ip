/**
 * Store the list of tasks
 */
public class TaskList {
    public static Task t[] = new Task[100];
    public static Parser parser = new Parser();
    public static Ui ui = new Ui();

    /**
     * Constructor of the class
     */
    public TaskList(){
    }

    /**
     * @return the list of stored tasks
     */
    protected Task[] getTasks(){
        return t;
    }

    /**
     * Delete the task from the stored list
     * @param user_input
     */
    protected static void deleteTask(String user_input) {
        if (parser.isDelete(user_input)) {
            int spaceIndex = user_input.indexOf(' ');
            String deleteString = user_input.substring(spaceIndex + 1);
            int deleteIndex = Integer.parseInt(deleteString);
            System.out.println("I have deleted this task for you: ");
            System.out.println("[" + t[deleteIndex].getStatusIcon() + "] " + t[deleteIndex].getDescription() + "\n");


            for (int i = deleteIndex; i < t.length - 1; i++) {
                    t[i] = t[i + 1];
            }

            ui.num_of_goals--;

        }
    }

    /**
     * Message to show to users after adding a task
     */
    protected static void repeatTaskAdded() {
        System.out.println(t[ui.num_of_goals]);
        System.out.println("Now you have " + ui.num_of_goals + " tasks in the list.");
    }

    /**
     * Mark a specific task as done
     * @param user_input
     */
    protected static void markAsDone(String user_input) {
        int doneTaskIndex = parser.getDoneTaskIndex(user_input);
        t[doneTaskIndex].markAsDone();
        doneMessage(doneTaskIndex);
    }

    /**
     * add a new task to the task list
     * @param user_input
     */
    protected static void addNewTask(String user_input) {
        t[ui.num_of_goals] = new Task(user_input);
    }


    /**
     * add a new deadline task
     * @param user_input
     */
    protected static void addNewDeadline(String user_input) {
        t[ui.num_of_goals] = new Deadline(user_input.substring(ui.DEADLINE_START, parser.getDeadlineIndex(user_input)), parser.getDeadline(user_input));
    }

    /**
     * add a new event task
     * @param user_input
     */
    protected static void addNewEvent(String user_input) {
        t[ui.num_of_goals] = new Event(user_input.substring(ui.EVENT_START, parser.getDeadlineIndex(user_input)), parser.getDeadline(user_input));
    }

    /**
     * add a new todo task
     * @param user_input
     */
    protected static void addNewTodo(String user_input) {
        t[ui.num_of_goals] = new Todo(user_input.substring(ui.TODO_START, user_input.length()));
    }

    /**
     * print the message to user showing that task is added
     * @param taskIndex
     */
    protected static void doneMessage(int taskIndex) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + t[taskIndex].getStatusIcon() + "] " + t[taskIndex].getDescription() + "\n");
    }

    protected static void listTaskMsg() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Print the list of all tasks
     */
    protected static void enumerateTasks() {
        for (int i = 0; i < ui.num_of_goals; i++) {
            System.out.println((i + 1) + "." + t[i + 1].toString());
        }
    }

    /**
     * Find the task as specified in the input
     * @param user_input
     */
    protected static void findTasks(String user_input){
        if(parser.isFind(user_input)){
            String taskname = user_input.substring(ui.FIND_START, user_input.length());
            System.out.println("Here are the matching tasks in your list:");
            for (int i=1;i<=ui.num_of_goals;i++){
                if(t[i].getDescription().contains(taskname)){
                    System.out.println((i) + "." + t[i].toString());
                }
            }
        }
    }
}
