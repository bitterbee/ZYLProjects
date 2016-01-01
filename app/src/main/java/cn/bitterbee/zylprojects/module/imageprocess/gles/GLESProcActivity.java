package cn.bitterbee.zylprojects.module.imageprocess.gles;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.bitterbee.zylprojects.R;
import cn.bitterbee.zylprojects.module.imageprocess.gles.gl.GLRenderer;

public class GLESProcActivity extends Activity
        implements View.OnClickListener {
    private static final String TAG = "GLESProcActivity";

    private GLSurfaceView glView = null;
    private GLRenderer glRenderer = null;
    private ImageView ivInput = null;
    private ImageView ivOutput = null;
    private TextView tvTime = null;

    private Bitmap lastResultBm = null;

    public static void start(Context context) {
        Intent intent = new Intent(context, GLESProcActivity.class);
        context.startActivity(intent);
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.image_process_activity_gles);

        // create GL view
        glView = (GLSurfaceView) findViewById(R.id.glview);
        glView.setEGLContextClientVersion(2);
        glRenderer = new GLRenderer(this);
        glView.setRenderer(glRenderer);
        glRenderer.setView(glView);
        glView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        // set up image view
        ivInput = (ImageView) findViewById(R.id.iv_input);
        ivInput.setOnClickListener(this);

        ivOutput = (ImageView) findViewById(R.id.iv_output);
        tvTime = (TextView) findViewById(R.id.tv_time);
    }

    @Override
    protected void onPause() {
        super.onPause();

        glView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        glView.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_input:{
                handleTouchOnImg();
            }break;
            default:
                break;
        }
    }

    private void handleTouchOnImg() {
        Log.i(TAG, "Touch on image");

        lastResultBm = null;

        if (glRenderer != null) {
            lastResultBm = glRenderer.getResultBm();
            float time = glRenderer.getExecAndPullTime();
            tvTime.setText("time = " + time);
        }

        if (lastResultBm != null) {
            Log.i(TAG, "Updating image view");
            ivOutput.setImageBitmap(lastResultBm);
        }

        Log.i(TAG, "Content view is now: main");
    }
}
