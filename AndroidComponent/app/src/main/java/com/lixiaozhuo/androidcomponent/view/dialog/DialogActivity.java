package com.lixiaozhuo.androidcomponent.view.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.lixiaozhuo.androidcomponent.R;

import java.util.Calendar;

/**
 * 对话框
 */
public class DialogActivity extends Activity {
    /**
     * 显示控件
     */
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");//初始为空，会传入空值
        textview = findViewById(R.id.textView123);
        textview.setText(name);
    }

    /**
     * 对话框
     *
     * @param v
     */
    public void onDialogClick1(View v) {
        new AlertDialog
                .Builder(DialogActivity.this)
                .setIcon(android.R.drawable.alert_dark_frame)
                .setTitle("注意")
                .setMessage("确认退出")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //结束
                        finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .create().show();
    }

    /**
     * 单选对话框
     * @param v
     */
    public void onDialogClick2(View v) {
        final String[] items = {"男", "女", "F", "M"};

        AlertDialog dialog = new AlertDialog
                .Builder(this)
                .setTitle("单选对话框")
                .setIcon(R.drawable.cat)
                //设置单选
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, items[which], Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }

    /**
     * 复选对话框
     *
     * @param v
     */
    public void onDialogClick3(View v) {
        //准备数据源
        final String[] items = {"北京", "上海", "广州", "深圳", "天津", "保定"};
        AlertDialog dialog = new AlertDialog
                .Builder(this)
                .setTitle("多选对话框")
                .setIcon(R.drawable.cat)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", null)
                .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        String itemStr = items[which];
                        if (isChecked) {
                            Toast.makeText(DialogActivity.this, "选择了" + itemStr, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DialogActivity.this, "取消了" + itemStr, Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .create();
        dialog.show();
    }

    /**
     * 列表对话框
     *
     * @param v
     */
    public void onDialogClick4(View v) {
        final String[] items = {"北京", "上海", "广州", "深圳", "天津", "保定"};
        AlertDialog dialog = new AlertDialog
                .Builder(this)
                .setTitle("列表对话框")
                .setIcon(R.drawable.cat)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, items[which], Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }

    /**
     * 进度条
     *
     * @param v
     */
    public void onDialogClick5(View v) {
        ProgressDialog pd1 = ProgressDialog.show(this, "提示", "正在登陆中",
                false, true);
    }

    /**
     * 日期对话框
     *
     * @param v
     */
    public void onDialogClick6(View v) {
        //获取当前日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        //通过DatePickerDialog来创建日期选择对话框
        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //当时间被设置后回调的方法
                Toast.makeText(DialogActivity.this,
                        year + "年" + monthOfYear + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT)
                        .show();
            }
        }, year, monthOfYear, dayOfMonth);
        dpd.show();
    }

    /**
     * 时间对话框
     *
     * @param v
     */
    public void onDialogClick7(View v) {
        //获取当前日期
        Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(DialogActivity.this,
                        hourOfDay + ":" + minute + "!",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
    }

    /**
     * 拖动对话框
     *
     * @param v
     */
    public void onDialogClick8(View v) {
        //设置对话框
        Dialog dialog = new Dialog(this);
        dialog.setTitle("拖动对话框");
        dialog.setContentView(R.layout.dialog_seek);
        //获取进度条
        SeekBar seekBar =dialog.findViewById(R.id.seekBar1);
        //设置进度条最大进度
        seekBar.setMax(100);
        //对话框中信息显示框架
        final TextView textViewDialog =dialog.findViewById(R.id.tv_result);
        //设置当前音量
        textViewDialog.setText("当前音量为：" + seekBar.getProgress());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //对话框中显示当前音量
                textViewDialog.setText("设置音量大小为：" + seekBar.getProgress());
                //Activity中显示当前音量
                textview.setText(textViewDialog.getText());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        dialog.show();
    }

    /**
     * 自定义对话框
     *
     * @param v
     */
    public void onDialogClick9(View v) {
        MyDialog dialog = new MyDialog(DialogActivity.this);
        dialog.show();
    }

    /**
     * 打开新的Activity
     *
     * @param v
     */
    public void onDialogClick10(View v) {
        Intent intent = new Intent(DialogActivity.this, RadiobuttonActivity.class);
        startActivity(intent);
    }
}
