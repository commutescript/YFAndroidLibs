package com.yf.yfandroidlibs;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.github.yf_library.tab.TabView;
import com.github.yf_library.tab.TabViewStyle;

import java.util.ArrayList;
import java.util.List;

public class TabViewDemo extends Activity {


    LinearLayout ll;
    TabView mTabView;
    private String[] txt={
            "我的","你的","他的","其他的"
    };

    private int[] pics={
            R.drawable.shop_normal,	R.drawable.shop_normal,	R.drawable.shop_normal,R.drawable.shop_normal
    };

    private List<Intent> mList;
    private ArrayList<View> actvity_list;
    private TabViewStyle mTabViewStyle;

    private Intent mIntent;
    @SuppressWarnings("deprecation")
    LocalActivityManager manager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_view_demo);

        manager = new LocalActivityManager(this, true);
        manager.dispatchCreate(savedInstanceState);


//		ll=(LinearLayout)findViewById(R.id.ll);
//		ll.setWeightSum(4.0f);
        mTabView=(TabView)findViewById(R.id.tab_view);


        initView();
    }

    private void initView(){
        mList=new ArrayList<Intent>();
        mIntent=new Intent(this,SplashViewDemo.class);
        mList.add(mIntent);
        Intent intent02=new Intent(this,GuidePageViewDemo.class);
        mList.add(intent02);
        Intent intent03=new Intent(this,SearchViewDemo.class);
        mList.add(intent03);
        Intent intent04=new Intent(this,BannerViewDemo.class);
        mList.add(intent04);

        actvity_list=new ArrayList<View>();

        mTabViewStyle=mTabView.getTabViewStyle();
        mTabViewStyle.setmPositionType(TabViewStyle.TabPositionType.top);
        mTabViewStyle.setmTextColor(Color.BLACK);
        mTabViewStyle.setmTextColorSelected(Color.RED);
        mTabViewStyle.setTabHeight(150);
        mTabViewStyle.setTabBackGround(Color.CYAN);

        mTabView.setTabData(manager,txt, pics, mList);

    }
}
