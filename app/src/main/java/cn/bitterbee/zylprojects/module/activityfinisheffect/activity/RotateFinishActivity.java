package cn.bitterbee.zylprojects.module.activityfinisheffect.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
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

    private static final String BACKGROUND_BITMAP_KEY = "BACKGROUND_BITMAP_KEY";
    private Bitmap mBgBitmap;

    /**
     * 用于监听滑动的view
     */
    protected CubeRotateFinishLayout mContentView;

    public static void start(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();

        Bitmap bitmap = decorView.getDrawingCache();
        Bitmap bgBitmap = bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 10, bitmap.getHeight() / 10, true);
        bitmap.recycle();
        decorView.destroyDrawingCache();
        decorView.setDrawingCacheEnabled(false);

        Intent intent = new Intent(activity, RotateFinishActivity.class);
        intent.putExtra(BACKGROUND_BITMAP_KEY, bgBitmap);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_effect_activity_rotate);
        Intent intent = getIntent();
        if (intent != null) {
            mBgBitmap = intent.getParcelableExtra(BACKGROUND_BITMAP_KEY);
        }

        initContentView();

        setSlidingFinishEnabled(true);
    }

    private void initContentView() {
        mContentView = (CubeRotateFinishLayout) findViewById(R.id.content_view);
        mContentView.setOnSlidingFinishListener(this);
        mContentView.setLeftBitmap(mBgBitmap);

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
