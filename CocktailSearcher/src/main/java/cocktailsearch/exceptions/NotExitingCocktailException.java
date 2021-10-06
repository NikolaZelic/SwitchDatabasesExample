package cocktailsearch.exceptions;

public class NotExitingCocktailException extends RuntimeException {

    public NotExitingCocktailException(String cocktailName) {
        super(String.format("Cocktail with name: '%s' doesn't exist", cocktailName));
    }

}
