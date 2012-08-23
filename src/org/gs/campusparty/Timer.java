package org.gs.campusparty;

import android.os.Handler;
import android.os.SystemClock;

public class Timer {
   private Handler mHandler = new Handler();
   private Field field;
   
   public static final int MIN_GHOST_TIME = 20;
   public static final int MAX_GHOST_TIME = 40;
   
   private int timeUntilGhost = -1;
   
   public static final int MIN_CHIP_TIME = 5;
   public static final int MAX_CHIP_TIME = 10;
   
   private int timeUntilChip = -1;
   
   
   public Timer() {
      field = Field.getSingleton();
   }
   
   private Runnable mUpdateTimeTask = new Runnable() {
      public void run() {
          long millis = SystemClock.uptimeMillis();
          int nghost;
          int nchip;

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
             
             field.ghosts[nghost].time = 40;
             for(int i = 0; i < field.C_COUNT; i++) {
                 field.ghosts[nghost].chips[i] = 0;
             }
             
             for(int i = 3 + field.rand.nextInt(3); i >= 0; i--) {
                 field.ghosts[nghost].chips[field.rand.nextInt(field.C_COUNT)] += 1;
             }
             
             timeUntilGhost = MIN_GHOST_TIME + field.rand.nextInt(MAX_GHOST_TIME-MIN_GHOST_TIME);
          }
          if(timeUntilChip >= 0) {
              timeUntilChip -= 1;
          } else {
              nchip = field.rand.nextInt(9);
              
          }
          
          for(int i = 0; i < 9; i++) {
             field.ghosts[i].label.setTextColor(Field.getColor(field.chips[i]));
             field.ghosts[i].chiplabel.setTextColor(Field.getColor(field.chips[i]));
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
