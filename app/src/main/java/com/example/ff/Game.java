package com.example.ff;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;

public class Game extends Activity {
    Button btnToday, btnWeek , btnMonth , btnYear ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        btnToday =(Button)findViewById (R.id.btnToday);
        btnWeek =(Button)findViewById (R.id.btnWeek);
        btnMonth =(Button)findViewById (R.id.btnMonth);
        btnYear =(Button)findViewById (R.id.btnYear);

        btnToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Today.class);
                startActivity(intent);
            }
        });

        /*btnWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Week.class);
                startActivity(intent);
            }
        });

        btnMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Month.class);
                startActivity(intent);
            }
        });

        btnYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Year.class);
                startActivity(intent);
            }
        });*/



    }
}