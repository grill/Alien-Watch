package org.gs.campusparty;

import java.util.Random;


public class Field {
    public static final int C_NONE = -1;
    public static final int C_RED = 0;
    public static final int C_BLUE = 1;
    public static final int C_GREEN = 2;
    public static final int C_YELLOW = 3;
    public static final int C_COUNT = 4;
    
    public int[] chips;
    public Ghost[] ghosts;
    
    public Random rand;
    
    public Field(long seed) {
        chips = new int[9];
        ghosts = new Ghost[9];
        
        for(int i = 0; i < 9; i++) {
            chips[i] = C_NONE;
            ghosts[i] = new Ghost();
        }

        rand = new Random(seed);
        
        ghosts[7].time=20;
        ghosts[4].time=30;
    }
    
    private static Field instance;
    
    public static Field init(long seed) {
       instance = new Field(seed);
       return instance;
    }
    
    public static Field getSingleton() {
       return instance;
    }
}