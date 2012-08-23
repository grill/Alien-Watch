package org.gs.campusparty;

import android.widget.TextView;

public class Ghost {
    int time;
    int[] chips;
    TextView label;
    
    public Ghost() {
        time = -1;
        chips = new int[Field.C_COUNT];
    }
    
    public void step() {
       label.setText(Integer.toString(time));
       time -= 1;
    }
}
