package tasklist;

import java.util.ArrayList;

public class searchResultsList {
    private static final ArrayList<Task> searchResults = new ArrayList<>();

    public static void addTask(Task task) {
        searchResults.add(task);
    }

    public static void removeTask(Task task) {
        searchResults.remove(task);
    }

    public static ArrayList<Task> viewTasks() {
        return searchResults;
    }

    public static Task getIndex(int index) {
        return searchResults.get(index);
    }

    public static int getSize() {
        return searchResults.size();
    }

    public static boolean isEmpty() {
        if (searchResultsList.getSize() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<Task> getTaskList() {
        return searchResults;
    }

    public static void clearList() {
        searchResults.clear();
    }
}

