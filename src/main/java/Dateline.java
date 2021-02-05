class Dateline extends Task{
    private String by;

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
}
