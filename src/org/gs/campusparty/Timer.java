package org.gs.campusparty;

import android.os.Handler;
import android.os.SystemClock;

public class Timer {
   private Handler mHandler = new Handler();
   private Field field;
   
   public static final int MIN_GHOST_TIME = 20;
   public static final int MAX_GHOST_TIME = 40;
   
   private int timeUntilGhost = -1;
   
   
   public Timer() {
      field = Field.getSingleton();
   }
   
   private Runnable mUpdateTimeTask = new Runnable() {
      public void run() {
          long millis = SystemClock.uptimeMillis();
          int nghost;

          //update time and textview
          for(Ghost g : field.ghosts) {
              if(g.time >= 0){
                  g.step();
              }
          }
          if(timeUntilGhost >= 0) {
             timeUntilGhost -= 1;
          } else {
             nghost = field.rand.nextInt(9);
             while(field.ghosts[nghost].time >= 0) {
                nghost = field.rand.nextInt(9);
             }
             
             timeUntilGhost = MIN_GHOST_TIME + field.rand.nextInt(MAX_GHOST_TIME-MIN_GHOST_TIME);
          }
          

          mHandler.postAtTime(this, millis + 1000);
      }
   };
   
   
   public void start() {
      mHandler.removeCallbacks(mUpdateTimeTask);
      mHandler.postDelayed(mUpdateTimeTask, 100);
   }
   
   public void stop() {
      mHandler.removeCallbacks(mUpdateTimeTask);
   }
}
