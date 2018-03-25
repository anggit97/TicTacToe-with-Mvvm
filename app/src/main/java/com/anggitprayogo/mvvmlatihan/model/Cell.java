package com.anggitprayogo.mvvmlatihan.model;

import com.anggitprayogo.mvvmlatihan.utilities.StringUtility;

/**
 * Created by Personal on 25/03/18.
 */

public class Cell {

    public Player player;

    public Cell(Player player) {
        this.player = player;
    }

    public boolean isEmpty() {
        return player == null || StringUtility.isNullOrEmpty(player.value);
    }
}
