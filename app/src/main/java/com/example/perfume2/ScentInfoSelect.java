package com.example.perfume2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import static com.example.perfume2.MainActivity.actList;

/**
 * ScentInfoSelect
 * @version 4.0.1
 * @author Inho Lee
 * 6개의 대표 향들에 대한 버튼 클릭시 정보 전달
 * 2020.11.20.
 */

public class ScentInfoSelect extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        actList.add(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scent_info_select);

        Button homebtn = (Button) findViewById(R.id.homebtn);

        final Button citrusbtn = (Button) findViewById(R.id.citrus);
        final Button fruitybtn = (Button) findViewById(R.id.fruity);
        final Button woodybtn = (Button) findViewById(R.id.woody);
        final Button floralbtn = (Button) findViewById(R.id.floral);
        final Button spicybtn = (Button) findViewById(R.id.spicy);
        final Button freshbtn = (Button) findViewById(R.id.fresh);

        //각가의 계열 버튼을 누를 시 계열 이름 정보를 Intent를 사용하여 ScentInfo로 넘겨줌
        citrusbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), ScentInfo.class);
                intent.putExtra("citrus","citrus");
                startActivity(intent);
            }
        });

        fruitybtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), ScentInfo.class);
                intent.putExtra("fruity","fruity");
                startActivity(intent);
            }
        });

        woodybtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), ScentInfo.class);
                intent.putExtra("woody","woody");
                startActivity(intent);
            }
        });

        floralbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), ScentInfo.class);
                intent.putExtra("floral","floral");
                startActivity(intent);
            }
        });

        spicybtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), ScentInfo.class);
                intent.putExtra("spicy","spicy");
                startActivity(intent);
            }
        });

        freshbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), ScentInfo.class);
                intent.putExtra("fresh","fresh");
                startActivity(intent);
            }
        });

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0;i<actList.size();i++){
                    actList.get(i).finish();
                    finish();
                }
            }
        });

    }
}
