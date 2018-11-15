package com.lixiaozhuo.androidcomponent.view.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixiaozhuo.androidcomponent.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 自定义消息适配器
 */
public class MessageAdapter extends ArrayAdapter<Message> {
    /**
     * 自定义布局id
     */
    private int resourceId;

    public MessageAdapter(Context context, int textViewResourceId,
                        List<Message> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获取当前消息实例
        Message message= getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            //获取视图
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            //设置控件组
            viewHolder = new ViewHolder();
            viewHolder.messageImage =  view.findViewById (R.id.messageImage);
            viewHolder.messageName =  view.findViewById (R.id.messageName);
            viewHolder.messageContent =  view.findViewById (R.id.messageContent);
            viewHolder.messageDateTime = view.findViewById(R.id.messageDateTime);
            //将ViewHolder存储在View中
            view.setTag(viewHolder);
        } else {
            view = convertView;
            // 重新获取ViewHolder
            viewHolder = (ViewHolder) view.getTag();
        }
        //设置控件的内容
        viewHolder.messageImage.setImageResource(message.getImageId());
        viewHolder.messageName.setText(message.getName());
        viewHolder.messageContent.setText(message.getContent());
        Date date = message.getDateTime();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String dateTime = formatter.format(date);
        viewHolder.messageDateTime.setText(dateTime);

        return view;
    }

    class ViewHolder {
        ImageView messageImage;
        TextView messageName;
        TextView messageContent;
        TextView messageDateTime;
    }

}
