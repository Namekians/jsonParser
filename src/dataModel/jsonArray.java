package dataModel;


import java.util.ArrayList;

/**
 * Created by yinch_000 on 7/2/2016.
 */
public class jsonArray extends jsonBaseObject {
    private ArrayList<jsonBaseObject> data;

    public jsonArray(ArrayList<jsonBaseObject> arr) {

        super(Type.ARRAY);
        this.data = arr;
    }


    public jsonBaseObject getBaseObject(int pos) {
        return data.get(pos);
    }

    public String getString(int pos) {
        jsonPrimitive<String> str = (jsonPrimitive<String>) data.get(pos);
        return str.getValue();
    }

    public float getNumber(int pos) {
        jsonPrimitive<Float> number = (jsonPrimitive<Float>) data.get(pos);
        return number.getValue();
    }

    public boolean getBoolean(int pos) {
        jsonPrimitive<Boolean> bool = (jsonPrimitive<Boolean>) data.get(pos);
        return bool.getValue();
    }

    public jsonObject getObject(int pos) {
        jsonObject obj = (jsonObject) data.get(pos);
        return obj;
    }

    public jsonArray getArray(int pos) {
        jsonArray arr = (jsonArray) data.get(pos);
        return arr;
    }

    @Override
    public int size() {
        return data.size();
    }

}
