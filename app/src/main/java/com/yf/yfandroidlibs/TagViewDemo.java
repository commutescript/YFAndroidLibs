package com.yf.yfandroidlibs;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.yf_library.tag.OnTagClickListener;
import com.github.yf_library.tag.TagEntity;
import com.github.yf_library.tag.TagView;
import com.github.yf_library.tag.TagViewStyle;

import java.util.ArrayList;
import java.util.List;

public class TagViewDemo extends Activity {

    private List<TagEntity> data_list;
    private TagViewStyle mTagViewStyle;
    private TagView mTagView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_view_demo);

        mTagView=(TagView)findViewById(R.id.tab_view);
        mTagViewStyle=mTagView.getTagViewStyle();
        mTagViewStyle.setmTagStyle(TagViewStyle.TagStyle.wrap);

        data_list=new ArrayList<TagEntity>();
        TagEntity tagEntity01=new TagEntity();
        tagEntity01.setTxt("我的我的");
        data_list.add(tagEntity01);

        tagEntity01=new TagEntity();
        tagEntity01.setTxt("我的我做主");
        data_list.add(tagEntity01);

        tagEntity01=new TagEntity();
        tagEntity01.setTxt("2333333");
        data_list.add(tagEntity01);

        tagEntity01=new TagEntity();
        tagEntity01.setTxt("嘻嘻");
        data_list.add(tagEntity01);

        tagEntity01=new TagEntity();
        tagEntity01.setTxt("呵呵呵呵呵呵");
        data_list.add(tagEntity01);

        tagEntity01=new TagEntity();
        tagEntity01.setTxt("22222");
        data_list.add(tagEntity01);

        tagEntity01=new TagEntity();
        tagEntity01.setTxt("要死啦，杀人啦");
        data_list.add(tagEntity01);

        mTagView.setTagsData(data_list, new OnTagClickListener() {

            @Override
            public void setTagClick(TagEntity entity) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), entity.getTxt(), Toast.LENGTH_SHORT).show();

            }
        });




    }

}
