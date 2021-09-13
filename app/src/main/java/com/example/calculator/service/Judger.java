package com.example.calculator.service;

import android.util.Log;

import com.example.calculator.bean.Equation;
import com.example.calculator.out.ArrayStack;

public class Judger {
    public static boolean judgeOperator(Equation equation){
        String finalC ;
        char c;
        try {
            finalC = equation.getText();
            c = finalC.substring(finalC.length()-1).charAt(0);
            if(c<='9'&&c>='0'||c=='%'||c=='.'||c==')'||c=='e'){
                return true;
            }else{
                Log.i("Judger","连续点击操作符");
                return false;
            }
        }catch (Exception e){
            Log.e("Judger","添加操作符失败,公式为空");
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
                Log.i("Judger","数字前不可为%");
                return false;
            }else{
                return true;
            }
        }catch (Exception e){
            Log.e("Judger","添加操作符失败,公式为空");
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
                Log.i("Judger","%前为操作符或%");
                return false;
            }
        }catch (Exception e){
                Log.e("Judger","添加操作符失败,公式为空");
        }
        return false;
    }
    public static boolean judgePoint(Equation equation){
        String finalC ;
        char c;
        try {
            finalC = equation.getEquation();
            c = finalC.substring(equation.getEquation().length()-1).charAt(0);
            if(equation.getClicked_point()&&equation.getCurrentD()!=0){
                return false;
            }else{
                Log.i("Judger","连续点击操作符");
                return true;
            }
        }catch (Exception e){
            Log.e("Judger","添加操作符失败,公式为空");
        }
        return true;
    }
    public static boolean judgeLeftBracket(Equation equation) {
        String finalC ;
        char c;
        try {
            finalC = equation.getEquation();
            c = finalC.substring(equation.getEquation().length() - 1).charAt(0);
            if (ArrayStack.isOper(c)&&c!=')') {
                return true;
            }else{
                Log.i("Judger","左括号前为非操作符");
                return false;
            }
        }catch (Exception e){
            Log.e("Judger","添加(,公式为空");
            return true;
        }
    }
    public static boolean judgeRightBracket(Equation equation){
        String finalC ;
        char c;
        try {
            finalC = equation.getEquation();
            c = finalC.substring(equation.getEquation().length() - 1).charAt(0);
            if (c <= '9' && c >= '0'||c=='(') {
                return true;
            }else {
                Log.i("Judger","%前为操作符或%");
                return false;
            }
        }catch (Exception e){
            Log.e("Judger","添加操作符失败,公式为空");
        }
        return false;
    }
    public static boolean judgeE(Equation equation){
        String finalC ;
        char c;
        try {
            finalC = equation.getEquation();
            c = finalC.substring(equation.getEquation().length() - 1).charAt(0);
            if (ArrayStack.isOper(c)&&c!=')') {
                return true;
            }else{
                Log.i("Judger","e前为非操作符");
                return false;
            }
        }catch (Exception e){
            Log.e("Judger","添加(,公式为空");
            return true;
        }
    }
    public static boolean judgeFunc(Equation equation){
        String finalC ;
        char c;
        try {
            finalC = equation.getEquation();
            Log.i("Judger","finalC="+finalC);
            c = finalC.substring(equation.getEquation().length()-1).charAt(0);
            if(c<='9'&&c>='0'){
                return true;
            }else{
                Log.i("Judger","前面不为数字");
                return false;
            }
        }catch (Exception e){
            Log.e("Judger","添加操作符失败,公式为空");
        }
        return false;
    }
    public static boolean judgePower(Equation equation){
        String finalC ;
        char c;
        try {
            finalC = equation.getEquation();
            Log.i("Judger","finalC="+finalC);
            c = finalC.substring(equation.getEquation().length()-1).charAt(0);
            if(c<='9'&&c>='0'){
                return true;
            }else{
                Log.i("Judger","前面不为数字");
                return false;
            }
        }catch (Exception e){
            Log.e("Judger","添加操作符失败,公式为空");
        }
        return false;
    }
}
