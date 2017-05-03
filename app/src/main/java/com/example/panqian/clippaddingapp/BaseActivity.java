package com.example.panqian.clippaddingapp;

import android.app.Activity;

import com.example.panqian.clippaddingapp.util.UnStatusBarTintUtil;

/**
 * Created by panqian on 2017/5/3.
 */

public class BaseActivity extends Activity {


    /**
     * 是否使状态栏全透明（全透明效果，默认会使页面布局叠加到状态栏下 重叠）
     */
    public boolean statusBarTransparentEnable = false;
    /**
     * 是否启用沉浸式状态栏（全名页面应该设置为false）
     */
    public boolean statusBarTintEnable = false;

    /**
     * 是否开启沉浸式状态栏,主要用于判断
     *
     * @return
     */
    public boolean isStatusBarTintEnable() {
        return UnStatusBarTintUtil.greaterOrEqualKitkat() && statusBarTintEnable;
    }
    @Override
    public void setContentView(int layoutResID) {
        try {
            super.setContentView(layoutResID);
        } catch (Throwable e) {
            super.setContentView(layoutResID);
        }
        if (isStatusBarTintEnable()) {
            UnStatusBarTintUtil.setStatusBar4Base(this, statusBarTransparentEnable);
        }
    }
}
