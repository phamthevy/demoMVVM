package com.example.vypt.demomvvm.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayMap;

import com.example.vypt.demomvvm.model.Cell;
import com.example.vypt.demomvvm.model.Game;
import com.example.vypt.demomvvm.model.Player;


public class GameViewModel extends ViewModel {
    public ObservableArrayMap<String, String> cells;
    private Game game;

    public void init(String player1, String player2) {
        game = new Game(player1, player2);
        cells = new ObservableArrayMap<>();
    }

    public LiveData<Player> getWinner() {
        return game.winner;
    }

    public static String stringFromNumbers(int... numbers) {
        StringBuilder sNumbers = new StringBuilder();
        for (int number : numbers)
            sNumbers.append(number);
        return sNumbers.toString();
    }

    public void onClickedCellAt(int row, int col) {
        if (game.cells[row][col] == null) {
            game.cells[row][col] = new Cell(game.currentPlayer);

            cells.put(stringFromNumbers(row, col), game.currentPlayer.value);
            if (game.hasEndedGame()) {
                game.reset();
            } else {
                game.switchPlayer();
            }
        }
    }

}
