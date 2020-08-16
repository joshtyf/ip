class Deadline extends Task {
    private String dueDate;
    
    Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (due: %s)", super.toString(), dueDate);
    }
}
