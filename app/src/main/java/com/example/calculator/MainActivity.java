package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculator.bean.Equation;
import com.example.calculator.service.Judger;
import com.example.calculator.service.myCalculator;

public class MainActivity extends AppCompatActivity {
    Equation equation = new Equation();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
                Log.e("MainActivity","?????????????????????");
            }
        }
    }
    public void onClearClick(View view){
        equation.clear();
        TextView textResult = findViewById(R.id.result);
        TextView textEquation = findViewById(R.id.equation);
        textEquation.setText("");
        textResult.setText("");
    }
    public void onIsClick(View view){
        TextView textResult = findViewById(R.id.result);
        TextView textE = findViewById(R.id.equation);
        try{
            String e = textE.getText().toString().trim();
            if(e.equals("")||e.equals("0")){
                textResult.setText(e);
            }else {
                String result = myCalculator.Calculate(equation);
                if(result!=null){
                    textResult.setText(result);
                    equation.clear();
                }else{
                    textResult.setText("??????");
                }

            }
        }catch (Exception e){
            e.toString();
        }

    }
    public void onBackClick(View view){
        TextView textEquation = findViewById(R.id.equation);
        String finalC;
        try{
            finalC = equation.pop();
        }catch (RuntimeException re){
            finalC="";
        }
        try{
            char ret = finalC.charAt(0);
            String final_e = equation.getEquation();
            if(ret=='%'){
                equation.deletePercent();
            }else if(ret=='s'||ret=='c'||ret=='t'||ret=='l'|ret=='L'||ret=='!'){
                textEquation.setText(final_e.substring(0,final_e.length()-1));
            }else{
                textEquation.setText(final_e);
            }
        }catch (Exception e){
            equation.clear();
            TextView textResult = findViewById(R.id.result);
            textEquation.setText("");
            textResult.setText("");
        }

    }
    public void onPercentClick(View view){
        //???????????????????????????
        //???currentD???0,???????????????.???%?????????????????????
        //?????????????????????????????????????????????%
        if(Judger.judgePercent(equation)){
            try {
                if(equation.getEquation().equals("")){
                    Log.i("MainActivity","????????????");
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
                Log.e("MainActivity", "??????currentNum??????");
            }
        }

    }
    public void onPointClick(View view){
        //????????????????????????.
        // ????????????????????????????????????????????????????????????????????????????????????
        //???currentD???0,???????????????.???%?????????????????????
        // ???0.???+currentNum
        if(Judger.judgePoint(equation)){
            try {
                equation.changeToDouble(".");
                Log.i("MainActivity","currentNum:"+equation.getCurrentNum());
                Log.i("MainActivity","currentD:"+equation.getCurrentD());

                TextView textEquation = findViewById(R.id.equation);
                Button button = findViewById(view.getId());
                String newEquation = button.getText().toString().trim();
                newEquation = textEquation.getText().toString().trim()+newEquation;
                textEquation.setText(newEquation);
            }catch (Exception e) {
                Log.e("MainActivity", "??????currentNum??????");
            }
        }
    }
    public void onLeftBracketClick(View view){
        //???????????????(???????????????
        //??????????????????
        if(Judger.judgeLeftBracket(equation)){
            try {
                TextView textEquation = findViewById(R.id.equation);
                if(equation.getEquation().equals("")){
                    textEquation.setText("");
                }
                equation.addBracket("(");
                Log.i("MainActivity","currentNum:"+equation.getCurrentNum());
                Log.i("MainActivity","currentD:"+equation.getCurrentD());

                Button button = findViewById(view.getId());
                String newEquation = button.getText().toString().trim();
                newEquation = textEquation.getText().toString().trim()+newEquation;
                textEquation.setText(newEquation);
            }catch (Exception e) {
                Log.e("MainActivity", "??????currentNum??????");
            }
        }
    }
    public void onRightBracketClick(View view){
        //????????????????????????(
        //?????????????????????
        if(Judger.judgeRightBracket(equation)){
            try {
                equation.addBracket(")");
                Log.i("MainActivity","currentNum:"+equation.getCurrentNum());
                Log.i("MainActivity","currentD:"+equation.getCurrentD());

                TextView textEquation = findViewById(R.id.equation);
                Button button = findViewById(view.getId());
                String newEquation = button.getText().toString().trim();
                newEquation = textEquation.getText().toString().trim()+newEquation;
                textEquation.setText(newEquation);
            }catch (Exception e) {
                Log.e("MainActivity", "??????currentNum??????");
            }
        }
    }
    public void onFactorialClick(View view){
        //???????????????
        //????????????????????????????????????equation
        if(Judger.judgeFunc(equation)){
            try {
                equation.addFunc("!");
                Log.i("MainActivity","currentNum:"+equation.getCurrentNum());
                Log.i("MainActivity","currentD:"+equation.getCurrentD());

                TextView textEquation = findViewById(R.id.equation);
                Button button = findViewById(view.getId());
                String newEquation = equation.getEquation();
                newEquation = newEquation.substring(0,newEquation.length()-1);//???????????????s??????
                textEquation.setText(newEquation);
            }catch (Exception e) {
                Log.e("MainActivity", "??????currentNum??????");
            }
        }
    }
    public void onPowerClick(View view){
        //???????????????
        //????????????????????????????????????equation
        if(Judger.judgePower(equation)){
            try {
                TextView textEquation = findViewById(R.id.equation);
                int id = view.getId();

                int p=0;
                switch (id){
                    case R.id.power:
                        p=-1;
                        break;
                    case R.id.power2:
                        p=2;
                        break;
                    case R.id.power3:
                        p=3;
                        break;
                }
                equation.addPower(p);
                textEquation.setText(equation.getText());
            }catch (Exception e){
                Log.e("MainActivity","?????????????????????");
            }
        }

    }
    public void onSinClick(View view){
        //????????????????????????????????????equation
        if(Judger.judgeFunc(equation)){
            try {
                equation.addFunc("s");
                Log.i("MainActivity","currentNum:"+equation.getCurrentNum());
                Log.i("MainActivity","currentD:"+equation.getCurrentD());

                TextView textEquation = findViewById(R.id.equation);
                Button button = findViewById(view.getId());
                String newEquation = equation.getEquation();
                newEquation = newEquation.substring(0,newEquation.length()-1);//???????????????s??????
                textEquation.setText(newEquation);
            }catch (Exception e) {
                Log.e("MainActivity", "??????currentNum??????");
            }
        }
    }
    public void onCosClick(View view){
        //????????????????????????????????????equation
        if(Judger.judgeFunc(equation)){
            try {
                equation.addFunc("c");
                Log.i("MainActivity","currentNum:"+equation.getCurrentNum());
                Log.i("MainActivity","currentD:"+equation.getCurrentD());

                TextView textEquation = findViewById(R.id.equation);
                Button button = findViewById(view.getId());
                String newEquation = equation.getEquation();
                newEquation = newEquation.substring(0,newEquation.length()-1);//???????????????s??????
                textEquation.setText(newEquation);
            }catch (Exception e) {
                Log.e("MainActivity", "??????currentNum??????");
            }
        }
    }
    public void onTanClick(View view){
        //????????????????????????????????????equation
        if(Judger.judgeFunc(equation)){
            try {
                equation.addFunc("t");
                Log.i("MainActivity","currentNum:"+equation.getCurrentNum());
                Log.i("MainActivity","currentD:"+equation.getCurrentD());

                TextView textEquation = findViewById(R.id.equation);
                Button button = findViewById(view.getId());
                String newEquation = equation.getEquation();
                newEquation = newEquation.substring(0,newEquation.length()-1);//???????????????s??????
                textEquation.setText(newEquation);
            }catch (Exception e) {
                Log.e("MainActivity", "??????currentNum??????");
            }
        }
    }
    public void onEClick(View view){
        //????????????????????????
        //????????????????????????
        if(Judger.judgeE(equation)){
            try {
                TextView textEquation = findViewById(R.id.equation);
                if(equation.getEquation().equals("")){
                    textEquation.setText("");
                }
                equation.addE("e");
                Log.i("MainActivity","currentNum:"+equation.getCurrentNum());
                Log.i("MainActivity","currentD:"+equation.getCurrentD());

                Button button = findViewById(view.getId());
                String newEquation = "e";
                newEquation = textEquation.getText().toString().trim()+newEquation;
                textEquation.setText(newEquation);
            }catch (Exception e) {
                Log.e("MainActivity", "??????currentNum??????");
            }
        }
    }
    public void onLnClick(View view){
        //????????????????????????????????????equation
        if(Judger.judgeFunc(equation)){
            try {
                equation.addFunc("l");
                Log.i("MainActivity","currentNum:"+equation.getCurrentNum());
                Log.i("MainActivity","currentD:"+equation.getCurrentD());

                TextView textEquation = findViewById(R.id.equation);
                Button button = findViewById(view.getId());
                String newEquation = equation.getEquation();
                newEquation = newEquation.substring(0,newEquation.length()-1);//???????????????s??????
                textEquation.setText(newEquation);
            }catch (Exception e) {
                Log.e("MainActivity", "??????currentNum??????");
            }
        }
    }
    public void onLogClick(View view){
        //????????????????????????????????????equation
        if(Judger.judgeFunc(equation)){
            try {
                equation.addFunc("L");
                Log.i("MainActivity","currentNum:"+equation.getCurrentNum());
                Log.i("MainActivity","currentD:"+equation.getCurrentD());

                TextView textEquation = findViewById(R.id.equation);
                Button button = findViewById(view.getId());
                String newEquation = equation.getEquation();
                newEquation = newEquation.substring(0,newEquation.length()-1);//???????????????s??????
                textEquation.setText(newEquation);
            }catch (Exception e) {
                Log.e("MainActivity", "??????currentNum??????");
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.science:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                Toast.makeText(this,"????????????????????????",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tools:

                Intent intent = new Intent(MainActivity.this,ToolsActivity_withRoll.class);
                startActivity(intent);
                Toast.makeText(this,"???????????????",Toast.LENGTH_SHORT).show();
                break;
            case R.id.help:
                Toast.makeText(this,"???????????????",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}