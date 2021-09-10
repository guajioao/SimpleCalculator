package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculator.bean.Equation;
import com.example.calculator.service.Judger;
import com.example.calculator.service.myCalculator;

public class MainActivity extends AppCompatActivity {
    Equation equation = new Equation();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onNumberClick(View view){
        if(Judger.judgeNumber(equation)){
            Button button = findViewById(view.getId());
            TextView textEquation = findViewById(R.id.equation);
            if(equation.getEquation().equals("")){
                textEquation.setText("");
            }
            String newEquation = button.getText().toString().trim();
            equation.addNumber(newEquation);
            newEquation = textEquation.getText().toString().trim()+newEquation;
            textEquation.setText(newEquation);
        }
    }

    public void onOperatorClick(View view){
        if(Judger.judgeOperator(equation)){
            try {
                Button button = findViewById(view.getId());
                TextView textEquation = findViewById(R.id.equation);
                String newEquation = button.getText().toString().trim();
                equation.addOperator(newEquation);
                newEquation = textEquation.getText().toString().trim()+newEquation;
                textEquation.setText(newEquation);
            }catch (Exception e){
                Log.e("MainActivity","添加操作符失败");
            }
        }
    }
    public void onClearClick(View view){
        equation.clear();
        TextView textResult = findViewById(R.id.result);
        TextView textEquation = findViewById(R.id.equation);
        textEquation.setText("");
        textResult.setText("");
        textResult.setTextSize(40);
    }
    public void onIsClick(View view){
        String result = myCalculator.Calculate(equation);
        TextView textResult = findViewById(R.id.result);
        if(result!=null){
            textResult.setText(result);
            textResult.setTextSize(50);
            equation.clear();
        }else if(result.equals("")){

        } else{
            textResult.setText("错误");
        }
    }
    public void onBackClick(View view){
        TextView textEquation = findViewById(R.id.equation);
        String ret = equation.pop();
        if(ret.equals("%")){
            equation.deletePercent();
        }
        textEquation.setText(equation.getEquation().toString());
    }
    public void onPercentClick(View view){
        if(Judger.judgePercent(equation)){
            try {
                if(equation.getEquation().equals("")){
                    Log.i("MainActivity","公式为空");
                }else{
                    equation.inputPercent();
                    Log.i("MainActivity","currentNum:"+equation.getCurrentNum());
                    Log.i("MainActivity","currentD:"+equation.getCurrentD());

                    TextView textEquation = findViewById(R.id.equation);
                    Button button = findViewById(view.getId());
                    String newEquation = button.getText().toString().trim();
                    newEquation = textEquation.getText().toString().trim()+newEquation;
                    textEquation.setText(newEquation);
                }
            }catch (Exception e) {
                Log.e("MainActivity", "获取currentNum失败");
            }
        }

    }
    public void onPointClick(View view){
        // 操作符，提交之前的，并在下一次操作符之前的数均为小数部分
        //若currentD非0,说明已点击.或%，不可再次点击
        // “0.”+currentNum

    }
}