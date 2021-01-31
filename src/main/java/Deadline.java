public class Deadline extends TaskList{
    private static final String TASKTITLE ="D";
    protected String []by;

    public Deadline(int size){
        super(size);
        by=new String[size];
    }

    @Override
    public void addNewTask(String line) {
        areAllTasksDone = false;
        (this.description)[tasksCounter] = getDescription(line);
        (this.by)[tasksCounter]=getBy(line);
        (this.isDone)[tasksCounter] = false;
        printDottedLines();
        printTaskDescription();
        tasksCounter++;
        printNumberOfTasksLeft();
        printDottedLines();
    }

    private String getDescription(String line){
        String[] description = line.split("/",2);
        return description[0];
    }
    public String getBy(String line){
        String[] lineWords= line.split("/",2);
        String by;
        try{
            by = lineWords[1];

        }catch(ArrayIndexOutOfBoundsException e) {
            by ="No Deadline!! Hehe! :)";
        }

        return by;

    }
    public String getTaskBy(int index){
        return this.by[index];
    }

    @Override
    public void printTaskDescription() {
        String phrase = "Aight Crewmate!! I've got a new deadline for you!!!"+System.lineSeparator()
                +"  ["+TASKTITLE+"]"+"["+getStatusIcon(getTasksCounter())+"]"+ getTaskDescription(tasksCounter)+"(by: "
                +getTaskBy(getTasksCounter())+")";
        System.out.println(phrase);
    }

    @Override
    public void printTask(int index) {
        String phrase =(index+1)+ "."+"["+TASKTITLE+"]"+"["+getStatusIcon(index)+"]"+ getTaskDescription(index)+"(by: "
                +getTaskBy(index)+")";
        System.out.println(phrase);
    }

    @Override
    public void printListName() {
        System.out.println("ATTENTION, Here's your list of DEADLINE(S) to meet  Crewmate!!!");
    }
}
