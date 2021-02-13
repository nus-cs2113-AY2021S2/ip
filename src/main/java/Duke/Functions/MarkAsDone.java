package Duke.Functions;

import Duke.Duke;
import Duke.Exceptions.DukeException;

public class MarkAsDone extends Duke {
    public static void markAsDone(String doneTask){
        try{
            int doneTaskIndex = Integer.parseInt(doneTask) - 1;
            if (doneTaskIndex < taskCount && doneTaskIndex >= 0) {
                System.out.println(" Yay! This task is done!");
                lists.get(doneTaskIndex).markAsDone();
                printList(doneTaskIndex, doneTaskIndex + 1);
            } else {
                DukeException.exceedListLength(doneTaskIndex + 1);
            }
        } catch (Exception e) {
            DukeException.doneWithoutNo();
        }
    }
}
