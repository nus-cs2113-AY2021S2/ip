public class todolist {
    protected String[] list = new String[100];
    protected Boolean[] tracker = new Boolean[100];
    protected int items = 0;



    public todolist(){

    }

    public String displayCheck(Boolean arg){
        if (arg){
            return "[X]";
        }else{
            return "[ ]";
        }
    }


    public String listItems(){
        String output = "";

        for(int i = 0; i < items; i++){
            if(i < items - 1) {
                output += Integer.toString(i + 1) + ". " + displayCheck(tracker[i]) + " " + list[i] + "\n";
            }else{
                output += Integer.toString(i + 1) + ". " + displayCheck(tracker[i]) + " " + list[i];
            }
        }
        return output;
    }


    public String addItems(String arg){
        this.list[items] = arg;
        this.tracker[items] = false;
        this.items += 1;
        return arg;
    }

    public String resolveItem(String arg){
        String output = "";

        Integer taskNum = Integer.parseInt(arg) - 1;

        if(tracker[taskNum]){
            return "Task is already done!";
        }


        this.tracker[taskNum] = true;
        output = "Nice! I've marked this task as done: \n" +
                 displayCheck(true) + " " + list[taskNum];

        return output;
    }

    public int getItems(){
        return this.items;
    }


}
