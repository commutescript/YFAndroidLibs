package com.yf.yfandroidlibs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.yf_library.banner.BannerEntity;
import com.github.yf_library.banner.BannerEntity;
import com.github.yf_library.banner.BannerStyle;
import com.github.yf_library.banner.BannerView;
import com.github.yf_library.banner.OnImageClickListener;

import java.util.ArrayList;
import java.util.List;

public class BannerViewDemo extends Activity {

    private BannerView mBannerView;
    private List<BannerEntity> data;
    private BannerStyle bannerStyle;
    private String[] imageLocalUrls = {"drawable://" +R.drawable.banner1,
            "drawable://"+R.drawable.banner2,
            "drawable://"+R.drawable.banner3,
            "drawable://"+R.drawable.banner4,
            "drawable://"+R.drawable.banner5};

    private Intent mintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_view_demo);
        mBannerView=(BannerView)getFragmentManager().findFragmentById(R.id.banner_frg);
        mintent=new Intent(BannerViewDemo.this,CommonTestActivity.class);

        init();
    }
    private void init(){
        data = new ArrayList<BannerEntity>();

        BannerEntity adDomain = new BannerEntity();
        adDomain.setId("108078");
        adDomain.setDate("3月4日");
        adDomain.setTitle("这是广告的标题");
        adDomain.setTopicFrom("阿宅");
        adDomain.setTopic("“广告的内容广告的内容广告的内容”");
        adDomain.setImgUrl(imageLocalUrls[0]);
        adDomain.setmIntent(mintent);


        adDomain.setAd(false);
        data.add(adDomain);

        BannerEntity adDomain2 = new BannerEntity();
        adDomain2.setId("108078");
        adDomain2.setDate("3月5日");
        adDomain2.setTitle("这是广告的标题");
        adDomain2.setTopicFrom("小巫");
        adDomain2.setTopic("“广告的内容广告的内容广告的内容”");
        adDomain2.setImgUrl(imageLocalUrls[1]);
        adDomain2.setmIntent(mintent);

        adDomain2.setAd(false);
        data.add(adDomain2);

        BannerEntity adDomain3 = new BannerEntity();
        adDomain3.setId("108078");
        adDomain3.setDate("3月6日");
        adDomain3.setTitle("这是广告的标题");
        adDomain3.setTopicFrom("旭东");
        adDomain3.setTopic("“广告的内容广告的内容广告的内容”");
        adDomain3
                .setImgUrl("http://e.hiphotos.baidu.com/image/w%3D310/sign=392ce7f779899e51788e3c1572a6d990/8718367adab44aed22a58aeeb11c8701a08bfbd4.jpg");
        adDomain3.setAd(false);
        adDomain3.setmIntent(mintent);

        data.add(adDomain3);

        BannerEntity adDomain4 = new BannerEntity();
        adDomain4.setId("108078");
        adDomain4.setDate("3月7日");
        adDomain4.setTitle("这是广告的标题");
        adDomain4.setTopicFrom("小软");
        adDomain4.setTopic("“广告的内容广告的内容广告的内容”");
        adDomain4
                .setImgUrl("http://d.hiphotos.baidu.com/image/w%3D310/sign=54884c82b78f8c54e3d3c32e0a282dee/a686c9177f3e670932e4cf9338c79f3df9dc55f2.jpg");
        adDomain4.setAd(false);
        adDomain4.setmIntent(mintent);

        data.add(adDomain4);

        BannerEntity adDomain5 = new BannerEntity();
        adDomain5.setId("108078");
        adDomain5.setDate("3月8日");
        adDomain5.setTitle("这是广告的标题");
        adDomain5.setTopicFrom("大熊");
        adDomain5.setTopic("“广告的内容广告的内容广告的内容”");
        adDomain5.setmIntent(mintent);
        adDomain5
                .setImgUrl("http://e.hiphotos.baidu.com/image/w%3D310/sign=66270b4fe8c4b7453494b117fffd1e78/0bd162d9f2d3572c7dad11ba8913632762d0c30d.jpg");
        adDomain5.setAd(true); // 代表是广告
        data.add(adDomain5);

        bannerStyle= mBannerView.getBannerStyle();
        bannerStyle.setTime(4000);
        bannerStyle.setmHorizontalAlignment(BannerStyle.HorizontalAlignment.CENTER);
        bannerStyle.setmVerticalAlignment(BannerStyle.VerticalAlignment.BOTTOM);
        bannerStyle.setShowInfo(false);

        bannerStyle.setmIndicatorStyle(BannerStyle.IndicatorStyle.INDIC);
        mBannerView.setBannerData(data, new OnImageClickListener() {
            @Override
            public void onImageClick(BannerEntity info, int postion, View imageView) {
                // TODO Auto-generated method stub
                //注意此处的postion是从1开始
                startActivity(info.getmIntent());
                Toast.makeText(getApplicationContext(), "点击了"+postion, 1).show();
            }
        });
    }


}
