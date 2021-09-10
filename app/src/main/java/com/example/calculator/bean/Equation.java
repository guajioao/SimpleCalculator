package com.example.calculator.bean;

import android.util.Log;
import android.widget.Toast;

import com.example.calculator.out.ArrayStack;

import java.util.ArrayList;

public class Equation {
    String equation = "";
    double currentNum;
    double currentD=0;
    boolean isDouble = false;

    //创建两个栈，数栈，一个符号栈
    ArrayStack numStack = new ArrayStack(20);

    public ArrayStack getNumStack() {
        return numStack;
    }

    public void setNumStack(ArrayStack numStack) {
        this.numStack = numStack;
    }


    public Equation(){}

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public String getEquation() {
        return equation;
    }

    public double getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(double currentNum) {
        this.currentNum = currentNum;
    }

    public double getCurrentD() {
        return currentD;
    }

    public void setCurrentD(double currentD) {
        this.currentD = currentD;
    }

    private void push(String c){
        try {
            String newE= equation+c;
            this.setEquation(newE);
        }catch (Exception e){
            Log.e("Equation","Equation.push():添加失败,c="+c);
        }
    }
    public String pop(){
        try {
            String ret = equation.substring(equation.length()-1,equation.length());
            String newE = equation.substring(0,equation.length()-1);
            this.setEquation(newE);
            return ret;
        }catch (Exception e){
            Log.e("Equation","Equation.pop()失败");
        }
        return "";
    }
    public void addNumber(String num){
        try{
            currentNum*=10;
            double number = Double.parseDouble(num);
            currentNum+=number;
            this.push(num);
            Log.i("Equation","addNumber:currentNum="+currentNum);
            Log.i("Equation","addNumber:currentD="+currentD);
            Log.i("Equation","addNumber:numStack="+numStack.list());
        }catch(NumberFormatException e) {
            e.toString();
            Log.e("Equation","添加number失败:非double");
        }catch (Exception e){
            Log.e("Equation","添加number失败");
        }
    }
    public void addOperator(String c){
        try{
            this.pushIntoStack(currentNum);
            currentNum=0;
            currentD=0;
            this.push(c);
            Log.i("Equation","addOperator:currentNum="+currentNum);
            Log.i("Equation","addOperator:currentD="+currentD);
            Log.i("Equation","addOperator:numStack="+numStack.list());
        }catch(Exception e) {
            e.toString();
            Log.e("Equation","添加operator失败");
        }
    }
    public void pushIntoStack(double num){
        numStack.push(num);
        Log.i("Equation","pushIntoStack:num="+num);
    }
    public void inputPercent(){
        currentD=currentNum*0.01;
        currentNum=0;
        numStack.push(currentNum+currentD);
        this.push("%");
        Log.i("Equation","inputPercent:currentNum="+currentNum);
        Log.i("Equation","inputPercent:currentD="+currentD);
        Log.i("Equation","inputPercent:numStack="+numStack.list());
        Log.i("Equation","equation="+equation);
    }
    public void deletePercent(){
        currentNum=currentD*100;
        currentD=0;
//        this.pop();
        Log.i("Equation","deletePercent:currentNum="+currentNum);
        Log.i("Equation","deletePercent:currentD="+currentD);

    }


    public void clear(){
        try {
            this.setEquation("");
            this.numStack = new ArrayStack(20);
            this.currentD=0;
            this.currentNum=0;
        }catch (Exception e){
            //Toast.makeText(Equation.this,"clear fail",Toast.LENGTH_SHORT).show();
            e.toString();
            Log.e("Equation","clear()：清除失败");
        }
    }
}
