package cn.bitterbee.zylprojects.module.activityfinisheffect.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.bitterbee.zylprojects.R;

/**
 * Created by zyl06 on 1/1/16.
 */
public class FinishEffectActivity extends AppCompatActivity implements View.OnClickListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_effect_activity_main);
        findViewById(R.id.slide_finish).setOnClickListener(this);
        findViewById(R.id.rotate_finish).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.slide_finish : {
                SlideFinsihActivity.start(this);
            } break;
            case R.id.rotate_finish : {
                RotateFinishActivity.start(this);
            } break;
        }
    }
}
