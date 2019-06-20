package  com.example.androidtest;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import static com.example.androidtest.R.id.zhuceBtn;
import android.content.ContentValues;
public class MainActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "login";
    ImageButton loginBtn = null;
    Button register=null;
    Button forgetpass=null;
    EditText useridEt = null;
    EditText passEt = null;
    public static String userid;
    public static String pass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {  finish(); return; }

        setContentView(R.layout.activity_main);
        loginBtn = (ImageButton) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
        register=(Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public  void onClick(View v)
            {
                Intent intent2=new Intent("com.example.androidtest.ZHUCE_START");
                startActivity(intent2);
            }
        });
        forgetpass=(Button)findViewById(R.id.forgetpass);
        forgetpass.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public  void onClick(View v)
            {
                Intent intent6=new Intent("com.example.androidtest.zhaohui_START");
                startActivity(intent6);
            }
        });
        useridEt = (EditText) findViewById(R.id.userId);
        passEt = (EditText) findViewById(R.id.pass);

    }
    @Override
    public void onClick(View v) {
        DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this, "test_db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        userid = useridEt.getText().toString().trim();
        pass = passEt.getText().toString().trim();
        //textView2.setText(userid);
        Cursor cursor=null;
        cursor=db.rawQuery("select * from person where Sno=? and Password=?",new String[]{userid,pass});
        String _id;
        String Sno;
        String Password;
        while (cursor.moveToNext()){
            _id=cursor.getString(cursor.getColumnIndex("_id"));
            Sno=cursor.getString(cursor.getColumnIndex("Sno"));
            Password=cursor.getString(cursor.getColumnIndex("Password"));
            System.out.println(_id);
            System.out.println(Sno);
            System.out.println(Password);
            /*ContentValues values=new ContentValues();
            values.put("_id",_id);
            values.put("Sno",Sno);
            values.put("Password",Password);
            db.insert("person",null,values);*/
        }
        if(!userid.equals("")&&!pass.equals(""))
        {
            if( cursor.getCount()!=0)
            {
                Toast.makeText(MainActivity.this, R.string.loginSuccess, Toast.LENGTH_LONG).show();
                Intent intent=new Intent("com.example.androidtest.ACTION_START");
                startActivity(intent);
            }else{
                Toast.makeText(MainActivity.this, R.string.loginError, Toast.LENGTH_LONG).show();
            }
            db.close();
        }else{
            Toast.makeText(MainActivity.this, R.string.kong, Toast.LENGTH_LONG).show();
        }




    }
    public static String getmessage(String Sno,String Password){
        Sno=userid;
        Password=pass;
        return  Sno;
    }

}