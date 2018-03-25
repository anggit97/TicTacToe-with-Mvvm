package com.anggitprayogo.mvvmlatihan.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.anggitprayogo.mvvmlatihan.R;

/**
 * Created by Personal on 25/03/18.
 */

public class GameEndDialog extends DialogFragment {


    private View rootView;
    private MainActivity activity;
    private String winnerName;


    public static GameEndDialog newInstance(MainActivity activity, String winnerName) {
        GameEndDialog dialog = new GameEndDialog();
        dialog.activity = activity;
        dialog.winnerName = winnerName;
        return dialog;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setCancelable(false)
                .setPositiveButton(R.string.done, ((dialog, which) -> onNewGame()))
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        return alertDialog;
    }


    private void initViews() {
        rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.game_end_dialog, null, false);
        ((TextView) rootView.findViewById(R.id.tv_winner)).setText(winnerName);
    }


    private void onNewGame() {
        dismiss();
        activity.promptForPlayers();
    }
}