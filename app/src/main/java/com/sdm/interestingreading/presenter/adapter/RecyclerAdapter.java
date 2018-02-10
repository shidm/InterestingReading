package com.sdm.interestingreading.presenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sdm.interestingreading.R;

import java.util.List;

/**
 * Created by shidongming on 18-2-10.
 */

public class RecyclerAdapter<T> extends RecyclerView.Adapter {

    private List<T> list;
    private Context context;
    private String whichMsg;

    public RecyclerAdapter(Context context, List<T> list, String whichMsg) {
        this.context = context;
        this.list = list;
        this.whichMsg = whichMsg;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder = null;
        switch (whichMsg) {
            case "段子":
                view = LayoutInflater.from(context).inflate(R.layout.item_text, parent, false);
                viewHolder = new TextViewHolder(view);
                break;
            case "图片":
                break;
            case "视频":
                break;
            case "声音":
                break;
            default:
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (whichMsg) {
            case "段子":
                ((TextViewHolder)holder).username.setText("用户名");
                break;
            case "图片":
                break;
            case "视频":
                break;
            case "声音":
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void update(List<T> list){
        this.list = list;
        notifyDataSetChanged();
    }

    class TextViewHolder extends RecyclerView.ViewHolder {

        private TextView username;

        public TextViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
        }
    }
}
