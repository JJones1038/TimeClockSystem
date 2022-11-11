package edu.jsu.mcis.cs310.tas_fa22;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Punch {

    private int id, terminalId, eventTypeId;
    private String badgeId;
    private LocalDateTime originalTimeStamp;
    private Badge badge;
    private EventType punchType;
    private PunchAdjustmentType adjustmentType;

    // New Punch //

    public Punch(int terminalId, Badge badge, EventType punchType){
        this.terminalId = terminalId;
        this.badge = badge;
        this.punchType = punchType;
    }

    // Existing Punch //

    public Punch(int id, int terminalId, Badge badge, LocalDateTime originalTimeStamp, EventType punchType){
        this.id = id;
        this.terminalId = terminalId;
        this.badge = badge;
        this.originalTimeStamp = originalTimeStamp;
        this.punchType = punchType;
    }

    public int getId() {
        return id;
    }

    public int getTerminalId() {
        return terminalId;
    }

    public int getEventTypeId() {
        return eventTypeId;
    }

    public String getBadgeId() {
        return badgeId;
    }

    public LocalDateTime getOriginalTimeStamp() {
        return originalTimeStamp;
    }

    public Badge getBadge() {
        return badge;
    }

    public EventType getPunchType() {
        return punchType;
    }

    public PunchAdjustmentType getAdjustmentType() {
        return adjustmentType;
    }

    public String printOriginal(){
        StringBuilder s = new StringBuilder();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");

        s.append('#').append(badge.getId()).append(' ');
        s.append(punchType).append(": ").append(originalTimeStamp.format(formatter).toUpperCase());

        return s.toString();
    }
}
