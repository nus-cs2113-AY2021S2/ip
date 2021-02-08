package list;

public class Event extends Deadline {
    private static final String TASK_TITLE = "E";

    public Event(int size) {
        super(size);
    }

    @Override
    public String getBy(String line) {
        String[] lineWords = line.split("/", 2);
        String by;
        try {
            by = lineWords[1];

        } catch (ArrayIndexOutOfBoundsException e) {
            by = "No Event time!! Noice!!! :)";
        }

        return by;

    }

    @Override
    public void printTaskDescription() {
        String phrase = "Aight Crewmate!! I've got a new event for you!!!" + System.lineSeparator()
                + "  [" + TASK_TITLE + "]" + "[" + getStatusIcon(getTasksCounter()) + "]"
                + getTaskDescription(tasksCounter) + "(by: "
                + getTaskBy(getTasksCounter()) + ")";
        System.out.println(phrase);
    }


    @Override
    public void printListName() {
        System.out.println("ATTENTION, Here's your list of EVENT(S) to attend  Crewmate!!!");
    }


}

