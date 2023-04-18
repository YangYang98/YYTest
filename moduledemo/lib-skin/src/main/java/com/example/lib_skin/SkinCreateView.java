package com.example.lib_skin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

import org.xmlpull.v1.XmlPullParser;

/**
 * 系统源码 [AppCompatDelegateImpl]
 *
 * Create by Yang Yang on 2023/4/13
 */
public abstract class SkinCreateView implements LayoutInflater.Factory2 {

    @SuppressLint("ObsoleteSdkInt")
    private static final boolean IS_PRE_LOLLIPOP = Build.VERSION.SDK_INT < 21;
    private final Activity mActivity;

    public SkinCreateView(Activity activity) {
        mActivity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        SystemAppCompatViewInflater systemAppCompatViewInflater = new SystemAppCompatViewInflater();
        boolean inheritContext = false;
        if (IS_PRE_LOLLIPOP) {
            inheritContext = (attrs instanceof XmlPullParser)
                    // If we have a XmlPullParser, we can detect where we are in the layout
                    ? ((XmlPullParser) attrs).getDepth() > 1
                    // Otherwise we have to use the old heuristic
                    : shouldInheritContext(mActivity, (ViewParent) parent);
        }
        View view = systemAppCompatViewInflater
                .createView(parent, name, context, attrs, inheritContext,
                        IS_PRE_LOLLIPOP /* Only read android:theme pre-L (L+ handles this anyway) */
                        /* Read read app:theme as a fallback at all times for legacy reasons */
                );

        if (view != null) {
            viewAttrs(context, view, name, attrs);
        }

        return view;
    }

    /**
     * 用来收集view信息
     */
    abstract void viewAttrs(Context context, View view, String name, AttributeSet attrs);

    private boolean shouldInheritContext(Activity activity, ViewParent parent) {
        if (parent == null) {
            // The initial parent is null so just return false
            return false;
        }
        final View windowDecor = activity.getWindow().getDecorView();
        while (true) {
            if (parent == null) {
                // Bingo. We've hit a view which has a null parent before being terminated from
                // the loop. This is (most probably) because it's the root view in an inflation
                // call, therefore we should inherit. This works as the inflated layout is only
                // added to the hierarchy at the end of the inflate() call.
                return true;
            } else if (parent == windowDecor || !(parent instanceof View)
                    || ViewCompat.isAttachedToWindow((View) parent)) {
                // We have either hit the window's decor view, a parent which isn't a View
                // (i.e. ViewRootImpl), or an attached view, so we know that the original parent
                // is currently added to the view hierarchy. This means that it has not be
                // inflated in the current inflate() call and we should not inherit the context.
                return false;
            }
            parent = parent.getParent();
        }
    }
}
