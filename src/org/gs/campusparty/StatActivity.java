package org.gs.campusparty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpRequestFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.gs.campusparty.R;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class StatActivity extends Activity {
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
        
        Timer t = new Timer();
        t.start();
        
        new Thread(new Runnable() {
            AndroidHttpClient c = AndroidHttpClient.newInstance("");
            HttpResponse r = null;
            byte[] buffer = new byte[2048];
       
            
            public void run() {
        
        Intent intent = getIntent();
        ((TextView)findViewById(R.id.txtplayerdesc)).setText(intent.getExtras() == null ? "b" : "" + intent.getExtras().size());
        //intent.putExtra("FlutterTapId", "50360633ad5c85b82c74d27f");
        if (intent.hasExtra("FlutterTapId")) {
            String tapId = intent.getStringExtra("FlutterTapId");
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
            
            
        }
        c.close();
            }
        }).start();
    }
}