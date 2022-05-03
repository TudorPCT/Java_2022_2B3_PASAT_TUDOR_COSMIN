package app.exceptions;

public class CityNotFoundException  extends RuntimeException{
    public CityNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
