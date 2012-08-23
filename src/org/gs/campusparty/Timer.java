package org.gs.campusparty;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Timer {
	private Handler mHandler = new Handler();

	public static final int MIN_GHOST_TIME = 20;
	public static final int MAX_GHOST_TIME = 40;

	private int timeUntilGhost = -1;

	public static final int MIN_CHIP_TIME = 5;
	public static final int MAX_CHIP_TIME = 10;

	private int timeUntilChip = -1;

	public static final int FIELDS_UNTIL_YOU_DIE = 3;

	public LinearLayout layout;
	public Context context;

	TextView txtend;
	Button btnend;

	private long startTime;
	
	private boolean started = false;

	public Timer() {
	}

	private Runnable mUpdateTimeTask = new Runnable() {
		public void run() {
		    Field field = Field.getSingleton();
			long millis = SystemClock.uptimeMillis();
			int nghost;
			int nchip;
			int free = 0;
			int ghostcount = 0;

			// update time and textview
			for (Ghost g : field.ghosts) {
				if (g.time > 0) {
					g.step();
				}else if(g.time == 0) {
					ghostcount++;
				} else {
					g.chiplabel.setText("NO GHOST");
					g.label.setText("");
				}
			}
			if (FIELDS_UNTIL_YOU_DIE <= ghostcount) {
				int seconds = (int) (System.currentTimeMillis() - startTime) / 1000;
				int minutes = seconds / 60;
				end("You Lost!!\n You survived for: " + minutes + ":"
						+ (seconds - (minutes * 60)) + "!");
			} else {
				if (timeUntilGhost >= 0) {
					timeUntilGhost -= 1;
				} else {
					Log.w("aa", "1");
					free = 0;
					for (int i = 0; i < 9; i++) {
						if (field.ghosts[i].time < 0) {
							free += 1;
						}
					}
					Log.w("aa", "1");
					if (free > 0) {
						nghost = field.rand.nextInt(free);
						int h = 0;
						while (field.ghosts[h].time >= 0) {
							h++;
						}
						while (nghost > 0) {
							h++;
							while (field.ghosts[h].time >= 0) {
								h++;
							}
							nghost--;
						}
						nghost = h;

						Log.w("aa", "1");
						field.ghosts[nghost].time = 40;
						for (int i = 0; i < field.C_COUNT; i++) {
							field.ghosts[nghost].chips[i] = 0;
						}

						for (int i = 3 + field.rand.nextInt(3); i >= 0; i--) {
							field.ghosts[nghost].chips[field.rand
									.nextInt(field.C_COUNT)] += 1;
						}
					}

					timeUntilGhost = MIN_GHOST_TIME
							+ field.rand.nextInt(MAX_GHOST_TIME
									- MIN_GHOST_TIME) - field.kills*2;
				}
				if (timeUntilChip >= 0) {
					timeUntilChip -= 1;
				} else {
					Log.w("aa", "1");
					free = 0;
					for (int i = 0; i < 9; i++) {
						if (field.chips[i] == Field.C_NONE) {
							free += 1;
						}
					}
					Log.w("aa", "1");
					if (free > 0) {
						nchip = field.rand.nextInt(free);
						int h = 0;
						while (field.chips[h] != Field.C_NONE) {
							h++;
						}
						while (nchip > 0) {
							h++;
							while (field.chips[h] != Field.C_NONE) {
								h++;
							}
							nchip--;
						}
						nchip = h;
						Log.w("aa", "1");

						field.chips[nchip] = field.rand.nextInt(field.C_COUNT);
					}
					timeUntilChip = MIN_CHIP_TIME
							+ field.rand.nextInt(MAX_CHIP_TIME - MIN_CHIP_TIME) + field.kills;
				}

				for (int i = 0; i < 9; i++) {
					field.ghosts[i].label.setTextColor(Field
							.getColor(field.chips[i]));
					field.ghosts[i].chiplabel.setTextColor(Field
							.getColor(field.chips[i]));
				}

				String chiptext = "";
				for (int i = 0; i < Field.C_COUNT; i++) {
					for (int j = 0; j < field.playerchips[i]; j++) {
						chiptext += Field.names[i];
					}
				}
				field.playerchiplabel.setText(chiptext);

				mHandler.postAtTime(this, millis + 1000);
			}
		}
	};

	public void end(String mes) {
		txtend = new TextView(context);
		layout.addView(txtend);
		txtend.setText(mes);
		btnend = new Button(context);
		layout.addView(btnend);
		btnend.setText("Restart");
		btnend.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				started = false;
				layout.removeView(txtend);
				layout.removeView(btnend);
				Field.getSingleton().nuke();
				StatActivity.t.start();
			}
		});
	}

	public void start() {
		if(!started) {
			started = true;
			startTime = System.currentTimeMillis();
			mHandler.removeCallbacks(mUpdateTimeTask);
			mHandler.postDelayed(mUpdateTimeTask, 100);
		}
	}

	public void stop() {
		mHandler.removeCallbacks(mUpdateTimeTask);
	}
}
