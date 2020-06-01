package com.nixo.afterworklife.Utils.RecyclerView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


/**
 * SustainRecyClerView 2019-11-4 17:11:11
 * 解决横向滑动冲突问题
 * Q：为什么不继承XRecyclerView
 * A：因为XRecyclerView的源码可自行观看
 * 在XRecyclerView源码中，他自行判断了滑动冲突，但是不好使，所以只能使用RecyclerView解决，
 * 其实横向也可以用XrecyclerView的header来解决，但是修改和拓展性不好，所以单独来写了
 */


public class SustainRecyclerView extends RecyclerView {


    public SustainRecyclerView(@NonNull Context context) {
        super(context);
    }

    public SustainRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SustainRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }


}
