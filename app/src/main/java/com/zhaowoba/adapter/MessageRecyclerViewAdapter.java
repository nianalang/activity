package com.zhaowoba.adapter;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhaowoba.R;

/**
 * Created by 念阿郎 on 2018/5/20.
 * message_item的适配器
 */

public class MessageRecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private LayoutInflater  messageLayoutInflater;
    private Context messageContext;
    /**
     * 二种类型
     */
    /**
     * 头
     */
    public static final int TITLE = 0;

    /**
     * 内容
     */
    public static final int CONTENT = 1;
    /**
     * 内容1
     */
    public static final int CONTENTS = 2;

    /**
     * 当前类型
     */
    public int currentType = TITLE;

    public MessageRecyclerViewAdapter(Context context){
        messageContext = context;
        messageLayoutInflater = LayoutInflater.from(context);
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TITLE) {
            return new MessageTitleViewHolder(messageLayoutInflater.inflate(R.layout.message_item_title, parent,false), messageContext);
        } else if (viewType == CONTENT) {
            return new TaskContentViewHolder(messageLayoutInflater.inflate(R.layout.message_item_page, parent,false), messageContext);
        }else if (viewType == CONTENTS) {
            return new TaskContentViewHolder1(messageLayoutInflater.inflate(R.layout.message_item_page, parent,false), messageContext);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TITLE) {
            MessageTitleViewHolder bannerViewHolder = (MessageTitleViewHolder) holder;
            //设置监听
            //TODO

        } else if (getItemViewType(position) == CONTENT) {
            TaskContentViewHolder recommendViewHolder = (TaskContentViewHolder) holder;
            //设置监听
            //TODO
        }else if (getItemViewType(position) == CONTENTS) {
            TaskContentViewHolder1 recommendViewHolder = (TaskContentViewHolder1) holder;
            //设置监听
            //TODO
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
    /**
     * 根据位置得到类型-系统调用
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case TITLE:
                currentType = TITLE;
                break;
            case CONTENT:
                currentType = CONTENT;
                break;
            case CONTENTS:
                currentType = CONTENTS;
                break;
        }
        return currentType;
    }

    class MessageTitleViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_theme_title;
        public ImageView iv_look_arrow;
        public Context mContext;

        public MessageTitleViewHolder(View itemView, Context mContext) {
            super(itemView);
            tv_theme_title=itemView.findViewById(R.id.tv_theme_title);
            iv_look_arrow=itemView.findViewById(R.id.iv_look_arrow);
            this.mContext = mContext;
        }
    }

    class TaskContentViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_logo;
        public TextView tv_intro;
        public TextView tv_specific_time;
        public TextView tv_specific_type;

        public Context mContext;

        public TaskContentViewHolder(View itemView, Context context) {
            super(itemView);

            iv_logo = (ImageView) itemView.findViewById(R.id.iv_logo);
            tv_intro = (TextView) itemView.findViewById(R.id.tv_intro);
            tv_specific_time = (TextView) itemView.findViewById(R.id.tv_specific_time);
            tv_specific_type = (TextView) itemView.findViewById(R.id.tv_specific_type);

            this.mContext = context;

        }
    }

    class TaskContentViewHolder1 extends RecyclerView.ViewHolder {
        public ImageView iv_logo;
        public TextView tv_intro;
        public TextView tv_specific_time;
        public TextView tv_specific_type;

        public Context mContext;

        public TaskContentViewHolder1(View itemView, Context context) {
            super(itemView);

            iv_logo = (ImageView) itemView.findViewById(R.id.iv_logo);
            tv_intro = (TextView) itemView.findViewById(R.id.tv_intro);
            tv_specific_time = (TextView) itemView.findViewById(R.id.tv_specific_time);
            tv_specific_type = (TextView) itemView.findViewById(R.id.tv_specific_type);

            this.mContext = context;

        }
    }
}
