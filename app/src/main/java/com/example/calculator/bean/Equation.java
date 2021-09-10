package com.example.calculator.bean;

import android.util.Log;
import android.widget.Toast;

import com.example.calculator.out.ArrayStack;

import java.util.ArrayList;

public class Equation {
    ArrayList equation = null;
    double currentNum;

    //创建两个栈，数栈，一个符号栈
    ArrayStack numStack = new ArrayStack(20);
    ArrayStack optStack = new ArrayStack(20);
    public Equation(){}

    public void setEquation(ArrayList equation) {
        this.equation = equation;
    }

    public ArrayList getEquation() {
        return equation;
    }
    private void push(String c){
        try {
            equation.add(c);
        }catch (Exception e){
            System.out.println("Equation.push():添加失败");
        }
    }
    public void pop(){
        try {
            equation.remove(equation.size()-1);

        }catch (Exception e){

            System.out.println("Equation.push():添加失败");
        }
    }
    public void addNumber(String num){
        try{
            currentNum*=10;
            double number = Double.parseDouble(num);
            currentNum+=number;
//                numStack.push(number);
            this.push(num);
        }catch(NumberFormatException e) {
            e.toString();
            Log.i("Equation","添加number失败:非double");
        }catch (Exception e){
            Log.i("Equation","添加number失败");
        }
    }
    public void addOperator(String c){
        try{
            numStack.push(currentNum);
            optStack.push(c.charAt(0));
            currentNum=0;
            this.push(c);
        }catch(Exception e) {
            e.toString();
            Log.i("Equation","添加operator失败");
        }
    }

    public static boolean isCorrect(ArrayList equation){

        return true;
    }
    public void clear(){
        try {
            equation.clear();
        }catch (Exception e){
            //Toast.makeText(Equation.this,"clear fail",Toast.LENGTH_SHORT).show();
            e.toString();
            Log.i("Equation","clear()：清除失败");
        }
    }
}
