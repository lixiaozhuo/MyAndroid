package com.lixiaozhuo.listviewtest;

import android.support.v4.widget.TextViewCompat;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class DateTimeTest {
    @Test
    public void test(){
        System.out.println(new SimpleDateFormat("HH:mm").format(new Date()));
    }
}
