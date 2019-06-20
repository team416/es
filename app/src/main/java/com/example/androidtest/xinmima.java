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


public class xinmima extends AppCompatActivity implements View.OnClickListener{
    EditText xm = null;
    EditText zxm=null;
    Button bc = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xinmima);
        bc = (Button) findViewById(R.id.baocun1);
        bc.setOnClickListener(this);
        xm = (EditText) findViewById(R.id.newpass);
        zxm= (EditText) findViewById(R.id.renew);
    }
    public void onClick(View v){
        String pass = xm.getText().toString();
        String pass1 = zxm.getText().toString();
        //DatabaseHelper dbHelper = new DatabaseHelper(zhuceActivity.this, "test_db",null,1);
        //SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteDatabase db =SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.androidtest/databases/test_db",null);
        //TextView textView = (TextView)findViewById(R.id.textView);
        String Sno="";
        Sno=zhaohuimima.getzhanghao(Sno);
        if(!pass.equals("")&&!pass1.equals("")){
            if(pass.equals(pass1)){
                ContentValues values= new ContentValues();
                values.put("Password",pass);
                db.update("person",values,"Sno=?",new String[]{Sno});
                db.close();
                Toast.makeText(xinmima.this, R.string.bcsuccess, Toast.LENGTH_LONG).show();
                Intent intent_login = new Intent();
                intent_login.setClass(xinmima.this,MainActivity.class);
                intent_login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //关键的一句，将新的activity置为栈顶
                startActivity(intent_login);
                finish();
            }else{
                Toast.makeText(xinmima.this, R.string.mimabuyizhi, Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(xinmima.this, R.string.kong1, Toast.LENGTH_LONG).show();
        }
    }
}
