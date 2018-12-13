package com.lixiaozhuo.androidcomponent._09_data.sqlite;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lixiaozhuo.androidcomponent.R;

import java.util.ArrayList;

/**
 *学生数据库管理器:RecyclerView适配器
 */
public class SQLiteApplicationAdapter extends RecyclerView.Adapter<SQLiteApplicationAdapter.ViewHolder> {
    //数据源
    private ArrayList<SQLiteApplicationStudent> mData;

    public SQLiteApplicationAdapter(ArrayList<SQLiteApplicationStudent> data) {
        this.mData = data;
    }

    /**
     * 更新数据源
     * @param data
     */
    public void updateData(ArrayList<SQLiteApplicationStudent> data) {
        this.mData = data;
        //刷新
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sqlite_student, parent, false);
        // 实例化ViewHolder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //获取学生信息
        SQLiteApplicationStudent student = mData.get(position);
        //设置学生信息
        holder.tvAge.setText(student.age+"");
        holder.tvName.setText(student.name);
        holder.tvID.setText(student.student_ID+"");
    }

    @Override
    public int getItemCount() {
        //获取数据数目
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvID,tvName,tvAge;
        public ViewHolder(View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.item_stuID);
            tvName = itemView.findViewById(R.id.item_stuName);
            tvAge = itemView.findViewById(R.id.item_stuAge);
        }
    }
}