package duke;

import java.io.IOException;

/**
 * Duke is a Task Manager Software that allows a user to add tasks into the system,
 * modify or delete existing tasks, search for tasks base on keywords, and displaying
 * current tasks in the system.
 */
public class Duke {
    /**
     * The main class of Duke, which serves as the entrance point for the whole software.
     *
     * @param args user's input.
     * @throws IOException If a particular file is not found in the database.
     */
    public static void main(String[] args) throws IOException {
        DukeUI.printWelcomeMessage();
        DukeUI.printMenu();
        DukeController.run();
    }
}