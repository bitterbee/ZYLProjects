package cn.bitterbee.zylprojects.module.activityfinisheffect.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.bitterbee.zylprojects.R;
import cn.bitterbee.zylprojects.module.activityfinisheffect.activity.finishlayout.CubeRotateFinishLayout;
import cn.bitterbee.zylprojects.module.activityfinisheffect.activity.finishlayout.OnSlidingFinishListener;

/**
 * Created by zyl06 on 1/1/16.
 */
public class RotateFinishActivity extends AppCompatActivity
        implements OnSlidingFinishListener {
    /**
     * 用于监听滑动的view
     */
    protected CubeRotateFinishLayout mContentView;

    public static void start(Activity activity) {



        Intent intent = new Intent(activity, RotateFinishActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_effect_activity_rotate);
        initContentView();

        setSlidingFinishEnabled(true);
    }

    private void initContentView() {
        mContentView = (CubeRotateFinishLayout) findViewById(R.id.content_view);
        mContentView.setOnSlidingFinishListener(this);

        View touchView = findViewById(R.id.touchview);
        mContentView.setTouchView(touchView);
    }

    @Override
    public void onSlidingFinish() {
        finish();
        overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out);
    }

    public void setSlidingFinishEnabled(boolean isSlidingEnabled) {
        if (mContentView != null) {
            mContentView.setIsSlidingEnabled(isSlidingEnabled);
        }
    }

    public boolean isSlidingFinishEnabled() {
        return mContentView != null && mContentView.isSlidingEnabled();
    }
}
