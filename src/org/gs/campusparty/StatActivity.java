package org.gs.campusparty;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class StatActivity extends Activity {
    private static Timer t = null;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Field f = Field.init(0L);
        f.ghosts[0].label = (TextView) findViewById(R.id.txtfield0time);
        f.ghosts[1].label = (TextView) findViewById(R.id.txtfield1time);
        f.ghosts[2].label = (TextView) findViewById(R.id.txtfield2time);
        f.ghosts[3].label = (TextView) findViewById(R.id.txtfield3time);
        f.ghosts[4].label = (TextView) findViewById(R.id.txtfield4time);
        f.ghosts[5].label = (TextView) findViewById(R.id.txtfield5time);
        f.ghosts[6].label = (TextView) findViewById(R.id.txtfield6time);
        f.ghosts[7].label = (TextView) findViewById(R.id.txtfield7time);
        f.ghosts[8].label = (TextView) findViewById(R.id.txtfield8time);
        f.ghosts[0].chiplabel = (TextView) findViewById(R.id.txtfield0chips);
        f.ghosts[1].chiplabel = (TextView) findViewById(R.id.txtfield1chips);
        f.ghosts[2].chiplabel = (TextView) findViewById(R.id.txtfield2chips);
        f.ghosts[3].chiplabel = (TextView) findViewById(R.id.txtfield3chips);
        f.ghosts[4].chiplabel = (TextView) findViewById(R.id.txtfield4chips);
        f.ghosts[5].chiplabel = (TextView) findViewById(R.id.txtfield5chips);
        f.ghosts[6].chiplabel = (TextView) findViewById(R.id.txtfield6chips);
        f.ghosts[7].chiplabel = (TextView) findViewById(R.id.txtfield7chips);
        f.ghosts[8].chiplabel = (TextView) findViewById(R.id.txtfield8chips);
       
        if(t == null) {
            Timer t = new Timer();
            t.start();
        }
        

        Intent intent = getIntent();
        ((TextView)findViewById(R.id.txtplayerdesc)).setText(intent.getExtras() == null ? "b" : "" + intent.getExtras().size());
        //intent.putExtra("FlutterTapId", "50360633ad5c85b82c74d27f");
        ((TextView)findViewById(R.id.txtfield1chips)).setText(intent.getAction());
        if (intent.hasExtra("FlutterTapId")) {
            tap(intent.getStringExtra("FlutterTapId"));
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }
    
    

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Log.w("asdf", "onPause");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // TODO Auto-generated method stub
        super.onNewIntent(intent);
        ((TextView)findViewById(R.id.txtfield1chips)).setText(intent.getAction());
        ((TextView)findViewById(R.id.txtfield2chips)).setText("blub");

        if (intent.hasExtra("FlutterTapId")) {
            tap(intent.getStringExtra("FlutterTapId"));
        }
        
    }
    
    private void tap(final String tapId) {
        new Thread(new Runnable() {
            AndroidHttpClient c = AndroidHttpClient.newInstance("");
            HttpResponse r = null;
            byte[] buffer = new byte[2048];
       
            
            public void run() {
            
            int fieldid = 0;
            try {
                r = c.execute(new HttpGet("https://bluebutterflyapi.cloudfoundry.com/api/taps/"+tapId));
                r.getEntity().getContent().read(buffer);
                String json = new String(buffer);
                           
                Log.w("a", ""+json.length());
                Log.w("a", json);
                JSONObject jo = new JSONObject(json);
                fieldid = jo.getInt("lat");
                Field.getSingleton().ghosts[fieldid].time = 30;
                
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        c.close();
            }
        }).start();
    }
    
    
}