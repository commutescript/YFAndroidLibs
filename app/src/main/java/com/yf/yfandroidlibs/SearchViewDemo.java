package com.yf.yfandroidlibs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.github.yf_library.search.OnItemsClickListener;
import com.github.yf_library.search.OnSerachClickListener;
import com.github.yf_library.search.SearchView;
import com.github.yf_library.tag.TagEntity;

import java.util.ArrayList;
import java.util.List;

public class SearchViewDemo extends Activity {

    SearchView mSearchView;
    private List<String> list;
    private List<TagEntity> data_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view_demo);

        list=new ArrayList<String>();
        Intent mIntent=new Intent(getApplicationContext(),CommonTestActivity.class);
        list.add("我的我的");
        list.add("你的你的");
        list.add("2222222");
        list.add("2333333");
        list.add("我干");

        data_list=new ArrayList<TagEntity>();
        TagEntity tagEntity01=new TagEntity();
        tagEntity01.setTxt("我的我的");
        tagEntity01.setmIntent(mIntent);

        data_list.add(tagEntity01);

        tagEntity01=new TagEntity();
        tagEntity01.setTxt("我的我做主");
        tagEntity01.setmIntent(mIntent);

        data_list.add(tagEntity01);

        tagEntity01=new TagEntity();
        tagEntity01.setTxt("2333333");
        tagEntity01.setmIntent(mIntent);

        data_list.add(tagEntity01);

        tagEntity01=new TagEntity();
        tagEntity01.setTxt("你好你好");
        tagEntity01.setmIntent(mIntent);

        data_list.add(tagEntity01);

        tagEntity01=new TagEntity();
        tagEntity01.setTxt("不是你");
        tagEntity01.setmIntent(mIntent);

        data_list.add(tagEntity01);

        tagEntity01=new TagEntity();
        tagEntity01.setTxt("杀人啦，要死啦");
        tagEntity01.setmIntent(mIntent);

        data_list.add(tagEntity01);

        tagEntity01=new TagEntity();
        tagEntity01.setTxt("哈哈哈哈哈哈哈");
        tagEntity01.setmIntent(mIntent);

        data_list.add(tagEntity01);


        tagEntity01=new TagEntity();
        tagEntity01.setTxt("嘻嘻");
        tagEntity01.setmIntent(mIntent);

        data_list.add(tagEntity01);


        mSearchView=(SearchView)findViewById(R.id.search_view);
        mSearchView.setSearchData(data_list,list,new OnSerachClickListener() {

            @Override
            public void OnSearchclick() {
                // TODO Auto-generated method stub
                Intent mIntent=new Intent(SearchViewDemo.this,CommonTestActivity.class);
                startActivity(mIntent);
            }
        });

        mSearchView.setItemClick(new OnItemsClickListener() {

            @Override
            public void setClick(String str) {
                // TODO Auto-generated method stub
                if(str==null){
                    str="yh_library";
                }
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();;

            }
        });
    }

  }
