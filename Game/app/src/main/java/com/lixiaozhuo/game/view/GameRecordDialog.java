package com.lixiaozhuo.game.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lixiaozhuo.game.R;
import com.lixiaozhuo.game.domain.GameRecord;
import com.lixiaozhuo.game.domain.GameScore;

/**
 * 游戏记录弹框
 */
public class GameRecordDialog extends Dialog {
    //上下文
    private Context context;

    public GameRecordDialog(@NonNull Context context) {
        super(context,R.style.Theme_dialog);
        setContentView(R.layout.game_record);
        this.context = context;
        //初始化游戏记录
        final GameRecord gameRecord = new GameRecord(context);
        //排行榜显示控件
        ListView listView = findViewById(R.id.list_view);
        //初始化数据适配器
        final ArrayAdapter adapter = new ArrayAdapter<String>(context, R.layout.custom_adapter);
        //设置适配器
        listView.setAdapter(adapter);
        //将数据填入适配器
        for (GameScore gameScore : gameRecord.getData()) {
            adapter.add(String.format("%s      最高记录：%s ",gameScore.getLevelName(),gameScore.getDate()));
        }
        findViewById(R.id.btn_DialogScore_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(GameRecordDialog.this.context)
                        .setTitle("提示")
                        .setMessage("你确认要删除所有记录吗？")
                        .setPositiveButton("确定", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //清除适配器数据
                                adapter.clear();
                                adapter.notifyDataSetChanged();
                                //清空存储数据
                                gameRecord.clearData();
                            }
                        }).setNegativeButton("放弃", null).show();
            }
        });
        //返回按钮
        findViewById(R.id.btn_DialogScore_Back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public GameRecordDialog(Context context, int width, int height) {
        this(context);
        //设置显示大小

        //获取布局参数
        WindowManager.LayoutParams params = getWindow().getAttributes();
        //获取像素密度
        float density = context.getResources().getDisplayMetrics().density;
        //设置显示大小
        params.width = (int) (width * density);
        params.height = (int) (height * density);
        //设置居中显示
        params.gravity = Gravity.CENTER;
        getWindow().setAttributes(params);
    }
}
