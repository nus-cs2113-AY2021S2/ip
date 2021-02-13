package Duke;

import java.util.*;

public class Delete extends Duke{
    public static void deleteTask(String deleteTask){
        try{
            int deletetaskIndex = Integer.parseInt(deleteTask) - 1;
            if (deletetaskIndex < taskCount && deletetaskIndex >= 0) {
                System.out.println(" Yay! This task is deleted!");
                printList(deletetaskIndex, deletetaskIndex + 1);
                for (int i = deletetaskIndex; i < taskCount; ++i)
                {
                    lists[i] = lists[i+1];
                }
                taskCount--;
            } else {
                DukeException.exceedListLength(deletetaskIndex + 1);
            }
        } catch (Exception e) {
            DukeException.deleteWithoutNo();
        }
    }
}
