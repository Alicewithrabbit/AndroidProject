package alicewithrabbit.severtest.view;

/**
 * Created by Administrator on 2016/10/2 0002.
 */
//角色选择元素
public class Chara {
    public int getmCode() {
        return mCode;
    }

    public String getmName() {
        return mName;
    }

    private String mName;
    private int mCode;

    public Chara(String name,int code){
        mName = name;
        mCode = code;
    }
}
