package com.yf.yfandroidlibs;

import java.util.ArrayList;
import java.util.List;

import com.github.yf_library.guidepage.GuideAdapter;
import com.github.yf_library.guidepage.GuidePageView;
import com.github.yf_library.guidepage.GuidePageStyle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class GuidePageViewDemo extends Activity {

    private GuidePageView mGuidePageView;
    private GuidePageStyle guidePageViewStyle;
    //设置图片
    private static final int[] pics = { R.drawable.guide3,
            R.drawable.guide2, R.drawable.guide1, R.drawable.guide2 };
    private List<View> views,dotViews;
    LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
    RelativeLayout.LayoutParams Params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_page_view_demo);
        mGuidePageView=(GuidePageView)findViewById(R.id.gp_view);

        guidePageViewStyle=mGuidePageView.getGuidePageStyle();
		guidePageViewStyle.setBtnStr("我要进入了");
		guidePageViewStyle.setBtnHeight(200);
        mGuidePageView.setAdapter(new CustomGuideAdapter());

//		mGuidePageView.setData(R.drawable.dot, pics);
//		Toast.makeText(getApplicationContext(), guidePageViewStyle.getBtnStr(), 1).show();
        mGuidePageView.setJumpClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent mIntent=new Intent(GuidePageViewDemo.this,CommonTestActivity.class);
                startActivity(mIntent);
            }
        });
    }

    class CustomGuideAdapter extends GuideAdapter{

        @Override
        public List<View> getIndicator() {

            // TODO Auto-generated method stub
            dotViews = new ArrayList<View>();

            for(int i = 0; i < pics.length; i++){

                ImageView iv=(ImageView)LayoutInflater.from(getApplicationContext()).inflate(R.layout.dot_view_yfdef, null);
                ImageView iv01=new ImageView(getApplicationContext());
                iv01.setBackgroundResource(R.drawable.dot_yfdef);
                iv01.setLayoutParams(mParams);
                mParams.setMargins(20, 20, 20,
                        20);
//
//	        	iv.setEnabled(true);
//	        	iv.setTag(i);//设置位置tag，方便取出与当前位置对应
                dotViews.add(iv);

            }
            return dotViews;
        }

        @Override
        public List<View> getDisplayView() {
            // TODO Auto-generated method stub

            views = new ArrayList<View>();

            for(int i = 0; i < pics.length; i++){
                View view=LayoutInflater.from(getApplicationContext()).inflate(R.layout.img, null);
                view.setBackgroundResource(pics[i]);


                views.add(view);

            }
            return views;


        }




    }

}
