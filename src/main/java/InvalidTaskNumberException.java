public class InvalidTaskNumberException extends DukeException {
    private String taskNumber;

    public InvalidTaskNumberException(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String toString() {
        return super.toString() + " " + taskNumber + " is an invalid task number.";
    }
}
