package com.example.smartqueue.model;

public enum Priority {
    EMERGENCY("Emergency / VIP", 1, "VIP"),
    ASSISTED("Senior Citizen / Specially Abled", 2, "ASSIST"),
    REGULAR("Regular Customer", 3, "REG");

    private final String label;
    private final int rank;
    private final String code;

    Priority(String label, int rank, String code) {
        this.label = label;
        this.rank = rank;
        this.code = code;
    }
    public String getLabel() { return label; }
    public int getRank() { return rank; }
    public String getCode() { return code; }
}
