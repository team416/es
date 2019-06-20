package com.example.androidtest;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Activity;
import android.widget.Button;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.DatePicker;
import android.support.v7.app.AppCompatActivity;
import java.util.Calendar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import android.view.WindowManager;
public class Afterdenglu extends Activity implements View.OnClickListener {
    Button kaishiqiandao = null;
    Button gerencenter = null;
    private DatePicker datePicker;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterdenglu);
        datePicker = (DatePicker) findViewById(R.id.datepicker);
        gerencenter = (Button) findViewById(R.id.geren);
        gerencenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent("com.example.androidtest.zhongxin_START");
                startActivity(intent3);

            }
        });
        kaishiqiandao = (Button) findViewById(R.id.kaishiqiandao);
        kaishiqiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent("com.example.androidtest.lbs");
                startActivity(intent3);
            }
        });
        initData();
    }

    public void initData() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


    @Override
    public void onClick (View v){

    }
}