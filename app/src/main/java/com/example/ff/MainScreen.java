package com.example.ff;

import android.Manifest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.credentials.Credential;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;



public class MainScreen extends AppCompatActivity implements SensorEventListener {
    Button gbtn1,frbtn2, evbtn3, inbtn4, rbtn1, mybtn1;
    TextView steptextview12;

    SensorManager sensorManager;
    Sensor stepCountSensor;

    int currentSteps = 0;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen);
        gbtn1 = (Button) findViewById(R.id.gbtn1);
        frbtn2 = (Button) findViewById(R.id.frbtn2);
        evbtn3 = (Button) findViewById(R.id.evbtn3);
        inbtn4= (Button) findViewById(R.id.inbtn4);
        rbtn1=(Button) findViewById(R.id.rBtn1);
        mybtn1=(Button) findViewById(R.id.myBtn1);
        steptextview12=(TextView) findViewById(R.id.stepCountView12);

        gbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Game.class);//클래스 추가
                startActivity(intent);
            }
        });

        frbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Free.class);//클래스 추가
                startActivity(intent);
            }
        });

        evbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Event.class);//클래스 추가
                startActivity(intent);
            }
        });

        mybtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Information.class);//클래스 추가
                startActivity(intent);
            }
        });

        rbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg=new AlertDialog.Builder(MainScreen.this);
                dlg.setTitle("알림");
                dlg.setMessage("알림1:ㅇㅇㅇㅇㅇㅇ");
                dlg.setMessage("알림2:ㅇㅇㅇㅇㅇㅇ");
                dlg.setMessage("알림3:ㅇㅇㅇㅇㅇㅇ");

                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainScreen.this,"감사합니다", Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.show();

            }
        });






        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){

            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
        }

        // 걸음 센서 연결
        // * 옵션
        // - TYPE_STEP_DETECTOR:  리턴 값이 무조건 1, 앱이 종료되면 다시 0부터 시작
        // - TYPE_STEP_COUNTER : 앱 종료와 관계없이 계속 기존의 값을 가지고 있다가 1씩 증가한 값을 리턴
        //
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);



    }

    public void onStart() {
        super.onStart();
        if(stepCountSensor !=null) {
            // 센서 속도 설정
            // * 옵션
            // - SENSOR_DELAY_NORMAL: 20,000 초 딜레이
            // - SENSOR_DELAY_UI: 6,000 초 딜레이
            // - SENSOR_DELAY_GAME: 20,000 초 딜레이
            // - SENSOR_DELAY_FASTEST: 딜레이 없음
            //
            sensorManager.registerListener(this,stepCountSensor,SensorManager.SENSOR_DELAY_FASTEST);
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR){

            if(event.values[0]==1.0f){
                // 센서 이벤트가 발생할때 마다 걸음수 증가
                currentSteps++;
                steptextview12.setText(String.valueOf(currentSteps));
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}