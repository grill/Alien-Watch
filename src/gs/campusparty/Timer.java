package gs.campusparty;

import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;

public class Timer {
   private Handler mHandler = new Handler();
   
   public Timer() { }
   
   private Runnable mUpdateTimeTask = new Runnable() {
      public void run() {
          long millis = SystemClock.uptimeMillis();

          //update time and textview
          for(Ghost g : Field.getSingleton().ghosts) {
              if(g.time >= 0){ 
                  g.label.setText(Integer.toString(g.time));
                  g.time -= 1;
              }
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
