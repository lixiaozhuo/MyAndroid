package com.lixiaozhuo.androidcomponent._08_view.listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.lixiaozhuo.androidcomponent.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 列表
 */
public class ListViewActivity extends Activity {
    /**
     * 自定义适配器数据源
     */
    private List<Message> list = new ArrayList<>();
    /**
     * 简单适配器数据源
     */
    private List<Map<String,Object>> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        //简单适配器消息列表
        setSimpleList();
        //自定义适配器消息列表
        //setMessageList();
    }

    /**
     * 简单适配器数据源
     */
    private void setSimpleList() {
        //初始化数据源
        initMessageMap();
        //初始化简单适配器
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data,
                R.layout.listview_item,
                new String[]{"imageId", "name", "content", "dateTime"},
                new int[]{R.id.messageImage, R.id.messageName, R.id.messageContent, R.id.messageDateTime});
        //获取ListView控件
        ListView listView = findViewById(R.id.messageListView);
        //设置数据源
        listView.setAdapter(simpleAdapter);
        //监听ListView点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,Object> message = data.get(position);
                //显示姓名
                Toast.makeText(ListViewActivity.this, message.get("name").toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 自定义适配器消息列表
     */
    private void setMessageList() {
        //初始化数据源
        initMessageList();
        //初始化自定义消息适配器
        MessageAdapter messageAdapter = new MessageAdapter(ListViewActivity.this, R.layout.listview_item, list);
        //获取ListView控件
        ListView listView = findViewById(R.id.messageListView);
        //设置数据源
        listView.setAdapter(messageAdapter);
        //监听ListView点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Message message = list.get(position);
                //显示姓名
                Toast.makeText(ListViewActivity.this, message.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化简单适配器数据源
     */
    private void initMessageMap() {
        Map<String,Object> map1 = new HashMap<>();
        map1.put("imageId",R.drawable.user01);
        map1.put("name","群助手");
        map1.put("content","签到");
        map1.put("dateTime",new SimpleDateFormat("HH:mm").format(new Date()));
        Map<String,Object> map2 = new HashMap<>();
        map2.put("imageId",R.drawable.user02);
        map2.put("name","QQ邮箱提醒");
        map2.put("content","GitHub");
        map2.put("dateTime",new SimpleDateFormat("HH:mm").format(new Date()));
        Map<String,Object> map3 = new HashMap<>();
        map3.put("imageId",R.drawable.user03);
        map3.put("name","我的电脑");
        map3.put("content","文档");
        map3.put("dateTime",new SimpleDateFormat("HH:mm").format(new Date()));
        Map<String,Object> map4 = new HashMap<>();
        map4.put("imageId",R.drawable.user04);
        map4.put("name","运动");
        map4.put("content","[应用消息]");
        map4.put("dateTime",new SimpleDateFormat("HH:mm").format(new Date()));
        Map<String,Object> map5 = new HashMap<>();
        map5.put("imageId",R.drawable.user05);
        map5.put("name","订阅号");
        map5.put("content","aaaa");
        map5.put("dateTime",new SimpleDateFormat("HH:mm").format(new Date()));
        Map<String,Object> map6 = new HashMap<>();
        map6.put("imageId",R.drawable.user06);
        map6.put("name","服务助手");
        map6.put("content","抽奖结果通知");
        map6.put("dateTime",new SimpleDateFormat("HH:mm").format(new Date()));
        Map<String,Object> map7 = new HashMap<>();
        map7.put("imageId",R.drawable.user07);
        map7.put("name","对分易");
        map7.put("content","签到成功");
        map7.put("dateTime",new SimpleDateFormat("HH:mm").format(new Date()));
        Map<String,Object> map8 = new HashMap<>();
        map8.put("imageId",R.drawable.user08);
        map8.put("name","文件传输助手");
        map8.put("content","");
        map8.put("dateTime",new SimpleDateFormat("HH:mm").format(new Date()));
        Map<String,Object> map9 = new HashMap<>();
        map9.put("imageId",R.drawable.user09);
        map9.put("name","测试9");
        map9.put("content","aaa");
        map9.put("dateTime",new SimpleDateFormat("HH:mm").format(new Date()));
        Map<String,Object> map10 = new HashMap<>();
        map10.put("imageId",R.drawable.user10);
        map10.put("name","测试10");
        map10.put("content","aaa");
        map10.put("dateTime",new SimpleDateFormat("HH:mm").format(new Date()));
        Map<String,Object> map11 = new HashMap<>();
        map11.put("imageId",R.drawable.user09);
        map11.put("name","测试11");
        map11.put("content","aaa");
        map11.put("dateTime",new SimpleDateFormat("HH:mm").format(new Date()));
        data.add(map1);
        data.add(map2);
        data.add(map3);
        data.add(map4);
        data.add(map5);
        data.add(map6);
        data.add(map7);
        data.add(map8);
        data.add(map9);
        data.add(map10);
        data.add(map11);
    }
    /**
     * 初始化自定义适配器数据源
     */
    private void initMessageList() {
        Message message1 = new Message(R.drawable.user01, "群助手", "签到", new Date());
        list.add(message1);
        Message message2 = new Message(R.drawable.user02, "QQ邮箱提醒", "GitHub", new Date());
        list.add(message2);
        Message message3 = new Message(R.drawable.user03, "我的电脑", "文档", new Date());
        list.add(message3);
        Message message4 = new Message(R.drawable.user04, "运动", "[应用消息]", new Date());
        list.add(message4);
        Message message5 = new Message(R.drawable.user05, "订阅号", "aaaa", new Date());
        list.add(message5);
        Message message6 = new Message(R.drawable.user06, "服务助手", "抽奖结果通知", new Date());
        list.add(message6);
        Message message7 = new Message(R.drawable.user07, "对分易", "签到成功", new Date());
        list.add(message7);
        Message message8 = new Message(R.drawable.user08, "文件传输助手", "", new Date());
        list.add(message8);
        Message message9 = new Message(R.drawable.user09, "测试9", "aaaa", new Date());
        list.add(message9);
        Message message10 = new Message(R.drawable.user10, "测试10", "aaaa", new Date());
        list.add(message10);
        Message message11 = new Message(R.drawable.user11, "测试11", "aaaa", new Date());
        list.add(message11);
        Message message12 = new Message(R.drawable.user12, "测试12", "aaaa", new Date());
        list.add(message12);
        Message message13 = new Message(R.drawable.user13, "测试13", "aaaa", new Date());
        list.add(message13);
        Message message14 = new Message(R.drawable.user14, "测试14", "aaaa", new Date());
        list.add(message14);
        Message message15 = new Message(R.drawable.user15, "测试15", "aaaa", new Date());
        list.add(message15);
    }


}
