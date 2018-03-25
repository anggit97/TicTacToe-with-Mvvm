package com.anggitprayogo.mvvmlatihan.model;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

/**
 * Created by Personal on 25/03/18.
 */

public class Game {

    private final String TAG = getClass().getSimpleName();
    private static final int BOARD_SIZE = 3;//Ukuran dari board adalah 3 x 3

    public Player player1, player2; //akan ada 2 pemain

    public Player currentPlayer = player1; //inisialisasi player yang berjalan duluan adalah player 1
    public MutableLiveData<Player> winner = new MutableLiveData<>(); //karena blum mulai, maka winner di setting null dulu

    public Cell[][] cells; // karena tictactoe akan mainannya seperti papan, maka diperlukan array dimensi untuk merepesentasikannya

    //construktor diisi dengan inisialisasi array cell dengan size == BOARD_SIZE
    public Game(String playerOne, String playerTwo){
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        player1 = new Player(playerOne, "x");
        player2 = new Player(playerTwo, "o");
        currentPlayer = player1;
    }

    //method untuk ganti giliran main setiap ronde
    public void switchPlayer(){
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    //Menentukan apakah game berakhir
    public boolean hasGameEnded() {
        if (hasThreeSameHorizontalCells() || hasThreeSameVerticalCells() || hasThreeSameDiagonalCells()) {
            winner.setValue(currentPlayer);
            return true;
        }

        if (isBoardFull()) {
            winner = null;
            return true;
        }

        return false;
    }

    public boolean hasThreeSameHorizontalCells() {
        try {
            for (int i = 0; i < BOARD_SIZE; i++)
                if (areEqual(cells[i][0], cells[i][1], cells[i][2]))
                    return true;

            return false;
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }


    public boolean hasThreeSameVerticalCells() {
        try {
            for (int i = 0; i < BOARD_SIZE; i++)
                if (areEqual(cells[0][i], cells[1][i], cells[2][i]))
                    return true;
            return false;
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }


    public boolean hasThreeSameDiagonalCells() {
        try {
            return areEqual(cells[0][0], cells[1][1], cells[2][2]) ||
                    areEqual(cells[0][2], cells[1][1], cells[2][0]);
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }


    public boolean isBoardFull() {
        for (Cell[] row : cells)
            for (Cell cell : row)
                if (cell == null ||cell.isEmpty())
                    return false;
        return true;
    }
    /**
     * 2 cells are equal if:
     * - Both are none null
     * - Both have non null values
     * - both have equal values
     *
     * @param cells: Cells to check if are equal
     * @return
     */
    private boolean areEqual(Cell... cells) {
        if (cells == null || cells.length == 0)
            return false;

        for (Cell cell : cells)
            if (cell == null || cell.player.value == null || cell.player.value.length() == 0)
                return false;

        Cell comparisonBase = cells[0];
        for (int i = 1; i < cells.length; i++)
            if (!comparisonBase.player.value.equals(cells[i].player.value))
                return false;

        return true;
    }

    // Setelah game brakhir, akan direset lagi ke awal
    public void reset() {
        player1 = null;
        player2 = null;
        currentPlayer = null;
        cells = null;
    }
}
