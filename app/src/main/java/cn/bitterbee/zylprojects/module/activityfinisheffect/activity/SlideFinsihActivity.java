package cn.bitterbee.zylprojects.module.activityfinisheffect.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.bitterbee.zylprojects.R;
import cn.bitterbee.zylprojects.module.activityfinisheffect.view.finishlayout.OnSlidingFinishListener;
import cn.bitterbee.zylprojects.module.activityfinisheffect.view.finishlayout.SlidingFinishLayout;

/**
 * Created by zyl06 on 1/1/16.
 */
public class SlideFinsihActivity extends AppCompatActivity
        implements OnSlidingFinishListener {
    /**
     * 用于监听滑动的view
     */
    protected SlidingFinishLayout contentView;

    public static void start(Context context) {
        Intent intent = new Intent(context, SlideFinsihActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_effect_activity_slide);
        initContentView();

        setSlidingFinishEnabled(true);
    }

    private void initContentView(){
        contentView = (SlidingFinishLayout)findViewById(R.id.content_view);
        contentView.setOnSlidingFinishListener(this);

        View touchView = findViewById(R.id.touchview);
        contentView.setTouchView(touchView);
    }

    @Override
    public void onSlidingFinish() {
        finish();
        overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out);
    }

    public void setSlidingFinishEnabled(boolean isSlidingEnabled) {
        if (contentView != null) {
            contentView.setIsSlidingEnabled(isSlidingEnabled);
        }
    }

    public boolean isSlidingFinishEnabled() {
        return contentView != null && contentView.isSlidingEnabled();
    }
}
