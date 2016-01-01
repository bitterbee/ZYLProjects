package cn.bitterbee.zylprojects.module.imageprocess.normal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.bitterbee.zylprojects.R;
import cn.bitterbee.zylprojects.common.util.media.ImageGrayUtil;

/**
 * Created by zyl06 on 12/13/15.
 */
public class NormalProcActivity extends Activity
        implements View.OnClickListener {
    ImageView ivInput;
    ImageView ivOutput;
    TextView tvTime;

    public static void start(Context context) {
        Intent intent = new Intent(context, NormalProcActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.image_process_activity_nor);

        // set up image view
        ivInput = (ImageView) findViewById(R.id.iv_input);
        ivInput.setOnClickListener(this);

        ivOutput = (ImageView) findViewById(R.id.iv_output);

        tvTime = (TextView) findViewById(R.id.tv_time);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_input: {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image_process_01_256);
                long t0 = System.nanoTime();
                Bitmap result = ImageGrayUtil.apply(this, bitmap);
                long t1 = System.nanoTime();
                tvTime.setText("time = " + (t1 - t0)/1000000.0f);
                ivOutput.setImageBitmap(result);
            }
            break;
            default:
                break;
        }
    }
}
