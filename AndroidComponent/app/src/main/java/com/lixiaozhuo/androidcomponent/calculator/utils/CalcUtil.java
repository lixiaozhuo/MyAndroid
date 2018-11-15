package com.lixiaozhuo.androidcomponent.calculator.utils;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class CalcUtil {
    private CalcUtil(){}

    public static Map<String,Integer> signGrade;//符号等级

    static {//初始化符号优先级序列
        signGrade = new HashMap<>();
        signGrade.put("#",10);
        signGrade.put("*",1);
        signGrade.put("/",1);
        signGrade.put("%",1);
        signGrade.put("+",2);
        signGrade.put("-",2);
    }

    /**
     * 符号优先级比较
     */
    private static Integer signComparte(String str1,String str2){
        return signGrade.get(str2)-signGrade.get(str1);
    }

    /**
     *四则运算
     */
    private static Double calc(Double num1,Double num2,String str){
        switch (str){
            case "+": return num1 + num2;
            case "-": return num1 - num2;
            case "*" : return num1 * num2;
            case "/": return num1 /num2;
            case "%": return num1 % num2;
        }
        return 0.0;
    }

    /**
     *将算数表达式转换成数组
     */
    private static String[] getArray(String str){
        String[] number = str.split("\\+|\\-|\\*|\\/|\\%");//根据字符分割数字
        if(number.length == 1){
            return new String[]{number[0]};
        }
        String[] mark = str.split("\\d+\\.?\\d*");//根据数字分割字符
        String[] strs = new String[number.length + mark.length-1];
        strs[0] = number[0];
        int k = 1;
        for(int i = 1;i<number.length;i++){
            strs[k] = mark[i];//保存符号
            strs[k+1] = number[i];//保存数字
            k = k+2;
        }
        return strs;
    }

    /**
     *将中缀表达式转换成前缀表达式
     */
    private static String[] infixToPostfix(String [] infix){
        String[] postfix = new String[infix.length];
        int k = 0;//记录postfix数组索引

        ArrayDeque<String>  deque = new ArrayDeque<>();//存储符号
        deque.push("#");//栈底优先级最低

        for(int i = 0;i< infix.length;i++){
            if(infix[i].matches("\\d+\\.?\\d*")){//数字
                postfix[k] = infix[i];
                k++;
            }else{
                String s = deque.peek();
                if(signComparte(infix[i],s)>0) {//符号优先级大于栈顶,压入栈顶
                    deque.push(infix[i]);
                }else{
                    s = deque.pop();//取出栈顶元素
                    postfix[k] = s;//放入postfix数组
                    k++;
                    i--;//继续插入第i个符号
                }
            }
        }
        while(!"#".equals(deque.peek())){//栈中存在符号
            String s = deque.pop();//取出栈顶元素
            postfix[k] = s;//放入postfix数组
            k++;
        }
        return postfix;
    }

    /**
     *根据后缀表达求值
     */
    private static Double getPostfixResult(String[] postfix){
        ArrayDeque<Double>  dequeNum = new ArrayDeque<>();//存储数字

        for(int i = 0;i< postfix.length;i++){
            if(postfix[i].matches("\\d+\\.?\\d*")){//数字
                dequeNum.push(Double.valueOf(postfix[i]));
            }else{//运算符
                Double num1 = dequeNum.pop();
                Double num2 = dequeNum.pop();
                dequeNum.push(calc(num2,num1,postfix[i]));//将运算结果压栈
            }
        }
        return dequeNum.pop();
    }


    public static Double result(String str){
        char c = str.charAt(str.length() - 1);
        if(!OperatorUtil.isNum(c)){//最后一位不是数字去掉
            str = str.substring(0,str.length()-1);
        }
        String[] strs = getArray(str);//字符串转换为数组
        String[] newStrs = infixToPostfix(strs);//中缀表达式转换为后缀表达式
        Double num = getPostfixResult(newStrs);//根据后缀表达式计算值
        return num;
    }
}
