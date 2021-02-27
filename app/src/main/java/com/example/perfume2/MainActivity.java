package com.example.perfume2;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * MainActivity
 * @version 4.0.1
 * @author InhoLee
 * 홈 화면
 * 2020.11.20
 */

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Activity> actList = new ArrayList<Activity>();  //실행된 activity를 저장해놓을 리스트

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button perfumereco = (Button) findViewById(R.id.perfumereco);
        Button scentinfo = (Button) findViewById(R.id.scentinfo);

        perfumereco.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), PerfumeReco.class);
                startActivity(intent);
            }
        });

        scentinfo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), ScentInfoSelect.class);
                startActivity(intent);
            }
        });

    }




}