package org.gs.campusparty;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.p2p.WifiP2pManager;

public class Connector {

   private WifiP2pManager manager;
   
   public Connector(Activity a) {
      manager = (WifiP2pManager) a.getSystemService(Context.WIFI_P2P_SERVICE);
      mChannel = mManager.initialize(this, a.getMainLooper(), null);
   }
}
