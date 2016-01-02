package cn.bitterbee.zylprojects.module.activityfinisheffect.activity.finishlayout;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import cn.bitterbee.zylprojects.application.AppProfile;
import cn.bitterbee.zylprojects.common.util.ScreenUtil;

/**
 * Created by zyl06 on 10/9/15.
 */
public class CubeRotateFinishLayout extends RelativeLayout implements
        View.OnTouchListener {
    /**
     * SildingFinishLayout布局的父布局
     */
    private ViewGroup mParentView;
    /**
     * 处理滑动逻辑的View
     */
    private View mTouchView;
    /**
     * 滑动的最小距离
     */
    private int mTouchSlop;
    /**
     * 按下点的X坐标
     */
    private int downX;
    /**
     * 按下点的Y坐标
     */
    private int downY;
    /**
     * 临时存储X坐标
     */
    private int tempX;
    /**
     * SildingFinishLayout的宽度
     */
    private int viewWidth;
    /**
     * 记录是否正在滑动
     */
    private boolean mIsSliding;

    private OnSlidingFinishListener mOnSlidingFinishListener;
    private boolean isFinish;

    private boolean isSlidingEnabled = true;


    private Camera mCamera;
    private float mInterpolate = 0;

    private ImageView mLeftView;
    private Matrix mLeftViewMatrix;

    private View mRightView;
    private Matrix mRightViewMatrix;

    private Matrix mMatrix = new Matrix();

    private boolean mIsShowCubePrevView = true;

    public CubeRotateFinishLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CubeRotateFinishLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mCamera = new Camera();
        mRightViewMatrix = new Matrix();
        mLeftViewMatrix = new Matrix();

        applyTransformation(0);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            // 获取SildingFinishLayout所在布局的父布局
            mParentView = (ViewGroup) this.getParent();
            viewWidth = this.getWidth();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mLeftView = (ImageView) getChildAt(0);
        mRightView = getChildAt(1);
    }

    /**
     * 设置OnSlidingFinishListener, 在onSlidingFinish()方法中finish Activity
     *
     * @param onSlidingFinishListener
     */
    public void setOnSlidingFinishListener(OnSlidingFinishListener onSlidingFinishListener) {
        this.mOnSlidingFinishListener = onSlidingFinishListener;
    }

    public void setLeftBitmap(Bitmap bitmap) {
        if (mLeftView != null && bitmap != null) {
            mLeftView.setScaleType(ImageView.ScaleType.FIT_XY);
            mLeftView.setImageDrawable(new BitmapDrawable(bitmap));
        }
    }

    /**
     * 设置Touch的View
     *
     * @param touchView
     */
    public void setTouchView(View touchView) {
        this.mTouchView = touchView;
        mTouchView.setOnTouchListener(this);
    }

    public View getTouchView() {
        return mTouchView;
    }

    /**
     * 滚动出界面
     */
    private void scrollRight() {
        long duration = 400 - (long) mInterpolate * 400;
        ValueAnimator animator = ValueAnimator.ofFloat(mInterpolate, 1).
                setDuration(duration);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mOnSlidingFinishListener != null && isFinish) {
                    mOnSlidingFinishListener.onSlidingFinish();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mInterpolate = (float) animation.getAnimatedValue();
                applyTransformation(mInterpolate);
            }
        });
        animator.start();
    }

    /**
     * 滚动到起始位置
     */
    private void scrollOrigin() {
        long duration = (long) (mInterpolate * 400);
        ValueAnimator animator = ValueAnimator.ofFloat(mInterpolate, 0).
                setDuration(duration);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setBackgroundColor(Color.WHITE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mInterpolate = (float) animation.getAnimatedValue();
                applyTransformation(mInterpolate);
            }
        });
        animator.start();
    }

    /**
     * touch的View是否是AbsListView， 例如ListView, GridView等其子类
     *
     * @return
     */
    private boolean isTouchOnAbsListView() {
        return mTouchView instanceof AbsListView ? true : false;
    }

    /**
     * touch的view是否是ScrollView或者其子类
     *
     * @return
     */
    private boolean isTouchOnScrollView() {
        return mTouchView instanceof ScrollView ? true : false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (isSlidingEnabled) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    downX = tempX = (int) event.getRawX();
                    downY = (int) event.getRawY();
                    setBackgroundColor(Color.TRANSPARENT);
                    break;
                case MotionEvent.ACTION_MOVE:
                    int moveX = (int) event.getRawX();
                    int deltaX = tempX - moveX;
                    tempX = moveX;
                    if (Math.abs(moveX - downX) > mTouchSlop
                            && Math.abs((int) event.getRawY() - downY) < mTouchSlop) {
                        mIsSliding = true;

                        // 若touchView是AbsListView，
                        // 则当手指滑动，取消item的点击事件，不然我们滑动也伴随着item点击事件的发生
                        if (isTouchOnAbsListView()) {
                            MotionEvent cancelEvent = MotionEvent.obtain(event);
                            cancelEvent.setAction(MotionEvent.ACTION_CANCEL
                                    | (event.getActionIndex() << MotionEvent.ACTION_POINTER_INDEX_SHIFT));
                            v.onTouchEvent(cancelEvent);
                        }
                    }

                    if (moveX - downX >= 0 && mIsSliding) {

                        float deltaAngle = (float) Math.PI * deltaX / getWidth();
                        mRightViewMatrix.postRotate(deltaAngle, 0, 1);

                        mInterpolate = 1.0f * (moveX - downX) / getWidth();
                        applyTransformation(mInterpolate);

                        //mParentView.scrollBy(deltaX, 0);

                        // 屏蔽在滑动过程中ListView ScrollView等自己的滑动事件
                        if (isTouchOnScrollView() || isTouchOnAbsListView()) {
                            return true;
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    mIsSliding = false;
                    if (mInterpolate >= 0.5f) {
                        isFinish = true;
                        scrollRight();
                    } else {
                        scrollOrigin();
                        isFinish = false;
                    }
                    break;
            }
        }

        // 假如touch的view是AbsListView或者ScrollView 我们处理完上面自己的逻辑之后
        // 再交给AbsListView, ScrollView自己处理其自己的逻辑
        if (isTouchOnScrollView() || isTouchOnAbsListView()) {
            return v.onTouchEvent(event);
        }

        // 其他的情况直接返回true
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mLeftView != null && isSlidingEnabled() && mIsSliding) {
            canvas.setMatrix(mLeftViewMatrix);
            mLeftView.draw(canvas);
        }
        if (mRightView != null && isSlidingEnabled && mIsSliding) {
            canvas.setMatrix(mRightViewMatrix);
            mRightView.draw(canvas);
        }

