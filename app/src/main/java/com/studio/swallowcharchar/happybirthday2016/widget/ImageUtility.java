package com.studio.swallowcharchar.happybirthday2016.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.io.IOException;

/**
 * Created by Swallow on 6/24/16.
 */
public class ImageUtility {

    public static class Dimension {
        int mWidth;
        int mHeight;

        public Dimension(int width, int height) {
            mWidth = width;
            mHeight = height;
        }

        public int getWidth() {
            return mWidth;
        }

        public int getHeight() {
            return mHeight;
        }
    }

    public static Dimension getDecodeDimension(Context context, int imgResId) {
        Dimension dimension;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), imgResId);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        String imageType = options.outMimeType;
        return new Dimension(imageHeight, imageWidth);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeResource(res, resId, null);
        return resizeBitmap(bitmap, reqWidth, reqHeight);
    }

    public static Bitmap resizeBitmap(Bitmap bitmap, int reqWidth, int reqHeight) {
        if (bitmap == null) {
            return null;
        }
        int bitmapHeight = bitmap.getHeight();
        int bitmapWidth = bitmap.getWidth();
        int scaledHeight, scaledWidth, cropStartX, cropStartY;
        float ratio;
        if (bitmapHeight > bitmapWidth) {
            ratio = bitmapWidth / (float) reqWidth;
        } else {
            ratio = bitmapHeight / (float) reqHeight;
        }
        scaledHeight = (int) Math.ceil(bitmapHeight / ratio);
        scaledWidth = (int) Math.ceil(bitmapWidth / ratio);
        cropStartX = (scaledWidth - reqWidth ) / 2;
        cropStartY = (scaledHeight - reqHeight) / 2;
        bitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, false);
        bitmap = Bitmap.createBitmap(bitmap, cropStartX, cropStartY, reqWidth, reqHeight);
        return bitmap;
    }

    public static int getImageRotation(Resources res, int resId) {
        int rotation = 0;
        try {
            ExifInterface exifInterface = new ExifInterface("mipmap-xxhdpi/dsc_0052.JPG");
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotation = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotation = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotation = 90;
                    break;
            }
        } catch (IOException e) {

        }
        return rotation;
    }

    public static void ImageViewAnimatedChange(Context c, final ImageView v, final Bitmap new_image) {
        final Animation anim_out = AnimationUtils.loadAnimation(c, android.R.anim.fade_out);
        final Animation anim_in  = AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
        anim_out.setAnimationListener(new Animation.AnimationListener()
        {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation)
            {
                v.setImageBitmap(new_image);
                anim_in.setAnimationListener(new Animation.AnimationListener() {
                    @Override public void onAnimationStart(Animation animation) {}
                    @Override public void onAnimationRepeat(Animation animation) {}
                    @Override public void onAnimationEnd(Animation animation) {}
                });
                v.startAnimation(anim_in);
            }
        });
        v.startAnimation(anim_out);
    }
}
