public class EventFormatException extends TaskFormatException{
    @Override
    public String toString(){
        return new String("    Name of event should be specified after keyword event. " +
                "Name and the time should be separated by \"/at\"\n" +
                super.toString());
    }
}