//        canvas.setMatrix(mMatrix);
        super.onDraw(canvas);
    }


    public void resetPosition() {
        mInterpolate = 0;
        applyTransformation(mInterpolate);
    }

    public boolean isSlidingEnabled() {
        return isSlidingEnabled;
    }

    public void setIsSlidingEnabled(boolean isSlidingEnabled) {
        this.isSlidingEnabled = isSlidingEnabled;
    }

    protected void applyTransformation(float interpolatedTime) {
        float rotate = (90 * interpolatedTime);
        int width = getWidth();
        int height = getHeight();

        if (mRightView != null) {
            mCamera.save();
            mCamera.translate((-width + width * interpolatedTime), 0, 0);
            mCamera.rotateY(rotate);
            mCamera.getMatrix(mRightViewMatrix);
            mCamera.restore();

            mRightViewMatrix.postTranslate(width, height / 2);
            mRightViewMatrix.preTranslate(0, -height / 2 + ScreenUtil.getStatusBarHeight(AppProfile.getContext()));
        }

        ///////
        if (mIsShowCubePrevView && mLeftView != null) {
            float preRotate = (-90 + 90 * interpolatedTime);
            mCamera.save();
            mCamera.translate((interpolatedTime * width), 0, 0);
            mCamera.rotateY(preRotate);
            mCamera.getMatrix(mLeftViewMatrix);
            mCamera.restore();

            mLeftViewMatrix.postTranslate(0, height / 2);
            mLeftViewMatrix.preTranslate(-width, -height / 2 + ScreenUtil.getStatusBarHeight(AppProfile.getContext()));
        }

        postInvalidate();
    }
}
