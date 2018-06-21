package com.example.vypt.demomvvm.model;

import android.arch.lifecycle.MutableLiveData;

public class Game {

    private static final String TAG = Game.class.getSimpleName();
    private static final int BOARD_SIZE = 3;

    public Player player1;
    public Player player2;

    public Player currentPlayer;
    public Cell[][] cells;

    public MutableLiveData<Player> winner = new MutableLiveData<>();

    public Game(String playerOne, String playerTwo) {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        player1 = new Player(playerOne, "x");
        player2 = new Player(playerTwo, "o");
        currentPlayer = player1;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1 ? player2 : player1);
    }

    public boolean hasEndedGame() {
        if (hasThreeSameVertical() || hasThreeSameHorizontal() || hasThreeSameDiagonal()) {
            winner.setValue(currentPlayer);
            return true;
        } else if (isBoardFull()) {
            winner.setValue(null);
            return true;
        } else return false;
    }

    public boolean checkEqualCell(int i, int j, int m, int n){
        if (cells[i][j] == null || cells[m][n] == null) return false;
        else if (cells[i][j].player.value == cells[m][n].player.value) return true;
        else return false;
    }

    public boolean hasThreeSameVertical() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (checkEqualCell(0,i,1,i) && checkEqualCell(1,i,2,i)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasThreeSameHorizontal() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (checkEqualCell(i,0,i,1) && checkEqualCell(i,1,i,2)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasThreeSameDiagonal() {
        if (checkEqualCell(0,0,1,1) && checkEqualCell(1,1,2,2)) {
            return true;
        } else if (checkEqualCell(0,2,1,1) && checkEqualCell(2,0,1,1)) {
            return true;
        } else return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (cells[i][j] == null || cells[i][j].isEmpty()) return false;
            }
        }
        return true;
    }

    public void reset() {
        player1 = null;
        player2 = null;
        currentPlayer = null;
        cells = null;
    }
}
