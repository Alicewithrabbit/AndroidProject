package alicewithrabbit.severtest.Activity;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

import alicewithrabbit.severtest.R;

public class ClientActivity extends Activity implements View.OnClickListener{

    private EditText meditText;
    private TextView mtextView;
    private Button mbutton;
    private String mCSmsg;
    Socket socket = null;
    String buffer = "";
    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0x11){
                Bundle bundle = msg.getData();
                mtextView.append("sever:"+bundle.getString("msg")+"\n");
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        findViews();
        setListeners();


    }

    private void setListeners() {
        mbutton.setOnClickListener(this);
    }

    private void findViews() {
        meditText = (EditText)findViewById(R.id.editText1);
        mtextView = (TextView)findViewById(R.id.textView1);
        mtextView.setMovementMethod(ScrollingMovementMethod.getInstance());
        mbutton = (Button)findViewById(R.id.button2);
}

    private String getEditTextIp() {
        return meditText !=null? meditText.getText().toString(): "";
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button2:
                mCSmsg = getEditTextIp();
                mtextView.append("client:" + mCSmsg + "\n");
                new CsThread(mCSmsg).start();
                break;

        }
    }

    //新建线程听取服务器消息
    class CsThread extends Thread{
        public String txt;

        public CsThread(String str){
            txt = str;
        }

        @Override
        public void run() {
            Message msg = new Message();
            msg.what = 0x11;
            Bundle bundle = new Bundle();
            bundle.clear();
            try {
                socket = new Socket();
                socket.connect(new InetSocketAddress("192.168.0.5", 9085), 5000);
                OutputStream output = socket.getOutputStream();
                BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = null;
                buffer = "";
                while ((line = bf.readLine()) != null) {
                    buffer = line + buffer;
                }
                output.write("android".getBytes("UTF-8"));
                output.flush();
                bundle.putString("msg", buffer.toString());
                msg.setData(bundle);

                mHandler.sendMessage(msg);

                bf.close();
                output.close();
                socket.close();

            }catch (SocketTimeoutException a){

                bundle.putString("msg","Warring:Cannot link to the sever, please check the connection of the internet!");
                msg.setData(bundle);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


}
