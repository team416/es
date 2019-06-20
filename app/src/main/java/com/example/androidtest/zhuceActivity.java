package com.example.androidtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;
import java.text.SimpleDateFormat;
import java.util.Date;
public class zhuceActivity extends Activity implements View.OnClickListener {
    Button zhuceBtn = null;
    EditText zhuceId = null;
    EditText zhucepass = null;
    EditText querenpass = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        zhuceBtn = (Button) findViewById(R.id.zhuceBtn);
        zhuceBtn.setOnClickListener(this);
        zhuceId = (EditText) findViewById(R.id.zhuceId);
        zhucepass = (EditText) findViewById(R.id.zhucepass);
        querenpass = (EditText) findViewById(R.id.querenpass);
    }

    @Override
    public void onClick(View v) {
        String Id = zhuceId.getText().toString();
        String password = zhucepass.getText().toString();
        String queren = querenpass.getText().toString();
        //DatabaseHelper dbHelper = new DatabaseHelper(zhuceActivity.this, "test_db",null,1);
        //SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.androidtest/databases/test_db", null);
        //TextView textView = (TextView)findViewById(R.id.textView);
        ContentValues values = new ContentValues();
        //ContentValues values2 = new ContentValues();
        if (!Id.equals("")) {
            if (!password.equals("")) {
                if (!queren.equals("")) {
                    Cursor cursor = null;
                    cursor = db.rawQuery("select * from person where Sno=?", new String[]{Id});
                    if (cursor.getCount() == 0) {
                        if (password.equals(queren)) {
                            values.put("Sno", Id);
                            values.put("Password", password);
                            //数据库执行插入命令
                            db.insert("person", null, values);
                            db.close();
                            Toast.makeText(zhuceActivity.this, R.string.registerSuccess, Toast.LENGTH_LONG).show();
                            Intent intent_login = new Intent();
                            intent_login.setClass(zhuceActivity.this, MainActivity.class);
                            intent_login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //关键的一句，将新的activity置为栈顶
                            startActivity(intent_login);
                            finish();
                        } else {
                            Toast.makeText(zhuceActivity.this, R.string.mimabuyizhi, Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(zhuceActivity.this, R.string.registerError, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(zhuceActivity.this, R.string.zmimakong, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(zhuceActivity.this, R.string.mimakong, Toast.LENGTH_LONG).show();
            }
        } else {
            if (!password.equals("")) {
                if (!queren.equals("")) {
                    Toast.makeText(zhuceActivity.this, R.string.mimakong, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(zhuceActivity.this, R.string.ersank, Toast.LENGTH_LONG).show();
                }
            } else {
                if (!queren.equals("")) {
                    Toast.makeText(zhuceActivity.this, R.string.yierk, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(zhuceActivity.this, R.string.wushuru, Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}


                /*
                Date date = new Date(System.currentTimeMillis());
                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分 EEE");
                ContentValues values1= new ContentValues();
                values1.put("Sno",Id);
                values1.put("time",format.format(date));
                db.insert("message",null,values1);

                //Cursor cursor = db.query("person", null, null, null, null, null, null);
               /*Cursor cursor = db.query("user_tb",null,"Sno=? and Password=?",new String[]{"Sno","Password"},null,null,null);
                //利用游标遍历所有数据对象
                //为了显示全部，把所有对象连接起来，放到TextView中
                String textview_data = "";

                while(cursor.moveToNext()){

                    String name = cursor.getString(cursor.getColumnIndex("ColumnName"));

                    textview_data = textview_data + "\n" + name;

                });
                // 关闭游标，释放资源
                cursor.close();
                break;*/



