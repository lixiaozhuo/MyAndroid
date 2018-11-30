package com.lixiaozhuo.game.view;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lixiaozhuo.game.R;
import com.lixiaozhuo.game.domain.GameSetting;
import com.lixiaozhuo.game.service.GameSettingService;


/**
 * 设置对话框
 */
public class GameSettingDialog extends Dialog implements View.OnClickListener{
    //上下文内容
    private Context context;
    //游戏设置业务
    private GameSettingService gameSettingService;
    //游戏设置数据
    private GameSetting setting;

    public GameSettingDialog(Context context) {
        super(context,R.style.Theme_dialog);
        setContentView(R.layout.game_setting);
        this.context = context;
        gameSettingService = new GameSettingService(context);
        //初始化界面
        initView();
        //获取设置
        setting = gameSettingService.getSetting();
        //将对应人物和级别选中
        ((RadioButton)findViewById(setting.getMenNO())).setChecked(true);
        Log.e("AndroidApplication",setting.getLevelNO()+"");
        Log.e("AndroidApplication",R.id.radioLevel1+"");
        ((RadioButton)findViewById(setting.getLevelNO())).setChecked(true);
        //设置级别按钮单选
        radioSingleSelection(setting.getLevelNO());

        //保存按钮
        findViewById(R.id.btn_Save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存设置
                gameSettingService.saveSetting(GameSettingDialog.this.setting);
                dismiss();
            }
        });

        //退出按钮
        findViewById(R.id.btn_Back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //人物控件
        RadioGroup groupMen = findViewById(R.id.group_Men);
        groupMen.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.getId() == R.id.group_Men) {
                    //人物编号
                    GameSettingDialog.this.setting.setMenNO(checkedId);
                }
            }
        });

        //绑定级别控件点击事件
        findViewById(R.id.radioLevel1).setOnClickListener(this);
        findViewById(R.id.radioLevel2).setOnClickListener(this);
        findViewById(R.id.radioLevel3).setOnClickListener(this);
        findViewById(R.id.radioLevel4).setOnClickListener(this);
    }
    /**
     *级别控件点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        setting.setLevelNO(view.getId());
        //按钮单选
        radioSingleSelection(view.getId());
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

    /**
     * RadioButton单选实现(RadioGroup自定义布局后单选失效,需自己实现)
     * @param id
     */
    private void radioSingleSelection(int id) {
        switch (id) {
            case R.id.radioLevel1:
                ((RadioButton)findViewById(R.id.radioLevel2)).setChecked(false);
                ((RadioButton)findViewById(R.id.radioLevel3)).setChecked(false);
                ((RadioButton)findViewById(R.id.radioLevel4)).setChecked(false);
                break;
            case R.id.radioLevel2:
                ((RadioButton)findViewById(R.id.radioLevel1)).setChecked(false);
                ((RadioButton)findViewById(R.id.radioLevel3)).setChecked(false);
                ((RadioButton)findViewById(R.id.radioLevel4)).setChecked(false);
                break;
            case R.id.radioLevel3:
                ((RadioButton)findViewById(R.id.radioLevel1)).setChecked(false);
                ((RadioButton)findViewById(R.id.radioLevel2)).setChecked(false);
                ((RadioButton)findViewById(R.id.radioLevel4)).setChecked(false);
                break;
            case R.id.radioLevel4:
                ((RadioButton)findViewById(R.id.radioLevel1)).setChecked(false);
                ((RadioButton)findViewById(R.id.radioLevel2)).setChecked(false);
                ((RadioButton)findViewById(R.id.radioLevel3)).setChecked(false);
                break;
        }
    }


}


