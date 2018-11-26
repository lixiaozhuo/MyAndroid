package com.lixiaozhuo.game.view;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lixiaozhuo.game.R;
import com.lixiaozhuo.game.domain.GameSetting;

import static android.content.Context.MODE_PRIVATE;


/**
 * 设置对话框
 */
public class GameSettingDialog extends Dialog implements View.OnClickListener{
    //上下文内容
    private Context context;
    //游戏设置数据
    private GameSetting setting = new GameSetting();

    public GameSettingDialog(@NonNull Context context) {
        super(context,R.style.Theme_dialog);
        setContentView(R.layout.game_setting);
        this.context = context;
        //获取人物编号和级别编号并对应设置
        showSetting();
        //获取控件并绑定事件
        //保存按钮
        ImageButton btnSave = findViewById(R.id.btn_Save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存设置
                saveSetting();
                dismiss();
            }
        });
        //退出按钮
        ImageButton btnBack = findViewById(R.id.btn_Back);
        btnBack.setOnClickListener(new View.OnClickListener() {
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
                    setting.setMenNO(checkedId);
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
    /**
     * RadioButton单选实现(RadioGroup自定义布局后单选失效,需自己实现)
     * @param id
     */
    public void radioSingleSelection(int id) {
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

    //显示设置
    private void showSetting() {
        //取出人物编号和级别编号
        SharedPreferences preferences = context.getSharedPreferences("SettingData", MODE_PRIVATE);
        setting.setMenNO(Integer.valueOf(preferences.getString("menNO", String.valueOf(R.id.radioMen1))));
        setting.setLevelNO(Integer.valueOf(preferences.getString("levelNO", String.valueOf(R.id.radioLevel1))));
        //将对应人物和级别选中
        ((RadioButton)findViewById(setting.getMenNO())).setChecked(true);
        ((RadioButton)findViewById(setting.getLevelNO())).setChecked(true);
        //设置级别按钮单选
        radioSingleSelection(setting.getLevelNO());
    }

    //保存设置
    private void saveSetting() {
        //将人物编号和级别编号保存到SharedPreferences
        //创建名为“SettingData”的文件，加入键值对，保存数据
        SharedPreferences.Editor editor = context.getSharedPreferences("SettingData", MODE_PRIVATE).edit();
        editor.putString("menNO", String.valueOf(setting.getMenNO()));
        editor.putString("levelNO", String.valueOf(setting.getLevelNO()));
        //提交数据
        editor.commit();
    }


    public GameSettingDialog(Context context, int width, int height) {
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


