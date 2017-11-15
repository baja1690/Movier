package com.bz.movier.common.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Created by Cuong Pham on 11/8/17.
 */

public class ImageUtil {

    public static void showImage(Context context, ImageView imageview, final ProgressBar progress, String url) {
        if (imageview == null) {
            return;
        }
        if (NullUtil.isNullOrEmpty(url)) {
            return;
        }
        if (progress != null) {
            progress.setVisibility(View.VISIBLE);
        }

        GlideRequest<Drawable> request = GlideApp.with(context)
                .load(url)
                .fitCenter()
                .placeholder(new ColorDrawable(Color.WHITE));
        addProgress(request, progress);
        request.into(imageview);
    }

    public static void addProgress(GlideRequest<Drawable> request, ProgressBar progress){
        request.listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                if (progress != null) {
                    progress.setVisibility(View.GONE);
                }
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                if (progress != null) {
                    progress.setVisibility(View.GONE);
                }
                return false;
            }
        });
    }
}
