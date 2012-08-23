package org.gs.campusparty;

import java.util.Random;

import android.graphics.Color;
import android.widget.TextView;


public class Field {
    public static final int C_NONE = -1;
    public static final int C_RED = 0;
    public static final int C_BLUE = 1;
    public static final int C_GREEN = 2;
    public static final int C_YELLOW = 3;
    public static final int C_COUNT = 4;
    
    public static final int[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
    public static final char[] names = {'R', 'B', 'G', 'Y'};
    
    public static int getColor(int i) {
        if(i == -1) {
            return Color.WHITE;
        } else {
            return colors[i];
        }
    }
    
    public int[] chips;
    public Ghost[] ghosts;
    public int[] playerchips;
    public TextView[] playerchiplabels;
    public int kills;
    
    public Random rand;
    
    public Field() {
        chips = new int[9];
        ghosts = new Ghost[9];
        playerchips = new int[C_COUNT];
        playerchiplabels = new TextView[C_COUNT];
        kills = 0;
        
        for(int i = 0; i < 9; i++) {
            chips[i] = C_NONE;
            ghosts[i] = new Ghost();
        }

        rand = new Random();
        
        for(int i = 0; i < C_COUNT; i++) {
            playerchips[i] = 1;
        }
    }
    
    private static Field instance;
    
    public void nuke() {
        chips = new int[9];
        for(int i = 0; i < 9; i++) {
            chips[i] = C_NONE;
        }
        for(Ghost g : ghosts) {
            g.time = -1;
        }
        
        for(int i = 0; i < C_COUNT; i++) {
            playerchips[i] = 1;
        }
        kills = 0;
    }
    
    public static Field init() {
        if(instance == null) {
            instance = new Field();
        }
        return instance;
        
    }
    
    public static Field getSingleton() {
        return instance;
    }
}
