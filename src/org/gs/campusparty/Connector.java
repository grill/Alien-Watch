package org.gs.campusparty;

import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.util.Log;

public class Connector {

   private WifiP2pManager manager;
   private Channel channel;
   
   public Connector(WifiP2pManager manager, Channel channel) {
      this.manager = manager;
      this.channel = channel;
      
      manager.createGroup(channel, new GroupListener());
   }
   
   public class GroupListener implements WifiP2pManager.ActionListener {

      public void onFailure(int res) {
         Log.w("Oh no...", "" + res);
      }

      public void onSuccess() {
         
      }
      
   }
}
