package com.yf.yfandroidlibs;

import java.util.ArrayList;
import java.util.List;

import com.github.yf_library.tab.TabView;
import com.github.yf_library.tab.TabStyle;
import com.github.yf_library.tab.TabStyle.TabPositionType;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class TabViewDemo extends Activity {

    LinearLayout ll;
    TabView mTabView;
    private String[] txt={
            "我的","你的","他的","其他的","我不管","这是测试"
    };

    private int[] pics={
            R.drawable.icon_bottom_yfdef,	R.drawable.icon_bottom_yfdef,	R.drawable.icon_bottom_yfdef,R.drawable.icon_bottom_yfdef,R.drawable.icon_bottom_yfdef
            ,R.drawable.icon_bottom_yfdef
    };

    private List<Intent> mList;
    private ArrayList<View> actvity_list;
    private TabStyle mTabViewStyle;

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

        mIntent=new Intent(this,GuidePageViewDemo.class);
        mList.add(mIntent);

        mIntent=new Intent(this,SearchViewDemo.class);
        mList.add(mIntent);

        mIntent=new Intent(this,DialogViewDemo.class);
        mList.add(mIntent);

        mIntent=new Intent(this,TagViewDemo.class);
        mList.add(mIntent);


        mIntent=new Intent(this,TagViewDemo.class);
        mList.add(mIntent);

        actvity_list=new ArrayList<View>();

        mTabViewStyle=mTabView.getTabViewStyle();
        mTabViewStyle.setmPositionType(TabPositionType.middle_movable);
        mTabViewStyle.setmTextColor(Color.BLACK);
        mTabViewStyle.setmTextColorSelected(Color.RED);
        mTabViewStyle.setTabHeight(150);
        mTabViewStyle.setTabBackGround(Color.CYAN);

        mTabView.setTabData(manager,txt, pics, mList);

    }


}
