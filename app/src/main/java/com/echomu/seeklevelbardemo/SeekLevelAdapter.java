package com.echomu.seeklevelbardemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SeekLevelAdapter extends RecyclerView.Adapter {
    private List<LevelBean> lists = new ArrayList<>();
    private LayoutInflater inflater;

    private Context context;
    private int levelWidth;

    public SeekLevelAdapter(Context context, List<LevelBean> levelBeanList, int levelWidth) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.lists= levelBeanList;
        this.levelWidth = levelWidth;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EditViewHolder(inflater.inflate(R.layout.item_seeklevel, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final EditViewHolder viewHolder = (EditViewHolder) holder;
        viewHolder.tvLevel.setText(lists.get(position).getValue());
        viewHolder.tvLevel.setBackgroundColor(lists.get(position).getColor());
        viewHolder.tvName.setText(lists.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    private final class EditViewHolder extends RecyclerView.ViewHolder {
        public TextView tvLevel,tvName;

        EditViewHolder(View itemView) {
            super(itemView);
            tvLevel = itemView.findViewById(R.id.tv_level);
            tvName = itemView.findViewById(R.id.tv_name);
            tvLevel.setWidth(levelWidth);
        }
    }

}
