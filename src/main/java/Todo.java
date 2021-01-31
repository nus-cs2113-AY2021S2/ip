public class Todo extends TaskList{

    private static final String TASKTITLE ="T";

    public Todo(int size){
        super(size);
    }

    @Override
    public void printTaskDescription() {
        String phrase = "Aight Crewmate!! I've got a new task for you to do!!!"+System.lineSeparator()
                +"  ["+TASKTITLE+"]"+"["+getStatusIcon(getTasksCounter())+"]"+ getTaskDescription(tasksCounter);
        System.out.println(phrase);
    }

    @Override
    public void printTask(int index) {
        String phrase =(index+1)+ "."+"["+TASKTITLE+"]"+"["+getStatusIcon(index)+"]"+ getTaskDescription(index);
        System.out.println(phrase);
    }

    @Override
    public void printListName() {
        System.out.println("ATTENTION, Here's your list of your tasks TO DO Crewmate!!!");
    }
}
