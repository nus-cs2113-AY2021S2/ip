package exception;

public class DeleteFormatException extends RuntimeException {
    public DeleteFormatException(){};

    @Override
    public String toString(){
        return new String("Invalid input format for delete command.\n" +
                "    Input format for delete: delete + task index    eg. delete 1");
    }

}
