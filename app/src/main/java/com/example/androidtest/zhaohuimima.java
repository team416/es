package com.example.androidtest;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;

public class zhaohuimima extends Activity implements View.OnClickListener{
    Button zh=null;
    Button qr=null;
    TextView wt = null;
    EditText da = null;
    EditText yh = null;
    public static String userid;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhaohuimima);
        zh = (Button) findViewById(R.id.zhaohui);
       /*zh.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public  void onClick(View v)
            {
                Intent intent2=new Intent("com.example.androidtest.xinmima");
                startActivity(intent2);
            }
        });*/
        qr = (Button) findViewById(R.id.button);
        qr.setOnClickListener(this);
        zh.setOnClickListener(this);
        wt = (TextView) findViewById(R.id.wenti);
        da = (EditText) findViewById(R.id.daan);
        yh = (EditText) findViewById(R.id.yh);
    }
    @Override
    public void onClick(View v) {
        String Id = yh.getText().toString().trim();
        userid=yh.getText().toString();
        String question="";
        String answer = da.getText().toString();
        //DatabaseHelper dbHelper = new DatabaseHelper(zhuceActivity.this, "test_db",null,1);
        //SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteDatabase db =SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.androidtest/databases/test_db",null);
        ContentValues values= new ContentValues();
        switch(v.getId())
        {
            case R.id.zhaohui:
                //ContentValues values2 = new ContentValues();
                Cursor cursor=null;
                cursor=db.rawQuery("select * from person where Sno=?",new String[]{Id});
                //String wt="";
                String da="";
                while (cursor.moveToNext()) {
                    da = cursor.getString(cursor.getColumnIndex("answer"));
                }
                //db.close();
                if(!Id.equals(""))
                {
                    if(da.equals(answer))
                    {
                        Intent intent2=new Intent("com.example.androidtest.xinmima");
                        startActivity(intent2);
                    }else{
                        Toast.makeText(zhaohuimima.this, R.string.answerwrong, Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(zhaohuimima.this, R.string.zhanghaokong, Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button:
                Cursor cursor1=null;
                cursor1=db.rawQuery("select * from person where Sno=?",new String[]{Id});
                String wt1="";
                while (cursor1.moveToNext()) {
                    wt1 = cursor1.getString(cursor1.getColumnIndex("question"));
                }
                wt.setText("密保问题：  "+wt1);
                break;
        }
        db.close();
    }
    public static String getzhanghao(String user){
        user=userid;
        return  userid;
    }
}