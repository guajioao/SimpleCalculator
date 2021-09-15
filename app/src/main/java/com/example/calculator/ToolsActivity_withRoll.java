package com.example.calculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.aigestudio.wheelpicker.WheelPicker;
import com.example.calculator.bean.RollItem;

import java.util.ArrayList;
import java.util.List;

public class ToolsActivity_withRoll extends AppCompatActivity {
    int num[] = {2,8,10,16};
    private static final String TAG = "ToolsActivity";
    List<RollItem> list = new ArrayList<>();
    List<RollItem> list2 = new ArrayList<>();
    RollItem selectedF,selectedT;
    int fromId,toId;
    int unix_type = 0;//0计算长度，1计算体积

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_with_roll);
        //添加菜单
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
    }

    public void onRateClick(View view){
        Log.i(TAG,"onRateClick:");
        try{
            AlertDialog.Builder builder = new AlertDialog.Builder(ToolsActivity_withRoll.this);
            final View viewDialog = LayoutInflater.from(ToolsActivity_withRoll.this).inflate(R.layout.rate_window,null,false);
            builder.setView(viewDialog);
            builder.setPositiveButton("计算",null);
            builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            final AlertDialog mAlertDialog = builder.create();
            mAlertDialog.show();
            Log.i(TAG,"onRateClick:ShowAlertDialog");
            try {
                fromId=R.id.from;
                toId=R.id.to;
                WheelPicker wheelFrom = viewDialog.findViewById(fromId);
                WheelPicker wheelTo = viewDialog.findViewById(toId);
                initRateData();
                initWP(wheelFrom);
                initWP(wheelTo);

            }catch (Exception e){
                Log.e(TAG,"选择器生成失败");
            }
            mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {}

            });
        }catch (Exception e){
            Log.i(TAG,"弹窗生成失败");
        }

    }

    public void onUnitsClick(View view){
        Log.i(TAG,"onRateClick:");
        try{
            AlertDialog.Builder builder = new AlertDialog.Builder(ToolsActivity_withRoll.this);
            final View viewDialog = LayoutInflater.from(ToolsActivity_withRoll.this).inflate(R.layout.unix_window,null,false);
            builder.setView(viewDialog);
            builder.setPositiveButton("计算",null);
            builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            final AlertDialog mAlertDialog = builder.create();
            mAlertDialog.show();
            Log.i(TAG,"onRateClick:ShowAlertDialog");

            mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ToolsActivity_withRoll.this,"unix_type:"+unix_type,Toast.LENGTH_SHORT).show();
                    String input;
                    double ret=0;
                    TextView result = viewDialog.findViewById(R.id.result);
                    if(unix_type==0){
                        EditText number = viewDialog.findViewById(R.id.numberL);
                        input = number.getText().toString().trim();
                        Log.i(TAG,"输入为"+input);
                        try{//统一转换为10进制
                            //转化为double
                            ret = Double.parseDouble(input);
                            Log.i(TAG,ret+selectedF.getContent()+"转换为" +
                                    selectedT.getContent());
                        }catch (Exception e){
                            Toast.makeText(ToolsActivity_withRoll.this,"输入错误，请正确输入",Toast.LENGTH_SHORT).show();
                        }
                        ret*=Math.pow(0.1,(selectedT.getRid()-selectedF.getRid()));
                        Log.i(TAG,"ret*pow(0.1,"+(selectedT.getRid()-selectedF.getRid())+")");
                    }else{
                        EditText number = viewDialog.findViewById(R.id.numberV);
                        input = number.getText().toString().trim();
                        Log.i(TAG,"输入为"+input);
                        try{//统一转换为10进制
                            //转化为double
                            ret = Double.parseDouble(input);
                            Log.i(TAG,ret+selectedF.getContent()+"转换为" +
                                    selectedT.getContent());
                        }catch (Exception e){
                            Toast.makeText(ToolsActivity_withRoll.this,"输入错误，请正确输入",Toast.LENGTH_SHORT).show();
                        }
                        ret*=Math.pow(1000,(selectedT.getRid()-selectedF.getRid()));
                        Log.i(TAG,"ret*pow(1000,"+(selectedT.getRid()-selectedF.getRid())+")");
                    }
                    result.setText("result:"+ret);
                }
            });

            Button len_btn = (Button) viewDialog.findViewById(R.id.calLen);
            Button ver_btn = (Button) viewDialog.findViewById(R.id.calVer);
            len_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //设置长度计算卡片可见,隐藏体积计算卡片
                    Log.i(TAG,"点击切换为长度计算");
                    LinearLayout len_card = (LinearLayout) viewDialog.findViewById(R.id.lin_l);
                    LinearLayout ver_card = (LinearLayout) viewDialog.findViewById(R.id.lin_v);
                    len_card.setVisibility(View.VISIBLE);
                    ver_card.setVisibility(View.GONE);
                    unix_type=0;
                }
            });
            ver_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG,"点击切换为体积计算");
                    //设置长度计算卡片可见,隐藏体积计算卡片
                    LinearLayout len_card = viewDialog.findViewById(R.id.lin_l);
                    LinearLayout ver_card = viewDialog.findViewById(R.id.lin_v);
                    ver_card.setVisibility(View.VISIBLE);
                    len_card.setVisibility(View.GONE);

                    unix_type = 1;

                }
            });

            try {
                WheelPicker wheelFromL = viewDialog.findViewById(R.id.fromL);
                WheelPicker wheelToL = viewDialog.findViewById(R.id.toL);
                initUnitsLData();
                initWP(wheelFromL);
                initWP(wheelToL);

                //添加获取item
                wheelFromL.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(WheelPicker picker, Object data, int position) {
                        selectedF = (RollItem) data;
                        Log.i(TAG, "onItemSelected: " + selectedF + "  " + position);
                    }
                });
                wheelToL.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(WheelPicker picker, Object data, int position) {
                        selectedT = (RollItem) data;
                        Log.i(TAG, "onItemSelected: " + selectedF + "  " + position);
                    }
                });

                WheelPicker wheelFromV = viewDialog.findViewById(R.id.fromV);
                WheelPicker wheelToV = viewDialog.findViewById(R.id.toV);
                initUnitsVData();
                initWP(wheelFromV,list2);
                initWP(wheelToV,list2);

                //添加获取item
                wheelFromV.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(WheelPicker picker, Object data, int position) {
                        selectedF = (RollItem) data;
                        Log.i(TAG, "onItemSelected: " + selectedF + "  " + position);
                    }
                });
                wheelToV.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(WheelPicker picker, Object data, int position) {
                        selectedT = (RollItem) data;
                        Log.i(TAG, "onItemSelected: " + selectedF + "  " + position);
                    }
                });
            }catch (Exception e){
                Log.e(TAG,"选择器生成失败,fromId="+fromId);
            }

        }catch (Exception e){
            Log.i(TAG,"弹窗生成失败");
        }
    }
    public void onHexClick(View view){
        Log.i(TAG,"onHexClick:");
        AlertDialog.Builder builder = new AlertDialog.Builder(ToolsActivity_withRoll.this);
        final View viewDialog = LayoutInflater.from(ToolsActivity_withRoll.this).inflate(R.layout.hex_window,null,false);
        builder.setView(viewDialog);
        builder.setPositiveButton("计算",null);
        builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        final AlertDialog mAlertDialog = builder.create();
        mAlertDialog.show();
        Log.i(TAG,"onRateClick:ShowAlertDialog");
        try {
            WheelPicker wheelFrom = viewDialog.findViewById(R.id.from);
            WheelPicker wheelTo = viewDialog.findViewById(R.id.to);
            initHexData();
            initWP(wheelFrom);
            initWP(wheelTo);
            //添加获取item
            wheelFrom.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
                @Override
                public void onItemSelected(WheelPicker picker, Object data, int position) {
                    selectedF = (RollItem) data;
                    Log.i(TAG, "onItemSelected: " + selectedF + "  " + position);
                }
            });
            wheelTo.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
                @Override
                public void onItemSelected(WheelPicker picker, Object data, int position) {
                    selectedT = (RollItem) data;
                    Log.i(TAG, "onItemSelected: " + selectedF + "  " + position);
                }
            });
        }catch (Exception e){
            Log.e(TAG,"选择器生成失败");
        }
        mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input;
                int ret=0;
                EditText number = viewDialog.findViewById(R.id.number);
                input = number.getText().toString().trim();
                Log.i(TAG,"输入为"+input);
                TextView result = viewDialog.findViewById(R.id.result);
                try{//统一转换为10进制
                    ret = Integer.parseInt(input,num[selectedF.getRid()]);
                    Log.i(TAG,num[selectedF.getRid()]+"进制输入转换为10进制后为"+ret);
                }catch (Exception e){
                    Toast.makeText(ToolsActivity_withRoll.this,"输入错误，请正确输入",Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG,"转换为"+num[selectedT.getRid()]+"进制");
                switch (num[selectedT.getRid()]){
                    case 2:
                        result.setText("result:"+Integer.toBinaryString(ret));
                        break;
                    case 8:
                        result.setText("result:"+Integer.toOctalString(ret));
                        break;
                    case 10:
                        result.setText("result:"+ret);
                        break;
                    case 16:
                        result.setText("result:"+Integer.toHexString(ret));
                        break;
                }
            }

        });

    }

    public void initRateData(){
        list.clear();
        list.add(new RollItem("美元($)",0));
        list.add(new RollItem("英镑(￡)",1));
        list.add(new RollItem("人民币(¥)",2));
        list.add(new RollItem("欧元(€)",3));
        list.add(new RollItem("日圆(¥)",4));
    }
    public void initUnitsLData(){
        list.clear();
        list.add(new RollItem("毫米(mm)",1));
        list.add(new RollItem("厘米(cm)",2));
        list.add(new RollItem("分米(dm)",3));
        list.add(new RollItem("米(m)",4));
        list.add(new RollItem("公里(km)",5));
    }
    public void initUnitsVData(){
        list2.clear();
        list2.add(new RollItem("立方米",0));
        list2.add(new RollItem("立方厘米",1));
        list2.add(new RollItem("立方毫米",2));
    }
    public void initHexData(){
        list.clear();
        list.add(new RollItem("2",0));
        list.add(new RollItem("8",1));
        list.add(new RollItem("10",2));
        list.add(new RollItem("16",3));

    }

    private void initWP(WheelPicker wheelPicker) {
        // 通过setData方式设置数据集，不过此方法设置的数据在显示时都会被自动转换为String
        // 如果数据传入的是一个类的集合，那显示时就会直接调用他的toString方法，此时就要重写toString。
        wheelPicker.setData(list);
        initWPShow(wheelPicker);
        //选择监听器，会监听被选中的item（滑动停止后），需要自己做强制类型转换


        //获取当前的item，一样要做强制类型转换
        RollItem wheel = (RollItem) wheelPicker.getData().get(wheelPicker.getCurrentItemPosition());
        Log.i(TAG, "initWP: " + wheel);


        //滚轮监听器，滑动状态监听
        wheelPicker.setOnWheelChangeListener(new WheelPicker.OnWheelChangeListener() {
            @Override
            public void onWheelScrolled(int offset) {
                // 滑动距离，初始状态（即一开始position=0时）为0
                // 数据往上滑（即手往下滑）为正数，往下滑为负数
                Log.i(TAG, "onWheelScrolled: " + offset);
            }

            @Override
            public void onWheelSelected(int position) {
                // 等同于选择监听器的onItemSelected，停止滑动时所在的position
                Log.i(TAG, "onWheelSelected: " + position);
            }

            @Override
            public void onWheelScrollStateChanged(int state) {
                // 滚动状态监听器，0表示没有在滑动，1表示触屏造成的滑动，
                // 2表示停止触屏时造成的滑动（停止触屏后的回弹）
                Log.i(TAG, "onWheelScrollStateChanged: " + state);
            }
        });
    }
    private void initWP(WheelPicker wheelPicker,List list) {
        // 通过setData方式设置数据集，不过此方法设置的数据在显示时都会被自动转换为String
        // 如果数据传入的是一个类的集合，那显示时就会直接调用他的toString方法，此时就要重写toString。
        wheelPicker.setData(list);
        initWPShow(wheelPicker);
        //选择监听器，会监听被选中的item（滑动停止后），需要自己做强制类型转换


        //获取当前的item，一样要做强制类型转换
        RollItem wheel = (RollItem) wheelPicker.getData().get(wheelPicker.getCurrentItemPosition());
        Log.i(TAG, "initWP: " + wheel);


        //滚轮监听器，滑动状态监听
        wheelPicker.setOnWheelChangeListener(new WheelPicker.OnWheelChangeListener() {
            @Override
            public void onWheelScrolled(int offset) {
                // 滑动距离，初始状态（即一开始position=0时）为0
                // 数据往上滑（即手往下滑）为正数，往下滑为负数
                Log.i(TAG, "onWheelScrolled: " + offset);
            }

            @Override
            public void onWheelSelected(int position) {
                // 等同于选择监听器的onItemSelected，停止滑动时所在的position
                Log.i(TAG, "onWheelSelected: " + position);
            }

            @Override
            public void onWheelScrollStateChanged(int state) {
                // 滚动状态监听器，0表示没有在滑动，1表示触屏造成的滑动，
                // 2表示停止触屏时造成的滑动（停止触屏后的回弹）
                Log.i(TAG, "onWheelScrollStateChanged: " + state);
            }
        });
    }
    private void initWPShow(WheelPicker wheelPicker) {
        // 设置数据是否循环显示
        wheelPicker.setCyclic(true);
        // 查看是否循环显示
        wheelPicker.isCyclic();

        //设置是否有指示器，设置后选中项的上下会用线框柱
        wheelPicker.setIndicator(true);
        wheelPicker.setIndicatorColor(0xFF123456); //16进制
        wheelPicker.setIndicatorSize(3); //单位是px

        // 设置是否有幕布，设置后选中项会被指定的颜色覆盖，默认false
        wheelPicker.setCurtain(false);
        wheelPicker.setCurtainColor(0xFF777777);

        // 设置是否有空气感，设置后上下边缘会渐变为透明，默认false
        wheelPicker.setAtmospheric(true);

        // 设置是否有卷曲感，不能微调卷曲幅度，默认false
        wheelPicker.setCurved(true);

        // 设置item的排列，左中右，默认中
        wheelPicker.setItemAlign(WheelPicker.ALIGN_CENTER);

        //设置背景颜色

    }
    private void initWPText(WheelPicker wheelPicker) {

        // 设置选中项的字色，16进制（0xFF+16进制的rgb）
        wheelPicker.setSelectedItemTextColor(0xFF000000);

        // 设置字体大小，字体颜色
        wheelPicker.setItemTextColor(0xFF888888);
        wheelPicker.setItemTextSize(30); // 单位px，需要自己做sp转px，也可以在xml中直接设置sp

        // 设置字体排列方式，同普通textView，一般两行以上的数据才有效果
        wheelPicker.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_top,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent;
        switch (item.getItemId()){
            case R.id.science:
                intent = new Intent(ToolsActivity_withRoll.this,MainActivity.class);
                startActivity(intent);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                Toast.makeText(this,"切换为科学计算器",Toast.LENGTH_SHORT).show();
                break;
            case R.id.simple:
                intent = new Intent(ToolsActivity_withRoll.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(this,"切换为简易",Toast.LENGTH_SHORT).show();
                break;
            case R.id.help:
                Toast.makeText(this,"点击了帮助",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

}