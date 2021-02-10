package exception;

public class DukeException extends Exception{
    String errmsg;
    public DukeException(String s) {
        this.errmsg = s;
    }
    @Override
    public String toString(){
        return this.errmsg;
    }
}
