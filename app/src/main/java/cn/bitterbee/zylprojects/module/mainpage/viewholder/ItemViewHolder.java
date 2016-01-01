package cn.bitterbee.zylprojects.module.mainpage.viewholder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cn.bitterbee.zylprojects.R;

/**
 * Created by zyl06 on 1/1/16.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
    View mView;
    TextView mTvContent;
    Class mActivityClass;

    public ItemViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mTvContent = (TextView) mView.findViewById(R.id.tv_content);
        mTvContent.setOnClickListener(this);
    }

    public void refresh(ItemModel model) {
        mTvContent.setText(model.getContent());
        mActivityClass = model.getActivityClass();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mView.getContext(), mActivityClass);
        mView.getContext().startActivity(intent);
    }
}
