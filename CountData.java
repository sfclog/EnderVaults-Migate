package me.sfclog.smalladdon.util;

public class CountData {
    int i ;
    public CountData() {
        i = 1;
    }
    public int get() {
        return i;
    }
    public void up() {
        this.i = i + 1;
    }
}
