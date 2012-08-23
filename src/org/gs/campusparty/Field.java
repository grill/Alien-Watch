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
    public TextView playerchiplabel;
    public int kills;
    
    public Random rand;
    
    public Field() {
        chips = new int[9];
        ghosts = new Ghost[9];
        playerchips = new int[C_COUNT];
        kills = 0;
        
        for(int i = 0; i < 9; i++) {
            chips[i] = C_NONE;
            ghosts[i] = new Ghost();
        }

        rand = new Random();
        
        ghosts[7].time=20;
        ghosts[4].time=30;
        chips[1] = C_BLUE;
        chips[3] = C_RED;
        
        for(int i = 0; i < C_COUNT; i++) {
            playerchips[i] = 1;
        }
    }
    
    private static Field instance;
    
    public static Field nuke() {
        instance = new Field();
        return instance;
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
