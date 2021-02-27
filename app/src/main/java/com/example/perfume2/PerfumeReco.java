package com.example.perfume2;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import java.util.Random;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perfume2.R;

import java.io.InputStream;

import static com.example.perfume2.MainActivity.actList;

/**
 * PerfumeReco
 * @version 4.0.1
 * @author Inho Lee
 * 선호하는 향에 적합한 향수 추천과 정보 제공
 * 2020.11.20.
 */

public class PerfumeReco extends Activity {
    private NotesDbAdapter dbAdapter;
    private static final String TAG = "NotesDbAdapter";
    int cbcheck = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        actList.add(this); //activity실행 시 리스트에 activity 저장
        final String[] buy = {""};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfume_reco);

        this.dbAdapter = new NotesDbAdapter(this);
        copyExcelDataToDatabase();

        Button recobtn = (Button) findViewById(R.id.recobtn);
        Button homebtn = (Button) findViewById(R.id.homebtn);
        final CheckBox citruscheckbox = (CheckBox) findViewById(R.id.citrus);
        final CheckBox fruitycheckbox = (CheckBox) findViewById(R.id.fruity);
        final CheckBox floralcheckbox = (CheckBox) findViewById(R.id.floral);
        final CheckBox woodycheckbox = (CheckBox) findViewById(R.id.woody);
        final CheckBox spicycheckbox = (CheckBox) findViewById(R.id.spicy);
        final CheckBox freshcheckbox = (CheckBox) findViewById(R.id.fresh);
        final TextView line = (TextView) findViewById(R.id.line);
        TextView buyLink = (TextView) findViewById(R.id.buyLink);

        buyLink.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setData(Uri.parse(buy[0]));

                startActivity(intent);

            }
        });


        //홈 버튼을 누를 시 리스트에 있는 activity를 모두 종료하고 홈 화면으로 돌아감
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0;i<actList.size();i++){
                    actList.get(i).finish();
                    finish();
                }
            }
        });


        recobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                copyExcelDataToDatabase();

                dbAdapter.open();

                Cursor result = dbAdapter.fetchAllNotes();
                result.moveToFirst();
                String brand = "";
                String name = "";
                String price = "";
                String topNote = "";
                String middleNote = "";
                String baseNote = "";
                String buyLink = "";

                while (!result.isAfterLast()) {
                    brand = result.getString(1);
                    if (brand == null) brand = "blank";
                    name = result.getString(2);
                    if (name == null) name = "blank";
                    price = result.getString(3);
                    if (name == null) price = "blank";
                    topNote = result.getString(4);
                    if (topNote == null) topNote = "blank";
                    middleNote = result.getString(5);
                    if (middleNote == null) middleNote = "blank";
                    baseNote = result.getString(6);
                    if (baseNote == null) baseNote = "blank";
                    buyLink = result.getString(7);
                    if (buyLink == null) buyLink = "blank";
                    result.moveToNext();
                }

                TextView tv1 = (TextView) findViewById(R.id.brand);
                tv1.setText(brand);
                TextView tv2 = (TextView) findViewById(R.id.name);
                tv2.setText(name);
                TextView tv3 = (TextView) findViewById(R.id.price);
                tv3.setText(price);
                TextView tv4 = (TextView) findViewById(R.id.topnote);
                tv4.setText(topNote);
                TextView tv5 = (TextView) findViewById(R.id.middlenote);
                tv5.setText(middleNote);
                TextView tv6 = (TextView) findViewById(R.id.basenote);
                tv6.setText(baseNote);
                buy[0] = buyLink;

                result.close();
                dbAdapter.close();

                //체크박스가 하나만 선택되었을 때 계열 항목에 체크된 계열 이름 표시
                if(citruscheckbox.isChecked() && fruitycheckbox.isChecked() == false && floralcheckbox.isChecked() == false
                        && woodycheckbox.isChecked() == false && spicycheckbox.isChecked() == false && freshcheckbox.isChecked() == false){
                    line.setText(citruscheckbox.getText().toString());
                }
                else if(fruitycheckbox.isChecked() && citruscheckbox.isChecked() == false && floralcheckbox.isChecked() == false
                        && woodycheckbox.isChecked() == false && spicycheckbox.isChecked() == false && freshcheckbox.isChecked() == false){
                    line.setText(fruitycheckbox.getText().toString());
                }
                else if(floralcheckbox.isChecked() && fruitycheckbox.isChecked() == false && citruscheckbox.isChecked() == false
                        && woodycheckbox.isChecked() == false && spicycheckbox.isChecked() == false && freshcheckbox.isChecked() == false){
                    line.setText(floralcheckbox.getText().toString());
                }
                else if(woodycheckbox.isChecked() && fruitycheckbox.isChecked() == false && floralcheckbox.isChecked() == false
                        && citruscheckbox.isChecked() == false && spicycheckbox.isChecked() == false && freshcheckbox.isChecked() == false){
                    line.setText(woodycheckbox.getText().toString());
                }
                else if(spicycheckbox.isChecked() && fruitycheckbox.isChecked() == false && floralcheckbox.isChecked() == false
                        && woodycheckbox.isChecked() == false && citruscheckbox.isChecked() == false && freshcheckbox.isChecked() == false){
                    line.setText(spicycheckbox.getText().toString());
                }
                else if(freshcheckbox.isChecked() && fruitycheckbox.isChecked() == false && floralcheckbox.isChecked() == false
                        && woodycheckbox.isChecked() == false && spicycheckbox.isChecked() == false && citruscheckbox.isChecked() == false){
                    line.setText(freshcheckbox.getText().toString());
                }
                // 체크박스가 하나도 선택되지 않거나 두개 이상 선택되었을 때 정보를 표시하지 않고
                // Toast를 이용하여 메세지 출력
                else{
                    tv1.setText("");
                    tv2.setText("");
                    tv3.setText("");
                    tv4.setText("");
                    tv5.setText("");
                    tv6.setText("");
                    line.setText("");
                    Toast.makeText(getApplicationContext(),"한개 체크해주세요",Toast.LENGTH_SHORT).show();
                }
            }

        });

        //계열 항목에 표시된 이름을 클릭 시 Intent를 사용하여 계열 이름 정보를 ScentInfo로 넘겨줌
        line.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), ScentInfo.class);
                intent.putExtra("line", line.getText());
                startActivity(intent);
            }
        });

    }

    private void copyExcelDataToDatabase() {
        Log.w("ExcelToDatabase", "copyExcelDataToDatabase()");

        Workbook workbook = null;
        Sheet sheet = null;
        try {
            InputStream is = getBaseContext().getResources().getAssets().open("beta.xls");

            workbook = Workbook.getWorkbook(is);

            if (workbook != null) {
                sheet = workbook.getSheet(0);

                if (sheet != null) {

                    int nMaxColumn = 2;
                    int nRowStartIndex = 1;
                    int nRowEndIndex = sheet.getColumn(nMaxColumn - 1).length - 1;
                    int nColumnStartIndex = 0;
                    int nColumnEndIndex = sheet.getRow(2).length - 1;

                    dbAdapter.open();
                    dbAdapter.reset();

                    CheckBox citruscheckbox = (CheckBox) findViewById(R.id.citrus);
                    String citrus = citruscheckbox.getText().toString();

                    CheckBox fruitycheckbox = (CheckBox) findViewById(R.id.fruity);
                    String fruity = fruitycheckbox.getText().toString();

                    CheckBox floralcheckbox = (CheckBox) findViewById(R.id.floral);
                    String floral = floralcheckbox.getText().toString();

                    CheckBox woodycheckbox = (CheckBox) findViewById(R.id.woody);
                    String woody = woodycheckbox.getText().toString();

                    CheckBox spicycheckbox = (CheckBox) findViewById(R.id.spicy);
                    String spicy = spicycheckbox.getText().toString();

                    CheckBox freshcheckbox = (CheckBox) findViewById(R.id.fresh);
                    String fresh = freshcheckbox.getText().toString();

                    CheckBox[] lines = {citruscheckbox, fruitycheckbox, floralcheckbox, woodycheckbox, spicycheckbox, freshcheckbox};
                    String[] linetexts = {citrus, fruity, floral, woody, spicy, fresh};

                    boolean temp = true;

                    for (int i=0; i<lines.length; i++) {
                        if (lines[i].isChecked()) {
                            String line_checked = linetexts[i];
                            while(temp) {
                                Random random = new Random();
                                int nRow = random.nextInt(nRowEndIndex);

                                String brand = sheet.getCell(nColumnStartIndex, nRow).getContents();
                                String name = sheet.getCell(nColumnStartIndex + 1, nRow).getContents();
                                String price = "";
                                for(int j=2; j<5; j++) {
                                    price = sheet.getCell(nColumnStartIndex + j, nRow).getContents();
                                    if(price != "") {
                                        if(price.contains("ml")) price = price + "";
                                        else {
                                            if(j==2) price = price + "(30ml)";
                                            if(j==3) price = price + "(50ml)";
                                            if(j==4) price = price + "(100ml)";
                                        }
                                        break;
                                    }
                                }

                                String topnote = sheet.getCell(nColumnStartIndex + 5, nRow).getContents();
                                String middlenote = sheet.getCell(nColumnStartIndex + 6, nRow).getContents();
                                String basenote = sheet.getCell(nColumnStartIndex + 7, nRow).getContents();
                                String line_RowData = sheet.getCell(nColumnStartIndex + 8, nRow).getContents();
                                String buyLink = sheet.getCell(nColumnStartIndex + 10, nRow).getContents();

                                String[] line_Cropped = line_RowData.split(",");

                                for(int j=0; j<line_Cropped.length; j++) {
                                    if (line_Cropped[j].equals(line_checked)) {
                                        dbAdapter.createNote(brand, name, price, topnote, middlenote, basenote, buyLink);
                                        temp = false;
                                    }
                                }
                            }
                        }
                    }

                    dbAdapter.close();
                } else {
                    System.out.println("Sheet is null!!");
                }
            } else {
                System.out.println("WorkBook is null!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }

    }


}
