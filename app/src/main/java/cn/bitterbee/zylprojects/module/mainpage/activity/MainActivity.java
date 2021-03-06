package cn.bitterbee.zylprojects.module.mainpage.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import cn.bitterbee.zylprojects.R;
import cn.bitterbee.zylprojects.common.util.ResUtil;
import cn.bitterbee.zylprojects.module.activityfinisheffect.activity.FinishEffectActivity;
import cn.bitterbee.zylprojects.module.imageprocess.ImageProcessActivity;
import cn.bitterbee.zylprojects.module.mainpage.viewholder.DividerItemDecoration;
import cn.bitterbee.zylprojects.module.mainpage.viewholder.ItemModel;

import cn.bitterbee.zylprojects.module.mainpage.adapter.MainItemsAdapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRvItems;

    ItemModel[] sItemModels = new ItemModel[] {
            new ItemModel(ResUtil.getString(R.string.item_image_process), ImageProcessActivity.class),
            new ItemModel(ResUtil.getString(R.string.item_activity_transition_effect), FinishEffectActivity.class)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRvItems = (RecyclerView) findViewById(R.id.rv_items);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        mRvItems.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRvItems.setLayoutManager(lm);

        MainItemsAdapter adapter = new MainItemsAdapter(sItemModels);
        mRvItems.setAdapter(adapter);
    }
}
