package com.dinosys.wmcloyalty.ui.adapter.welcome;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dinosys.wmcloyalty.R;
import com.dinosys.wmcloyalty.ui.model.PromotionModel;

import java.util.Collection;
import java.util.List;

/**
 * Created by Huynh
 * Since: 1/9/2016 - 17:23
 * Project: WMCLoyalty
 */
public class PromotionPagerAdapter extends PagerAdapter {

    public interface OnPromotionItemClickListener {
        void onPromotionItemClicked(PromotionModel model, View view);
    }

    private OnPromotionItemClickListener mOnItemClickListener;

    private Context mContext;
    private List<PromotionModel> mPromotionModels;

    public PromotionPagerAdapter(Context context, Collection<PromotionModel> promotionModels) {
        this.mContext = context;
        this.mPromotionModels = (List<PromotionModel>) promotionModels;
    }

    @Override
    public int getCount() {
        return mPromotionModels.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(this.mContext, R.layout.adapter_viewpage_item_promotion, null);
        final PromotionModel promotionModel = mPromotionModels.get(position);
        TextView textOldCost = (TextView)view.findViewById(R.id.textOldCost);
        textOldCost.setPaintFlags(textOldCost.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        container.addView(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PromotionPagerAdapter.this.mOnItemClickListener != null) {
                    mOnItemClickListener.onPromotionItemClicked(promotionModel, view);
                }
            }
        });

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public float getPageWidth(int position) {
        return 1.0f;
    }

    public void setOnItemClickListener(OnPromotionItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
