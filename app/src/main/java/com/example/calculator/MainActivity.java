package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculator.bean.Equation;
import com.example.calculator.service.myCalculator;

public class MainActivity extends AppCompatActivity {
    Equation equation = new Equation();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onNumberClick(View view){
        Button button = findViewById(view.getId());
        TextView textEquation = findViewById(R.id.equation);
        String newEquation = button.getText().toString().trim();
        equation.addNumber(newEquation);
        newEquation = textEquation.getText().toString().trim()+newEquation;
        textEquation.setText(newEquation);
    }

    public void onOperatorClick(View view){
        Button button = findViewById(view.getId());
        TextView textEquation = findViewById(R.id.equation);
        String newEquation = button.getText().toString().trim();
        equation.addOperator(newEquation);
        newEquation = textEquation.getText().toString().trim()+newEquation;
        textEquation.setText(newEquation);
    }
    public void onClearClick(View view){
        equation.clear();
        TextView textResult = findViewById(R.id.result);
        TextView textEquation = findViewById(R.id.equation);
        textEquation.setText("");
        textResult.setTextSize(30);
    }
    public void onIsClick(View view){
        String result = myCalculator.Calculate(equation);
        TextView textResult = findViewById(R.id.result);
        if(result!=null){
            textResult.setText(result);
            textResult.setTextSize(50);
        }else {
            textResult.setText("错误");
        }
    }
    public void onBackClick(View view){
        TextView textEquation = findViewById(R.id.equation);
        equation.pop();
        textEquation.setText(equation.getEquation().toString());
    }
    public void onPercentClick(View view){

    }
    public void onPointClick(View view){

    }
}