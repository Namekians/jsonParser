package dataModel;


/**
 * Created by yinch_000 on 7/5/2016.
 */
public class jsonPrimitive<T> extends jsonBaseObject {
    private T data;


//    public jsonPrimitive(T data){
//        super(Type.UNKNOWN);
//        this.data=data;
//
//    }


    public jsonPrimitive(T data, Type t){
        super(t);

        this.data=data;
    }

    public T getValue(){return data;}
}
