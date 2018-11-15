package com.lixiaozhuo.androidcomponent.calculator.utils;

import java.util.Arrays;

public class OperatorUtil {
    private OperatorUtil(){};

    public  static  boolean isNum(char c){
        return Character.isDigit(c);
    }

    public static  boolean isOperator(char c){
        if(c == '+' || c=='-' || c=='*' ||c =='/'||c=='%'){
            return true;
        }
        return false;
    }

    public static  boolean isPoint(char c){
        if(c=='.'){
            return true;
        }
        return false;
    }

    /**
     *是否存在点
     */
    public static boolean  isHavePoint(String str){
        if(str.indexOf('.')>-1){
            return true;
        }
        return false;
    }

    /**
     *获取最后一个运算符的索引
     */
    public static int getLastOperatorIndex(String str){
        int[] index = new int[5];
        index[0]  = str.lastIndexOf('+');
        index[1]  = str.lastIndexOf('-');
        index[2]  = str.lastIndexOf('*');
        index[3]  = str.lastIndexOf('/');
        index[4]  = str.lastIndexOf('%');
        Arrays.sort(index);
        return index[4];
    }
}
