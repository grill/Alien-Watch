package gs.campusparty;


public class Field {
    public static final int C_NONE = -1;
    public static final int C_RED = 0;
    public static final int C_BLUE = 1;
    public static final int C_GREEN = 2;
    public static final int C_YELLOW = 3;
    public static final int C_COUNT = 4;
    
    public int[] chips;
    public Ghost[] ghosts;
    
    public Field() {
        chips = new int[9];
        ghosts = new Ghost[9];
        
        for(int i = 0; i < 9; i++) {
            chips[i] = C_NONE;
            ghosts[i] = new Ghost();
        }
        
        ghosts[0].label = "txtfield0time";
        ghosts[1].label = "txtfield1time";
        ghosts[2].label = "txtfield2time";
        ghosts[3].label = "txtfield3time";
        ghosts[4].label = "txtfield4time";
        ghosts[5].label = "txtfield5time";
        ghosts[6].label = "txtfield6time";
        ghosts[7].label = "txtfield7time";
        ghosts[8].label = "txtfield8time";
        
        ghosts[7].time=20;
        ghosts[4].time=30;
    }
    
    private static Field instance;
    
    public static Field getSingleton() {
        if(instance != null) {
            return instance;
        } else {
            return new Field();
        }
    }
}
