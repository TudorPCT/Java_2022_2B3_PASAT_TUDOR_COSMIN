package app.exceptions;

public class CountryNotFoundException extends RuntimeException{
    public CountryNotFoundException(String errorMessage){
        super(errorMessage);
    }
}