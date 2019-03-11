package exeptions;

public class DatabaseException extends Throwable{

    public DatabaseException(String message, Throwable throwable) { //the throwable is the cause
        super(message, throwable);
    }
}
