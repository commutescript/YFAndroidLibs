package com.yf.yfandroidlibs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener{
    private TextView search_txt,tag_txt,dialog_txt,splash_txt,guidepage_txt,banner_txt,tab_txt,dw_txt;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        initView();

    }

    public void findViewById() {
        // TODO Auto-generated method stub
        search_txt=(TextView)findViewById(R.id.search_txt);
        tag_txt=(TextView)findViewById(R.id.tag_txt);
        dialog_txt=(TextView)findViewById(R.id.dialog_txt);
        splash_txt=(TextView)findViewById(R.id.splash_txt);
        guidepage_txt=(TextView)findViewById(R.id.guidepage_txt);
        banner_txt=(TextView)findViewById(R.id.banner_txt);
        tab_txt=(TextView)findViewById(R.id.tab_txt);
        dw_txt=(TextView)findViewById(R.id.dw_txt);

        search_txt.setOnClickListener(this);
        tag_txt.setOnClickListener(this);
        dialog_txt.setOnClickListener(this);
        splash_txt.setOnClickListener(this);
        guidepage_txt.setOnClickListener(this);
        banner_txt.setOnClickListener(this);
        tab_txt.setOnClickListener(this);
        dw_txt.setOnClickListener(this);
    }

    public void initView() {
        // TODO Auto-generated method stub

    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.search_txt:
                mIntent=new Intent(this,SearchViewDemo.class);
                startActivity(mIntent);
                break;

            case R.id.tag_txt:
                mIntent=new Intent(this,TagViewDemo.class);
                startActivity(mIntent);
                break;
            case R.id.dialog_txt:
                mIntent=new Intent(this,DialogViewDemo.class);
                startActivity(mIntent);
                break;
            case R.id.splash_txt:
                mIntent=new Intent(this,SplashViewDemo.class);
                startActivity(mIntent);
                break;
            case R.id.guidepage_txt:
                mIntent=new Intent(this,GuidePageViewDemo.class);
                startActivity(mIntent);
                break;
            case R.id.tab_txt:
                mIntent=new Intent(this,TabViewDemo.class);
                startActivity(mIntent);
                break;
            case R.id.banner_txt:
                mIntent=new Intent(this,BannerViewDemo.class);
                startActivity(mIntent);
                break;
            case R.id.dw_txt:
                mIntent=new Intent(this,AddressViewDemo.class);
                startActivity(mIntent);
                break;
            default:
                break;
        }

    }


}
