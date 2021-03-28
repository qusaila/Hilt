package com.example.hilltapp.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.hilltapp.BaseApplication;

public class LoadImage {

    @BindingAdapter("imageUrl")
public static void  loadImage(ImageView imageView,String url){
        Glide  .with(BaseApplication.getInstance())
                .load(url)
                .into(imageView);
}
}
