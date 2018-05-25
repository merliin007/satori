package base;

public class CustomExceptions extends Exception {

    public CustomExceptions(){}

    public CustomExceptions(String message){
        super(message);
    }

    public CustomExceptions(Throwable cause){
        super(cause);
    }

    public CustomExceptions(String message, Throwable cause){
        super(message, cause);
    }
}
