package com.example.wangweijun.ch4.ch4;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by wangweijun on 2018/4/12.
 */

public class CustomTextView extends android.support.v7.widget.AppCompatTextView{

    public CustomTextView(Context context){
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs, android.R.attr.textViewStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i("wang", "onMeasure tid:"+Thread.currentThread().getId()+", name:"+Thread.currentThread().getName());
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.i("wang", "onLayout tid:"+Thread.currentThread().getId()+", name:"+Thread.currentThread().getName());
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("wang", "onDraw tid:"+Thread.currentThread().getId()+", name:"+Thread.currentThread().getName());
        super.onDraw(canvas);
    }
}
