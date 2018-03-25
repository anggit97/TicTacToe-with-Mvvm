package com.anggitprayogo.mvvmlatihan.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayMap;
import android.util.Log;

import com.anggitprayogo.mvvmlatihan.model.Cell;
import com.anggitprayogo.mvvmlatihan.model.Game;
import com.anggitprayogo.mvvmlatihan.model.Player;
import com.anggitprayogo.mvvmlatihan.utilities.StringUtility;

/**
 * Created by Personal on 25/03/18.
 */

public class GameViewModel extends ViewModel{

    public ObservableArrayMap<String, String> cells = new ObservableArrayMap<>();
    private Game game;

    public void init(String player1, String player2) {
        game = new Game(player1, player2);
    }

    public void onClickedCellAt(int row, int column) {
        if (game.cells[row][column] == null) {
            game.cells[row][column] = new Cell(game.currentPlayer);
            cells.put(StringUtility.stringFromNumber(row, column), game.currentPlayer.value);
            if (game.hasGameEnded()) {
                Log.d("RESET", "onClickedCellAt: ");
                game.reset();
            } else {
                Log.d("TIDAK RESET", "onClickedCellAt: ");
                game.switchPlayer();
            }
        }
    }

    public LiveData<Player> getWinner() {
        return game.winner;
    }

}
