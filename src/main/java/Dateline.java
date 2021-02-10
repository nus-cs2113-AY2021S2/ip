class Dateline extends Task {
    protected String by;

    public Dateline(String description, String byInput) {
        super(description);
        this.by = byInput;
    }

    public void setBy(String byInput) {
        this.by = byInput;
    }
    public String getBy() {
        return this.by;
    }
    @Override
    public String getType() {
        return "D";
    }
    @Override
    public void print(){
        if (this.isDone) {
            System.out.println("[D][\u2713] " + description + " (by: " + by + ")");
        } else {
            System.out.println("[D][\u2718] " + description + " (by: " + by + ")");
        }
    }
}
