package com.example.vypt.demomvvm.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.vypt.demomvvm.MainActivity;
import com.example.vypt.demomvvm.R;

public class GameBeginDialog extends DialogFragment {

    private TextInputLayout player1Layout;
    private TextInputLayout player2Layout;

    private TextInputEditText player1EditText;
    private TextInputEditText player2EditText;

    private String player1;
    private String player2;
    private View rootView;
    private MainActivity activity;

    public static GameBeginDialog newInstant(MainActivity activity) {
        GameBeginDialog dialog = new GameBeginDialog();
        dialog.activity = activity;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle saveInstantState) {
        initViews();
        final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setTitle(R.string.game_dialog_title)
                .setCancelable(false)
                .setPositiveButton("DONE", null)
                .create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                onDialogShow(alertDialog);
            }
        });
        return alertDialog;
    }

    private void initViews() {
        rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.activity_game_begin_dialog, null, false);

        player1Layout = rootView.findViewById(R.id.layout_player1);
        player2Layout = rootView.findViewById(R.id.layout_player2);

        player1EditText = rootView.findViewById(R.id.et_player1);
        player2EditText = rootView.findViewById(R.id.et_player2);
        addTextWatchers();
    }

    public void onDialogShow(AlertDialog dialog) {
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(v -> {
            if (isValidName(player1Layout, player1) & isValidName(player2Layout, player2)) {
                activity.onPlayerSet(player1, player2);
                dismiss();
            }
        });
    }

    private boolean isValidName(TextInputLayout layout, String name) {
        if (TextUtils.isEmpty(name)) {
            layout.setErrorEnabled(true);
            layout.setError("Name mustn't be empty");
            return false;
        }
        if (player1 != null && player2 != null && player1.equalsIgnoreCase(player2)) {
            layout.setError("Name mustn't be same");
            layout.setErrorEnabled(true);
            return false;
        }
        layout.setErrorEnabled(false);
        layout.setError("");
        return true;
    }

    private void addTextWatchers() {
        player1EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                player1 = s.toString();
            }
        });

        player2EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                player2 = s.toString();
            }
        });
    }
}
