package cn.bitterbee.zylprojects.module.mainpage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.bitterbee.zylprojects.R;
import cn.bitterbee.zylprojects.module.mainpage.viewholder.ItemModel;
import cn.bitterbee.zylprojects.module.mainpage.viewholder.ItemViewHolder;

/**
 * Created by zyl06 on 1/1/16.
 */
public class ItemsAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private ItemModel[] mModels;

    public ItemsAdapter(ItemModel[] itemModels) {
        mModels = itemModels;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // 加载数据item的布局，生成VH返回
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main_page, viewGroup, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder viewHolder, int i) {
        // 数据绑定
        viewHolder.refresh(mModels[i]);
    }

    @Override
    public int getItemCount() {
        // 返回数据有多少条
        if (null == mModels) {
            return 0;
        }
        return mModels.length;
    }
}
