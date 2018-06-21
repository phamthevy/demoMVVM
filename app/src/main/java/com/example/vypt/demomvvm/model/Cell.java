package com.example.vypt.demomvvm.model;

public class Cell {

    public Player player;

    public Cell(Player player) {
        this.player = player;
    }

    public boolean isEmpty(){
        if (player==null || player.value == null || player.value.isEmpty())
            return true;
        else return false;
    }
}
