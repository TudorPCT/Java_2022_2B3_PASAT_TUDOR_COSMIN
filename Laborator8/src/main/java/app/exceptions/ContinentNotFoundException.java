package app.exceptions;

public class ContinentNotFoundException extends RuntimeException{
    public ContinentNotFoundException(String errorMessage){
        super(errorMessage);
    }
}