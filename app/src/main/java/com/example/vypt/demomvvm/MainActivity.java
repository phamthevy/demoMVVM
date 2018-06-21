package com.example.vypt.demomvvm;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.example.vypt.demomvvm.databinding.ActivityMainBinding;
import com.example.vypt.demomvvm.model.Player;
import com.example.vypt.demomvvm.view.GameBeginDialog;
import com.example.vypt.demomvvm.view.GameEndDialog;
import com.example.vypt.demomvvm.viewmodel.GameViewModel;


public class MainActivity extends AppCompatActivity {

    private static final String NO_WINNER = "No winner";
    private GameViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        promptForPlayer();
    }

    public void promptForPlayer(){
        GameBeginDialog dialog = GameBeginDialog.newInstant(this);
        dialog.show(getSupportFragmentManager(), "game_start");
    }

    public void onPlayerSet(String player1, String player2){
        initDataBiding(player1, player2);
    }

    private void initDataBiding(String player1, String player2){
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        gameViewModel.init(player1, player2);
        activityMainBinding.setGameViewModel(gameViewModel);
        setUpGameEnd();
    }

    public void setUpGameEnd(){
        gameViewModel.getWinner().observe(this,this::onGameWinnerChange);
    }

    @VisibleForTesting
    public void onGameWinnerChange(Player winner){
        String winnerName = (winner == null || TextUtils.isEmpty(winner.name)) ? NO_WINNER : winner.name;
        GameEndDialog dialog = GameEndDialog.newInstant(this, winnerName);
        dialog.show(getSupportFragmentManager(), "GAME_END");
    }
}
