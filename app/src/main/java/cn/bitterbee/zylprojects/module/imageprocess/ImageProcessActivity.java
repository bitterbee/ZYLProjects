package cn.bitterbee.zylprojects.module.imageprocess;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.bitterbee.zylprojects.R;
import cn.bitterbee.zylprojects.module.imageprocess.gles.GLESProcActivity;
import cn.bitterbee.zylprojects.module.imageprocess.normal.NormalProcActivity;

public class ImageProcessActivity extends AppCompatActivity
    implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_process_activity_main);

        initContentView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal_image_process : {
                NormalProcActivity.start(this);
            } break;
            case R.id.gles_image_process : {
                GLESProcActivity.start(this);
            } break;
        }
    }

    private void initContentView() {
        findViewById(R.id.normal_image_process).setOnClickListener(this);
        findViewById(R.id.gles_image_process).setOnClickListener(this);
    }
}
