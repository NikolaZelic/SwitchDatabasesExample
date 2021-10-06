package cocktailsearch.exceptions;

public class InvalidParameterException extends RuntimeException {

    public InvalidParameterException(String parameter, String cause) {
        super(String.format("Problem with parameter: %s. Cause: %s", parameter, cause));
    }
}
