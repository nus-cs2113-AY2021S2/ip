package duke;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import duke.exception.SaveException;
import duke.exception.SaveException.SaveExceptionType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Helper {
    // Print a line with 1 tab as indentation
    protected static void printlnWithIndent(String line) {
        System.out.println("\t" + line);
    }

    // Print a message for a successful insertion of task
    protected static void printNewTask(Vector<Task> tasks) {
        int size = tasks.size();
        printlnWithIndent("Great. We added a new task:");
        printlnWithIndent("\t" + tasks.get(size - 1));
        printlnWithIndent(String.format("You have in total %d tasks", size));
    }

    // Find the index of a string in a string array
    // Return the index if found and -1 if not found
    protected static int findIndex(String[] haystack, String needle) {
        for (int i = 0; i < haystack.length; i += 1) {
            if (haystack[i].equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    protected static Vector<Task> loadList(String file) throws IOException, SaveException {
        Vector<Task> tasks = new Vector<>();
        FileInputStream fs = null;
        ObjectInputStream os = null;
        try {
            fs = new FileInputStream(file);
            os = new ObjectInputStream(fs);
            Object obj = os.readObject();

            if (obj instanceof Vector<?>) {
                Vector<?> vec = (Vector<?>) obj;
                for (Object task: vec) {
                    // Check task type and push them to vector accordingly
                    if (task instanceof Deadline) {
                        tasks.add((Deadline) task);
                    } else if (task instanceof Event) {
                        tasks.add((Event) task);
                    } else if (task instanceof ToDo) {
                        tasks.add((ToDo) task);
                    } else {
                        // We encountered an element that is not of any valid type
                        throw new SaveException(SaveExceptionType.INVALID_SAVE);
                    }
                }
            } else {
                throw new SaveException(SaveExceptionType.INVALID_SAVE);
            }
        } catch (FileNotFoundException e) {
            throw new SaveException(SaveExceptionType.NO_SAVE, e);
        } catch (ClassNotFoundException e) {
            throw new SaveException(SaveExceptionType.INVALID_SAVE, e);
        } finally {
            // Close all open file and object handles
            if (os != null) {
                os.close();
            }
            if (fs != null) {
                fs.close();
            }
        }
        return tasks;
    }

    protected static void saveList(String file, Vector<Task> tasks) throws IOException, SaveException {
        FileOutputStream fs = null;
        ObjectOutputStream os = null;
        try {
            fs = new FileOutputStream(file);
            os = new ObjectOutputStream(fs);
            os.writeObject(tasks);
        } catch (FileNotFoundException e) {
            // Thrown by `new FileOutputStream`, usually when `file` is a folder or cannot be created
            throw new SaveException(SaveExceptionType.INVALID_PATH);
        } finally {
            // Close all open file and object handles
            if (os != null) {
                os.close();
            }
            if (fs != null) {
                fs.close();
            }
        }
    }
}
