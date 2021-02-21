package Duke.Exceptions;

import Duke.Duke;

public class DukeException extends Duke {
    public static void taskDescriptionEmpty(){
        System.out.println(" No task description :(\n Ahh what's the task here?");
    }

    public static void taskWithoutTime() {
        System.out.println(" No time input :(");
    }

    public static void illegalInput() {
        System.out.println(" Duke doesn't know what to do with the command D:");
    }

    public static void noIndexInput() {
        System.out.println(" No index input :(");
    }

    public static void exceedListLength(int taskIndex) {
        System.out.println(" You don't have Task "+ taskIndex +" in your list ^_^");
    }

    public static void cannotRecognise(String input) {
        System.out.println(" Duke could find any tasks related to " + input);
    }
}
