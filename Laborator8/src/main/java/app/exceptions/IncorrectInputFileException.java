package app.exceptions;

public class IncorrectInputFileException extends RuntimeException{
    public IncorrectInputFileException(String errorMessage){
        super(errorMessage);
    }
}
