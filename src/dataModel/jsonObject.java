package dataModel;

import java.util.HashMap;

/**
 * Created by yinch_000 on 7/2/2016.
 */
public class jsonObject extends jsonBaseObject {
    private HashMap<String, jsonBaseObject> data;
    public jsonObject(HashMap<String, jsonBaseObject> map){

        super(Type.OBJECT);this.data=map;
    }



    public jsonBaseObject getBaseObject(String key){
        return data.get(key);
    }
    public String getString(String key){
        jsonPrimitive<String> str = (jsonPrimitive<String>)data.get(key);
        return str.getValue();
    }

    public float getNumber(String key){
        jsonPrimitive<Float> number = (jsonPrimitive<Float>) data.get(key);
        return number.getValue();
    }

    public boolean getBoolean(String key){
        jsonPrimitive<Boolean> bool = (jsonPrimitive<Boolean>) data.get(key);
        return bool.getValue();
    }

    public jsonObject getObject(String key){
        jsonObject obj = (jsonObject) data.get(key);
        return obj;
    }

    public jsonArray getArray(String key){
        jsonArray arr = (jsonArray) data.get(key);
        return arr;
    }

    @Override
    public int size(){
        return data.size();
    }



}
