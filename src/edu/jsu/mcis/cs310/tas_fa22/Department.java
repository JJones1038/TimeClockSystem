package edu.jsu.mcis.cs310.tas_fa22;

public class Department {

    private int id;
    private String description;
    private Integer terminalId;

    public Department(int id, String description, Integer terminalID) {
        this.id = id;
        this.description = description;
        this.terminalId = terminalID;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Integer getTerminalID() {
        return terminalId;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append('#').append(id).append(' ').append('(').append(description);
        s.append(')').append(',').append(" Terminal ID: ").append(terminalId);

        return s.toString();
    }
}
