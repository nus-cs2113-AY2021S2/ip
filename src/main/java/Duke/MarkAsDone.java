package Duke;

public class MarkAsDone extends Duke{
    public static void MarkAsDone(String doneTask){
        try{
            int donetaskIndex = Integer.parseInt(doneTask) - 1;
            if (donetaskIndex < listIndex && donetaskIndex >= 0) {
                System.out.println(" Yay! This task is done!");
                lists[donetaskIndex][1] = "X";
                PrintList(donetaskIndex, donetaskIndex + 1);
            } else {
                DukeException.exceedListLength(donetaskIndex + 1);
            }
        } catch (Exception e) {
            DukeException.doneWithoutNo();
        }
    }
}
