package com.example.railrush.Models;

public class Train{
    String start,dest,time,count;

    public Train(String start, String dest, String time, String count) {
        this.start = start;
        this.dest = dest;
        this.time = time;
        this.count = count;
    }

    public String getStart() {
        return start;
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
