package commands;

import myexceptions.BlankDescriptionException;
import myexceptions.InvalidSpaceException;
import myexceptions.NoMatchesFoundException;
import myexceptions.SearchLimitExceededException;
import parser.Parser;
import tasklist.Task;
import tasklist.Tasklist;
import tasklist.searchResultsList;

import javax.naming.LimitExceededException;
import javax.naming.directory.SearchResult;
import java.util.ArrayList;

public class Find {

    public static void execute(String userInput)
            throws InvalidSpaceException, BlankDescriptionException, NoMatchesFoundException {

        searchResultsList searchResults = new searchResultsList();
        String keyword = Parser.parsingFindCommand(userInput);
        for (Task task : Tasklist.viewTasks()){
            if(task.description.contains(keyword)){
                searchResultsList.addTask(task);
            }
        }

        if(searchResultsList.isEmpty()){
            throw new NoMatchesFoundException();
        }

        for (int i = 0; i < searchResults.getSize(); ++i) {
            Task task = searchResults.getIndex(i);
            System.out.println(i + 1 + "." + task.getStatusIcon() + " "
                    + task.getDescription());
        }

        searchResultsList.clearList();
    }
}
