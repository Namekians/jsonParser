import dataModel.*;
import exceptions.SyntaxErrorException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yinch_000 on 7/2/2016.
 */
public class jsonParser {
    private String tokens;
    private int length;
    private int counter;
    private char current;


    jsonParser() {
    }


    public jsonObject parseObject(String json) throws SyntaxErrorException {
        return (jsonObject) parse(json);
    }

    public jsonArray parseArray(String json) throws SyntaxErrorException {
        return (jsonArray) parse(json);
    }


    private jsonBaseObject parse(String json) throws SyntaxErrorException {
        tokens = json;
        length = tokens.length();
        counter = -1;
        current = ' ';

        print("start to parse following json: " + tokens);
        return valueOf();
    }
    //    private char next(char target) throws exceptions.SyntaxErrorException{
//        if(counter<length-1){
//            counter++;
//            current = tokens.charAt(counter);
//            if(current != target){throw new exceptions.SyntaxErrorException("'"+target+"' missing",counter);}
//            return current;
//        }
//        else{
//            print("next() out of boundary");
//            return '\u0000';
//        }
//    }
//    private char next() throws exceptions.SyntaxErrorException{
//        if(counter<length-1){
//            counter++;
//            current = tokens.charAt(counter);
//            return current;
//        }
//        else{
//            print("next() out of boundary");
//            return '\u0000';
//        }
//    }
    private char next() {
        if (counter < length - 1) {
            counter++;
            current = tokens.charAt(counter);
            return current;

        } else if (counter == length - 1) {
            current = '\u0000';
            print("ENDSIGN");
            return '\u0000';
        } else {
            current = '\u0000';
            print("next(): out of tokens");
            return '\u0000';
        }
    }

    private char next(char target) throws SyntaxErrorException {

        if (current != target) {
            throw new SyntaxErrorException("'" + target + "' missing", current, counter);
        } else {
            return next();
        }
    }
//
//    private char peek() {
//        if (counter < length - 1) {
//            return tokens.charAt(counter + 1);
//        } else {
//            print("peek() out of boundary");
//            return '\u0000';
//        }
//    }

//    private String[] tokenizer(String json) {
//
//        return new String[]{"{", "a", ":", "b", "}"};
//    }

    private jsonBaseObject valueOf() throws SyntaxErrorException {
        whiteSpace();
        switch (current) {
            case '{':
                return object();
            case '[':
                return array();
            case '\"':
                return string();
            default:
                return current >= '0' && current <= '9' ? number() : word();
        }

    }

    private void whiteSpace() {
        while (current == ' '||current=='\n'||current=='\r') {
            next();
        }
    }


    // handle maps/objects
    private jsonObject object() throws SyntaxErrorException {
        //print("object() is called with " + current);
        HashMap<String, jsonBaseObject> map = new HashMap<>();
        if (current == '{') {
            next('{');


            while (current != '}') {
                whiteSpace();
                String key = string().getValue();

                whiteSpace();
                next(':');
                jsonBaseObject object = valueOf();
                map.put(key, object);

                whiteSpace();
                if (current == ',') {
                    next(',');
                }
            }
            print("compose a object of size " + map.size());
            next('}');
            return new jsonObject(map);
        } else {
            throw new SyntaxErrorException("{ is missing in object()");
        }

    }

    // handle arrays
    private jsonArray array() throws SyntaxErrorException {
        //print("array() is called with " + current);
        ArrayList<jsonBaseObject> arr = new ArrayList<>();
        if (current == '[') {
            next('[');
            while (current != ']') {
                jsonBaseObject jsonBaseObject = valueOf();
                arr.add(jsonBaseObject);

                whiteSpace();
                if (current == ',') {
                    next(',');
                }
            }
            print("compose a array of size " + arr.size());
            next(']');
            return new jsonArray(arr);
        } else {
            throw new SyntaxErrorException("[ is missing in array()");
        }

    }

    // handle strings, cant deal with escape sign and \n\r
    private jsonPrimitive<String> string() throws SyntaxErrorException {
        //print("string() is called with " + current);
        String str = "";
        if (current == '\"') {
            next('\"');
            while (current != '\"') {
                str += current;
                next();
            }
            next('\"');

            print("compose a jsonPrimitive<String>: " + str);
            return new jsonPrimitive<String>(str, jsonBaseObject.Type.STRING);
        } else {
            throw new SyntaxErrorException("\" is missing in string()", current, counter - 1);
        }
    }

    // handle numbers, store as float for now
    private jsonPrimitive<Float> number() throws SyntaxErrorException {
        //print("number() is called with " + current);
        String str = "";
        if (current == '-') {
            str += current;
            next('-');
        }
        while (current >= '0' && current <= '9') {
            str += current;
            next();
        }
        if (current == '.') {
            str += current;
            next('.');
            while (current >= '0' && current <= '9') {
                str += current;
                next();
            }
        }
        return new jsonPrimitive<Float>(Float.parseFloat(str),jsonBaseObject.Type.NUMBER);
    }


    // handle boolean(true/false) and null
    private jsonBaseObject word() throws SyntaxErrorException {
        //print("word() is called with " + current);

        switch (current) {
            case 't':
                next('t');
                next('r');
                next('u');
                next('e');
                return new jsonPrimitive<Boolean>(true,jsonBaseObject.Type.BOOLEAN);
            case 'f':
                next('f');
                next('a');
                next('l');
                next('s');
                next('e');
                return new jsonPrimitive<Boolean>(false,jsonBaseObject.Type.BOOLEAN);
            case 'n':
                next('n');
                next('u');
                next('l');
                next('l');
                return new jsonNull();
            default:throw new SyntaxErrorException("word() parsing error",current,counter);
        }
    }


    public static void print(String message) {
        System.out.println(message);
    }

}
