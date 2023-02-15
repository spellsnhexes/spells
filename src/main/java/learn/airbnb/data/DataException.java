package learn.airbnb.data;

public class DataException extends Exception{
    public DataException (String message, Throwable rootCause) {
        //root cause is showing the original error thrown whilst displaying a new, general message
        super (message, rootCause);
    }
}