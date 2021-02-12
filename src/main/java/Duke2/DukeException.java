package Duke2;

public class DukeException extends Duke {
    public static void taskDescriptionEmpty(){
        System.out.println(" No task description :(\n Ahh what's the task here?");
    }

    public static void taskWithoutTime() {
        System.out.println(" No time input :(\n When do you need to get it done?");
    }

    public static void illegalInput() {
        System.out.println(" Duke2.Duke doesn't know what to do with the command D:");
    }

    public static void doneWithoutNo() {
        System.out.println(" No index input :(\n Which task is done?");
    }

    public static void exceedListLength(int taskIndex) {
        System.out.println(" You don't have Task "+ taskIndex +" in your list ^_^");
    }
}
