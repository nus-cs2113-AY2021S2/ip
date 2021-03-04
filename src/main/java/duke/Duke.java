package duke;

import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

//import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
//import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public static void main(String[] args) throws DukeException{
        Ui.printWelcomeMessage();
        Storage.loadData();
        Parser.getInput();
    }
}