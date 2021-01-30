public class Todo extends Task{
    private static final String ALPHABET_T = "T";

    public Todo(String d){
        super(d);
    }

    @Override
    public String getTaskType(){
        return ALPHABET_T;
    }
}
