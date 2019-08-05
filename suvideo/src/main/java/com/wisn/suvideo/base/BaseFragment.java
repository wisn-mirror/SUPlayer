package com.wisn.suvideo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Wisn on 2019-08-05 18:02.
 */
public abstract class BaseFragment extends Fragment {
    private View mContextView = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null != mContextView) {
            ViewGroup parent = (ViewGroup) mContextView.getParent();
            if (null != parent) parent.removeView(mContextView);
        } else {
            try {
                mContextView = inflater.inflate(bindLayout(), null);
                initView(mContextView);
            } catch (Exception e) {
                getActivity().finish();
                return mContextView;
            }
        }
        return mContextView;
    }

    protected abstract void initView(View mContextView);

    public abstract int bindLayout();
}
