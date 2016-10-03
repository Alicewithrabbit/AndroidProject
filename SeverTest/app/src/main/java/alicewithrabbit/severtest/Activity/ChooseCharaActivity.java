package alicewithrabbit.severtest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import alicewithrabbit.severtest.Class.Logout;
import alicewithrabbit.severtest.Interface.OnLogoutListener;
import alicewithrabbit.severtest.R;
import alicewithrabbit.severtest.adapter.ChooseAdapter;
import alicewithrabbit.severtest.view.Chara;

/**
 * Created by Administrator on 2016/10/2 0002.
 */

public class ChooseCharaActivity extends Activity implements View.OnClickListener{

    private List<Chara> mChara = new ArrayList<>();
    private Button mButton;
    private ListView mListView;
    private ChooseAdapter mChooseAdapter;
    private Intent mIntent;
    private Intent mIntentAccount;
    private String mAccountCode;
    public void findViews(){

        mListView = (ListView) findViewById(R.id.chara_list);
        mButton = (Button)findViewById(R.id.logout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_chara);
        mIntentAccount = getIntent();
        mAccountCode = mIntentAccount.getStringExtra("code");
        mChara.add(new Chara("JOKER",1));
        mChara.add(new Chara("QUEEN",2));
        mChara.add(new Chara("PANTHER",3));
        mChara.add(new Chara("NOIR",4));
        mChara.add(new Chara("NAVI",5));
        mChara.add(new Chara("HAKUYA",6));
        mChooseAdapter = new ChooseAdapter(ChooseCharaActivity.this,mChara);
        findViews();
        setListeners();
        mListView.setAdapter(mChooseAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int chara_code = mChara.get(position).getmCode();

                switch (chara_code){
                    case 1:
                        mIntent = new Intent(ChooseCharaActivity.this,KaitouChatActivity.class);
                        mIntent.putExtra("acode",mAccountCode);
                        mIntent.putExtra("code","1");
                        ChooseCharaActivity.this.startActivity(mIntent);
                        break;
                    case 2:
                        mIntent = new Intent(ChooseCharaActivity.this,KaitouChatActivity.class);
                        mIntent.putExtra("acode",mAccountCode);
                        mIntent.putExtra("code","2");
                        ChooseCharaActivity.this.startActivity(mIntent);
                        break;
                    case 3:
                        mIntent = new Intent(ChooseCharaActivity.this,KaitouChatActivity.class);
                        mIntent.putExtra("acode",mAccountCode);
                        mIntent.putExtra("code","3");
                        ChooseCharaActivity.this.startActivity(mIntent);
                        break;
                    case 4:
                        mIntent = new Intent(ChooseCharaActivity.this,KaitouChatActivity.class);
                        mIntent.putExtra("acode",mAccountCode);
                        mIntent.putExtra("code","4");
                        ChooseCharaActivity.this.startActivity(mIntent);
                        break;
                    case 5:
                        mIntent = new Intent(ChooseCharaActivity.this,KaitouChatActivity.class);
                        mIntent.putExtra("acode",mAccountCode);
                        mIntent.putExtra("code","5");
                        ChooseCharaActivity.this.startActivity(mIntent);
                        break;
                    case 6:
                        mIntent = new Intent(ChooseCharaActivity.this,KaitouChatActivity.class);
                        mIntent.putExtra("acode",mAccountCode);
                        mIntent.putExtra("code","6");
                        ChooseCharaActivity.this.startActivity(mIntent);
                        break;
                }
            }
        });
    }
    private void setListeners(){
        mButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logout:
                Logout logout = new Logout();
                logout.setOnLogoutListener(new OnLogoutListener() {
                    @Override
                    public void onLogout(boolean success) {
                        if(success){
                            Toast.makeText(ChooseCharaActivity.this,"Logout successful",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ChooseCharaActivity.this,"Logout failed",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                logout.logout(mAccountCode);
                break;
        }
    }
}
