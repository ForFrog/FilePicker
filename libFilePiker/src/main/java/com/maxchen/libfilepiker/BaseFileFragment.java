package com.maxchen.libfilepiker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

public abstract class BaseFileFragment extends Fragment {

    protected Activity mActivity = null;
    protected Bundle bundle = null;
    protected Fragment mFragment = null;
    protected FragmentManager mFManager = null;

    protected boolean isVisible; //是否可见的标志
    protected boolean isPrepared = false; //是否已经准备好

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = getActivity();
        this.mFragment = this;
        bundle = getArguments();
        mFManager = getChildFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(getFragmentLayout(), container, false);
        initView(view);
        onVisible();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 可见的时候
     */
    private void onVisible() {
        if (isVisible && isPrepared) {
            lazyLoad();
        }
    }

    /**
     * fragment布局文件
     */
    public abstract int getFragmentLayout();

    /**
     * 延迟加载
     */
    protected abstract void lazyLoad();

    /**
     * 初始化控件
     *
     * @param view
     */
    protected void initView(View view) {
        isPrepared = true;
    }
}
