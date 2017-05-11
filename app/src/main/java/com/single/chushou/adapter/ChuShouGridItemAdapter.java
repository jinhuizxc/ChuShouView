package com.single.chushou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.single.chushou.R;
import com.single.chushou.activity.ChuShouGridActivity;
import com.single.chushou.holder.DoubleHolder;
import com.single.chushou.holder.SingleHolder;

import java.util.List;

/**
 * Created by xiangcheng on 17/4/14.
 */

public class ChuShouGridItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = ChuShouGridItemAdapter.class.getSimpleName();
    List<ChuShouGridActivity.GridItem> list;
    Context context;

    private static final int DOUBLE_ITEM = 0;
    private static final int SINGLE_ITEM = 1;

    public ChuShouGridItemAdapter(List<ChuShouGridActivity.GridItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).des1 != null) {
            return DOUBLE_ITEM;
        }
        return SINGLE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == DOUBLE_ITEM) {
            return new DoubleHolder(View.inflate(context, R.layout.double_item, null));
        } else {
            return new SingleHolder(View.inflate(context, R.layout.single_item, null));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder:" + position);
        final ChuShouGridActivity.GridItem gridItem = list.get(position);
        if (holder instanceof DoubleHolder) {
            DoubleHolder dh = (DoubleHolder) holder;
            Glide.with(context).load(gridItem.img).into(dh.leftImg);
            Glide.with(context).load(gridItem.img1).into(dh.rightImg);
            dh.leftText.setText(gridItem.des);
            dh.rightText.setText(gridItem.des1);
            dh.leftImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, gridItem.des, Toast.LENGTH_SHORT).show();
                }
            });
            dh.rightImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, gridItem.des1, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            SingleHolder dh = (SingleHolder) holder;
            Glide.with(context).load(gridItem.img).into(dh.img);
            dh.text.setText(gridItem.des);
            dh.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, gridItem.des, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
