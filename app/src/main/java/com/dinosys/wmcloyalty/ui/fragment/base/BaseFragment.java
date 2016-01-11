package com.dinosys.wmcloyalty.ui.fragment.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Huynh
 * Since: 1/9/2016 - 08:05
 * Project: TinhTeLite
 */
public class BaseFragment extends Fragment {

    protected void onScreenVisible(){}

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        onScreenVisible();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
