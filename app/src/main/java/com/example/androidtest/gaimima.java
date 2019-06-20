package  com.example.androidtest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.androidtest.R.id.zhuceBtn;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class gaimima extends Activity implements View.OnClickListener {
    private static final String TAG = "login";
    Button bc = null;
    EditText jm = null;
    EditText xm = null;
    EditText zxm=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaimima);
        bc = (Button) findViewById(R.id.baocun);
       /*bc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public  void onClick(View v)
            {
                Intent intent4=new Intent("com.example.androidtest.zhongxin_START");
                startActivity(intent4);
            }
        });*/
        bc.setOnClickListener(this);
        jm = (EditText) findViewById(R.id.old);
        xm = (EditText) findViewById(R.id.newpass);
        zxm= (EditText) findViewById(R.id.renew);
    }
    @Override
    public void onClick(View v) {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.androidtest/databases/test_db", null);
        String oldpass = jm.getText().toString().trim();
        String newpass = xm.getText().toString().trim();
        String newpass1 = zxm.getText().toString().trim();
        String userid="";
        String pass="";
        userid=MainActivity.getmessage(userid,pass);
        Cursor cursor=null;
        cursor=db.rawQuery("select * from person where Sno=?",new String[]{userid});
        while (cursor.moveToNext()) {
            pass = cursor.getString(cursor.getColumnIndex("Password"));
        }
      if(!oldpass.equals("")&&!newpass.equals("")&&!newpass1.equals("")){
          if(oldpass.equals(pass)){
              if(!oldpass.equals(newpass)){
                  if(newpass.equals(newpass1)){
                      ContentValues values= new ContentValues();
                      values.put("Password",newpass);
                      db.update("person",values,"Sno=?",new String[]{userid});
                      db.close();
                      Toast.makeText(gaimima.this, R.string.upadeteSuceess, Toast.LENGTH_LONG).show();
                      Intent intent3 = new Intent("com.example.androidtest.zhongxin_START");
                      startActivity(intent3);
                  }else{
                      Toast.makeText(gaimima.this, R.string.mimabuyizhi, Toast.LENGTH_LONG).show();
                  }
              }else{
                  Toast.makeText(gaimima.this, R.string.xinjiuyizhi, Toast.LENGTH_LONG).show();
              }
          }else{
              Toast.makeText(gaimima.this, R.string.oldpasswrong, Toast.LENGTH_LONG).show();
          }
      }else{
          Toast.makeText(gaimima.this, R.string.kong1, Toast.LENGTH_LONG).show();
      }
    }

}