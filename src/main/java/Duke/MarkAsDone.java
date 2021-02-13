package Duke;

public class MarkAsDone extends Duke{
    public static void markAsDone(String doneTask){
        try{
            int donetaskIndex = Integer.parseInt(doneTask) - 1;
            if (donetaskIndex < taskCount && donetaskIndex >= 0) {
                System.out.println(" Yay! This task is done!");
                lists[donetaskIndex][1] = "X";
                printList(donetaskIndex, donetaskIndex + 1);
            } else {
                DukeException.exceedListLength(donetaskIndex + 1);
            }
        } catch (Exception e) {
            DukeException.doneWithoutNo();
        }
    }
}
