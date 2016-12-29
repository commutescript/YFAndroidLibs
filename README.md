# YFAndroidLibs
## YFAndroidLibs ##

### 关于（About）###
这是抽取的工作日常工作代码形成的一个库，持续更新中。借鉴了一些网上现有的代码，在后续的介绍中会逐一提及。本文是基于android的库，后续会有iOS版本以及JS版本。
 - 如何抽取
自定义view和布局，然后加以封装，形成aar包。
 - 会上传到github：https://github.com/commutescript以及maven仓库。

### 功能（Features）
这个库包含的主要内容有：

1. [起始页splash](http://blog.csdn.net/u011072613/article/details/53899141)

2. [第一次导航guidepage](http://blog.csdn.net/u011072613/article/details/53907395)

3. [广告banner](http://blog.csdn.net/u011072613/article/details/53909522)

4. [tab导航和分栏](http://blog.csdn.net/u011072613/article/details/53914988)

5. [定位sqlite数据库](http://blog.csdn.net/u011072613/article/details/53914951)

6. [搜索框和界面](http://blog.csdn.net/u011072613/article/details/53914968)

7. [自定义dialog和Toast](http://blog.csdn.net/u011072613/article/details/53914851)

8. 一些常用的工具类，utils

9. [标签布局TagView](http://blog.csdn.net/u011072613/article/details/53914735)

10. 联动布局LInkageView

其中字体、颜色和背景的样式均可自定义，一键设置。


### 导入（Usage）

   1.导入aar包
  
 - 在libs文件夹下，添加相应的aar包
   
   
 - 在 repositories添加
   
    android {
    compileSdkVersion 24
    buildToolsVersion "24.0.2"

    defaultConfig {
        applicationId "com.yf.memorycard"
        minSdkVersion 14
        targetSdkVersion 24
        versionCode 1
        versionName "1.0"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
    repositories {
        flatDir {
            dirs 'libs'
        }
      } 
    }
  
 - 在dependencies 中添加
   
    dependencies {
    compile(name:'yf_Lib-release', ext:'aar')
    }
    
2.maven导入

   还在测试中，敬请期待！


###代码（Code）
   以SplashVIew为例：
   在布局中添加：

    ` <com.github.yf_library.splash.SplashView
        android:id="@+id/sp_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        /> `
        
   在代码中添加逻辑：
       
       //设置闪屏图片
    ` sp_view.setBg(getDrawable(R.drawable.guide1));
	  sp_view.setTime(t,new OnFinishListener() {

			@Override
			public void setFinish() {
				// TODO Auto-generated method stub
				Intent mIntent=new Intent(getApplicationContext(),CommonTestActivity.class);
				startActivity(mIntent);				
			}
		});
        //设置点击跳过
		sp_view.setJumpClick(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(TestActivity.this, "点击跳过", 1).show();;
				Intent mIntent=new Intent(getApplicationContext(),CommonTestActivity.class);
				startActivity(mIntent);			
			}
		});
    `

###部分效果图（ScreenShot）  
1. SplashView截图：

![](http://i.imgur.com/PS9RusR.jpg)

2.GuidePageView截图

![](http://i.imgur.com/OBcPHD5.jpg)

3.BannerView截图

![](http://i.imgur.com/0vgTnrs.jpg)

![](http://i.imgur.com/hIX2wfl.jpg)

![](http://i.imgur.com/WoM8Qd4.jpg)

4.TabView截图

![](http://i.imgur.com/AjKq0kF.jpg)

![](http://i.imgur.com/wS6ACvC.jpg)

![](http://i.imgur.com/IqRDpvV.jpg)

5.AddressView截图

![](http://i.imgur.com/r4HtfF4.jpg)

6.SerachView截图

![](http://i.imgur.com/tkeiFPa.jpg)

![](http://i.imgur.com/eUi8QGk.jpg)

![](http://i.imgur.com/lVZOmec.jpg)

7.DialogView截图
![](http://i.imgur.com/SUA56Sk.jpg)

8.TagView截图
![](http://i.imgur.com/ZTj4mAw.jpg)

![](http://i.imgur.com/AuGCZ5F.jpg)


4.其他的还在测试中。敬请期待。
