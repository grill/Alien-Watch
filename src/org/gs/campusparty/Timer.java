package org.gs.campusparty;

import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;

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
          int free = 0;

          //update time and textview
          for(Ghost g : field.ghosts) {
              if(g.time >= 0){
                  g.step();
              } else {
                  g.chiplabel.setText("NO GHOST");
                  g.label.setText("");
              }
          }
          if(timeUntilGhost >= 0) {
             timeUntilGhost -= 1;
          } else {
              Log.w("aa", "1");
              free = 0;
              for(int i = 0; i < 9; i++) {
                  if(field.ghosts[i].time < 0) {
                      free += 1;
                  }
              }
              Log.w("aa", "1");
              if(free > 0) {
             nghost = field.rand.nextInt(free);
             int h = 0;
             while(field.ghosts[h].time >= 0) {
                 h++;
             }
             while(nghost > 0) {
                 h++;
                 while(field.ghosts[h].time >= 0) {
                     h++;
                 }
                 nghost--;
             }
             nghost = h;
             
              Log.w("aa", "1");
             field.ghosts[nghost].time = 40;
             for(int i = 0; i < field.C_COUNT; i++) {
                 field.ghosts[nghost].chips[i] = 0;
             }
             
             for(int i = 3 + field.rand.nextInt(3); i >= 0; i--) {
                 field.ghosts[nghost].chips[field.rand.nextInt(field.C_COUNT)] += 1;
             }
              }
             
             timeUntilGhost = MIN_GHOST_TIME + field.rand.nextInt(MAX_GHOST_TIME-MIN_GHOST_TIME);
          }
          if(timeUntilChip >= 0) {
              timeUntilChip -= 1;
          } else {
              Log.w("aa", "1");
              free = 0;
              for(int i = 0; i < 9; i++) {
                  if(field.chips[i] == Field.C_NONE) {
                      free += 1;
                  }
              }
              Log.w("aa", "1");
              if(free > 0) {
             nchip = field.rand.nextInt(free);
             int h = 0;
             while(field.chips[h] != Field.C_NONE) {
                 h++;
             }
             while(nchip > 0) {
                 h++;
                 while(field.chips[h] != Field.C_NONE) {
                     h++;
                 }
                 nchip--;
             }
             nchip = h;
              Log.w("aa", "1");
 
              field.chips[nchip] = field.rand.nextInt(field.C_COUNT);
              }
              timeUntilChip = MIN_CHIP_TIME + field.rand.nextInt(MAX_CHIP_TIME-MIN_CHIP_TIME);
          }
          
          for(int i = 0; i < 9; i++) {
             field.ghosts[i].label.setTextColor(Field.getColor(field.chips[i]));
             field.ghosts[i].chiplabel.setTextColor(Field.getColor(field.chips[i]));
          }
          
        String chiptext = "";
        for(int i = 0; i < Field.C_COUNT; i++) {
            for(int j = 0; j < field.playerchips[i]; j++) {
                chiptext += Field.names[i];
            }
        }
        field.playerchiplabel.setText(chiptext);

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
