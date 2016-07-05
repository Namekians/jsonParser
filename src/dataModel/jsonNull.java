package dataModel;

/**
 * Created by yinch_000 on 7/3/2016.
 */
public class jsonNull extends  jsonBaseObject {
    private Object data;

    public  jsonNull(){
        super(Type.UNKNOWN);
        data=null;
    }

    public Object getValue(){return data;}

}
