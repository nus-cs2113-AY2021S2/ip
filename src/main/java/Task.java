public class Task {
    static final int MAX_TASK_NUM = 100;

    private String[] list = new String[MAX_TASK_NUM];
    private int jobAmt;

    public Task() {
        jobAmt = 0;
    }

    public Task(String[] list, int jobAmt) {
        this.list = list;
        this.jobAmt = jobAmt;
    }

    public void addJob(String job) {
        list[jobAmt] = job;
        jobAmt++;
        System.out.println("added: " + job);
    }

    public void listJob() {
        for (int i=0; list[i] != null; i++) {
            System.out.println((i+1) + ". " + list[i]);
        }
    }
}
