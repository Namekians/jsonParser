package dataModel;

import java.util.Enumeration;

/**
 * Created by yinch_000 on 7/3/2016.
 */
public abstract class jsonBaseObject {

    public enum Type {
        STRING, BOOLEAN, NUMBER, OBJECT, ARRAY, UNKNOWN
    }

    Type t;

    jsonBaseObject(Type t) {
        this.t = t;
    }


    public Type typeOf() {
        return t;
    }

    public int size() {
        return 0;
    }


}
