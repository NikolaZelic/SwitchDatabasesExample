package cocktailsearch.exceptions;

public class MissingParameterException extends RuntimeException {

    public MissingParameterException(String parameterName) {
        super(String.format("Missing parameter: '%s'", parameterName));
    }

}
