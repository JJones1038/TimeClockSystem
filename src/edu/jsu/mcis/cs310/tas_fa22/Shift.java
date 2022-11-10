package edu.jsu.mcis.cs310.tas_fa22;

import java.time.*;
import java.util.HashMap;

public class Shift {

    private int id, roundInterval, gracePeriod, dockPenalty, lunchThreshold, lunchDuration, shiftDuration;
    private String description;
    private LocalTime shiftStart, shiftStop, lunchStart, lunchStop;

    public Shift(HashMap<String, String> map){

        this.id = Integer.parseInt(map.get("id"));
        this.description = map.get("description");
        this.shiftStart = LocalTime.parse(map.get("shiftStart"));
        this.shiftStop = LocalTime.parse(map.get("shiftStop"));
        this.roundInterval = Integer.parseInt(map.get("roundInterval"));
        this.gracePeriod = Integer.parseInt(map.get("gracePeriod"));
        this.dockPenalty = Integer.parseInt(map.get("dockPenalty"));
        this.lunchStart = LocalTime.parse(map.get("lunchStart"));
        this.lunchStop = LocalTime.parse(map.get("lunchStop"));
        this.lunchThreshold = Integer.parseInt(map.get("lunchThreshold"));
        this.lunchDuration = (int)Duration.between(this.lunchStart, this.lunchStop).toMinutes();
        this.shiftDuration = (int)Duration.between(this.shiftStart, this.shiftStop).toMinutes();
    }

    public int getId() {
        return id;
    }

    public int getRoundInterval() {
        return roundInterval;
    }

    public int getGracePeriod() {
        return gracePeriod;
    }

    public int getDockPenalty() {
        return dockPenalty;
    }

    public int getLunchThreshold() {
        return lunchThreshold;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getShiftStart() {
        return shiftStart;
    }

    public LocalTime getShiftStop() {
        return shiftStop;
    }

    public LocalTime getLunchStart() {
        return lunchStart;
    }

    public LocalTime getLunchStop() {
        return lunchStop;
    }

    public int getLunchDuration() {
        return lunchDuration;
    }

    public int getShiftDuration() {
        return shiftDuration;
    }

    @Override

    public String toString(){

        StringBuilder s = new StringBuilder();

        s.append(getDescription()).append(':').append(' ');
        s.append(getShiftStart()).append(' ').append('-');
        s.append(' ').append(getShiftStop()).append(' ');
        s.append('(').append(getShiftDuration()).append(' ').append("minutes").append(')').append(';');
        s.append(' ').append("Lunch:").append(' ').append(getLunchStart()).append(' ').append('-').append(' ');
        s.append(getLunchStop()).append(' ').append('(').append(getLunchDuration()).append(' ').append("minutes").append(')');

        return s.toString();
    }
}
