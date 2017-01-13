package com.github.yf_library.banner;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.github.yf_library.R;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * ImageView创建工厂
 */
public class ImgFactory {
	
	public static final int NET_IMG=1;
	public static final int SDCARD_IMG=2;
	public static final int ASSETS_IMG=3;
	public static final int DRAWABLE_IMG=4;
	public static final int CONTENT_IMG=5;
	public static final int SQLITE_IMG=6;


	/**
	 * 从各种渠道获取视图，并加载
	 * @param
	 * @rrturn img
	 */
	public static ImageView getImageView(Context context, String url, int type){
		ImageView img = (ImageView)LayoutInflater.from(context).inflate(
				R.layout.img_fitxy, null);
		switch (type) {
		case NET_IMG:
			ImageLoader.getInstance().displayImage(url, img);
			break;
		case SDCARD_IMG:
	        // String imageUri = "file:///mnt/sdcard/image.png"; // from SD card
	        ImageLoader.getInstance().displayImage("file://" + url, img);
	        break;
		case ASSETS_IMG:
	        // String imageUri = "assets://image.png"; // from assets
	        ImageLoader.getInstance().displayImage("assets://" + url,
	                img);
	        break;
		case DRAWABLE_IMG:
	        // String imageUri = "drawable://" + R.drawable.image; // from drawables
	        // (only images, non-9patch)
	        ImageLoader.getInstance().displayImage("drawable://" + Integer.parseInt(url),
	                img);				
	        break;
		case CONTENT_IMG:
	        // String imageUri = "content://media/external/audio/albumart/13"; //
	        // from content provider
	        ImageLoader.getInstance().displayImage("content://" + url, img);
//	        ImageLoader.getInstance().l			
	        break;
		case SQLITE_IMG:
			ImageLoader.getInstance().displayImage(url, img);
			break;

		default:
			break;
		}
		
		return img;
		
	}
	
	/**
	 * 获取ImageView视图的同时加载显示url
	 * 
	 * @param text
	 * @return
	 */
	public static ImageView getImageView(Context context, String url) {
		ImageView imageView = (ImageView)LayoutInflater.from(context).inflate(
				R.layout.img_fitxy, null);
		ImageLoader.getInstance().displayImage(url, imageView);
		return imageView;
	}
	
	/**
     * 从内存卡中异步加载本地图�?
     * 
     * @param uri
     * @param imageView
	 * @return 
     */
    public static ImageView getImageViewFromSDCard(Context context, String url,ImageView img) {

        // String imageUri = "file:///mnt/sdcard/image.png"; // from SD card
        ImageLoader.getInstance().displayImage("file://" + url, img);
        return img;
    }

    /**
     * 从assets文件夹中异步加载图片
     * 
     * @param imageName
     *            图片名称，带后缀的，例如�?1.png
     * @param imageView
     */
    public static ImageView getImageViewFromAssets(String imageName, ImageView imageView) {
        // String imageUri = "assets://image.png"; // from assets
        ImageLoader.getInstance().displayImage("assets://" + imageName,
                imageView);
        return imageView;
    }

    /**
     * 从drawable中异步加载本地图�?
     * 
     * @param imageId
     * @param imageView
     */
    public void getImageViewFromDrawable(int imageId, ImageView imageView) {
        // String imageUri = "drawable://" + R.drawable.image; // from drawables
        // (only images, non-9patch)
        ImageLoader.getInstance().displayImage("drawable://" + imageId,
                imageView);
    }

    /**
     * 从内容提提供者中抓取图片
     */
    public void getImageViewFromContent(String uri, ImageView imageView) {
        // String imageUri = "content://media/external/audio/albumart/13"; //
        // from content provider
        ImageLoader.getInstance().displayImage("content://" + uri, imageView);
//        ImageLoader.getInstance().l
    }

	
	
}
