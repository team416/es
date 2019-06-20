package com.example.androidtest;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class qiandaojilu extends Activity implements View.OnClickListener {
    Button fh=null;
    TextView textview=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qiandaojilu);
        fh = (Button) findViewById(R.id.fanhui);
        fh.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public  void onClick(View v)
            {
                Intent intent2=new Intent("com.example.androidtest.zhongxin_START");
                startActivity(intent2);
            }
        });
        textview=(TextView)findViewById((R.id.textview));
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.androidtest/databases/test_db", null);
        String userid = "";
        String pass = "";
        userid = MainActivity.getmessage(userid, pass);
        Cursor cursor = null;
        cursor = db.rawQuery("select * from message where Sno=?", new String[]{userid});
        String _id;
        String Sno;
        String time;
        System.out.println(cursor.getCount());
        String message="";
        for(int i=0;i<cursor.getCount();i++)
        {
            cursor.moveToPosition(i);;
            Sno = cursor.getString(cursor.getColumnIndex("Sno"));
            time = cursor.getString(cursor.getColumnIndex("time"));
            String name=Sno+"    "+time;
            message=message+name+"\n";
        }
        message=message+"\n"+"\n"+"\n"+"\n"+"                                                   "+"总签到次数："+cursor.getCount();
        textview.setText(message);
        db.close();
    }
    @Override
    public void onClick(View v) {

    }

}
