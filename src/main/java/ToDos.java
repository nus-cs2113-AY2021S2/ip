/**
 * This class deals with the todo task.
 */
public class ToDos extends Task {

    /**
     * Constructor used to instantiate a new deadline object.
     *
     * @param work This is a String variable which has the the actual description of the todos task.
     */

    public ToDos(String work) {
        super(work);
    }

    public String toString(){
        return "[T]" + super.toString();
    }

    public boolean istodo(){
        return true;
    }
}
