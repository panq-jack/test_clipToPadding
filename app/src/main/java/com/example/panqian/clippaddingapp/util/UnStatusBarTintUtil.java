package com.example.panqian.clippaddingapp.util;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.panqian.clippaddingapp.R;


/**
 * 沉浸式状态栏管理管理类
 * <p>
 * 所有操作都必须要在4.4（kitkat)及其以上版本
 * Created by nieyonghui on 2017/2/23.
 */

public class UnStatusBarTintUtil {
    /**
     * 判断系统版本是否大于等于4.4
     *
     * @return
     */
    public static boolean greaterOrEqualKitkat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    /**
     * 判断系统版本是否大于等于5.0
     *
     * @return
     */
    public static boolean greaterOrEqualLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static void setBackground(Activity activity, Drawable drawable) {
        if (!greaterOrEqualKitkat() || activity == null || drawable == null) {
            return;
        }
        View view = getStatusBarView(activity);
        if (view != null) {
            view.setBackgroundDrawable(drawable);
        }

    }

    public static void setBackgroundColor(Activity activity, int color) {
        if (!greaterOrEqualKitkat() || activity == null || color < 0) {
            return;
        }
        View view = getStatusBarView(activity);
        if (view != null) {
            view.setBackgroundColor(color);
        }
    }

    public static void setBackgroundResource(Activity activity, int resId) {
        if (!greaterOrEqualKitkat() || activity == null || resId < 0) {
            return;
        }
        View view = getStatusBarView(activity);
        if (view != null) {
            view.setBackgroundResource(resId);
        }
    }

    /**
     * BaseActivity默认操作
     *
     * @param activity
     * @param isTrans      是否设置状态栏全透明
     */
    public static void setStatusBar4Base(Activity activity, boolean isTrans) {
        if (activity == null  || !greaterOrEqualKitkat()) {
            return;
        }

        if (isTrans) {
            // 设置根布局的参数
            defaultSetTranslucent(activity);
        } else {
            defaultSetStatusBarbg(activity);
        }
    }

    public static View getStatusBarView(Activity activity) {
        ViewGroup decorView = getDecorView(activity);
        if (decorView != null) {
            return decorView.findViewById(R.id.un_status_bar_view);
        }
        return null;

    }

    /**
     * 填充工具条背景
     *
     * @param activity
     */
    public static void defaultSetStatusBarbg(Activity activity) {
        if (!greaterOrEqualKitkat()) {
            return;
        }
        setTransparentStatusBar(activity);
        addStatusBarView(activity, createStatusBarView(activity));
        if (greaterOrEqualLollipop()) {
            setBackgroundResource(activity, R.drawable.status_bar_background);
        } else {
            setBackgroundResource(activity, android.R.color.white);
        }
        setFitsSystemWindows(activity, true);
    }

    /**
     * 使状态栏透明
     * <p>
     * 适用于图片作为背景的界面,此时需要图片填充到状态栏
     *
     * @param activity 需要设置的activity
     */
    public static void defaultSetTranslucent(Activity activity) {
        if (!greaterOrEqualKitkat()) {
            return;
        }
        setTransparentStatusBar(activity);
        // 设置根布局的参数
        setFitsSystemWindows(activity, false);

    }

    private static void addStatusBarView(Activity activity, View view) {
        ViewGroup decorView = getDecorView(activity);
        if (decorView == null || view == null) {
            return;
        }
        Log.d("statusbar", "addView ");
        decorView.addView(view);
    }

    /**
     * 获取Activity 根View
     *
     * @param activity
     * @return
     */
    public static ViewGroup getDecorView(Activity activity) {
        return (ViewGroup) activity.getWindow().getDecorView();
    }

    /**
     * 生成一个和状态栏大小相同的矩形条
     *
     * @param activity 需要设置的activity
     * @return 状态栏矩形条
     */
    private static View createStatusBarView(Activity activity) {
        // 获得状态栏高度
        int StatusBarBarHeight = getStatusBarHeight(activity);

        // 绘制一个和状态栏一样高的矩形
        View StatusBarView = new View(activity);
        StatusBarView.setId(R.id.un_status_bar_view);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                StatusBarBarHeight);
        StatusBarView.setLayoutParams(params);
        return StatusBarView;
    }


    /**
     * 设置工具栏全透明
     *
     * @param activity
     */
    public static void setTransparentStatusBar(Activity activity) {
        Window window = activity.getWindow();
        if (greaterOrEqualLollipop()) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //如果页面头部有添加 工具栏背景view 则需要移除
        ViewGroup decorView = getDecorView(activity);
        View view = decorView.findViewById(R.id.un_status_bar_view);
        if (view != null) {
            decorView.removeView(view);
        }
    }

    /**
     * 设置页面FitsSystemWindows属性
     *
     * @param activity
     * @param values
     */
    public static void setFitsSystemWindows(Activity activity, boolean values) {
        ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        Log.d("statusbar", "rootViewid  " + rootView.getId());
        rootView.setFitsSystemWindows(values);
        rootView.setClipToPadding(true);
    }

    /**
     * 获取状态栏高度
     *
     * @param activity
     * @return
     */

    public static int getStatusBarHeight(Activity activity) {
        int result = 0;
        int resId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = activity.getResources().getDimensionPixelSize(resId);
        }
        return result;
    }
}
