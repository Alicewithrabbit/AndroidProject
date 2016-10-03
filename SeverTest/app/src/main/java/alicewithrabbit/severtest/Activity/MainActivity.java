package alicewithrabbit.severtest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import alicewithrabbit.severtest.Class.Login;
import alicewithrabbit.severtest.Interface.LoginCallback;
import alicewithrabbit.severtest.R;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText mEnterAccount;
    private Button mbutton1 ;
    private TextView mtextView;
    private Button mbutton4;
    private Button mbutton5;
    private String mLoginCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();

    }

    //获取输入的账号
    private String getEditText() {
        return mEnterAccount !=null? mEnterAccount.getText().toString(): "";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                Intent intent = new Intent(MainActivity.this,ClientActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.button4:
                System.exit(0);
                break;
            case R.id.button5:
                Intent intent1 = new Intent(MainActivity.this,ChooseCharaActivity.class);
                mLoginCode = getEditText();
                IsValid(intent1,mLoginCode);
                break;
        }
    }

    public void IsValid(Intent intent,String code){
        switch (code){
            case "1":
                mEnterAccount.setText("");
                login(code,intent);
                break;
            case "2":
                mEnterAccount.setText("");
                login(code,intent);
                break;
            case "3":
                mEnterAccount.setText("");
                login(code,intent);
                break;
            case "4":
                mEnterAccount.setText("");
                login(code,intent);
                break;
            case "5":
                mEnterAccount.setText("");
                login(code,intent);
                break;
            case "6":
                mEnterAccount.setText("");
                login(code,intent);
                break;
            default:
                Toast.makeText(MainActivity.this,"Account is not valid!",Toast.LENGTH_SHORT).show();
                break;
        }

    }

    //登入信息发送到服务器
    public void login(String userId,Intent intent){
        final String userid = userId;
        final Intent nintent = intent;
        Login login = new Login(userid);
        login.setOnLoginListener(new LoginCallback() {
            @Override
            public void onLogin(boolean success) {
                if (success){
                    nintent.putExtra("code",userid);
                    MainActivity.this.startActivity(nintent);
                }else{
                    Toast.makeText(MainActivity.this,"Login failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
        login.login();
    }




    private void setListeners() {
        mbutton1.setOnClickListener(this);
        mbutton4.setOnClickListener(this);
        mbutton5.setOnClickListener(this);
        mtextView.setOnClickListener(this);
    }
    private void findViews() {
        mEnterAccount = (EditText) findViewById(R.id.enter_account);
        mbutton1 = (Button)findViewById(R.id.button1);
        mtextView = (TextView)findViewById(R.id.textView);
        mbutton4 = (Button)findViewById(R.id.button4);
        mbutton5 = (Button)findViewById(R.id.button5);
    }
}
