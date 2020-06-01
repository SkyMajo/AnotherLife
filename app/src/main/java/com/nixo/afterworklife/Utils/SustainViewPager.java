package com.nixo.afterworklife.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class SustainViewPager extends ViewPager {
    public SustainViewPager(@NonNull Context context) {
        super(context);
    }

    public SustainViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    int lastX = -1;
    int lastY = -1;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
            int x = (int) ev.getRawX();
            int y = (int) ev.getRawY();
            int dealtX = 0;
            int dealtY = 0;

            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                case MotionEvent.ACTION_MOVE:
                    dealtX += Math.abs(x - lastX);
                    dealtY += Math.abs(y - lastY);
                    lastX = x;
                    lastY = y;

                    if (dealtX >= dealtY) { // 左右滑动请求父不要需要拦截
                        getParent().requestDisallowInterceptTouchEvent(true);
                    } else {
                        getParent().requestDisallowInterceptTouchEvent(false);
                        return false;
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                    break;
                case MotionEvent.ACTION_UP:
                    break;

            }
            return false;
        }
    }
