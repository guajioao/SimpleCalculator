package com.example.calculator.bean;

import android.util.Log;
import android.widget.Toast;

import com.example.calculator.MainActivity;
import com.example.calculator.out.ArrayStack;

import java.util.ArrayList;

public class Equation {
    String equation = "";



    String text = "";
    double currentNum;
    double currentD=0;
    boolean clicked_point = false;

    //创建两个栈，数栈，一个符号栈
    ArrayStack numStack = new ArrayStack(20);

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public ArrayStack getNumStack() {
        return numStack;
    }

    public void setNumStack(ArrayStack numStack) {
        this.numStack = numStack;
    }

    public boolean getClicked_point() {
        return clicked_point;
    }

    public void setClicked_point(boolean pre_Zero) {
        this.clicked_point = pre_Zero;
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
    private void addToText(String s){
        try {
            String newE= text+s;
            this.setText(newE);
        }catch (Exception e){
            Log.e("Equation","Equation.addToText():添加失败,s="+s);
        }
    }
    public String pop(){
        try {
            String ret = equation.substring(equation.length()-1,equation.length());
            String newE = equation.substring(0,equation.length()-1);
            this.setEquation(newE);
            ret = text.substring(text.length()-1,text.length());
            newE = text.substring(0,text.length()-1);
            text=newE;
            return ret;
        }catch (Exception e){
            Log.e("Equation","Equation.pop()失败");
            throw new RuntimeException("栈空");
        }
    }
    public char getTop(){
        try {
            char ret = equation.substring(equation.length()-1,equation.length()).charAt(0);
            return ret;
        }catch (Exception e){
            Log.e("Equation","Equation.getTop()失败");
            throw new RuntimeException("栈空");
        }
    }
    public void pushIntoStack(double num){
        numStack.push(num);
        Log.i("Equation","pushIntoStack:num="+num);
    }

    public void addNumber(String num){
        try{
            currentNum*=10;
            double number = Double.parseDouble(num);
            currentNum+=number;
            this.push(num);
            this.addToText(num);
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
    public void addE(String num){
        try{
            this.push(num);
            this.addToText(num);
        }catch(NumberFormatException e) {
            e.toString();
            Log.e("Equation","添加number失败:非double");
        }catch (Exception e){
            Log.e("Equation","添加number失败");
        }
    }
    public void addOperator(String c){
        try{
            Log.i("Equation","addOperator(): currentD="+currentD+",currentNum="+currentNum);
            if(currentD!=0){//若此次非整数
                this.pushIntoStack(currentD);
            }
            currentNum=0;
            currentD=0;
            this.push(c);
            this.addToText(c);
            Log.i("Equation","addOperator:numStack="+numStack.list());
        }catch(Exception e) {
            e.toString();
            Log.e("Equation","添加operator失败");
        }
    }
    public void inputPercent(){
        currentD=currentNum*0.01;
        currentNum=0;
        numStack.push(currentNum+currentD);
        this.push("%");
        this.addToText("%");
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
    public void changeToDouble(String point){
        try{
            this.push(point);
            this.addToText(point);
            this.setClicked_point(true);
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

    public void addBracket(String c){
        try{
            Log.i("Equation","addOperator(): currentD="+currentD+",currentNum="+currentNum);
            currentNum=0;
            currentD=0;
            this.push(c);
            this.addToText(c);
            Log.i("Equation","addOperator:numStack="+numStack.list());
        }catch(Exception e) {
            e.toString();
            Log.e("Equation","添加operator失败");
        }
    }
    public void addFunc(String c){
        //首先删去数字项，直到为操作符或(为止
        //已判断，前面不会为空
        char final_c = this.getTop();
        System.out.println("final_c="+final_c);
        try {
            while (final_c<='9'&&final_c>='0'||final_c=='.'){//当前面为数字,删去
                this.pop();
                final_c = this.getTop();
                System.out.println("final_c="+final_c);
            }
        }catch (Exception e){
            e.toString();
        }
        double p = currentNum/180*Math.PI;
        switch (c){
            case "s"://若为sin
                currentNum = Math.sin(p);
                break;
            case "c"://若为cos
                currentNum = Math.cos(p);
                break;
            case "t"://若为tan
                currentNum = Math.tan(p);
                break;
            case "l"://若为ln
                currentNum = log(currentNum,Math.E);
                break;
            case "L"://若为log
                currentNum = log(currentNum,10);
                break;
            case "!"://若为阶乘
                currentNum = (double)fac(currentNum);
                break;
        }
        this.push(currentNum+c);
        this.addToText(currentNum+"");
        System.out.println("E="+equation+"\nT="+text);
    }
    public void addPower(int n){
        char final_c = this.getTop();
        System.out.println("final_c="+final_c);
        try {
            while (final_c<='9'&&final_c>='0'||final_c=='.'){//当前面为数字,删去
                this.pop();
                final_c = this.getTop();
                System.out.println("final_c="+final_c);
            }
        }catch (Exception e){
            e.toString();
        }
        currentNum = Math.pow(currentNum,n);
        this.push(currentNum+"");
        this.addToText(currentNum+"");
        System.out.println("E="+equation+"\nT="+text);
    }


    public void clear(){
        try {
            this.setEquation("");
            this.setText("");
            this.numStack = new ArrayStack(20);
            this.currentD=0;
            this.currentNum=0;
        }catch (Exception e){
            //Toast.makeText(Equation.this,"clear fail",Toast.LENGTH_SHORT).show();
            e.toString();
            Log.e("Equation","clear()：清除失败");
        }
    }
    public double log(double value, double base) {
        return Math.log(value) / Math.log(base);
    }
    public int fac(double num){
        int ret=1;
        for(int i=1;i<=num;i++){
            ret*=i;
        }
        return ret;
    }
}
