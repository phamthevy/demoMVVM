package com.example.vypt.demomvvm.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.vypt.demomvvm.MainActivity;
import com.example.vypt.demomvvm.R;

public class GameEndDialog extends DialogFragment {
    private View rootView;
    private MainActivity activity;
    private String winnerName;

    public static GameEndDialog newInstant(MainActivity activity, String winnerName){
        GameEndDialog dialog = new GameEndDialog();
        dialog.activity = activity;
        dialog.winnerName = winnerName;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle saveInstantState){
        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setCancelable(false)
                .setPositiveButton("DONE", ((dialog, which) -> onNewGame()))
                .create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        return alertDialog;
    }

    public void initViews(){
        rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.activity_game_end_dialog, null, false);
        ((TextView) rootView.findViewById(R.id.tv_winner)).setText(winnerName);
    }

    public void onNewGame(){
        dismiss();
        activity.promptForPlayer();
    }
}
