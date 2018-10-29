package com.lixiaozhuo.listviewtest;

import android.content.SyncStatusObserver;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class JavaBeanTest {
    @Test
    public void test(){
        List<Message> list = new ArrayList<>();
        Message message1 = new Message(R.drawable.user01, "群助手", "签到", new Date());
        list.add(message1);


        List<Map<String, Object>> data = new ArrayList<>();
        for (Message message : list) {
            try {
                Map<String, Object> map = BeanUtils.describe(message);
                data.add(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (Map<String, Object> map: data){
            System.out.println(map);
        }
    }
}
