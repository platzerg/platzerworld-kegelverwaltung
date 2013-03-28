package com.platzerworld.biergarten.activity;

import com.platzerworld.biergarten.R;
import com.platzerworld.biergarten.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;

public class SplashActivity extends Activity {
	
	/** unique identifier for logging */
	private static final String TAG = SplashActivity.class.getSimpleName();
	
	protected boolean active = true;
    protected int splashTime = 2000;
    
   //--- section of activity callback methods    ---- 
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                
        setContentView(R.layout.activity_splash);
        	Log.d(TAG, "Activity created"); 

        
        // thread for displaying the SplashScreen
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while(active && (waited < splashTime)) {
                        sleep(100);
                        if(active) {
                            waited += 100;
                        }
                    }
                } catch(InterruptedException e) {
                    // do nothing
                } finally {
                	// finsh SplashScreen and start MainActivity 
                    finish();
                    startActivity(new Intent("com.platzerworld.biergarten.MAIN_ACTIVITY"));
                    // stop(); // deprecated
                }
            }
        };
        splashTread.start();
    }
    
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            active = false;
        }
        return true;
    }
}