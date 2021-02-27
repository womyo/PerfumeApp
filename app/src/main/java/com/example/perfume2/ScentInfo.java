package com.example.perfume2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import static com.example.perfume2.MainActivity.actList;

/**
 * ScentInfo
 * @version 4.0.1
 * @author Inho Lee
 * 향에 대한 설명 출력
 * 2020.11.20.
 */

public class ScentInfo extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        actList.add(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scent_info);

        LinearLayout citrus = (LinearLayout) findViewById(R.id.citrus);
        LinearLayout fruity = (LinearLayout) findViewById(R.id.fruity);
        LinearLayout woody = (LinearLayout) findViewById(R.id.woody);
        LinearLayout floral = (LinearLayout) findViewById(R.id.floral);
        LinearLayout spicy = (LinearLayout) findViewById(R.id.spicy);
        LinearLayout fresh = (LinearLayout) findViewById(R.id.fresh);

        Intent intent = getIntent();

        //ScentInfoSelect에서 넘겨준 정보를 name별로 가져와 각각 다른 id에 저장
        String id = intent.getStringExtra("citrus");
        String id1 = intent.getStringExtra("fruity");
        String id2 = intent.getStringExtra("woody");
        String id3 = intent.getStringExtra("floral");
        String id4 = intent.getStringExtra("spicy");
        String id5 = intent.getStringExtra("fresh");

        Button homebtn = (Button) findViewById(R.id.homebtn);

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0;i<actList.size();i++){
                    actList.get(i).finish();
                    finish();
                }
            }
        });

        // 특정 id값에 정보가 들어있고 다른 id값에는 null이 들어있어
        // nullpointerException이 발생할 경우를 대비하여 예외처리
        if (id != null && id.equals("citrus")){
            citrus.setVisibility(View.VISIBLE);
        }
        if (id1 != null && id1.equals("fruity")){
            fruity.setVisibility(View.VISIBLE);
        }
        if (id2 != null && id2.equals("woody")){
            woody.setVisibility(View.VISIBLE);
        }
        if (id3 != null && id3.equals("floral")){
            floral.setVisibility(View.VISIBLE);
        }
        if (id4 != null && id4.equals("spicy")) {
            spicy.setVisibility(View.VISIBLE);
        }
        if (id5 != null && id5.equals("fresh")){
            fresh.setVisibility(View.VISIBLE);
        }


        String line = intent.getStringExtra("line");
        if(line != null && line.equals("시트러스")){
            citrus.setVisibility(View.VISIBLE);
        }
        if(line != null && line.equals("프루티")){
            fruity.setVisibility(View.VISIBLE);
        }
        if(line != null && line.equals("플로랄")){
            floral.setVisibility(View.VISIBLE);
        }
        if(line != null && line.equals("우디")){
            woody.setVisibility(View.VISIBLE);
        }
        if(line != null && line.equals("스파이시")){
            spicy.setVisibility(View.VISIBLE);
        }
        if(line != null && line.equals("프레쉬")){
            fresh.setVisibility(View.VISIBLE);
        }



    }
}
