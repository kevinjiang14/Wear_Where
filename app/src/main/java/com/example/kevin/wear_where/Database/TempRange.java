package com.example.kevin.wear_where.Database;

/* Class used to move data in and out of the database */
public class TempRange {
    private long id;
    private int min;
    private int max;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMin() {
        return min;
    }

    public int getMax(){
        return max;
    }

    public void setRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public String toString() {
        return Integer.toString(min) + ":" + Integer.toString(max);
    }
}
