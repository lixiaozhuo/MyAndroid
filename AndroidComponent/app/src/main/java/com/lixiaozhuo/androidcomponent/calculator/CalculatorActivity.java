package com.lixiaozhuo.androidcomponent.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lixiaozhuo.androidcomponent.R;
import com.lixiaozhuo.androidcomponent.calculator.utils.CalcUtil;
import com.lixiaozhuo.androidcomponent.calculator.utils.OperatorUtil;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnC;
    private Button btnX;
    /**
     * .
     */
    private Button btnPoint;
    /**
     * +
     */
    private Button btnAdd;
    /**
     * -
     */
    private Button btnSub;
    /**
     * *
     */
    private Button btnMult;
    /**
     * /
     */
    private Button btnDivide;
    /**
     * %
     */
    private Button btnPercent;

    /**
     * =
     */
    private Button btnResult;

    private TextView txtResult;

    private Boolean resultFlag = false;//标志上一个字符是否为等号


    private void initView() {
        //获取控件
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnC = findViewById(R.id.btnC);
        btnPoint = findViewById(R.id.btnPoint);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMult = findViewById(R.id.btnMult);
        btnDivide = findViewById(R.id.btnDivide);
        btnPercent = findViewById(R.id.btnPercent);
        btnResult = findViewById(R.id.btnResult);
        txtResult = findViewById(R.id.txtResult);

        //绑定事件
        btnX = findViewById(R.id.btnX);
        btnResult = findViewById(R.id.btnResult);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnX.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnPercent.setOnClickListener(this);
        btnResult.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        //初始化视图
        initView();
    }

    @Override
    public void onClick(View v) {
        String str = txtResult.getText().toString().trim();
        switch (v.getId()) {
            case R.id.btn0:
            case R.id.btn1:
            case R.id.btn2:
            case R.id.btn3:
            case R.id.btn4:
            case R.id.btn5:
            case R.id.btn6:
            case R.id.btn7:
            case R.id.btn8:
            case R.id.btn9: {
                //显示数字
                if(resultFlag == true){//上一个字符等号,清除上次结果
                    txtResult.setText(((Button) v).getText());
                }else{
                    txtResult.setText(str + ((Button) v).getText());
                }
                break;
            }
            case R.id.btnC: {
                //清空显示
                txtResult.setText("");
                break;
            }
            case R.id.btnX: {
                //退格
                if (str.length() <= 1) {
                    txtResult.setText("");
                } else {
                    txtResult.setText(str.substring(0, str.length() - 1));
                }
                break;
            }
            case R.id.btnPoint: {
                if (str != "") {
                    //获取前一个字符
                    char c = str.charAt(str.length() - 1);
                    if (OperatorUtil.isNum(c)) {//前一个字符为数字
                        //获取数字
                        String subStr = str.substring(OperatorUtil.getLastOperatorIndex(str) + 1, str.length());
                        //数字中不存在小数点
                        if (!OperatorUtil.isHavePoint(subStr)) {
                            str = str + ((Button) v).getText();
                        }
                    }else if(!OperatorUtil.isPoint(c)){//前一个字符非点
                        str += "0.";
                    }
                }else{//点为第一个字符,加上前缀0
                    str +="0.";
                }
                txtResult.setText(str);
                break;
            }
            case R.id.btnAdd:
            case R.id.btnSub:
            case R.id.btnMult:
            case R.id.btnDivide:
            case R.id.btnPercent: {
                //当前一个字符为运算符时,用当前运算符替换掉
                if (str != "") {
                    char c = str.charAt(str.length() - 1);
                    if (OperatorUtil.isOperator(c) || OperatorUtil.isPoint(c)) {
                        str = str.substring(0,str.length()-1);
                    }
                    txtResult.setText(str + ((Button) v).getText());
                }
                break;
            }
            case R.id.btnResult: {
                //处理结果
                result();
                resultFlag = true;
                break;
            }
            default:
                break;
        }
        if(v.getId() != R.id.btnResult){
            resultFlag = false;
        }

    }

    private void result() {
        String str = txtResult.getText().toString().trim();
        if(str == "" ) return;

        try{
            Double result = 0.0;
            result = CalcUtil.result(str);
            if((result - result.intValue())<=0.00001){//结果为整数
                txtResult.setText(Integer.valueOf(result.intValue()).toString());
            }else{//结果为小数
                txtResult.setText(result.toString());
            }
        }catch(Exception e){
            txtResult.setText("error");
        }
    }
}
