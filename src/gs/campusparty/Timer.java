package gs.campusparty;

import android.os.Handler;
import android.os.SystemClock;

public class Timer {
   private Handler mHandler = new Handler();
   
   public Timer() { }
   
   private Runnable mUpdateTimeTask = new Runnable() {
      public void run() {
          long millis = SystemClock.uptimeMillis();

          //update time and textview

          mHandler.postAtTime(this, millis + 1);
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
