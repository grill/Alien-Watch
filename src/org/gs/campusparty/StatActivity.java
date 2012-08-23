package org.gs.campusparty;

import org.gs.campusparty.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class StatActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Field f = Field.getSingleton();
        f.ghosts[0].label = (TextView) findViewById(R.id.txtfield0time);
        f.ghosts[1].label = (TextView) findViewById(R.id.txtfield1time);
        f.ghosts[2].label = (TextView) findViewById(R.id.txtfield2time);
        f.ghosts[3].label = (TextView) findViewById(R.id.txtfield3time);
        f.ghosts[4].label = (TextView) findViewById(R.id.txtfield4time);
        f.ghosts[5].label = (TextView) findViewById(R.id.txtfield5time);
        f.ghosts[6].label = (TextView) findViewById(R.id.txtfield6time);
        f.ghosts[7].label = (TextView) findViewById(R.id.txtfield7time);
        f.ghosts[8].label = (TextView) findViewById(R.id.txtfield8time);
        
        Timer t = new Timer();
        t.start();
        
        Intent intent = getIntent();
        if (intent.hasExtra("FlutterTapId")) {
            String tapId = intent.getStringExtra("FlutterTapId");
        }
    }
}