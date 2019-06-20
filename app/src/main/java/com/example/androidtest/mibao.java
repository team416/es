package com.example.androidtest;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class mibao extends AppCompatActivity implements View.OnClickListener{
Button baocun;
    EditText wt = null;
    EditText da = null;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mibao);
        baocun= (Button) findViewById(R.id.baocun);
        wt = (EditText) findViewById(R.id.mibaowenti);
        da = (EditText) findViewById(R.id.mibaodaan);
        /*baocun.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public  void onClick(View v)
            {
                Intent intent2=new Intent("com.example.androidtest.zhongxin_START");
                startActivity(intent2);
            }
        });*/
        baocun.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        String question = wt.getText().toString();
        String answer = da.getText().toString();
        //DatabaseHelper dbHelper = new DatabaseHelper(zhuceActivity.this, "test_db",null,1);
        //SQLiteDatabase db = dbHelper.getWritableDatabase();
       if(!question.equals("")){
           if(!answer.equals("")){
               SQLiteDatabase db =SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.androidtest/databases/test_db",null);
               //TextView textView = (TextView)findViewById(R.id.textView);
               String user="";
               String pass="";
               user=MainActivity.getmessage(user,pass);
               ContentValues values= new ContentValues();
               values.put("question",question);
               values.put("answer",answer);
               db.update("person",values,"Sno=?",new String[]{user});
               db.close();
               Toast.makeText(mibao.this, R.string.bcsuccess, Toast.LENGTH_LONG).show();
               Intent intent2=new Intent("com.example.androidtest.zhongxin_START");
               startActivity(intent2);
           }else{
               Toast.makeText(mibao.this, R.string.dakong, Toast.LENGTH_LONG).show();
           }
       }else {
           if (!answer.equals("")) {
               Toast.makeText(mibao.this, R.string.wtkong, Toast.LENGTH_LONG).show();
           } else {
               Toast.makeText(mibao.this, R.string.dkong, Toast.LENGTH_LONG).show();
           }
       }
    }
}
