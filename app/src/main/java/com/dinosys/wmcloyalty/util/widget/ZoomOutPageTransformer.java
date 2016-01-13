package com.dinosys.wmcloyalty.util.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by Huynh
 * Since: 1/9/2016 - 17:25
 * Project: WMCLoyalty
 */
public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.6f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();
        Log.d("HTSI", "Get ScrollX " + ((ViewPager) view.getParent()).getScrollX());
        Log.d("HTSI", "Before - ViewPage: width = " + pageWidth
                + " - height = " + pageHeight
                + " - Pos = " + position + " - x =" + view.getX());


        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 2) { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0.75) {
                view.setTranslationX(horzMargin - vertMargin / 2);
            } else {
                view.setTranslationX(-horzMargin + vertMargin / 2);
            }

            // Scale the page down (between MIN_SCALE and 1)
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
            ViewCompat.setTranslationZ(view, scaleFactor);

            // Fade the page relative to its size.
            if (position > 0 && position < 0.75) {
                view.setAlpha(1.0f);
            } else {
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            }

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }

        Log.d("HTSI", "After ViewPage: width = " + pageWidth
                + " - height = " + pageHeight
                + " - Pos = " + position + " - x =" + view.getX());
    }


}
