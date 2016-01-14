package com.dinosys.wmcloyalty.ui.activity.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dinosys.wmcloyalty.R;
import com.dinosys.wmcloyalty.util.UIUtil;
import com.dinosys.wmcloyalty.util.widget.pagercontainer.PagerContainer;
import com.dinosys.wmcloyalty.util.widget.pagercontainer.ZoomOutPageTransformer;

public class TestActivity extends AppCompatActivity {

    PagerContainer mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mContainer = (PagerContainer) findViewById(R.id.pager_container);

        ViewPager pager = mContainer.getViewPager();
        pager.setPageTransformer(true, new ZoomOutPageTransformer());
        PagerAdapter adapter = new MyPagerAdapter();
        pager.setAdapter(adapter);
        //Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
        pager.setOffscreenPageLimit(adapter.getCount());
        //A little space between pages
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float viewPageWidth = displayMetrics.widthPixels - UIUtil.convertDpToPixel(20, this) * 2;
        int pageMargin = (int) (viewPageWidth * 0.2f);
        Log.d("HTSI", "viewPageWidth = " + viewPageWidth + " pageMargin/4 = " + pageMargin / 4);
        pager.setPageMargin(-pageMargin / 4);
        //If hardware acceleration is enabled, you should also remove
        // clipping on the pager for its children.
        pager.setClipChildren(false);
    }

    //Nothing special about this adapter, just throwing up colored views for demo
    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView view = new TextView(TestActivity.this);
            view.setText("Item " + position);
            view.setGravity(Gravity.CENTER);
            view.setBackgroundColor(Color.argb(255, position * 50, position * 10, position * 50));

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return 5;
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
    }
}
