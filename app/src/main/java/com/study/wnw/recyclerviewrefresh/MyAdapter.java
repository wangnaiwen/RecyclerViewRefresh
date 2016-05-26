package com.study.wnw.recyclerviewrefresh;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wnw on 16-5-26.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    //定义一个集合，接收从Activity中传递过来的数据和上下文
    private List<String> mList;
    private Context mContext;

    MyAdapter(Context context, List<String> list){
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item, parent, false);
        return new MyHolder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder){
            final String itemText = mList.get(position);
            ((MyHolder)holder).tv.setText(itemText);
        }
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView tv;
        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.list_item);
        }
    }
}
