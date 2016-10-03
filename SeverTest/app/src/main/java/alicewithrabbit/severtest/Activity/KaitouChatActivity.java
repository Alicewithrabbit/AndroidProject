package alicewithrabbit.severtest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import alicewithrabbit.severtest.Class.ReceiverMessage;
import alicewithrabbit.severtest.Class.SendMessage;
import alicewithrabbit.severtest.Interface.OnReceiverListener;
import alicewithrabbit.severtest.Interface.OnSendListener;
import alicewithrabbit.severtest.R;
import alicewithrabbit.severtest.adapter.ChatAdapter;
import alicewithrabbit.severtest.view.DropDownListView;
import alicewithrabbit.severtest.view.Msg;

public class KaitouChatActivity extends Activity implements View.OnClickListener{

    private EditText mEditText;
    private Button mbutton;
    private DropDownListView mkaiDouChannel;
    private ChatAdapter mchatAdapter;
    private List<Msg> mMsg = new ArrayList<>();
    private String mNmsg = new String();
    private String mCode;//好友账号
    private String mAcoountCode;//用户账号
    private String mCSmsg;

    private ReceiverMessage mReceiverMessage;
    private SendMessage mSendMessage;


    Socket socket = null;
    String buffer = "";
    private Intent mintent;

    public void findViews(){
        mbutton = (Button)findViewById(R.id.send_sms);
        mEditText = (EditText)findViewById(R.id.input_sms);
        mkaiDouChannel = (DropDownListView)findViewById(R.id.message_chat_listview);
    }
    private void setListeners() {
        mbutton.setOnClickListener(this);
    }

    private String getEditText() {
        return mEditText !=null? mEditText.getText().toString(): "";
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaitou);
        mintent = getIntent();
        mCode = mintent.getStringExtra("code");
        mAcoountCode = mintent.getStringExtra("acode");
        mchatAdapter = new ChatAdapter(KaitouChatActivity.this,mMsg);
        findViews();

        mSendMessage = new SendMessage();
        mSendMessage.setOnSendListener(new SendListener());

        mReceiverMessage = new ReceiverMessage();
        mReceiverMessage.setOnReceiverListener(new ReceiveListener());
        mReceiverMessage.receive();


        mkaiDouChannel.setAdapter(mchatAdapter);
        setListeners();
    }

    public class ReceiveListener implements OnReceiverListener{
        @Override
        public void onReceiver(String user, String msg) {
            mMsg.add(new Msg(msg,"1",user));
            mchatAdapter.refresh(mMsg);
        }
    }


    public void send(String fromid,String toid,String msg){
        mSendMessage.send(fromid,toid,msg);
    }

    private class SendListener implements OnSendListener{
        @Override
        public void onSend(String[] msg) {
            mMsg.add(new Msg(msg[1],"2",mAcoountCode));//2表示发送方的数据，显示在右边
            mchatAdapter.refresh(mMsg);
        }
    }



    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.send_sms:
                mNmsg = getEditText();
                mEditText.setText("");
                send(mAcoountCode,mCode,mNmsg);
                break;
        }
    }


}
