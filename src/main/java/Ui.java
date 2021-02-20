public class Ui {
    public Ui() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Here are the things that i can do: ");
        System.out.println("Add task to your list - todo, deadline and event tasks");
        System.out.println("list: View all tasks in the list");
        System.out.println("done: Done task and delete: Delete task");
        System.out.println("bye: quite Duke");
        System.out.println("Please enter the task you want to add in to the task list.");
    }

    public void showExitMessage() {
        System.out.println("Thank you for using Tasks function, " +
                "you can now choose other function :)");
    }

    public void fileErrorMessage() {
        System.out.println(":( OOps!! The file you are looking for does not exist.");
    }

    public void exceptionMessage() {
        System.out.println(":( OOps!! Something went wrong: ");
    }

    public void showErrorMessage() {
        System.out.println(":( OOps!! I'm sorry, " +
                "but i don't know what that means...");
    }

    public void todoIndexError() {
        System.out.println(":( OOps!! The description of a" +
                "todo cannot be empty");
    }

    public void eventIndexError() {
        System.out.println(":( OOps!! The description of a" +
                "event cannot be empty");
    }

    public void deadlineIndexError() {
        System.out.println(":( OOps!! The description of a" +
                "deadline cannot be empty");
    }

    public void doneIndexError() {
        System.out.println(":( OOps!! The index of task to be done" +
                "cannot be empty");
    }

    public void deleteIndexError() {
        System.out.println(":( OOps!! The index of task to be deleted" +
                "cannot be empty");
    }

    public void showLoadingError() {
        System.out.println(":( OOps!! Seems there's something wrong with" +
                "loading Duke");
    }
}
