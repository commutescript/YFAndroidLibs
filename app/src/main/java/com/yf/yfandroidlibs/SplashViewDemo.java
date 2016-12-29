package com.yf.yfandroidlibs;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.github.yf_library.splash.OnFinishListener;
import com.github.yf_library.splash.SplashView;

public class SplashViewDemo extends Activity {
    private SplashView sp_view;
    private long t=6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_view_demo);

        sp_view=(SplashView)findViewById(R.id.sp_view);
//		sp_view.setBg(getDrawable(R.drawable.guide1));
        sp_view.setTime(new OnFinishListener() {

            @Override
            public void setFinish() {
                // TODO Auto-generated method stub
                Intent mIntent=new Intent(getApplicationContext(),CommonTestActivity.class);
                startActivity(mIntent);
            }
        });
//		sp_view.setTime(t,new OnFinishListener() {
//
//			@Override
//			public void setFinish() {
//				// TODO Auto-generated method stub
//				Intent mIntent=new Intent(getApplicationContext(),CommonActivity.class);
//				startActivity(mIntent);
//			}
//		});
//		sp_view.count(t);
        sp_view.setJumpClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(SplashViewDemo.this, "点击跳过", 1).show();;
                Intent mIntent=new Intent(getApplicationContext(),CommonTestActivity.class);
                startActivity(mIntent);
            }
        });
    }

}
