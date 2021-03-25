import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This is the Task class.
 * This class will create a task with a localdatetime and actual description.
 */

public class Task {
    private boolean done;
    private String work;
    private LocalDateTime date;

    /**
     * Constructor initializes a task with user input.
     *
     * @param work A String variable with the description provided by the user.
     */

    public Task(String work){
        this.done = false;
        this.work = work;
    }

    /**
     * Constructor initializes a task with description provided by the user and localdatetime.
     *
     * @param work A String variable with the description provided by the user.
     * @param date A localdatetime object contains the timing.
     */

    public Task(String work, LocalDateTime date){
        this.work = work;
        this.date = date;
        this.done = false;
    }

    /**
     * Gets the formatted version of the date and time
     * @return A formatted String version of the date and time.
     */

    public String getDate(){
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Gets the actual description of the task
     *
     * @return A String which has the description.
     */

    public String description(){
        return done
                ? "[T][✓]"
                : "[T][✗]";
    }
    public void updateStatus(){
        done = true;
    }

    public boolean isDone(){
        return done;
    }

    public String getWork(){
        return work;
    }

    public boolean istodo(){
        return false;
    }

    /**
     * This will Override the toString method.
     *
     * @return A String variable that represents the task.
     */

    public String toString(){
        if (!this.done){
            return "[✗]" + this.work;
        } else {
            return "[✓]" + this.work;
        }
    }
}
