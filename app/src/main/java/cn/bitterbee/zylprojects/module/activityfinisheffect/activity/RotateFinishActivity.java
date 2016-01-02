package cn.bitterbee.zylprojects.module.activityfinisheffect.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.WeakHashMap;

import cn.bitterbee.zylprojects.R;
import cn.bitterbee.zylprojects.module.activityfinisheffect.activity.finishlayout.CubeRotateFinishLayout;
import cn.bitterbee.zylprojects.module.activityfinisheffect.activity.finishlayout.OnSlidingFinishListener;

/**
 * Created by zyl06 on 1/1/16.
 */
public class RotateFinishActivity extends AppCompatActivity
        implements OnSlidingFinishListener {

    private static final String PRE_ACTIVITY_HASH_KEY = "PRE_ACTIVITY_HASH_KEY";
    private int mPrevContextCode = 0;

    /**
     * 用于监听滑动的view
     */
    protected CubeRotateFinishLayout mContentView;

    public static void start(Activity activity) {
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        View contentView = decorView.findViewById(android.R.id.content);
        contentView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        contentView.setDrawingCacheEnabled(true);
        contentView.buildDrawingCache();

        Bitmap bitmap = contentView.getDrawingCache();
        Bitmap bgBitmap = bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
        sActivity2BgBitmap.put(activity, bgBitmap);

        contentView.destroyDrawingCache();
        contentView.setDrawingCacheEnabled(false);

        Intent intent = new Intent(activity, RotateFinishActivity.class);
        intent.putExtra(PRE_ACTIVITY_HASH_KEY, activity.hashCode());
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_effect_activity_rotate);
        Intent intent = getIntent();
        if (intent != null) {
            mPrevContextCode = intent.getIntExtra(PRE_ACTIVITY_HASH_KEY, 0);
        }

        initContentView();

        setSlidingFinishEnabled(true);
    }

    private void initContentView() {
        mContentView = (CubeRotateFinishLayout) findViewById(R.id.content_view);
        mContentView.setOnSlidingFinishListener(this);

        Bitmap bgBitmap = getPrevBitmap(mPrevContextCode);
        if (bgBitmap != null) {
            mContentView.setLeftBitmap(bgBitmap);
        }

        View touchView = findViewById(R.id.touchview);
        mContentView.setTouchView(touchView);
    }

    @Override
    public void onSlidingFinish() {
        getWindow().getDecorView().setAlpha(0);
        finish();
        overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out);
        removePrevActivityCache(mPrevContextCode);
    }

    public void setSlidingFinishEnabled(boolean isSlidingEnabled) {
        if (mContentView != null) {
            mContentView.setIsSlidingEnabled(isSlidingEnabled);
        }
    }

    public boolean isSlidingFinishEnabled() {
        return mContentView != null && mContentView.isSlidingEnabled();
    }

    private static WeakHashMap<Activity, Bitmap> sActivity2BgBitmap = new WeakHashMap<>();

    private static Activity getPrevAcrtivity(int preContextCode) {
        for (WeakHashMap.Entry<Activity, Bitmap> entry : sActivity2BgBitmap.entrySet()) {
            Activity activity = entry.getKey();
            if (activity != null && preContextCode == activity.hashCode()) {
                return activity;
            }
        }
        return null;
    }

    private static synchronized Bitmap getPrevBitmap(int preContextCode) {
        Activity activity = getPrevAcrtivity(preContextCode);
        return (activity != null) ? sActivity2BgBitmap.get(activity) : null;
    }

    private static synchronized boolean removePrevActivityCache(int preContextCode) {
        Activity activity = getPrevAcrtivity(preContextCode);
        if (activity != null) {
            Bitmap bitmap = sActivity2BgBitmap.remove(activity);
            if (bitmap != null) {
                bitmap.recycle();
                return true;
            }

            return false;
        }

        return false;
    }
}
