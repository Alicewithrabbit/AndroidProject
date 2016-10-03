package alicewithrabbit.severtest.view;

import java.io.Serializable;

/**
 * 消息存储类
 * Created by Alicewithrabbit on 2016/10/1 0001.
 */

public class Msg implements Serializable {

    private String mMsg;
    private String mAvatar;



    private String mCode;//用户编号
    public Msg(String msg,String avatar,String code){
        mMsg = msg;
        mAvatar = avatar;
        mCode = code;
    }
    public String getmCode() {return mCode;}
    public String getmAvatar() {
        return mAvatar;
    }
    public String getmMsg() {
        return mMsg;
    }
}
