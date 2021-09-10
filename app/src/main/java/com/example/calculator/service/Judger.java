package com.example.calculator.service;

import android.util.Log;

import com.example.calculator.bean.Equation;

public class Judger {
    Equation equation;
    public static boolean judgeOperator(Equation equation){
        String finalC ;
        char c;
        try {
            finalC = equation.getEquation();
            c = finalC.substring(equation.getEquation().length()-1).charAt(0);
            if(c<='9'&&c>='0'||c=='%'){
                return true;
            }else{
                Log.i("MainActivity","连续点击操作符");
                return false;
            }
        }catch (Exception e){
            Log.e("MainActivity","添加操作符失败,公式为空");
        }
        return false;
    }
    public static boolean judgeNumber(Equation equation){
        String finalC ;
        char c;
        try {
            finalC = equation.getEquation();
            c = finalC.substring(equation.getEquation().length()-1).charAt(0);
            if(c=='%'){
                Log.i("MainActivity","数字前不可为%");
                return false;
            }else{
                return true;
            }
        }catch (Exception e){
            Log.e("MainActivity","添加操作符失败,公式为空");
        }
        return true;
    }
    public static boolean judgePercent(Equation equation){
        String finalC ;
        char c;
        try {
            finalC = equation.getEquation();
            c = finalC.substring(equation.getEquation().length() - 1).charAt(0);
            if (c <= '9' && c >= '0') {
                return true;
            }else {
                Log.i("MainActivity","%前为操作符或%");
                return false;
            }
        }catch (Exception e){
                Log.e("MainActivity","添加操作符失败,公式为空");
        }
        return false;
    }
    public static boolean judgePoint(Equation equation){

        return true;
    }
}
