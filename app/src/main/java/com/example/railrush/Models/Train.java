package com.example.railrush.Models;

public class Train {
    String start, dest, time, count, lastStation;

    public Train(String start, String dest, String time, String count, String lastStation) {
        this.start = start;
        this.dest = dest;
        this.time = time;
        this.count = count;
        this.lastStation = lastStation;
    }

    public String getStart() {
        return start;
    }

    public String getLastStation() {
        return lastStation;
    }

    public String getDest() {
        return dest;
    }

    public String getTime() {
        return time;
    }

    public String getCount() {
        return count;
    }
}
