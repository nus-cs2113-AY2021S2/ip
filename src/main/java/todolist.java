public class todolist {
    private String[] list = new String[100];
    private int items = 0;
    private Boolean[] tracker = new Boolean[100];


    public todolist(String[] arg){
        this.items = arg.length;
        for(int i = 0; i < items; i++){
            this.list[i] = arg[i];
            this.tracker[i] = false;
        }
    }

    public todolist(){

    }

    public String displayCheck(Boolean arg){
        if (arg){
            return "[X]";
        }else{
            return "[ ]";
        }
    }


    public String itemLister(){
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


    public String itemAdder(String arg){
        this.list[items] = arg;
        this.tracker[items] = false;
        this.items += 1;
        return "added: " + arg;
    }

    public String itemResolver(String arg){
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


}
