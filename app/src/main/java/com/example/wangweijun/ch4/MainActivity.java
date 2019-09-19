package com.example.wangweijun.ch4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangweijun.ch4.ch4.MultiThreadActivity;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

   private TextView title;
    private MyHandler myHandler;

    private static class MyHandler extends Handler {
        WeakReference<MainActivity> weakReference;
        MyHandler(MainActivity mainActivity) {
            weakReference = new WeakReference<>(mainActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    MainActivity mainActivity = weakReference.get();
                    if (mainActivity != null) {
                        mainActivity.title.setText("收到了msg");
                    }
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = (TextView)findViewById(R.id.title);
        myHandler = new MyHandler(this);
        int depth = computeHierarchyDepth(findViewById(R.id.root));
        Log.i("wang", "final depth:"+depth);

    }

    public void startMultiThreadActivity(View v) {
        startActivity(new Intent(getApplicationContext(), MultiThreadActivity.class));
    }

    /**
     * 基本思路：
     * 采用递归的思想，计算所有子View的深度，然后返回最大那个
     *
     */
    int computeHierarchyDepth(View root) {
        if(null == root) return 0;
        Log.i("wang", "root:"+root);
        if (root instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup)root;
            int childCount = viewGroup.getChildCount();
            Log.i("wang", "childCount:"+childCount);
            int maxDepth = 0;
            for (int i=0; i<childCount; i++) {
                View childView = viewGroup.getChildAt(i);
                int depth = computeHierarchyDepth(childView)+1;// 1 指的是当前父view这一层
                Log.i("wang", "childView:"+childView+", depth:"+depth);
                if (depth > maxDepth) {
                    maxDepth = depth;
                }
            }
            Log.i("wang", "maxDepth:"+maxDepth);
            return maxDepth;
        } else {
            return 1;
        }
    }

    public void sendMsg(View v){
        myHandler.sendEmptyMessage(1);
    }
}
