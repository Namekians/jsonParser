package exceptions;

/**
 * Created by yinch_000 on 7/2/2016.
 */
public class SyntaxErrorException extends Exception {
    public SyntaxErrorException(String message) {
        super(message);
    }
    public SyntaxErrorException(String message, char ch, int position) {
        super(message);
        System.out.println(message+" with "+ch+" at "+position);
    }

}
