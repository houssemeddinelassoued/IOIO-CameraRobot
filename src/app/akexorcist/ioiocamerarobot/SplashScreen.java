package app.akexorcist.ioiocamerarobot;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SplashScreen extends Activity {
	int width, height;
	boolean state = false;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.RGBA_8888);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN 
        		| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); 
		overridePendingTransition(0, 0);
		setContentView(R.layout.splashscreen);
		Display display = getWindowManager().getDefaultDisplay(); 
        width = display.getWidth();
        height = display.getHeight();
        
        LinearLayout layout = (LinearLayout)findViewById(R.id.layout);
        layout.setVisibility(View.INVISIBLE);
        
        AnimationSet as = new AnimationSet(true);
        Animation aa = new AlphaAnimation(0, 1);
		aa.setDuration(300);
		aa.setStartOffset(3700);
		as.addAnimation(aa);
		as.setFillEnabled(true);
		as.setFillAfter(true);
		as.setInterpolator(new DecelerateInterpolator());
		as.setStartTime(0);
		layout.startAnimation(as);
		layout.setVisibility(View.VISIBLE);
        
        ImageView imageSplash = (ImageView)findViewById(R.id.imageSplash);
		imageSplash.setVisibility(View.INVISIBLE);
		
		as = new AnimationSet(true);
        aa = new ScaleAnimation((float)0.6
        		, (float)0.5, (float)0.6, (float)0.5
				, width / 2
				, height / 2);
		aa.setDuration(2000);
		aa.setStartOffset(1000);
		as.addAnimation(aa);
		aa = new AlphaAnimation(0, 1);
		aa.setDuration(2000);
		aa.setStartOffset(1000);
		as.addAnimation(aa);
		aa = new AlphaAnimation(1, 0);
		aa.setDuration(500);
		aa.setStartOffset(3200);
		as.addAnimation(aa);
		as.setFillEnabled(true);
		as.setFillAfter(true);
		as.setInterpolator(new DecelerateInterpolator());
		as.setStartTime(1000);
		imageSplash.startAnimation(as);
		imageSplash.setVisibility(View.VISIBLE);
		Runnable runnable = new Runnable() {
			public void run() {
				try {
					Thread.sleep(4100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(!state) {
					Intent go = new Intent(getApplicationContext(), Main.class);
					startActivity(go);
				}
				SplashScreen.this.finish();
				
			}
		};
		new Thread(runnable).start();
	}
    
    public void onPause() {
    	super.onPause();
    	state = true;
    }
}
