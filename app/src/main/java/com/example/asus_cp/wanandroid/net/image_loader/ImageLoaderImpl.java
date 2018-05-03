package com.example.asus_cp.wanandroid.net.image_loader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.app.APP;
import com.example.asus_cp.wanandroid.di.component.DaggerAppComponent;

import javax.inject.Inject;

public class ImageLoaderImpl implements ImageLoader {

    private static ImageLoaderImpl imageLoader = new ImageLoaderImpl();

    private Context context;

    private ImageLoaderImpl(){
        context = APP.getContext();
    }

    @Override
    public void load(ImageView imageView, String url) {
        GlideApp .with(context)
                .load(url)
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

    public static ImageLoaderImpl getInstance(){
        return imageLoader;
    }
}
