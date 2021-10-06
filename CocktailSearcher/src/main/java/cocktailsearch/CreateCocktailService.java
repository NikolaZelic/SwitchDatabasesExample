package cocktailsearch;

import cocktailsearch.database.Cocktail;

public interface CreateCocktailService {

    Cocktail createCocktail(String cocktailName, String... ingredients);

}
