package gs.campusparty;

import android.widget.TextView;

public class Ghost {
    int time;
    int[] chips;
    TextView label;
    
    public Ghost() {
        time = -1;
        chips = new int[Field.C_COUNT];
    }
}
