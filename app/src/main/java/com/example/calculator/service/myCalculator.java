package com.example.calculator.service;

import android.util.Log;

import com.example.calculator.bean.Equation;
import com.example.calculator.out.Calculator;

public class myCalculator {

    public static String Calculate(Equation e){
        try{
            String equation = e.getEquation().toString();
            Log.i("myCalculator","待计算的公式为:"+equation);
            Log.i("myCalculator","addOperator:numStack="+e.getNumStack().list());
            Calculator calculator = new Calculator(equation);
            try {
                String ret = calculator.getResult(e.getNumStack());
                return ret;
            }catch (Exception ecp){
                Log.e("myCalculator","获取结果失败");
            }

        }catch (Exception ecp){
            System.out.println("获取公式失败");
        }
        return null;
    }

}
