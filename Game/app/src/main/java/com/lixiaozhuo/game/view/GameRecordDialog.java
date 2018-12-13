package com.lixiaozhuo.game.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lixiaozhuo.game.R;
import com.lixiaozhuo.game.domain.GameScore;
import com.lixiaozhuo.game.service.GameRecordService;
import com.lixiaozhuo.game.service.MusicService;

/**
 * 游戏记录弹框
 */
public class GameRecordDialog extends Dialog {
    //上下文
    private Context context;
    //游戏记录业务
    private GameRecordService gameRecordService;
    //音乐业务
    private MusicService musicService;
    //数据适配器
    private ArrayAdapter<String> adapter;

    public GameRecordDialog(Context context) {
        super(context, R.style.Theme_dialog);
        setContentView(R.layout.game_record);
        this.context = context;
        //音乐业务
        musicService = new MusicService();
        //游戏记录业务
        gameRecordService = new GameRecordService();
        //初始化界面
        initView();
        //初始化数据适配器
        adapter = new ArrayAdapter<>(context, R.layout.game_record_adapter);
        //初始化数据
        initData();

        //清除按钮
        findViewById(R.id.btn_DialogScore_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //按键声音
                musicService.playKeyMusic();
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
                                gameRecordService.clearRecord();
                            }
                        }).setNegativeButton("放弃", null).show();
            }
        });
        //返回按钮
        findViewById(R.id.btn_DialogScore_Back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //按键声音
                musicService.playKeyMusic();
                dismiss();
            }
        });
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //初始化界面
    private void initView() {
        //获取布局参数
        WindowManager.LayoutParams params = getWindow().getAttributes();
        //获取像素密度
        float density = context.getResources().getDisplayMetrics().density;
        //设置显示大小
        params.width = (int) (300 * density);
        params.height = (int) (500 * density);
        //设置居中显示
        params.gravity = Gravity.CENTER;
        getWindow().setAttributes(params);
    }

    //初始化数据
    private void initData() {
        //设置适配器
        ((ListView) findViewById(R.id.list_view)).setAdapter(adapter);
        //将数据添加到适配器中
        for (GameScore gameScore : gameRecordService.getRecord()) {
            adapter.add(String.format("%s      最高记录：%s ", gameScore.getLevelName(), gameScore.getScore()));
        }
    }
}
